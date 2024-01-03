package de.officeryoda.api.sdui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.officeryoda.api.sdui.deserializer.DateDeserializer;
import de.officeryoda.api.sdui.deserializer.PreviewDeserializer;
import de.officeryoda.api.sdui.response.NewsInformation;
import de.officeryoda.api.sdui.response.ParentInformation;
import de.officeryoda.api.sdui.response.TimeTableInformation;
import de.officeryoda.api.sdui.response.UserInformation;
import de.officeryoda.api.sdui.response.data.news.Preview;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles communication with the SDUI API for various functionalities.
 */
public class SduiApiHandler {

    private final Gson gson;
    private final Map<String, String> settings;
    private final String baseUrl;
    private final Map<String, String> headers;
    private String apiUrl;

    /**
     * Constructs an instance of SduiApiHandler, initializing Gson, settings, base URL, and headers.
     * Checks and performs login and user information retrieval if necessary.
     */
    public SduiApiHandler() {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDateTime.class, new DateDeserializer())
                .registerTypeAdapter(Preview.class, new PreviewDeserializer())
                .create();
        this.settings = loadSettings();
        this.baseUrl = "https://api.sdui.app/v1/";
        this.headers = new HashMap<>();
        this.headers.put("Authorization", this.settings.get("access_token"));

        if(this.settings.get("access_token") == null) {
            System.out.println("login");
            login();
        }
        if(this.settings.get("user_id") == null) {
            System.out.println("get user information");
            fetchUserInformation();
        }
    }

    /**
     * Loads settings from a JSON file.
     *
     * @return A map containing loaded settings.
     */
    private Map<String, String> loadSettings() {
        // Load settings from a JSON file
        Map<String, String> loadedSettings;
        try(BufferedReader reader = new BufferedReader(new FileReader("tokens/settings.json"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line);
            }
            loadedSettings = parseJson(content.toString());
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return loadedSettings;
    }

    /**
     * Saves settings to a JSON file.
     */
    private void saveSettings() {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("tokens/settings.json"))) {
            writer.write(convertToJson(this.settings));
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Sends a POST request to the login API endpoint and updates access token in settings.
     */
    private void login() {
        this.apiUrl = this.baseUrl + "auth/login";
        this.headers.put("content-type", "application/json");

        Map<String, String> payload = new HashMap<>();
        payload.put("identifier", this.settings.get("email"));
        payload.put("password", this.settings.get("password"));
        payload.put("slink", this.settings.get("school"));

        String response = sendPostRequest(convertToJson(payload));
        if(!response.equals("401")) {
            JsonObject responseData = gson.fromJson(response, JsonObject.class);
            this.settings.put("access_token", "Bearer " + responseData.getAsJsonObject("data").get("access_token").getAsString());
            saveSettings();
        } else {
            System.out.println("Authentication failed. Wrong email or password.");
        }
    }

    /**
     * Retrieves user information and updates user ID in settings.
     *
     * @return UserInformation object containing user information.
     */
    public UserInformation fetchUserInformation() {
        this.apiUrl = this.baseUrl + "users/self";
        String response = sendGetRequest();
        if(this.settings.get("user_id") == null) {
            JsonObject responseData = gson.fromJson(response, JsonObject.class);
            JsonElement userIdElement = responseData.getAsJsonObject("data").get("id");
            if(userIdElement != null) {
                this.settings.put("user_id", userIdElement.getAsString());
                saveSettings();
            } else {
                System.out.println("Unable to retrieve user ID from the response.");
            }
        }
        return gson.fromJson(response, UserInformation.class);
    }

    /**
     * Retrieves information about the parent associated with the current user.
     *
     * @return ParentInformation object containing information about the parent.
     */
    public ParentInformation fetchParentInformation() {
        this.apiUrl = this.baseUrl + "users/self/family";
        String response = sendGetRequest();
        return gson.fromJson(response, ParentInformation.class);
    }

    /**
     * Gets the timetable without specifying start and end dates.
     *
     * @return TimeTableInformation object containing timetable information.
     */
    public TimeTableInformation fetchTimetable() {
        this.apiUrl = this.baseUrl + "timetables/users/" + this.settings.get("user_id") + "/timetable";
        return retrieveTimetable();
    }

    /**
     * Gets the timetable for a specific date range.
     *
     * @param startDate The start date of the timetable.
     * @param endDate   The end date of the timetable.
     * @return TimeTableInformation object containing timetable information for the specified date range.
     */
    public TimeTableInformation fetchTimetable(LocalDateTime startDate, LocalDateTime endDate) {
        // Format the start and end dates to the required pattern
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String queryString = "?begins_at=" + startDate.format(formatter) + "&ends_at=" + endDate.format(formatter);

        this.apiUrl = this.baseUrl + "timetables/users/" + this.settings.get("user_id") + "/timetable" + queryString;
        return retrieveTimetable();
    }

    private TimeTableInformation retrieveTimetable() {
        String response = sendGetRequest();
        return gson.fromJson(response, TimeTableInformation.class);
    }

    /**
     * Sends a GET request to the news API endpoint and retrieves news information.
     *
     * @param page The page number of news to get; each page contains 10 NewsItems; page <= 1 will return the same News.
     * @return NewsInformation object containing news information.
     */
    public NewsInformation fetchNews(int page) {
        this.apiUrl = this.baseUrl + "users/" + this.settings.get("user_id") + "/feed/news?page=" + page;
        String response = sendGetRequest();
        return gson.fromJson(response, NewsInformation.class);
    }

    /**
     * Overloaded method to get news with the default page number (0).
     *
     * @return NewsInformation object containing news information.
     */
    public NewsInformation fetchNews() {
        return fetchNews(0);
    }

    // Networking stuff

    /**
     * Sends a GET request to the API and returns the response.
     *
     * @return The response from the GET request.
     */
    private String sendGetRequest() {
        HttpURLConnection connection = getHttpURLConnection("GET");
        setRequestHeaders(connection);
        return handleResponse(connection);
    }

    /**
     * Sends a POST request to the API with the provided payload and returns the response.
     *
     * @param payload The payload for the POST request.
     * @return The response from the POST request.
     */
    private String sendPostRequest(String payload) {
        HttpURLConnection connection = getHttpURLConnection("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        setRequestHeaders(connection);
        sendPayload(connection, payload);

        return handleResponse(connection);
    }

    // ... (Other methods)

    /**
     * Sets request headers for the HTTP connection.
     *
     * @param connection The HTTP connection to set headers for.
     */
    private void setRequestHeaders(HttpURLConnection connection) {
        // Set headers
        for(Map.Entry<String, String> entry : this.headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }
    }

    /**
     * Creates an HTTP connection with the specified method.
     *
     * @param method The HTTP method (GET, POST, etc.).
     * @return The HttpURLConnection object.
     */
    private HttpURLConnection getHttpURLConnection(String method) {
        HttpURLConnection connection;

        try {
            URL url = new URL(this.apiUrl);
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod(method);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }

        return connection;
    }

    /**
     * Sends the payload to the specified HTTP connection.
     *
     * @param connection The HTTP connection to send the payload to.
     * @param payload    The payload to be sent.
     */
    private void sendPayload(HttpURLConnection connection, String payload) {
        // Send payload
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Handles the response from the HTTP connection.
     *
     * @param connection The HTTP connection to handle the response for.
     * @return The response string.
     */
    private static String handleResponse(HttpURLConnection connection) {
        // Handle response
        try {
            int responseCode = connection.getResponseCode();

            if(responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            } else {
                return String.valueOf(responseCode);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Converts a map to a JSON string.
     *
     * @param data The map to convert.
     * @return The JSON representation of the map.
     */
    private String convertToJson(Map<String, String> data) {
        return gson.toJson(data);
    }

    /**
     * Parses a JSON string into a map.
     *
     * @param json The JSON string to parse.
     * @return The parsed map.
     */
    private Map<String, String> parseJson(String json) {
        Map<String, String> result = new HashMap<>();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        for(Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonElement jsonValue = entry.getValue();
            result.put(entry.getKey(), jsonValue.isJsonNull() ? null : jsonValue.getAsString());
        }

        return result;
    }
}

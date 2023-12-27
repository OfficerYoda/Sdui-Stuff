package de.officeryoda.sdui;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import de.officeryoda.sdui.CustomJsonDeserializer.DateDeserializer;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SduiApi {

    private Map<String, String> settings;
    private String base_url;
    private String api_url;
    private Map<String, String> headers;
    private final Gson gson;

    public SduiApi() throws IOException {
        this.gson = new GsonBuilder()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .create();
        this.settings = loadSettings();
        this.base_url = "https://api.sdui.app/v1/";
        this.headers = new HashMap<>();
        this.headers.put("Authorization", this.settings.get("access_token"));

        if(this.settings.get("access_token") == null) {
            System.out.println("login");
            login();
        }
        if(this.settings.get("user_id") == null) {
            System.out.println("get user information");
            getUserInformation();
        }

        getUserInformation();
    }

    public static void main(String[] args) {
        try {
            SduiApi sduiApi = new SduiApi();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    private Map<String, String> loadSettings() throws IOException {
        Map<String, String> loadedSettings = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new FileReader("settings.json"))) {
            StringBuilder content = new StringBuilder();
            String line;
            while((line = reader.readLine()) != null) {
                content.append(line);
            }
            loadedSettings = parseJson(content.toString());
        }
        return loadedSettings;
    }

    private Map<String, String> parseJson(String json) {
        Map<String, String> result = new HashMap<>();
        Gson gson = new Gson();

        JsonObject jsonObject = gson.fromJson(json, JsonObject.class);
        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            JsonElement jsonValue = entry.getValue();
            result.put(entry.getKey(), jsonValue.isJsonNull() ? null : jsonValue.getAsString());
        }

        return result;
    }

    private void getUserInformation() throws IOException {
        this.api_url = this.base_url + "users/self";
        String response = sendRequest("GET");
        if (this.settings.get("user_id") == null) {
            Gson gson = new Gson();
            JsonObject responseData = gson.fromJson(response, JsonObject.class);
            JsonElement userIdElement = responseData.getAsJsonObject("data").get("id");
            if (userIdElement != null) {
                this.settings.put("user_id", userIdElement.getAsString());
                saveSettings();
            } else {
                System.out.println("Unable to retrieve user ID from the response.");
            }
        }
        handleResponse(response);
    }

    private void saveSettings() throws IOException {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("settings.json"))) {
            writer.write(convertToJson(this.settings));
        }
    }

    private String sendRequest(String method) throws IOException {
        URL url = new URL(this.api_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);

        // Set headers
        for(Map.Entry<String, String> entry : this.headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // Handle response
        int responseCode = connection.getResponseCode();
        if(responseCode == 200) {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            return String.valueOf(responseCode);
        }
    }

    private void handleResponse(String response) {
        System.out.println("handleResponse: \n" + response);
        SduiApiResponse apiResponse = gson.fromJson(response, SduiApiResponse.class);
        System.out.println(apiResponse);
    }

    private void login() throws IOException {
        this.api_url = this.base_url + "auth/login";
        this.headers.put("content-type", "application/json");

        Map<String, String> payload = new HashMap<>();
        payload.put("identifier", this.settings.get("email"));
        payload.put("password", this.settings.get("password"));
        payload.put("slink", this.settings.get("school"));

        String response = sendRequest("POST", convertToJson(payload));
        if (!response.equals("401")) {
            Gson gson = new Gson();
            JsonObject responseData = gson.fromJson(response, JsonObject.class);
            this.settings.put("access_token", "Bearer " + responseData.getAsJsonObject("data").get("access_token").getAsString());
            saveSettings();
        } else {
            System.out.println("Authentication failed. Wrong email or password.");
        }
    }

    private String sendRequest(String method, String payload) throws IOException {
        HttpURLConnection connection = getHttpURLConnection(method, payload);

        // Handle response
        int responseCode = connection.getResponseCode();
        if(responseCode == 200) {
            try(BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                StringBuilder response = new StringBuilder();
                String line;
                while((line = reader.readLine()) != null) {
                    response.append(line);
                }
                return response.toString();
            }
        } else {
            return String.valueOf(responseCode);
        }
    }

    private HttpURLConnection getHttpURLConnection(String method, String payload) throws IOException {
        URL url = new URL(this.api_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod(method);
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        // Set headers
        for(Map.Entry<String, String> entry : this.headers.entrySet()) {
            connection.setRequestProperty(entry.getKey(), entry.getValue());
        }

        // Send payload
        try(OutputStream os = connection.getOutputStream()) {
            byte[] input = payload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }
        return connection;
    }

    private String convertToJson(Map<String, String> data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }
}

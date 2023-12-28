package de.officeryoda.api.sdui.Responses;

import de.officeryoda.api.sdui.Responses.ResponseInformation.NewsItem;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewsInformation extends SduiBaseInformation {

    private List<NewsItem> data;

}

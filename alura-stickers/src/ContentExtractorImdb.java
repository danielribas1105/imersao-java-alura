import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ContentExtractorImdb implements ContentExtractor {

    public List<Content> extractContent(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> atributList = parser.parse(json);
        List<Content> contents = new ArrayList<>();
        for (Map<String, String> atribut : atributList) {
            String title = atribut.get("title");
            String urlImage = atribut.get("image");
            var content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;
    }
}

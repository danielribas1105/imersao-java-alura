import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class ContentExtractorNasa implements ContentExtractor {

    public List<Content> extractContent(String json) {
        var parser = new JsonParser();
        List<Map<String, String>> atributList = parser.parse(json);

        //usando stream e lambda
        return atributList.stream()
                .map(atribut -> new Content(atribut.get("title"), atribut.get("url")))
                .collect(toList());

        /*List<Content> contents = new ArrayList<>();
        for (Map<String, String> atribut : atributList) {
            String title = atribut.get("title");
            String urlImage = atribut.get("url");
            var content = new Content(title, urlImage);
            contents.add(content);
        }
        return contents;*/
    }
}

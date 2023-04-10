public enum API {

    IMDB_TOP_MOVIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json", new ContentExtractorImdb()),
    IMDB_TOP_SERIES("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopTVs.json", new ContentExtractorImdb()),
    NASA("https://api.nasa.gov/planetary/apod?api_key=1h6ReN05vxXQkBoJgP8huhYaQDvYmzqHpF0kdKCG&start_date=2023-04-01&end_date=2023-04-03", new ContentExtractorNasa());

    private String url;
    private ContentExtractor extractor;

    API(String url, ContentExtractor extractor) {
        this.url = url;
        this.extractor = extractor;
    }

    public String getUrl() {
        return url;
    }

    public ContentExtractor getExtractor() {
        return extractor;
    }
}

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Realizar uma conexão HTTP e pegar os TOP 250 melhores filmes

        //String imdbKey = System.getenv("IMDB_KEY");//capturar uma variável de ambiente

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI uri = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(uri).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        //Extrair somente os dados que interessam(Título, Poster, Classificação)

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        //Exibir os dados da forma que escolhermos
        var factory = new FactoryStickers();
        for (Map<String,String> filme : listaDeFilmes) {

            String nomeArquivo = filme.get("title") + ".png";
            String urlImagem = filme.get("image");
            InputStream inputStream = new URL(urlImagem).openStream();

            factory.createSticker(inputStream, "stickers/"+nomeArquivo);


            System.out.println("\u001b[1m" + "TÍTULO: " + "\u001b[36m" + "\u001b[3m" + filme.get("title") + "\u001b[m");
            /*System.out.println("\u001b[1m" + "CARTAZ: " + "\u001b[m" + filme.get("image"));
            System.out.println("\u001b[1m" + "CLASSIFICAÇÃO: " + "\u001b[34m" + filme.get("imDbRating") + "\u001b[m");

            int quantEstrelas = (int) Double.parseDouble(filme.get("imDbRating"));

            for(int i = 0; i <= quantEstrelas; i++){
                System.out.print("⭐");
            }

            System.out.println("\n");*/
        }

    }
}

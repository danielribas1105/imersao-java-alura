import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Realizar uma conexão HTTP e pegar os TOP 250 melhores filmes
        
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

        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("\u001b[1m" + "TÍTULO: " + "\u001b[36m" + "\u001b[3m" + filme.get("title") + "\u001b[m");
            System.out.println("\u001b[1m" + "CARTAZ: " + "\u001b[m" + filme.get("image"));
            System.out.println("\u001b[1m" + "CLASSIFICAÇÃO: " + "\u001b[34m" + filme.get("imDbRating") + "\u001b[m");

            int quantEstrelas = (int) Double.parseDouble(filme.get("imDbRating"));

            for(int i = 0; i <= quantEstrelas; i++){
                System.out.print("⭐");
            }

            System.out.println("\n");
        }

    }
}

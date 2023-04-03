import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        //Realizar uma conexão HTTP e pegar os TOP 250 melhores filmes

        //String imdbKey = System.getenv("IMDB_KEY");//capturar uma variável de ambiente
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/MostPopularMovies.json";
        //String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        String url = "https://api.nasa.gov/planetary/apod?api_key=1h6ReN05vxXQkBoJgP8huhYaQDvYmzqHpF0kdKCG&start_date=2023-04-01&end_date=2023-04-03";

        ClientHttp clientHttp = new ClientHttp();
        String jason = clientHttp.findDados(url);

        //Extrair somente os dados que interessam(Título, Poster, Classificação)

        var parser = new JsonParser();
        List<Map<String, String>> contentList = parser.parse(jason);

        //Criando o diretório
        String pastaSaida = "stickers/";
        var path = new File(pastaSaida);
        path.mkdir();

        //Exibir os dados da forma que escolhermos
        var factory = new FactoryStickers();
        for (Map<String,String> content : contentList) {

            String nomeArquivo = content.get("title") + ".png";
            String urlImagem = content.get("url");
            //double notaFilme = Double.parseDouble(content.get("imDbRating"));
            double notaFilme = 9.0;
            InputStream inputStream = new URL(urlImagem).openStream();

            factory.createSticker(inputStream, pastaSaida + nomeArquivo, notaFilme);

            System.out.println("\u001b[1m" + "TÍTULO: " + "\u001b[36m" + "\u001b[3m" + content.get("title") + "\u001b[m");
            /*System.out.println("\u001b[1m" + "CARTAZ: " + "\u001b[m" + content.get("image"));
            System.out.println("\u001b[1m" + "CLASSIFICAÇÃO: " + "\u001b[34m" + content.get("imDbRating") + "\u001b[m");

            int quantEstrelas = (int) Double.parseDouble(content.get("imDbRating"));

            for(int i = 0; i <= quantEstrelas; i++){
                System.out.print("⭐");
            }

            System.out.println("\n");*/
        }

    }
}

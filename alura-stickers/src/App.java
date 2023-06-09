import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        //Realizar uma conexão HTTP e pegar os TOP 250 melhores filmes

        //String imdbKey = System.getenv("IMDB_KEY");//capturar uma variável de ambiente

        //API api = API.NASA;
        //API api = API.IMDB_TOP_MOVIES;
        API api = API.IMDB_TOP_SERIES;

        String url = api.getUrl();
        ContentExtractor extractor = api.getExtractor();

        ClientHttp clientHttp = new ClientHttp();
        String jason = clientHttp.findDados(url);

        //Criando o diretório
        String pastaSaida = "stickers/";
        var path = new File(pastaSaida);
        path.mkdir();

        List<Content> contents = extractor.extractContent(jason);

        //Exibir os dados da forma que escolhermos
        var factory = new FactoryStickers();
        for (int i = 0; i < contents.size(); i++) {

            Content content = contents.get(i);
            String nomeArquivo = content.getTitle() + ".png";
            //String urlImagem = content.getUrlImage();
            //double notaFilme = Double.parseDouble(content.get("imDbRating"));
            double notaFilme = 9.0;

            InputStream inputStream = new URL(content.getUrlImage()).openStream();
            factory.createSticker(inputStream, pastaSaida + nomeArquivo, notaFilme);

            System.out.println("\u001b[1m" + "TÍTULO: " + "\u001b[36m" + "\u001b[3m" + nomeArquivo + "\u001b[m");
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

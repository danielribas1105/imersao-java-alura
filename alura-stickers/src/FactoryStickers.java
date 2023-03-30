import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.InputStream;

public class FactoryStickers {

    public void createSticker(InputStream inputStream, String nomeArquivo) throws Exception {

        //leitura  da imagem
        //InputStream inputStream = new FileInputStream(new File("img/filme_01.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imgOriginal = ImageIO.read(inputStream);

        //criar nova imagem em memória com transparência e tamanho novo
        int largura = imgOriginal.getWidth();
        int altura = imgOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage imgModificada = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //copiar a imagem original para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) imgModificada.getGraphics();
        graphics.drawImage(imgOriginal, 0, 0, null);

        //configurar a fonte
        Font fonte = new Font(Font.SANS_SERIF,Font.BOLD,64);
        graphics.setColor(Color.blue);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        graphics.drawString("TEXTO",100, novaAltura - 100);

        //salvar a nova imagem em um novo arquivo
        ImageIO.write(imgModificada, "png", new File(nomeArquivo));

    }

}

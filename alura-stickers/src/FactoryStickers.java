import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
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
        var fonte = new Font("Comic Sans",Font.BOLD,64);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        String texto = "TEXTO QQ";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangleText = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) rectangleText.getWidth();
        int positionX = (largura - larguraTexto)/2;
        int positionY = novaAltura - 100;
        graphics.drawString(texto, positionX, positionY);

        //criar borda no texto
        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        TextLayout textLayout = new TextLayout(texto, fonte, fontRenderContext);

        Shape outline = textLayout.getOutline(null);
        AffineTransform transform = graphics.getTransform();
        transform.translate(positionX, positionY);
        graphics.setTransform(transform);

        BasicStroke outLineStroke = new BasicStroke(largura * 0.004f); //setar o tamanho do pincel de contorno
        graphics.setStroke(outLineStroke);

        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

        //salvar a nova imagem em um novo arquivo
        ImageIO.write(imgModificada, "png", new File(nomeArquivo));

    }

}

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class FactoryStickers {

    public void createSticker(InputStream inputStream, String nomeArquivo, double nota) throws Exception {

        //leitura  da imagem do poster
        //InputStream inputStream = new FileInputStream(new File("img/filme_01.jpg"));
        //InputStream inputStream = new URL("https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies_1.jpg").openStream();
        BufferedImage imgOriginal = ImageIO.read(inputStream);

        //criar nova imagem em memória com transparência e tamanho novo
        int largura = imgOriginal.getWidth();
        int altura = imgOriginal.getHeight();
        int novaAltura = altura + 200;
        BufferedImage imgModificada = new BufferedImage(largura, novaAltura, BufferedImage.TRANSLUCENT);

        //classificação do filme
        String imgNota = "";
        if(nota > 9.0) {
            imgNota = "img/boneco-nota-10.jpg";
        }else if ((nota <= 9.0) && (nota > 8.5)) {
            imgNota = "img/boneco-nota-9.jpg";
        }else {
            imgNota = "img/boneco-nota-8.jpg";
        }

        //configurar imagem de classificação
        InputStream inputClassificacao = new FileInputStream(new File(imgNota));
        BufferedImage imgOriginalNota = ImageIO.read(inputClassificacao);

        //copiar a imagens originais (poster e nota) para a nova imagem (em memória)
        Graphics2D graphics = (Graphics2D) imgModificada.getGraphics();
        graphics.drawImage(imgOriginal, 0, 0, null);

        //configurar a fonte título
        var fonte = new Font("Comic Sans",Font.BOLD,48);
        graphics.setColor(Color.yellow);
        graphics.setFont(fonte);

        //escrever uma frase na nova imagem
        String texto = "* Nossa Classificação *";
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangleText = fontMetrics.getStringBounds(texto, graphics);
        int larguraTexto = (int) rectangleText.getWidth();
        int positionX = (largura - larguraTexto)/2;
        int positionY = novaAltura - 150;
        graphics.drawString(texto, positionX, positionY);

        /*
        *** Configuração e inserção da nota do filme
        */
        //configurar fonte nota
        var fonteNota = new Font("Impact", Font.BOLD | Font.ITALIC, 100);
        graphics.setColor(Color.blue);
        graphics.setFont(fonteNota);

        //escrever nota do filme
        String notaFilme = String.valueOf(nota);
        FontMetrics fontMetricsNota = graphics.getFontMetrics();
        Rectangle2D rectangleNota = fontMetricsNota.getStringBounds(notaFilme, graphics);
        int larguraNota = (int) rectangleNota.getWidth();
        int alturaNota = (int) rectangleNota.getHeight();
        int positionNotaX = (largura - larguraNota)/2;
        int positionNotaY = novaAltura - alturaNota/5;

        /*
         *** Configuração e inserção da foto
         */
        InputStream inputFoto = new FileInputStream(new File("img/daniel_ok.png"));
        BufferedImage imgOriginalFoto = ImageIO.read(inputFoto);

        //desenhar imagem da classificação
        graphics.drawImage(imgOriginalNota,positionX, novaAltura - 120, null);

        //escrever a nota do filme
        graphics.drawString(notaFilme,positionNotaX, positionNotaY);

        //desenhar a foto
        graphics.drawImage(imgOriginalFoto,positionX + larguraTexto - 100, novaAltura - 120, null);

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

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.*;

public class ImagePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private BufferedImage image;

    public ImagePanel(String src_img){
        super();
        this.setSize(new Dimension(100, 100));
        try {
            image = ImageIO.read(new File(src_img));
        } catch (IOException e) {
            System.out.println("Nu s-a gasit imaginea");
        }
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), null);          
    }

}
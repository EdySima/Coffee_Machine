import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ImgButton extends JButton{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    private ImageIcon image;

    public ImgButton(String img_src, String name, int w, int h) {
        super("<html><div style='text-align: center;'>" + name + "</div></html>");
        Format(img_src, w, h);
    }

    public void Format(String img_src, int w, int h){
        setPreferredSize(new Dimension(w, h));
        image = new ImageIcon(img_src);
        Image img = image.getImage();
        Image newimg = img.getScaledInstance(w, h, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(newimg);
        setIcon(image);
    }
}

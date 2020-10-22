import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Simulator_Automat_Cafea extends JFrame{

    /**
	 *
	 */
    private static final long serialVersionUID = 1L;

    int updown = 56, leftright = 100;
    boolean ok = true;

    JPanel mainPanel = new JPanel();
    JPanel westPanel = new ImagePanel("images/metal_west.png");
    JPanel northPanel = new ImagePanel("images/metal_north.png");
    JPanel southPanel = new ImagePanel("images/metal_south.png");
    JPanel eastPanel = new ImagePanel("images/metal_east.png");

    ImgButton westButton = new ImgButton("images/senzori_button.png", "", leftright, leftright); 
    JButton eastButton = new JButton(); 
    JButton southButton = new JButton(); 
    JButton northButton = new JButton(); 

    JPanel imgPanel = new ImagePanel("images/screen.png");
    Menu menu = new Menu();
    senzori senzori = new senzori();
    JPanel error = new JPanel();

    JLabel errorMsg = new JLabel("APARATUL ESTE DEFECT");

    Color backgroundColor = new Color(30, 15, 10);

    public Simulator_Automat_Cafea(){
        super("Simulator Automat Cafea");

        setLayout(new BorderLayout());

        setBackground(Color.GRAY);
        setSize(1600, 900);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TransparentButton(westButton);
        TransparentButton(eastButton);
        TransparentButton(southButton);
        TransparentButton(northButton);

        imgPanel.setLayout(new BorderLayout());
        imgPanel.setBorder(BorderFactory.createEmptyBorder(37, 41, 55, 44));

        westPanel.add(westButton);

        errorMsg.setFont(new Font("Times New Roman", Font.BOLD, 40));
        errorMsg.setForeground(Color.WHITE);
        error.add(errorMsg);
        error.setBackground(backgroundColor);

        eastPanel.add(eastButton);
        eastButton.setPreferredSize(new Dimension(leftright, leftright));

        northPanel.add(northButton);
        northButton.setPreferredSize(new Dimension(updown, updown));

        southPanel.add(southButton);
        southButton.setPreferredSize(new Dimension(updown, updown));

        menu.ChangeBtnImg();
        
        if(senzori.sj.Defect())
            imgPanel.add(error, BorderLayout.CENTER);
        else
            imgPanel.add(menu, BorderLayout.CENTER);
        
        add(imgPanel, BorderLayout.CENTER);
        add(westPanel, BorderLayout.WEST);
        add(northPanel, BorderLayout.NORTH);
        add(southPanel, BorderLayout.SOUTH);
        add(eastPanel, BorderLayout.EAST);
        westButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                senzori.sj.read_senz();
                senzori.ChangeText();
                menu.ChangeBtnImg();
                if(senzori.sj.Defect())
                {
                    if (ok){
                        nextPanel(error, senzori);
                        ok = false;
                        westButton.Format("images/senzori_button_press.png", leftright, leftright);
                    }
                    else {
                        nextPanel(senzori, error);
                        ok = true;
                        westButton.Format("images/senzori_button.png", leftright, leftright);
                    }
                }
                else
                    if (ok){
                        nextPanel(menu, senzori);
                        ok = false;
                        westButton.Format("images/senzori_button_press.png", leftright, leftright);
                    }
                    else{
                        nextPanel(senzori, menu);
                        ok = true;
                        westButton.Format("images/senzori_button.png", leftright, leftright);
                    }
                    
            }
        });

        setVisible(true);
    }

    private void nextPanel(JPanel here, senzori next){
        here.setVisible(false);
        imgPanel.remove(here);
        imgPanel.add(next, BorderLayout.CENTER);
        next.setVisible(true);
    }

    private void nextPanel(senzori here, JPanel next){
        here.setVisible(false);
        imgPanel.remove(here);
        imgPanel.add(next, BorderLayout.CENTER);
        next.setVisible(true);
    }

    private void nextPanel(Menu here, senzori next){
        here.setVisible(false);
        imgPanel.remove(here);
        imgPanel.add(next, BorderLayout.CENTER);
        next.setVisible(true);
    }

    private void nextPanel(senzori here, Menu next){
        here.setVisible(false);
        imgPanel.remove(here);
        imgPanel.add(next, BorderLayout.CENTER);
        next.setVisible(true);
    }

    private void TransparentButton(JButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

}
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;


public class senzori extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    Senzori_txt sj = new Senzori_txt();

    JPanel OKBtn_Panel = new JPanel();
    JPanel pan_senzori=new JPanel();
    JPanel zahar=new JPanel();
    JPanel lapte=new JPanel();
    JPanel cafea=new JPanel();
    JPanel apa=new JPanel();
    JPanel ceai=new JPanel();
    JPanel defectiune=new JPanel();
    JPanel pahare_mari=new JPanel();
    JPanel pahare_mici=new JPanel();
    JPanel cacao=new JPanel();
    JPanel monezi=new JPanel();

    JLabel label1 = new JLabel();
    JLabel label2 = new JLabel();
    JLabel label3 = new JLabel();
    JLabel label4 = new JLabel();
    JLabel label5 = new JLabel();
    JLabel label6 = new JLabel();
    JLabel label7 = new JLabel();
    JLabel label8 = new JLabel();
    JLabel label9 = new JLabel();
    JLabel label10 = new JLabel();

    ImgButton OKButton = new ImgButton("images/coffee1.png", "", 100, 100);
    JButton check1=new JButton("Introdu");
    JButton check2=new JButton("Introdu");
    JButton check3=new JButton("Introdu");
    JButton check4=new JButton("Introdu");
    JButton check5=new JButton("Introdu");
    JButton check6=new JButton("Introdu");
    JButton check7=new JButton("Introdu");
    JButton check9=new JButton("Introdu");
    JButton check10=new JButton("Introdu");
    JToggleButton jb=new JToggleButton();

    JTextField cant_zah=new JTextField(Double.toString(sj.jcant_zahar),5);
    JTextField cant_lap=new JTextField(Double.toString(sj.jcant_lap),5);
    JTextField cant_caf=new JTextField(Double.toString(sj.jcant_caf),5);
    JTextField cant_cacao=new JTextField(Double.toString(sj.jcant_cacao),5);
    JTextField cant_apa=new JTextField(Double.toString(sj.jcant_apa),5);
    JTextField cant_ceai=new JTextField(Double.toString(sj.jcant_ceai),5);
    JTextField nr_pahare_mari=new JTextField(Integer.toString(sj.jnr_pahare_mari),5);
    JTextField nr_pahare_mici=new JTextField(Integer.toString(sj.jnr_pahare_mici),5);
    JTextField nr_monezi=new JTextField(Integer.toString(sj.jnr_monezi),5);


    JFrame warning;
    JLabel warningText;
    JButton OKBtn;
    JPanel Btn_Panel;

    Color backgroundColor = new Color(30, 15, 10);

    public senzori() {

        //super("images/screen.png");

        //setBorder(BorderFactory.createEmptyBorder(37, 41, 55, 44));
        setLayout(new BorderLayout());
        setBackground(Color.LIGHT_GRAY);

        pan_senzori.setBorder(BorderFactory.createEmptyBorder(70, 0, 0, 0));

        add(pan_senzori, BorderLayout.CENTER);
        
        Format_Panel(pan_senzori);

        zahar();
        lapte();
        cafea();
        cacao();
        apa();
        ceai();
        defectiune();
        pahare_mari();
        pahare_mici();
        monezi();

        pan_senzori.setLayout(new GridLayout(5,2));
        pan_senzori.add(zahar);
        pan_senzori.add(lapte);
        pan_senzori.add(cafea);
        pan_senzori.add(cacao);
        pan_senzori.add(apa);
        pan_senzori.add(ceai);
        pan_senzori.add(defectiune);
        pan_senzori.add(monezi);
        pan_senzori.add(pahare_mari);
        pan_senzori.add(pahare_mici);
    }

    private void formatLabel(JLabel label){
        label.setFont(new Font("Times New Roman", Font.BOLD, 28));
        label.setForeground(Color.WHITE);
    }

    private void Format_Panel(JPanel panel){
        panel.setBackground(backgroundColor);
    }

    private void TransparentButton(ImgButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    public void ChangeText(){
        sj.read_senz();
        cant_zah.setText(Double.toString(sj.jcant_zahar));
        cant_lap.setText(Double.toString(sj.jcant_lap));
        cant_caf.setText(Double.toString(sj.jcant_caf));
        cant_cacao.setText(Double.toString(sj.jcant_cacao));
        cant_apa.setText(Double.toString(sj.jcant_apa));
        cant_ceai.setText(Double.toString(sj.jcant_ceai));
        nr_pahare_mari.setText(Integer.toString(sj.jnr_pahare_mari));
        nr_pahare_mici.setText(Integer.toString(sj.jnr_pahare_mici));
        nr_monezi.setText(Integer.toString(sj.jnr_monezi));
    }

    public void warning(){
        warning = new JFrame("WARNING!!!");
        warningText = new JLabel("INTRODUCETI O VALOARE", SwingConstants.CENTER);
        OKBtn = new JButton("OK");
        Btn_Panel = new JPanel();

        warning.setLayout(new BorderLayout());
        warning.setSize(500, 100);
        warning.setResizable(false);
        warning.setLocationRelativeTo(null);
        warning.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        warning.setVisible(true);

        warningText.setFont(new Font("Times New Roman", Font.BOLD, 20));
        OKBtn.setSize(20,40);
        Btn_Panel.add(OKBtn);
        warning.add(warningText, BorderLayout.CENTER);
        warning.add(Btn_Panel, BorderLayout.SOUTH);

        OKBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                warning.dispose();
            }
        });
    }


    public void zahar(){
        Format_Panel(zahar);
        zahar.add(label1);
        zahar.add(cant_zah);
        zahar.add(check1);

        label1.setText("Cantitatea de zahar (g) : ");
        formatLabel(label1);

        cant_zah.setSize(40, 50);

        check1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_zahar = Double.parseDouble(cant_zah.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void lapte(){
        Format_Panel(lapte);
        lapte.add(label2);
        lapte.add(cant_lap);
        lapte.add(check2);

        label2.setText("Cantitatea de lapte (g) : ");
        formatLabel(label2);

        cant_lap.setSize(40, 50);
        
        check2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_lap= Double.parseDouble(cant_lap.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void cafea(){
        Format_Panel(cafea);
        cafea.add(label3);
        cafea.add(cant_caf);
        cafea.add(check3);

        label3.setText("Cantitatea de cafea (g) : ");
        formatLabel(label3);

        cant_caf.setSize(40, 50);

        check3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_caf = Double.parseDouble(cant_caf.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void apa(){
        Format_Panel(apa);
        apa.add(label4);
        apa.add(cant_apa);
        apa.add(check4);

        label4.setText("Cantitatea de apa (ml) : ");
        formatLabel(label4);

        cant_apa.setSize(40, 50);

        check4.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_apa= Double.parseDouble(cant_apa.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void ceai(){
        Format_Panel(ceai);
        ceai.add(label7);
        ceai.add(cant_ceai);
        ceai.add(check7);

        label7.setText("Cantitatea de ceai (g) : ");
        formatLabel(label7);

        cant_ceai.setSize(40, 50);

        check7.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_ceai= Double.parseDouble(cant_ceai.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void defectiune(){
        Format_Panel(defectiune);
        defectiune.add(label8);
        defectiune.add(jb);

        label8.setText("Defectiuni fizice: ");
        formatLabel(label8);

        if(sj.jdefectiuni==false){
            jb.setText("Automatul nu este defect");
        }
        else{
            jb.setText("Automatul este defect");
            jb.setForeground(Color.WHITE);
            jb.setBackground(Color.RED);
        }
        ItemListener itemListener = new ItemListener() {
            public void itemStateChanged(ItemEvent itemEvent) 
            {
                int state = itemEvent.getStateChange();
                if (state != ItemEvent.SELECTED) {
                    
                    jb.setText("Automatul este defect");
                    jb.setForeground(Color.WHITE);
                    sj.jdefectiuni=true;
                } 
                else{
                    jb.setBackground(Color.RED);
                    jb.setForeground(Color.BLACK);
                    jb.setText("Automatul nu este defect");
                    sj.jdefectiuni=false;
                }
                sj.write_senz_new();
            }
        }; 
        jb.addItemListener(itemListener);
    }

    public void pahare_mari(){
        Format_Panel(pahare_mari);
        pahare_mari.add(label5);
        pahare_mari.add(nr_pahare_mari);
        pahare_mari.add(check5);

        label5.setText("Numarul de pahare mari (200ml): ");
        formatLabel(label5);

        nr_pahare_mari.setSize(40, 50);

        check5.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jnr_pahare_mari= Integer.parseInt(nr_pahare_mari.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void pahare_mici(){
        Format_Panel(pahare_mici);
        pahare_mici.add(label6);
        pahare_mici.add(nr_pahare_mici);
        pahare_mici.add(check6);

        label6.setText("Numarul de pahare mici (140ml) : ");
        formatLabel(label6);

        nr_pahare_mici.setSize(40, 50);
        
        check6.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jnr_pahare_mici= Integer.parseInt(nr_pahare_mici.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void monezi(){
        Format_Panel(monezi);
        monezi.add(label9);
        monezi.add(nr_monezi);
        monezi.add(check9);

        label9.setText("Numarul de monezii : ");
        formatLabel(label9);

        nr_monezi.setSize(40, 50);
        
        check9.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jnr_monezi= Integer.parseInt(nr_monezi.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }

    public void cacao(){
        Format_Panel(cacao);
        cacao.add(label10);
        cacao.add(cant_cacao);
        cacao.add(check10);

        label10.setText("Cantitatea de cacao (g) : ");
        formatLabel(label10);

        cant_cacao.setSize(40, 50);
        
        check10.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){  
                try {
                    sj.jcant_cacao= Double.parseDouble(cant_cacao.getText());
                    sj.write_senz_new();
                } catch (NumberFormatException nfe) {
                    warning();
                }
            }  
        });
    }
}
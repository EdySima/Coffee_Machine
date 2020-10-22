import javax.swing.*;
import javax.swing.plaf.BorderUIResource;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.text.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Menu extends JPanel{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    int k = 0;
    double credit = 0.0, credit_introdus_card = 0.0;
    int w = 55, h = 75, w2 = w + 15, h2 = h + 30;
    boolean ok = false;

    int cost_min = 2, cost_max = 3;
    ImgButton produs1 = new ImgButton("images/coffee1.png", "Espresso Mic<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs2 = new ImgButton("images/coffee2.png", "Cafea Mica<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs3 = new ImgButton("images/coffee3.png", "Cafea cu Lapte Mica<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs4 = new ImgButton("images/coffee4.png", "Cappuccino Mic<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs5 = new ImgButton("images/coffee5.png", "Espresso Lung<br>"+ String.valueOf(cost_min) +" RON" ,w, h);
    ImgButton produs6 = new ImgButton("images/coffee6.png", "Cafea Mare<br>"+ String.valueOf(cost_max) +" RON", w2, h2);
    ImgButton produs7 = new ImgButton("images/coffee7.png", "Cafea cu Lapte Mare<br>"+ String.valueOf(cost_max) +" RON", w2, h2);
    ImgButton produs8 = new ImgButton("images/coffee8.png", "Cappuccino Mare<br>"+ String.valueOf(cost_max) +" RON", w2, h2);
    ImgButton produs9 = new ImgButton("images/cacao.png", "Cacao cu Lapte<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs10 = new ImgButton("images/milk.png", "Lapte<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs11 = new ImgButton("images/tea.png", "Ceai<br>"+ String.valueOf(cost_min) +" RON", w, h);
    ImgButton produs12 = new ImgButton("images/water.png", "Apa Fiarta<br>"+ String.valueOf(cost_min) +" RON", w, h);

    ImgButton insertCreditBtn = new ImgButton("images/credit.png", "", 300, h2);
    ImgButton insertSugarBtn = new ImgButton("images/zahar.png", "", 300, h2);
    ImgButton rest = new ImgButton("images/rest.png", "", 300, h2);
    ImgButton OKBtn = new ImgButton("images/ok.png","", h2, h2);
    ImgButton cashBtn = new ImgButton("images/cash.png","", 300, 300);
    ImgButton cardBtn = new ImgButton("images/card.png","", 300, 300);
    ImgButton backPaymentBtn = new ImgButton("images/back.png","", h2, h2);

    JPanel prod_Panel = new JPanel();
    JPanel bg_prod_Panel = new JPanel();
    JPanel btn_prod_Panel = new JPanel();
    JPanel sugar_Panel = new JPanel();
    JPanel btn_sugar_Panel = new JPanel();
    JPanel select_payment_Panel = new JPanel();
    JPanel bg_select_payment_Panel = new JPanel();
    JPanel btn_select_payment_Panel = new JPanel();
    JPanel prepare_Panel = new JPanel();
    JPanel cash_Panel = new JPanel();
    JPanel card_Panel = new JPanel();
    JPanel text_Panel = new JPanel();
    JPanel done_Panel = new JPanel();
    
    JLabel sugar_title = new JLabel("CANTITATEA DE ZAHAR DORITA!", SwingConstants.CENTER);
    JLabel credit_text = new JLabel("Credit: " + String.valueOf(credit) + " RON" , SwingConstants.LEFT);
    JLabel select_payment_text = new JLabel("SELECTEAZA METODA DE PLATA", SwingConstants.CENTER);
    JLabel credit_insert_text = new JLabel("Credit: " + String.valueOf(credit) + " RON" , SwingConstants.CENTER);

    JSlider slider = new JSlider(0, 4, 2);

    Color backgroundColor = new Color(30, 15, 10);
    Color textcolor = Color.WHITE;
    Color linecolor = new Color(100, 40, 30);


    Senzori_txt var_senzori = new Senzori_txt();
    String warningMessages[] = {"NU MAI ESTE CAFEA", "NU MAI ESTE LAPTE", "NU MAI ESTE APA", "NU MAI ESTE CEAI", "NU MAI SUNT PAHARE MARI", "NU MAI SUNT PAHARE MICI"};
    
    JProgressBar preparation_bar = new JProgressBar();
    
    public Menu(){

        setLayout(new GridLayout(1,1));
        setBackground(Color.LIGHT_GRAY);

        ProdPanel();
        SugarPanel();
        SelectPayment();
        CashPayment();
        CardPayment();
        Prepare();
        Actions();
        Done();
        add(bg_prod_Panel);

    }

    //-------------------------------------------------------  USEFULL FUNCTIONS ------------------------------------------------------------//

    private void nextPanel(JPanel here, JPanel next){
        here.setVisible(false);
        remove(here);
        add(next);
        next.setVisible(true);
    }

    private void TransparentButton(ImgButton btn){
        btn.setOpaque(false);
        btn.setContentAreaFilled(false);
        btn.setBorderPainted(false);
    }

    public void ChangeBtnImg(){
        var_senzori.read_senz();
        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf)
            produs1.Format("images/nocoffee1.png", w, h);
        else
            produs1.Format("images/coffee1.png", w, h);
        
        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf)
            produs2.Format("images/nocoffee2.png", w, h);
        else
            produs2.Format("images/coffee2.png", w, h);

        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs3.Format("images/nocoffee3.png", w, h);
        else
            produs3.Format("images/coffee3.png", w, h);

        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs4.Format("images/nocoffee4.png", w, h);
        else
            produs4.Format("images/coffee4.png", w, h);

        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf)
            produs5.Format("images/nocoffee5.png", w, h);
        else
            produs5.Format("images/coffee5.png", w, h);

        if (var_senzori.jnr_pahare_mari == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf)
            produs6.Format("images/nocoffee6.png", w2, h2);
        else
            produs6.Format("images/coffee6.png", w2, h2);
        
        if (var_senzori.jnr_pahare_mari == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs7.Format("images/nocoffee7.png", w2, h2);
        else
            produs7.Format("images/coffee7.png", w2, h2);

        if (var_senzori.jnr_pahare_mari == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_caf < var_senzori.cant_caf || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs8.Format("images/nocoffee8.png", w2, h2);
        else
            produs8.Format("images/coffee8.png", w2, h2);

        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_cacao < var_senzori.cant_cacao || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs9.Format("images/nocacao.png", w, h);
        else
            produs9.Format("images/cacao.png", w, h);
            
        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_lap < var_senzori.cant_lap3)
            produs10.Format("images/nomilk.png", w, h);
        else
            produs10.Format("images/milk.png", w, h);
        
        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare || var_senzori.jcant_ceai < var_senzori.cant_ceai)
            produs11.Format("images/notea.png", w, h);
        else
            produs11.Format("images/tea.png", w, h);
            
        if (var_senzori.jnr_pahare_mici == 0 || var_senzori.jcant_apa < var_senzori.cant_apa_mare)
            produs12.Format("images/nowater.png", w, h);
        else
            produs12.Format("images/water.png", w, h);
    }


    //-------------------------------------------------------  PRODUCTS PANEL ------------------------------------------------------------//


    private void ProdPanel(){
        bg_prod_Panel.setLayout(new BorderLayout());
        bg_prod_Panel.setBackground(backgroundColor);
        bg_prod_Panel.add(credit_text, BorderLayout.NORTH);
        bg_prod_Panel.add(prod_Panel, BorderLayout.CENTER);
        bg_prod_Panel.add(btn_prod_Panel, BorderLayout.SOUTH);

        btn_prod_Panel.setBorder(BorderFactory.createMatteBorder(4, 0, 0, 0, linecolor));
        btn_prod_Panel.setLayout(new GridLayout(1, 2, 200, 200));
        btn_prod_Panel.setBackground(backgroundColor);
        btn_prod_Panel.add(insertCreditBtn);
        btn_prod_Panel.add(insertSugarBtn);
        btn_prod_Panel.add(rest);
        TransparentButton(rest);
        TransparentButton(insertCreditBtn);
        TransparentButton(insertSugarBtn);

        credit_text.setFont(new Font("Times New Roman", Font.BOLD, 30));
        credit_text.setForeground(textcolor);
        //credit_text.setBorder(BorderFactory.createEmptyBorder(10, 30, 0, 0));
        credit_text.setBorder(BorderFactory.createMatteBorder(0, 0, 4, 0, linecolor));

        prod_Panel.setLayout(new GridLayout(3,4, 70, 30));
        prod_Panel.setBackground(backgroundColor);

        FormatProdButton(produs1);
        FormatProdButton(produs2);
        FormatProdButton(produs3);
        FormatProdButton(produs4);
        FormatProdButton(produs5);
        FormatProdButton(produs6);
        FormatProdButton(produs7);
        FormatProdButton(produs8);
        FormatProdButton(produs9);
        FormatProdButton(produs10);
        FormatProdButton(produs11);
        FormatProdButton(produs12);

        prod_Panel.add(produs1);
        prod_Panel.add(produs2);
        prod_Panel.add(produs3);
        prod_Panel.add(produs4);
        prod_Panel.add(produs5);
        prod_Panel.add(produs6);
        prod_Panel.add(produs7);
        prod_Panel.add(produs8);
        prod_Panel.add(produs9);
        prod_Panel.add(produs10);
        prod_Panel.add(produs11);
        prod_Panel.add(produs12);

        insertSugarBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                var_senzori.read_senz();
                if (var_senzori.poate_fi_cu_zahar())
                    nextPanel(bg_prod_Panel, sugar_Panel);
                else
                warning("NU MAI ESTE ZAHAR\nPUTETI COMANDA PRODUSE, DAR FARA ZAHAR!");
            }
        });

        insertCreditBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(bg_prod_Panel, bg_select_payment_Panel);
                if (var_senzori.jnr_monezi >= 20)
                    warning("RESTUL POATE FI DAT\nPANA LA 10 RON");
                else
                warning("RESTUL POATE FI DAT\nPANA LA " + String.valueOf(var_senzori.jnr_monezi*0.5) + " RON");
            }
        });

        rest.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if (ok){
                    if (var_senzori.jnr_monezi*0.5 >= credit)
                        if (credit <= 10){
                            warning("ATI PRIMIT CA REST\n" + Integer.toString((int)(credit/0.5)) + " MONEDE DE 50 DE BANI!");
                            var_senzori.jnr_monezi -= (int)(credit/0.5);
                            var_senzori.write_senz_new();
                            credit = 0;
                            credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                        }
                        else
                            warning("RESTUL POATE FI DAT\nPANA LA 10 RON");
                    else
                        if (var_senzori.jnr_monezi*0.5 >= 10)
                            warning("RESTUL POATE FI DAT\nPANA LA 10 RON");
                        else
                             warning("NU SE POATE OFERI REST\nMAI MULT DE " + String.valueOf(var_senzori.jnr_monezi*0.5) + " RON\nMAI COMANDATI PRODUSE!");
                }
                else
                    warning("COMANDATI MAI INTAI UN PRODUS!");
            }
        });
    }


    //-------------------------------------------------------  EVENT AND FORMAT FOR BUTTONS ON THE PRODUCTS PANEL ------------------------------------------------------------//

    private void FormatProdButton(ImgButton produs){
        TransparentButton(produs);
        produs.setVerticalTextPosition(SwingConstants.BOTTOM);
        produs.setHorizontalTextPosition(SwingConstants.CENTER);
        produs.setFont(new Font("Times New Roman", Font.BOLD, 18));
        produs.setForeground(textcolor);
    }

    //-------------------------------------------------------  WARNING MESSAGE ------------------------------------------------------------//

    private void warning(String name){
        JFrame warning = new JFrame("WARNING!!!");
        JTextPane warningText = new JTextPane();
        JButton OKBtn = new JButton("OK");
        JPanel Btn_Panel = new JPanel();
        
        warning.setLayout(new BorderLayout());
        warning.setSize(500, 150);
        warning.setResizable(false);
        warning.setLocationRelativeTo(null);
        warning.setVisible(true);
        
        try{
            SimpleAttributeSet attrs=new SimpleAttributeSet();
            StyleConstants.setAlignment(attrs,StyleConstants.ALIGN_CENTER);
            StyledDocument doc=(StyledDocument)warningText.getDocument();
            doc.insertString(0,"",attrs);
            doc.setParagraphAttributes(0,doc.getLength()-1,attrs,false);
        }
        catch(Exception ex){
 
        } 

        warningText.setOpaque(false);
        warningText.setEditable(false);
        warningText.setText(name);
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


    
    //-------------------------------------------------------  SUGAR PANEL ------------------------------------------------------------//



    private void SugarPanel(){
        sugar_Panel.setLayout(new BorderLayout());

        slider.setBounds(10,10,470,60);
        slider.setSize(500,500);
        slider.setPaintTrack(true); 
        slider.setPaintTicks(true); 
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(1); 
        slider.setMinorTickSpacing(1); 
        slider.setBackground(backgroundColor);
        slider.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 50));
        slider.setForeground(textcolor);

        btn_sugar_Panel.setLayout(new BorderLayout());
        btn_sugar_Panel.add(OKBtn, BorderLayout.SOUTH);
        btn_sugar_Panel.setBackground(backgroundColor);
        btn_sugar_Panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, linecolor));

        TransparentButton(OKBtn);
        sugar_title.setFont(new Font("Times New Roman", Font.BOLD, 50));
        sugar_title.setForeground(textcolor);

        sugar_Panel.setBorder(BorderFactory.createEmptyBorder(50,0,0,0));
        sugar_Panel.setBackground(backgroundColor);
        sugar_Panel.add(sugar_title, BorderLayout.NORTH);
        sugar_Panel.add(slider, BorderLayout.CENTER);
        sugar_Panel.add(btn_sugar_Panel, BorderLayout.SOUTH);

        OKBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(sugar_Panel, bg_prod_Panel);
            }
        });

    }


//-------------------------------------------------------  SELECT PAYMENT PANEL ------------------------------------------------------------//


    private void SelectPayment(){
        bg_select_payment_Panel.setLayout(new BorderLayout());
        bg_select_payment_Panel.add(select_payment_Panel, BorderLayout.CENTER);
        bg_select_payment_Panel.add(select_payment_text, BorderLayout.NORTH);
        bg_select_payment_Panel.add(btn_select_payment_Panel, BorderLayout.SOUTH);
        bg_select_payment_Panel.setBackground(backgroundColor);

        btn_select_payment_Panel.setLayout(new BorderLayout());
        btn_select_payment_Panel.add(backPaymentBtn, BorderLayout.WEST);
        btn_select_payment_Panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, linecolor));
        TransparentButton(backPaymentBtn);
        btn_select_payment_Panel.setBackground(backgroundColor);

        select_payment_text.setFont(new Font("Times New Roman", Font.BOLD, 30));
        select_payment_text.setForeground(textcolor);
        select_payment_text.setBorder(BorderFactory.createMatteBorder(0, 0, 5, 0, linecolor));

        select_payment_Panel.setLayout(new GridLayout(1, 2, 40, 40));
        select_payment_Panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        select_payment_Panel.setBackground(backgroundColor);

        TransparentButton(cashBtn);
        TransparentButton(cardBtn);
        select_payment_Panel.add(cashBtn);
        select_payment_Panel.add(cardBtn);

        backPaymentBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(bg_select_payment_Panel, bg_prod_Panel);
            }
        });

        cashBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                cash_Panel.add(credit_insert_text, BorderLayout.CENTER);
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                nextPanel(bg_select_payment_Panel, cash_Panel);
            }
        });

        cardBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                text_Panel.add(credit_insert_text);
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                nextPanel(bg_select_payment_Panel, card_Panel);
                
            }
        });
    }


//-------------------------------------------------------  CASH  PANEL ------------------------------------------------------------//

    private void CashPayment(){
        int w = 100, h = 100;
        JPanel Btn_Panel = new JPanel();
        JPanel backBtn_Panel = new JPanel();
        ImgButton _10RON = new ImgButton("images/_10ron.png", "", w, w);
        ImgButton _5RON = new ImgButton("images/_5ron.png", "", w, w);
        ImgButton _1RON = new ImgButton("images/_1ron.png","", w, w);
        ImgButton _0_5RON = new ImgButton("images/_50bani.png","", w, w);
        ImgButton BackBtn = new ImgButton("images/back.png","", h, h);
        ImgButton OKBtn = new ImgButton("images/ok.png","", h, h);


        Btn_Panel.setLayout(new GridLayout(4,1,20,20));
        Btn_Panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        Btn_Panel.setBackground(backgroundColor);
        cash_Panel.setLayout(new BorderLayout());
        cash_Panel.setBackground(backgroundColor);
        backBtn_Panel.setLayout(new BorderLayout());
        backBtn_Panel.setBackground(backgroundColor);

        backBtn_Panel.add(BackBtn, BorderLayout.WEST);
        backBtn_Panel.add(OKBtn, BorderLayout.EAST);
        backBtn_Panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, linecolor));

        Btn_Panel.add(_10RON);
        Btn_Panel.add(_5RON);
        Btn_Panel.add(_1RON);
        Btn_Panel.add(_0_5RON);

        TransparentButton(_10RON);
        TransparentButton(_5RON);
        TransparentButton(_1RON);
        TransparentButton(_0_5RON);
        TransparentButton(OKBtn);
        TransparentButton(BackBtn);

        credit_insert_text.setFont(new Font("Times New Roman", Font.BOLD, 50));
        credit_insert_text.setForeground(textcolor);

        cash_Panel.add(Btn_Panel, BorderLayout.EAST);
        cash_Panel.add(backBtn_Panel, BorderLayout.SOUTH);

        BackBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(cash_Panel, bg_select_payment_Panel);
            }
        });

        OKBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(cash_Panel, bg_prod_Panel);
            }
        });

        _10RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit += 10;
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                ok = false;
            }
        });

        _5RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit += 5;
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                ok = false;
            }
        });

        _1RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit += 1;
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                ok = false;
            }
        });

        _0_5RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit += 0.5;
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                var_senzori.jnr_monezi++;
                var_senzori.write_senz_new();
                ok = false;
            }
        });
    }


    //-------------------------------------------------------  CARD  PANEL ------------------------------------------------------------//

    private void CardPayment(){
        JPanel Btn_Panel = new JPanel();
        JPanel backBtn_Panel = new JPanel();
        ImgButton _10RON = new ImgButton("images/_10ron.png", "", h2, h2);
        ImgButton _5RON = new ImgButton("images/_5ron.png", "", h2, h2);
        ImgButton _1RON = new ImgButton("images/_1ron.png","", h2, h2);
        ImgButton CardCloser = new ImgButton("images/cardcloser.png","", 200, 90);
        ImgButton BackBtn = new ImgButton("images/back.png","", h2, h2);
        ImgButton ResetBtn = new ImgButton("images/reset.png","", h2, h2);
        JLabel wish_credit_text = new JLabel("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON", SwingConstants.CENTER);
        credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");

        Btn_Panel.setLayout(new GridLayout(4,1,20,20));
        Btn_Panel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        Btn_Panel.setBackground(backgroundColor);

        card_Panel.setLayout(new BorderLayout());
        card_Panel.setBackground(backgroundColor);

        backBtn_Panel.setLayout(new BorderLayout());
        backBtn_Panel.setBackground(backgroundColor);
        backBtn_Panel.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, linecolor));

        text_Panel.setLayout(new GridLayout(3,1,20,20));
        text_Panel.setBackground(backgroundColor);

        backBtn_Panel.add(BackBtn, BorderLayout.WEST);
        backBtn_Panel.add(CardCloser, BorderLayout.EAST);

        Btn_Panel.add(_10RON);
        Btn_Panel.add(_5RON);
        Btn_Panel.add(_1RON);

        TransparentButton(_10RON);
        TransparentButton(_5RON);
        TransparentButton(_1RON);
        TransparentButton(CardCloser);
        TransparentButton(BackBtn);
        TransparentButton(ResetBtn);
        

        credit_insert_text.setFont(new Font("Times New Roman", Font.BOLD, 50));
        wish_credit_text.setFont(new Font("Times New Roman", Font.BOLD, 50));
        wish_credit_text.setForeground(textcolor);
        text_Panel.add(ResetBtn);
        text_Panel.add(wish_credit_text);

        card_Panel.add(Btn_Panel, BorderLayout.EAST);
        card_Panel.add(text_Panel, BorderLayout.CENTER);
        card_Panel.add(backBtn_Panel, BorderLayout.SOUTH);

        BackBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(card_Panel, bg_select_payment_Panel);
                credit_introdus_card = 0;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
            }
        });

        CardCloser.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                nextPanel(card_Panel, bg_prod_Panel);
                credit += credit_introdus_card;
                credit_introdus_card = 0;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                credit_insert_text.setText("Credit: " + String.valueOf(credit) + " RON");
                ok = false;
            }
        });

        _10RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit_introdus_card += 10;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
            }
        });

        _5RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit_introdus_card += 5;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
            }
        });

        _1RON.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit_introdus_card += 1;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
            }
        });

        ResetBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                credit_introdus_card = 0;
                wish_credit_text.setText("Creditul pe care il doriti: " + String.valueOf(credit_introdus_card) + " RON");
            }
        });
    }


    //-------------------------------------------------------  PREPARATION  PANEL ------------------------------------------------------------// 

    private void Prepare(){

        JLabel prepare_text = new JLabel("BAUTURA SE PREPARA!", SwingConstants.CENTER);
        prepare_text.setFont(new Font("Times New Roman", Font.BOLD, 50));
        prepare_text.setForeground(textcolor);

        preparation_bar.setBackground(backgroundColor);
        preparation_bar.setPreferredSize(new Dimension(500, 100));
        prepare_Panel.setBackground(backgroundColor);
        prepare_Panel.setLayout(new BorderLayout());

        preparation_bar.setBorder(BorderFactory.createEmptyBorder(200, 50, 200, 50));
        prepare_Panel.add(prepare_text, BorderLayout.NORTH);
        prepare_Panel.add(preparation_bar, BorderLayout.CENTER);
    }


    //-------------------------------------------------------  DONE PANEL ------------------------------------------------------------//


    private void Done(){
        JLabel done_text = new JLabel("BAUTURA PREPARATA!", SwingConstants.CENTER);
        ImgButton OKBtn = new ImgButton("images/ok.png", "", h2, h2);
        done_text.setFont(new Font("Times New Roman", Font.BOLD, 70));
        done_text.setForeground(textcolor);

        done_Panel.setBackground(backgroundColor);
        done_Panel.setLayout(new BorderLayout());

        done_Panel.add(done_text, BorderLayout.CENTER);
        done_Panel.add(OKBtn, BorderLayout.SOUTH);
        TransparentButton(OKBtn);

        OKBtn.setBorder(BorderFactory.createMatteBorder(5, 0, 0, 0, linecolor));

        OKBtn.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                nextPanel(done_Panel, bg_prod_Panel);
                ChangeBtnImg();
            }
        });
    }

    //-------------------------------------------------------  PRODUCTS BUTTONS ACTION ------------------------------------------------------------// 

    private void Actions(){

        produs1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(1, cost_min);
            }
        });

        produs2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(2, cost_min);
            }
        });

        produs3.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(3, cost_min);
            }
        });

        produs4.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(4, cost_min);
            }
        });

        produs5.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(5, cost_min);
            }
        });

        produs6.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(6, cost_max);
            }
        });

        produs7.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(7, cost_max);
            }
        });

        produs8.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(8, cost_max);
            }
        });

        produs9.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(9, cost_min);
            }
        });

        produs10.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(10, cost_min);
            }
        });

        produs11.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(11, cost_min);
            }
        });

        produs12.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                Verificare(12, cost_min);
            }
        });
    }
    private void Verificare(int i, int pret){
        var_senzori.read_senz();
        if (credit >= Double.valueOf(pret)){
            if (var_senzori.cumpara_produs(i)){
                nextPanel(bg_prod_Panel, prepare_Panel);
                credit -= pret;
                credit_text.setText("Credit: " + String.valueOf(credit) + " RON");
                ok = true;
                if (i != 12 && i != 11 && i != 9){
                    if (var_senzori.poate_fi_cu_zahar());
                        var_senzori.jcant_zahar -= 2*slider.getValue();
                        var_senzori.write_senz_new();
                }
                ProgressWorker pw = new ProgressWorker();
                pw.addPropertyChangeListener(new PropertyChangeListener() {
                    @Override
                    public void propertyChange(PropertyChangeEvent evt) {
                        String name = evt.getPropertyName();
                        if (name.equals("progress")) {
                            int progress = (int) evt.getNewValue();
                            preparation_bar.setValue(progress);
                        repaint();
                        } else if (name.equals("state")) {
                            SwingWorker.StateValue state = (SwingWorker.StateValue) evt.getNewValue();
                            switch (state) {
                                case DONE:
                                    nextPanel(prepare_Panel, done_Panel);
                                    break;
                            }
                        }
                    }
                });
                pw.execute();
            }
        }
        else
            warning("NU AVETI CREDIT DESTUL!");
    }
}
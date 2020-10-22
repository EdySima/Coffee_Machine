import java.io.*;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Senzori_txt{
    double jcant_zahar;
    double jcant_lap;
    double jcant_caf;
    double jcant_cacao;
    double jcant_apa;
    double jcant_ceai;
    boolean jdefectiuni;
    int jnr_pahare_mici;
    int jnr_pahare_mari;
    int jnr_monezi;

    double cant_lap1 = 3;
    double cant_lap2 = 6;
    double cant_lap3 = 18;
    double cant_caf = 8;
    double cant_cacao = 22;
    double cant_apa = 120;
    double cant_apa_mare = 180;
    double cant_apa_esp = 45;
    double cant_ceai = 22;

    JFrame warning = new JFrame("ERROR!!!");
    JTextPane warningText = new JTextPane();
    JButton OKBtn = new JButton("OK");
    JPanel Btn_Panel = new JPanel();
    File file; 

    String warningMessages[] = {"NU MAI ESTE CAFEA", "NU MAI ESTE LAPTE", "NU MAI ESTE APA", "NU MAI ESTE CEAI", "NU MAI ESTE CACAO", "NU MAI SUNT PAHARE MARI", "NU MAI SUNT PAHARE MICI"};

    public Senzori_txt(){
        file=new File("parametri.txt");
        read_senz();
        Format_Warning();
    }

    public void read_senz(){
        try{
        FileInputStream fis=new FileInputStream(file);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis));
	    String line = null;
        line=br.readLine();
        jcant_zahar=Double.parseDouble(line);
        line=br.readLine();
        jcant_lap=Double.parseDouble(line);
        line=br.readLine();
        jcant_caf=Double.parseDouble(line);
        line=br.readLine();
        jcant_cacao=Double.parseDouble(line);
        line=br.readLine();
        jcant_apa=Double.parseDouble(line);
        line=br.readLine();
        jcant_ceai=Double.parseDouble(line);
        line=br.readLine();
        jdefectiuni=Boolean.parseBoolean(line);
        line=br.readLine();
        jnr_monezi=Integer.parseInt(line);
        line=br.readLine();
        jnr_pahare_mari=Integer.parseInt(line);
        line=br.readLine();
        jnr_pahare_mici=Integer.parseInt(line);
        br.close();
        }
        catch(Exception e){
            System.out.println("Fisierul nu a fost gasit!!!");
        }

    }

    public void write_senz_new(){
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("parametri.txt")))) {
            writer.write(Double.toString(jcant_zahar)+'\n'+Double.toString(jcant_lap)+'\n'+Double.toString(jcant_caf)+'\n'+Double.toString(jcant_cacao)+'\n'+Double.toString(jcant_apa)+'\n'+Double.toString(jcant_ceai)+'\n'+this.jdefectiuni+'\n'+Integer.toString(jnr_monezi)+'\n'+Integer.toString(jnr_pahare_mari)+'\n'+Integer.toString(jnr_pahare_mici));
        }
        catch (IOException ex){

        }
    }


//-------------------------------------------------------  FUNCTIILE DE VALIDARE  ------------------------------------------------------------//

    public boolean Defect(){
        read_senz();
        if (jdefectiuni)
            return true;
        else
            return false;
    }

    public boolean poate_fi_cu_zahar(){
        if (jcant_zahar >= 10){
            return true;
        }
        else{
            return false;
        }
            
    }

    boolean poate_fi_cump_cafea (double cant_cafea){
        if (jcant_caf - cant_cafea >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_lapte (double cant_lapte){
        if (jcant_lap - cant_lapte >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_cacao (){
        if (jcant_cacao - cant_cacao >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_apa (double cant_apa){
        if (jcant_apa - cant_apa >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_ceai (){
        if (jcant_ceai - cant_ceai >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_pahare_mici (){
        if(jnr_pahare_mici - 1 >= 0){
            return true;
        }
        else
            return false;
    }

    boolean poate_fi_cump_pahare_mari (){
        if(jnr_pahare_mari - 1 >= 0){
            return true;
        }
        else
            return false;
    }

    public boolean cumpara_produs(int i){
        boolean cant_coresp = true;
        boolean cant_coresp_lapte = true;
        boolean cant_coresp_apa = true;
        boolean nrpahare_coresp = true;
        String name = "";
        read_senz();
        switch(i){
            case 1:                     //espresso scurt
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if(!cant_coresp) name += warningMessages[0] + '\n';
                if(!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[6] + '\n';
                if(name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa_esp;
                    jnr_pahare_mici--;
                }
                break;
            case 5:                     //exspresso lung
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if (!cant_coresp) name += warningMessages[0] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa_esp*2;
                    jnr_pahare_mici--;
                }
                break;
            case 2:                     //cafea mica
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if (!cant_coresp) name += warningMessages[0] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa;
                    jnr_pahare_mici--;
                }
                break;
            case 6:                     //cafea mare
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                nrpahare_coresp = poate_fi_cump_pahare_mari();
                if (!cant_coresp) name += warningMessages[0] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (!nrpahare_coresp) name += warningMessages[5] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa_mare;
                    jnr_pahare_mari--;
                }
                break;
            case 3:                     //cafea cu lapte mica
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if (!cant_coresp) name += warningMessages[0] + '\n';
                if (!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa;
                    jcant_lap -= cant_lap1;
                    jnr_pahare_mici--;
                }
                break;
            case 7:                     //cafea cu lapte mare
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                nrpahare_coresp = poate_fi_cump_pahare_mari();
                if(!cant_coresp) name += warningMessages[0] + '\n';
                if(!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[5] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa_mare;
                    jcant_lap -= cant_lap2;
                    jnr_pahare_mari--;
                }
                break;
            case 4:                     //capuccino mic
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if (!cant_coresp) name += warningMessages[0] + '\n';
                if (!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa;
                    jcant_lap -= cant_lap2;
                    jnr_pahare_mici--;
                }
                break;
            case 8:                     //capuccino mare
                cant_coresp = poate_fi_cump_cafea(cant_caf);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                nrpahare_coresp = poate_fi_cump_pahare_mari();
                if(!cant_coresp) name += warningMessages[0] + '\n';
                if(!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[5] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_caf -= cant_caf;
                    jcant_apa -= cant_apa_mare;
                    jcant_lap -= cant_lap2;
                    jnr_pahare_mari--;
                }
                break;
            case 9:                     //cacao cu lapte
                cant_coresp = poate_fi_cump_cacao();
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if(!cant_coresp) name += warningMessages[4] + '\n';
                if(!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if(!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if(cant_coresp && cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_cacao -= cant_cacao;
                    jcant_apa -= cant_apa;
                    jcant_lap -= cant_lap1;
                    jnr_pahare_mici--;
                }
                break;
            case 10:                     //lapte
                nrpahare_coresp = poate_fi_cump_pahare_mici();    
                cant_coresp_lapte = poate_fi_cump_lapte(cant_lap3);
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                if(!cant_coresp_lapte) name += warningMessages[1] + '\n';
                if(!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if (name != "") warning(name);
                if (cant_coresp_apa && nrpahare_coresp && cant_coresp_lapte){
                    jcant_lap -= cant_lap3;
                    jcant_apa -= cant_apa;
                    jnr_pahare_mici--;
                }
                break;
            case 11:                     //ceai
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                cant_coresp = poate_fi_cump_ceai();
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if(!cant_coresp) name += warningMessages[3] + '\n';
                if (!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if (cant_coresp_apa && nrpahare_coresp && cant_coresp){
                    jcant_ceai -= cant_ceai;
                    jcant_apa -= cant_apa;
                    jnr_pahare_mici--;
                }
                break;
            case 12:                     //apa fiarta
                cant_coresp_apa = poate_fi_cump_apa(cant_apa_mare);
                nrpahare_coresp = poate_fi_cump_pahare_mici();
                if(!cant_coresp_apa) name += warningMessages[2] + '\n';
                if(!nrpahare_coresp) name += warningMessages[6] + '\n';
                if (name != "") warning(name);
                if (cant_coresp_apa && nrpahare_coresp){
                    jcant_apa -= cant_apa;
                    jnr_pahare_mici--;
                }
                break;
            default:
                break;
        }
        write_senz_new();
        if(!cant_coresp || !cant_coresp_apa || !cant_coresp_lapte || !nrpahare_coresp){
            return false;
        }
        return true;
    }

    private void Format_Warning(){
        warning.setLayout(new BorderLayout());
        warning.setSize(500, 200);
        warning.setResizable(false);
        warning.setLocationRelativeTo(null);

        warningText.setFont(new Font("Times New Roman", Font.BOLD, 20));
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

        OKBtn.setSize(20,40);
        Btn_Panel.add(OKBtn);
        warning.add(warningText, BorderLayout.CENTER);
        warning.add(Btn_Panel, BorderLayout.SOUTH);

    }

    public void setWarningText(String nume){
        warningText.setText(nume + '\n');
    }

    private void warning(String nume){
        warning.setVisible(true);
        setWarningText(nume);
        OKBtn.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                warning.dispose();
            }
        });
    }

}
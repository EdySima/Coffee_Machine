import javax.swing.*;

public class Application {

    public static void main(String[] args){
        SwingUtilities.invokeLater(new Runnable(){
            public void run(){
                new Simulator_Automat_Cafea();
            }
        });
    }
    
}
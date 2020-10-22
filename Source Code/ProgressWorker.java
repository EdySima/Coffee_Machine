import javax.swing.*;
import java.awt.*;

public class ProgressWorker extends SwingWorker<Object, Object> {

    @Override
    protected Object doInBackground() throws Exception {
        int i = 0;
        int max = 2000;

        while (i < max) {
            i += 10;
            int progress = Math.round(((float)i / (float)max) * 100f);
            setProgress(progress);
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}
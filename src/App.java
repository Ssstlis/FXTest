
import javafx.application.Application;
import javafx.stage.Stage;
import okhttp3.OkHttpClient;

import javax.swing.*;
import java.util.logging.Level;


public class App extends Application
{
    public static String[] args;
    public static void main(String[] args)
    {
        try
        {
            App.args = args;
            launch(args);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            System.exit(1);
        }
    }

    @Override
    public void start(Stage primaryStage) throws
                                          Exception
    {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                java.util.logging.Logger.getLogger(OkHttpClient.class.getName()).setLevel(Level.FINEST);
                System.setProperty("org.slf4j.simpleLogger.defaultLogLevel", "trace");
                System.setProperty("org.slf4j.simpleLogger.showDateTime", "true");
                System.setProperty("org.slf4j.simpleLogger.dateTimeFormat", "yyyy-MM-dd HH:mm:ss");
                //Turn off metal's use of bold fonts
                UIManager.put("swing.boldMetal", Boolean.FALSE);
                MainForm test = new MainForm("Test");
                test.setVisible(true);
            }
        });
    }
}

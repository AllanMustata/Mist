package model;

import javafx.stage.Stage;
import screen.DevScreen;

public class Developer {
    public static void openDev(String devname, Stage primaryStage)
    {
        DevScreen.Load_Dev_screen(primaryStage, devname);
        return;
    }
}

package model;

import javafx.stage.Stage;
import screen.CustomerScreen;

public class Customer {
    public static void openCustomer(String username, Stage primaryStage)
    {
        CustomerScreen.Load_Customer_Screen(primaryStage, username);
        return;
    }
}

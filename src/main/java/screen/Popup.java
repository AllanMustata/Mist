package screen;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Popup {
    public static void Display(String title, String description){
        Stage window=new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label=new Label(description);
        Button okbutton=new Button("OK");
        okbutton.setOnAction(e->window.close());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label, okbutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout, 300, 150);
        window.setScene(scene);
        window.showAndWait();

    }

    public static void Display(String title, String description, String buttonText){
        Stage window=new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label=new Label(description);
        Button okbutton=new Button(buttonText);
        okbutton.setOnAction(e->window.close());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label, okbutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout, 300, 150);
        window.setScene(scene);
        window.showAndWait();

    }

    public static void Display(String title, String description, String buttonText, int x, int y){
        Stage window=new Stage();

        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);

        Label label=new Label(description);
        Button okbutton=new Button(buttonText);
        okbutton.setOnAction(e->window.close());

        VBox layout=new VBox(10);
        layout.getChildren().addAll(label, okbutton);
        layout.setAlignment(Pos.CENTER);

        Scene scene=new Scene(layout, x, y);
        window.setScene(scene);
        window.showAndWait();
    }
}

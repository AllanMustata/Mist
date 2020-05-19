import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main  extends Application {
    public static void main(String[] args)
    {
        System.out.println("Mist game selling app");
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        DevScreen.Load_Dev_screen(stage);

        /*stage.setTitle("test for title");

        Button button=new Button("button here");
        button.setOnAction(e->PopupWindow.Display("Error", "Username or pasword wrong!"));

        StackPane layout=new StackPane();
        layout.getChildren().add(button);

        Scene scene=new Scene(layout, 500, 500);
        stage.setScene(scene);
        stage.show();*/
    }
}

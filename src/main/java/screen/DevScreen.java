package model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.FileReader;

public class DevScreen {
    public static void Load_Dev_screen(Stage primaryStage){



        //Parent root= FXMLLoader.load(getClass().getResource("fxml/games.fxml"));

        primaryStage.setTitle("Mist- Developer Mode");
        Scene scene_default, scene_add, scene_view;

        //Layout for main dev page with view or add games
        Label label=new Label("Welcome, what would you like to do?");

        Button add_game_button=new Button("Add Game");

        Button view_games_button=new Button("View My Games");

        Button back_button1=new Button("Back");
        Button back_button2=new Button("Back");

        VBox layout_main=new VBox(10);
        layout_main.getChildren().addAll(label, add_game_button, view_games_button);
        scene_default=new Scene(layout_main, 500,500);
        back_button1.setOnAction(e-> primaryStage.setScene(scene_default));
        back_button2.setOnAction(e-> primaryStage.setScene(scene_default));


        //Layout for add game

        //VBox layout_add=new VBox(10);
        Label label_add=new Label("Please input your game info:");

        GridPane grid=new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(5);
        grid.setHgap(10);

        GridPane.setConstraints(label_add, 0,0);
        GridPane.setConstraints(back_button1, 0,1);

        Label title_input=new Label("Game Title");
        GridPane.setConstraints(title_input, 0,2);
        Label genre_input=new Label("Game Genre");
        GridPane.setConstraints(genre_input, 0,3);
        Label price_input=new Label("Game Price");
        GridPane.setConstraints(price_input, 0,4);

        TextField pass_title=new TextField();
        pass_title.setPromptText("Title");
        TextField pass_genre=new TextField();
        pass_genre.setPromptText("Genre");
        TextField pass_price=new TextField();
        pass_price.setPromptText("Price");
        GridPane.setConstraints(pass_title, 1,2);
        GridPane.setConstraints(pass_genre, 1,3);
        GridPane.setConstraints(pass_price, 1,4);

        Button add_game_confirm_button=new Button("Add Game");
        GridPane.setConstraints(add_game_confirm_button, 1,5);

        grid.getChildren().addAll(back_button1, label_add, title_input,
                genre_input, price_input, pass_title, pass_genre, pass_price,
                add_game_confirm_button);
        /*layout_add.getChildren().addAll(back_button1, label_add, title_input,
                genre_input, price_input, pass_title, pass_genre, pass_price,
                add_game_confirm_button);*/

        scene_add=new Scene(grid, 500, 500);
        add_game_button.setOnAction(e-> primaryStage.setScene(scene_add));


        //Layout for view games

        VBox layout_view=new VBox(10);
        Label label_view=new Label("Not yet either, check vid for table");
        layout_view.getChildren().addAll(label_view, back_button2);
        scene_view=new Scene(layout_view, 500, 500);
        view_games_button.setOnAction(e-> primaryStage.setScene(scene_view));


        primaryStage.setScene(scene_default);
        primaryStage.show();

    }
}

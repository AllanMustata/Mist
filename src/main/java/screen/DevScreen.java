package screen;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.PopupWindow;
import javafx.stage.Stage;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DevScreen {

    private static Game game_aux;
    private static List<Game> game_list=new ArrayList<>(100);
    private static TableView<Game> table;

    public static void Load_Dev_screen(Stage primaryStage, String dev_name){

        String dev=dev_name;

        try{
            File games_info=new File("src/main/resources/database/GameList.db");
            Scanner reader=new Scanner((games_info));
            while(reader.hasNextLine()){

                game_aux=new Game();

                //Name of game
                game_aux.setTitle(reader.nextLine());
                //Genre
                if(reader.hasNextLine())
                    game_aux.setGenre(reader.nextLine());
                //Price
                if(reader.hasNextLine())
                    game_aux.setPrice(Double.parseDouble(reader.nextLine()));
                //Owner
                if(reader.hasNextLine())
                    game_aux.setOwner(reader.nextLine());

                //Adding game to dev's list of games
                try {
                    if (game_aux.getOwner().equals(dev))
                        game_list.add(game_aux);
                }catch(NullPointerException e){

                }

            }
            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }


        primaryStage.setTitle("Mist- Developer Mode");
        Scene scene_default, scene_add, scene_view;

        //Layout for main dev page with view or add games
        Label label=new Label("Welcome, "+dev+" ,what would you like to do?");

        Button add_game_button=new Button("Add Game");

        Button view_games_button=new Button("View My Games");

        Button back_button1=new Button("Back");
        Button back_button2=new Button("Back");

        VBox layout_main=new VBox(10);

        layout_main.getChildren().addAll(label, add_game_button, view_games_button);
        layout_main.setPadding(new Insets(10,10,10,10));
        scene_default=new Scene(layout_main, 500,500);
        back_button1.setOnAction(e-> primaryStage.setScene(scene_default));


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

        Button add_game_confirm_button=new Button("Release Game");
        GridPane.setConstraints(add_game_confirm_button, 1,5);

        grid.getChildren().addAll(back_button1, label_add, title_input,
                genre_input, price_input, pass_title, pass_genre, pass_price,
                add_game_confirm_button);
        /*layout_add.getChildren().addAll(back_button1, label_add, title_input,
                genre_input, price_input, pass_title, pass_genre, pass_price,
                add_game_confirm_button);*/

        scene_add=new Scene(grid, 500, 500);
        add_game_button.setOnAction(e-> primaryStage.setScene(scene_add));

        add_game_confirm_button.setOnAction(event -> {
            if(Validate_Game_Info(pass_title.getText(), pass_genre.getText(),
                                                      pass_price.getText())) {
                game_aux=new Game();
                game_aux.setTitle(pass_title.getText());
                game_aux.setGenre(pass_genre.getText());
                game_aux.setPrice(Double.parseDouble(pass_price.getText()));
                game_list.add(game_aux);
                Popup.Display("Game uploaded","Your game has been uploaded to out database");

                try{
                    BufferedWriter writer=new BufferedWriter(new FileWriter(
                            "src/main/resources/database/GameList.db", true));
                    writer.write(pass_title.getText()+"\n"+ pass_genre.getText()+
                            "\n"+pass_price.getText()+"\n"+dev+"\n");
                    writer.close();
                }catch (IOException e){
                    e.printStackTrace();
                }

            }
            else {
                Popup.Display("Game info wrong", "Price must be a number. Title must be unique");
            }
        });


        //Layout for view games


        //Columns for the table
        TableColumn<Game, String> title_column=new TableColumn<>("Game title");
        title_column.setMinWidth(100);
        title_column.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Game, String> genre_column=new TableColumn<>("Game genre");
        genre_column.setMinWidth(80);
        genre_column.setCellValueFactory(new PropertyValueFactory<>("genre"));

        TableColumn<Game, String> price_column=new TableColumn<>("Game price");
        price_column.setMinWidth(50);
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        back_button2.setOnAction(e-> primaryStage.setScene(scene_default));

        table=new TableView<>();
        table.setItems(Get_Games());
        table.getColumns().addAll(title_column, genre_column, price_column);

        VBox layout_view=new VBox(10);
        Label label_view=new Label("Here is the info about your games, "+dev);
        label_view.setPadding(new Insets(10,10,10,10));
        layout_view.getChildren().addAll(label_view, back_button2, table);

        scene_view=new Scene(layout_view, 500, 500);
        view_games_button.setOnAction(e-> primaryStage.setScene(scene_view));


        primaryStage.setScene(scene_default);
        primaryStage.show();

    }

    private static boolean Validate_Game_Info(String name, String genre, String price) {
        try{
            double price_is_double=Double.parseDouble(price);

        }catch (NumberFormatException e){
            return  false;
        }

        for(Game g : game_list)
            if(g.getTitle().equals(name))
                return false;
        return true;
    }
    public static ObservableList<Game> Get_Games(){
        ObservableList<Game> my_games= FXCollections.observableArrayList();
        for(Game game : game_list){
            my_games.add(game);
        }
        return my_games;
    }
}

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
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DevScreen {

    private static Game game_aux;
    private static List<Game> game_list=new ArrayList<>(100);
    private static TableView<Game> table;

    public static void Load_Dev_screen(Stage primaryStage, String dev_name){

        String dev=dev_name;

        //Reads all games
        Read_From_File(dev);



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
                game_aux.setTitle(pass_title.getText().replaceAll("\\s", ""));
                game_aux.setGenre(pass_genre.getText());
                game_aux.setPrice(Double.parseDouble(pass_price.getText()));
                //Copies sold and rating will automatically be randomized
                game_list.add(game_aux);

                Popup.Display("Game uploaded",
                        "Your game has been uploaded to out database");

                try{
                    BufferedWriter writer=new BufferedWriter(new FileWriter(
                            "src/main/resources/database/GameList.db", true));
                    writer.write(pass_title.getText().replaceAll("\\s","")
                            +"\n"+dev+"\n");
                    writer.flush();
                    writer.close();

                    BufferedWriter game_writer=new BufferedWriter(new FileWriter(
                            "src/main/resources/database/"+
                                    game_aux.getTitle()
                                    +".db",
                            true));

                    game_writer.write(
                            pass_title.getText().replaceAll("\\s","")
                            +"\n"+ pass_genre.getText()+
                            "\n"+pass_price.getText()+"\n"+game_aux.getCopies_sold()+
                            "\n"+game_aux.getRating()+"\n"+dev+"\n");
                    game_writer.flush();
                    game_writer.close();

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

        TableColumn<Game, String> copies_column=new TableColumn<>("Total copies sold");
        copies_column.setMinWidth(50);
        copies_column.setCellValueFactory(new PropertyValueFactory<>("copies_sold"));

        TableColumn<Game, String> rating_column=new TableColumn<>("Avg Rating");
        rating_column.setMinWidth(50);
        rating_column.setCellValueFactory(new PropertyValueFactory<>("rating"));

        back_button2.setOnAction(e-> primaryStage.setScene(scene_default));

        table=new TableView<>();
        table.setItems(Get_Games(dev));
        table.getColumns().addAll(title_column, genre_column,
                price_column, copies_column, rating_column);

        VBox layout_view=new VBox(10);


        Label label_view=new Label("Here is the info about your games, "+dev);
        Label edit_game_label=new Label("In case you'd like to edit your game");
        TextField pass_edit=new TextField();
        pass_edit.setPromptText("Name of game to edit");
        Button edit_button=new Button("Edit game");

        label_view.setPadding(new Insets(10,10,10,10));
        layout_view.getChildren().addAll(label_view, back_button2, edit_game_label,
                pass_edit, edit_button, table);

        edit_button.setOnAction(e->{



            Change_Stuff(pass_edit.getText(), dev);
        });

        scene_view=new Scene(layout_view, 500, 500);
        view_games_button.setOnAction(e-> primaryStage.setScene(scene_view));


        primaryStage.setScene(scene_default);
        primaryStage.show();

    }



    private static void Change_Stuff(String game_title, String dev) {
        boolean found=false;
        try{
            if(Files.exists(Paths.get("src/main/resources/database/" + game_title
                    + ".db")))

                for(Game g : game_list)
                    if(g.getTitle().equals(game_title)){

                        //list
                        found=true;

                        Stage window=new Stage();

                        window.initModality(Modality.APPLICATION_MODAL);
                        window.setTitle("Editting Game");

                        Label label=new Label("You may change "+game_title+
                                "'s genre and price");
                        Button okbutton=new Button("OK");

                        TextField pass_new_genre=new TextField(g.getGenre());
                        TextField pass_new_price=new TextField(String.valueOf(g.getPrice()));

                        okbutton.setOnAction(e->{
                            g.setGenre(pass_new_genre.getText());
                            g.setPrice(Double.parseDouble(pass_new_price.getText()));
                            Popup.Display("Game edited", "Please refresh the app to save changes");
                        });

                        VBox layout=new VBox(10);
                        layout.getChildren().addAll(label, pass_new_genre,
                                pass_new_price, okbutton);
                        layout.setAlignment(Pos.CENTER);

                        Scene scene=new Scene(layout, 300, 300);
                        window.setScene(scene);
                        window.showAndWait();


                        BufferedWriter edit_file_writer=new BufferedWriter(new FileWriter(
                                "src/main/resources/database/" + game_title
                                        + ".db", false));
                        edit_file_writer.write(g.getTitle()+"\n"+g.getGenre()+"\n"+
                                g.getPrice()+"\n"+g.getCopies_sold()+"\n"+g.getRating()+
                                "\n"+dev);

                        edit_file_writer.close();
                    }

            if(!found)
                Popup.Display("Uh oh!", "No game with that name");

        } catch (IOException e){

        }
    }

    private static void Read_From_File(String dev) {
        try{
            File games_info=new File("src/main/resources/database/GameList.db");
            Scanner reader=new Scanner((games_info));
            while(reader.hasNextLine()){

                String path_name=reader.nextLine();
                if(Files.exists(Paths.get("src/main/resources/database/" +
                        path_name.replaceAll("\\s","") + ".db"))) {

                    File individual_game = new File("src/main/resources/database/" +
                            path_name.replaceAll("\\s","")
                            + ".db");

                    Scanner game_reader=new Scanner(individual_game);

                    game_aux = new Game();

                    //Name of game
                    game_aux.setTitle(game_reader.nextLine());
                    //Genre
                    if (game_reader.hasNextLine())
                        game_aux.setGenre(game_reader.nextLine());
                    //Price
                    if (game_reader.hasNextLine())
                        game_aux.setPrice(Double.parseDouble(game_reader.nextLine()));
                    //Copies sold
                    if (game_reader.hasNextLine())
                        game_aux.setCopies_sold(Integer.parseInt(game_reader.nextLine()));
                    //Avg rating
                    if (game_reader.hasNextLine())
                        game_aux.setRating(Double.parseDouble(game_reader.nextLine()));
                    //Owner
                    if (game_reader.hasNextLine())
                        game_aux.setOwner(game_reader.nextLine());

                    //Adding game to dev's list of games
                    try {
                        if (game_aux.getOwner().equals(dev))
                            game_list.add(game_aux);
                    } catch (NullPointerException e) {

                    }
                    game_reader.close();
                }
            }

            reader.close();
        }catch (FileNotFoundException e){
            System.out.println("File not found");
            e.printStackTrace();
        }
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

    public static ObservableList<Game> Get_Games(String dev) {
        ObservableList<Game> my_games = FXCollections.observableArrayList();
        for (Game game : game_list)
            my_games.add(game);
        return my_games;
    }
}

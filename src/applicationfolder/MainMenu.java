package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class MainMenu extends TestGUI {
    private UserTest ut = new UserTest();

    public void appearance_menu(Stage primaryStage) {
        TestGUI testGUI = new TestGUI();
//

        BorderPane root = new BorderPane();
        VBox buttom_space = new VBox(15);
        buttom_space.setAlignment(Pos.CENTER);
        //////////////////////////Background_menu//////////////////////////////////////////
        Image menuBackground = new Image(getClass().getClassLoader().getResourceAsStream("images/background1.png"));
        ImageView menuBackgroundView = new ImageView(menuBackground);
        menuBackgroundView.setFitHeight(menuBackground.getHeight());
        menuBackgroundView.setFitWidth(menuBackground.getWidth());

        root.setBackground(new Background(new BackgroundImage(menuBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////Button////////////////////////////////////////////////////////
        Button button = new Button("Go Testing");
        button.getStyleClass().add("button");
        Button button1 = new Button("Go study");
        button1.getStyleClass().add("button1");
        Button button2 = new Button("Settings");
        button2.getStyleClass().add("button2");
        Button button3 = new Button("Exit");
        button3.getStyleClass().add("button3");


        buttom_space.getChildren().addAll(button, button1, button2, button3);
        root.setCenter(buttom_space);
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////Add_function_button////////////////////////////////////////////
        button.setOnAction(event -> {
            primaryStage.close();
            testGUI.playtest(primaryStage, "/text.txt", true);
        });
        button1.setOnAction(event -> {
            String s = ut.chooser(primaryStage);
            primaryStage.close();
            testGUI.playtest(primaryStage, s, false);
        });
        button2.setOnAction(event -> {
            /*try {
                primaryStage.close();
                testGUI.playtest(primaryStage);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }*/
        });
        button3.setOnAction(event -> {
            primaryStage.close();
            System.exit(0);
        });
        /////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////Create scene////////////////////////////////////////
        final Scene scene = new Scene(root, menuBackground.getWidth(), menuBackground.getHeight());
        /////////////////////////////////////////////////////////////////////////////////////////

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
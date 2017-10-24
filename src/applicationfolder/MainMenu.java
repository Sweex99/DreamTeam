package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.*;
import java.io.*;


public class MainMenu extends TestGUI {
    private UserTest ut = new UserTest();
    TestCreator TC = new TestCreator();
    applicationSound sound = new applicationSound();


    public void appearanceMenu(Stage primaryStage) {
        TestGUI testGUI = new TestGUI();
//

        BorderPane root = new BorderPane();
        VBox buttomSpace = new VBox(15);
        buttomSpace.setAlignment(Pos.CENTER);
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
        Button goTesting = new Button("Go Testing");
        goTesting.getStyleClass().add("button1");
        Button goStudy = new Button("Go study");
        goStudy.getStyleClass().add("button2");
        Button settings = new Button("Settings");
        settings.getStyleClass().add("button3");
        Button exit = new Button("Exit");
        exit.getStyleClass().add("button4");

        goTesting.setShape(new Circle(20));

        buttomSpace.getChildren().addAll(goTesting, goStudy, settings, exit);
        root.setCenter(buttomSpace);
        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////Add_function_button////////////////////////////////////////////
        goTesting.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            testGUI.playtest(primaryStage, "/text.txt", true);

        });
        goStudy.setOnAction(event -> {
            sound.clickSound();
            String s = ut.chooser(primaryStage);
            primaryStage.close();
            testGUI.playtest(primaryStage, s, false);
        });
        settings.setOnAction(event -> {
            sound.clickSound();
//            primaryStage.close();
//            TC.create_window(primaryStage);
        });
        exit.setOnAction(event -> {
            sound.clickSound();
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
package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainMenu extends TestGUI {
    private UserTest ut = new UserTest();
    TestCreator TC = new TestCreator();
    ApplicationSound sound = new ApplicationSound();
    StackPane root = new StackPane();

    public Image mb(String name) {
        Image menuBackground = new Image(getClass().getClassLoader().getResourceAsStream(name));
        ImageView menuBackgroundView1 = new ImageView(menuBackground);
        menuBackgroundView1.setFitHeight(menuBackground.getHeight());
        menuBackgroundView1.setFitWidth(menuBackground.getWidth());
        return menuBackground;
    }
    public void putMenuBackground( String fileName){
        root.setBackground(new Background(new BackgroundImage(mb(fileName),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }

    public void appearanceMenu(Stage primaryStage) {
        TestGUI testGUI = new TestGUI();
        ////////////////////////////////////add_background/////////////////////////////////////////////
        putMenuBackground("images/Main_menu.png");
        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////Button_option////////////////////////////////////////////////////////
        Button goTesting = new Button("Go Testing");
        goTesting.getStyleClass().add("button1");
        Button goStudy = new Button("Go study");
        goStudy.getStyleClass().add("button2");
        Button settings = new Button("Settings");
        settings.getStyleClass().add("button3");
        Button exit = new Button("Exit");
        exit.getStyleClass().add("button4");
        Button user = new Button("User Test");
        user.getStyleClass().add("button1");

        exit.setShape(new Circle(20));

        root.getChildren().addAll(goTesting,goStudy,settings,user,exit);

        goTesting.setTranslateX(-195);
        goTesting.setTranslateY(-130);

        goStudy.setTranslateX(210);
        goStudy.setTranslateY(-128);

        settings.setTranslateX(210);
        settings.setTranslateY(130);

        user.setTranslateX(-197);
        user.setTranslateY(135);

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////Add_event_button////////////////////////////////////////////
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
            primaryStage.close();
            TC.create_window(primaryStage);
        });
        exit.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            System.exit(0);
        });
        goTesting.setOnMouseExited(event -> {
            putMenuBackground("images/Main_menu.png");
        });
        goTesting.setOnMouseEntered(event -> {
            putMenuBackground("images/Main_menu2.png");
        });
        goStudy.setOnMouseExited(event -> {
            putMenuBackground("images/Main_menu.png");
        });
        goStudy.setOnMouseEntered(event -> {
            putMenuBackground("images/Main_menu3.png");

        });

        exit.setOnMouseExited(event -> {
            putMenuBackground("images/Main_menu.png");

        });
        exit.setOnMouseEntered(event -> {
            putMenuBackground("images/exit.png");

        });

        settings.setOnMouseExited(event -> {
            putMenuBackground("images/Main_menu.png");

        });
        settings.setOnMouseEntered(event -> {
            putMenuBackground("images/Main_menu4.png");

        });

        user.setOnMouseExited(event -> {
            putMenuBackground("images/Main_menu.png");

        });
        user.setOnMouseEntered(event -> {
            putMenuBackground("images/Main_menu5.png");

        });
        /////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////Create scene////////////////////////////////////////
        final Scene scene = new Scene(root, mb("images/Main_menu.png").getWidth(), mb("images/Main_menu.png").getHeight());
        /////////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////add_icon_and_include_css//////////////////////////////////////////
        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        //////////////////////////////////////////////////////////////////////////////////////////
    }
}
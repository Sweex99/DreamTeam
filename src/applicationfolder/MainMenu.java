package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MainMenu extends TestGUI {

    private UserTest userTest = new UserTest();
    private TestCreator createWindow = new TestCreator();
    private ApplicationSound sound = new ApplicationSound();
    private StackPane root = new StackPane();
    private AddBackgroundAnimation backgroundAnim = new AddBackgroundAnimation();
    private MenuLanguage menuLanguage = new MenuLanguage();
    private TestCreator testCreator = new TestCreator();



    public void appearanceMenu(Stage primaryStage) {


        TestGUI testGUI = new TestGUI();
        ////////////////////////////////////add_background/////////////////////////////////////////////
        backgroundAnim.putMenuBackground(root,"images/Main_menu.png");


        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////Button_option////////////////////////////////////////////////////////
        Button goTesting = new Button("Go Testing");
        goTesting.getStyleClass().add("button1");
        Button goStudy = new Button("Go study");
        goStudy.getStyleClass().add("button2");
        Button createTest = new Button("Create Test");
        createTest.getStyleClass().add("button3");
        Button exit = new Button("Exit");
        exit.getStyleClass().add("button4");
        Button user = new Button("User file Test");
        user.getStyleClass().add("button1");

        exit.setShape(new Circle(20));

        root.getChildren().addAll(goTesting,goStudy,createTest,user,exit);

        goTesting.setTranslateX(-195);
        goTesting.setTranslateY(-130);

        goStudy.setTranslateX(210);
        goStudy.setTranslateY(-128);

        createTest.setTranslateX(210);
        createTest.setTranslateY(130);

        user.setTranslateX(-197);
        user.setTranslateY(135);

        ////////////////////////////////////////////////////////////////////////////////////////////
        ////////////////////////Add_event_button////////////////////////////////////////////
        goTesting.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            //testGUI.playtest(primaryStage, "/text.txt", true);
            menuLanguage.menuLanguageBackground(primaryStage);
        });
        goStudy.setOnAction(event -> {
            sound.clickSound();
            String s = userTest.chooser(primaryStage);
            primaryStage.close();
            testGUI.playtest(primaryStage, s, false);
        });
        createTest.setOnAction(event -> {
            sound.clickSound();
            testCreator.createFileWindow(primaryStage);
            /*primaryStage.close();
            createWindow.createFileWindow(primaryStage);*/
        });
        exit.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            System.exit(0);
        });
        user.setOnAction(event -> {
            userTest.chooser(primaryStage);
        });

        backgroundAnim.animetionButton(goTesting,goStudy,exit,createTest,user,root);
        /////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////////////////////
        ///////////////////////////Create scene////////////////////////////////////////
        final Scene scene = new Scene(root, backgroundAnim.mb("images/Main_menu.png").getWidth(), backgroundAnim.mb("images/Main_menu.png").getHeight());
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
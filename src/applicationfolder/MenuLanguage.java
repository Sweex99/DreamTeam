package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MenuLanguage {
    private StackPane root = new StackPane();
    private BackgroundAnimation backgroundAnim = new BackgroundAnimation();
    private ApplicationSound sound = new ApplicationSound();
    private TestGUI testGUI = new TestGUI();

    public void menuLanguageBackground(Stage primaryStage) {
        MainMenu main = new MainMenu();


        backgroundAnim.putMenuBackground(root, "images/Main_menu.png");

        //////////////////////////////////button_settings////////////////////////////////////////////////
        Button java = new Button("Java");
        java.getStyleClass().add("button1");
        Button cpp = new Button("C++");
        cpp.getStyleClass().add("button2");
        Button web = new Button("HTML/CSS");
        web.getStyleClass().add("button3");
        Button back = new Button("Back");
        back.getStyleClass().add("back");
        Button php = new Button("PHP");
        php.getStyleClass().add("button1");

        root.getChildren().addAll(java, cpp, web, php, back);

        java.setTranslateX(-195);
        java.setTranslateY(-130);

        cpp.setTranslateX(210);
        cpp.setTranslateY(-128);

        web.setTranslateX(210);
        web.setTranslateY(130);

        php.setTranslateX(-197);
        php.setTranslateY(135);

        cpp.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            testGUI.playtest(primaryStage, "/text.txt", true);

        });/*
        goStudy.setOnAction(event -> {
            sound.clickSound();
            String s = userTest.chooser(primaryStage);
            primaryStage.close();
            testGUI.playtest(primaryStage, s, false);
        });
        settings.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            createWindow.createFileWindow(primaryStage);
        });*/
        back.setOnAction(event -> {
            main.appearanceMenu(primaryStage);
        });

        backgroundAnim.animationButton(root, java, cpp, back, web, php);
        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////create_scene/////////////////////////////////////////
        final Scene scene = new Scene(root, backgroundAnim.loadedImage("images/Main_menu.png").getWidth(), backgroundAnim.loadedImage("images/Main_menu.png").getHeight());

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MenuLanguage {
    private StackPane root = new StackPane();

    private ApplicationSound sound = new ApplicationSound();
    private TestGUI testGUI = new TestGUI();

    public void menuLanguageBackground(Stage primaryStage) {
        MainMenu main = new MainMenu();

        //////////////////////////////////button_settings////////////////////////////////////////////////
        Button java = new Button("Java");
        java.getStyleClass().add("button");
        Button cpp = new Button("C++");
        cpp.getStyleClass().add("button");
        Button web = new Button("HTML/CSS");
        web.getStyleClass().add("button");
        Button php = new Button("PHP");
        php.getStyleClass().add("button");
        Button back = new Button();
        back.getStyleClass().add("back");

        Label label1 = new Label();
        label1.getStyleClass().add("redLine");
        Label label2 = new Label();
        label2.getStyleClass().add("redLine");

        label1.setTranslateY(-130);
        label2.setTranslateY(130);


        Label label3 = new Label();
        label3.getStyleClass().add("redLine1");
        Label label4 = new Label();
        label4.getStyleClass().add("redLine1");

        label3.setTranslateX(160);
        label4.setTranslateX(-160);


        Label textLabel = new Label("Оберіть рівень складності");
        textLabel.setStyle("-fx-text-fill: white;");


        Button buton1 = new Button("easy");
        buton1.getStyleClass().add("button1");


        Button buton2 = new Button("middle");
        buton2.getStyleClass().add("button2");


        Button buton3 = new Button("hard");
        buton3.getStyleClass().add("button3");

        textLabel.setVisible(false);
        buton1.setVisible(false);
        buton2.setVisible(false);
        buton3.setVisible(false);

        back.setShape(new Circle(6));

        root.getStyleClass().add("background");

        root.getChildren().addAll(java, cpp, web, php, back, buton1, buton2, buton3, textLabel, label1, label2, label3, label4);

        textLabel.setTranslateY(-75);

        buton1.setTranslateY(-40);
        buton3.setTranslateY(40);

        java.setTranslateX(-195);
        java.setTranslateY(-130);

        cpp.setTranslateX(210);
        cpp.setTranslateY(-128);

        web.setTranslateX(210);
        web.setTranslateY(130);

        php.setTranslateX(-197);
        php.setTranslateY(135);

        back.setTranslateX(-430);
        back.setTranslateY(-280);


        java.setOnAction(event -> {
            textLabel.setVisible(true);
            animationMenu(primaryStage, java, buton1, buton2, buton3);

            //testGUI.playtest(primaryStage,"/texts/text.txt", true);
        });
        cpp.setOnAction(event -> {
            textLabel.setVisible(true);
            animationMenu(primaryStage, cpp, buton1, buton2, buton3);
        });
        web.setOnAction(event -> {
            textLabel.setVisible(true);
            animationMenu(primaryStage, web, buton1, buton2, buton3);
        });
        php.setOnAction(event -> {
            textLabel.setVisible(true);
            animationMenu(primaryStage, php, buton1, buton2, buton3);
        });
        back.setOnAction(event -> {
            primaryStage.close();
            main.appearanceMenu(primaryStage);
        });
        root.setOnMouseClicked(event -> {
            java.setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white");
            cpp.setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white");
            web.setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white");
            php.setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: white");
            textLabel.setVisible(false);
            buton1.setVisible(false);
            buton2.setVisible(false);
            buton3.setVisible(false);
        });

        /////////////////////////////////////////////////////////////////////////////////////////////////////////
        //////////////////////////////////////////////////////////create_scene/////////////////////////////////////////
        final Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void animationMenu(Stage primaryStage, Button... buttons) {

        buttons[0].setStyle("-fx-background-color: #d8d8d8;-fx-text-fill: black");

        buttons[1].setVisible(true);
        buttons[2].setVisible(true);
        buttons[3].setVisible(true);
        buttons[1].setOnAction(event1 -> {
        });
        buttons[2].setOnAction(event1 -> {
            testGUI.playTest(primaryStage,"/texts/text.txt", true);
        });
        buttons[3].setOnAction(event1 -> {
            sound.clickSound();
        });
    }
}

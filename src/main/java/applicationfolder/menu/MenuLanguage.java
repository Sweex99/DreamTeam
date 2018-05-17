package applicationfolder.menu;

import applicationfolder.utils.DataBaseDriver;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class MenuLanguage {
    private StackPane root = new StackPane();
    private TestGUI testGUI = new TestGUI();
    private static final String BUTTON_STYLE_CLASS = "button";
    private static final String BUTTON_STYLE = "-fx-background-color: #1d1d1d;-fx-text-fill: white";
    private static final String URL_TEXT = "/texts/";

    public void menuLanguageBackground(DataBaseDriver dataBaseDriver, Stage primaryStage) {
        MainMenu main = new MainMenu();

        //////////////////////////////////button_settings////////////////////////////////////////////////
        Button java = new Button("Java");
        java.getStyleClass().add(BUTTON_STYLE_CLASS);
        java.setId("btnJava");
        Button cpp = new Button("C++");
        cpp.getStyleClass().add(BUTTON_STYLE_CLASS);
        cpp.setId("btnCpp");
        Button web = new Button("HTML/CSS");
        web.getStyleClass().add(BUTTON_STYLE_CLASS);
        web.setId("btnWeb");
        Button php = new Button("PHP");
        php.getStyleClass().add(BUTTON_STYLE_CLASS);
        php.setId("btnPhp");
        Button back = new Button("Back");
        back.getStyleClass().add("back");
        back.setId("btnBack");

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
        buton1.setId("btnEasy");

        Button buton2 = new Button("middle");
        buton2.getStyleClass().add("button2");
        buton2.setId("btnMiddle");

        Button buton3 = new Button("hard");
        buton3.getStyleClass().add("button3");
        buton3.setId("btnHard");

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
            complexity(dataBaseDriver, primaryStage, "java", java, buton1, buton2, buton3);
            animationMenu(cpp, web, php);
        });
        cpp.setOnAction(event -> {
            textLabel.setVisible(true);
            complexity(dataBaseDriver, primaryStage, "cpp", cpp, buton1, buton2, buton3);
            animationMenu(java, web, php);
        });
        web.setOnAction(event -> {
            textLabel.setVisible(true);
            complexity(dataBaseDriver, primaryStage, "web", web, buton1, buton2, buton3);
            animationMenu(java, cpp, php);
        });
        php.setOnAction(event -> {
            textLabel.setVisible(true);
            complexity(dataBaseDriver, primaryStage, "php", php, buton1, buton2, buton3);
            animationMenu(java, cpp, web);
        });
        back.setOnAction(event -> {
            primaryStage.close();
            main.appearanceMenu(primaryStage);
        });
        root.setOnMouseClicked(event -> {
            java.setStyle(BUTTON_STYLE);
            cpp.setStyle(BUTTON_STYLE);
            web.setStyle(BUTTON_STYLE);
            php.setStyle(BUTTON_STYLE);
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
        primaryStage.setTitle("Меню вибору мов");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void complexity(DataBaseDriver dataBaseDriver, Stage primaryStage, String lang, Button... buttons) {

        buttons[0].setStyle("-fx-background-color: #d8d8d8;-fx-text-fill: black");

        buttons[1].setVisible(true);
        buttons[2].setVisible(true);
        buttons[3].setVisible(true);
        buttons[1].setOnAction(event1 -> testGUI.playTest(dataBaseDriver, primaryStage, URL_TEXT + lang+"/"+lang+"_easy.txt", true));
        buttons[2].setOnAction(event1 -> testGUI.playTest(dataBaseDriver, primaryStage, URL_TEXT + lang+"/"+lang+"_middle.txt", true));
        buttons[3].setOnAction(event1 -> testGUI.playTest(dataBaseDriver, primaryStage, URL_TEXT + lang+"/"+lang+"_hard.txt", true));
    }
    private void animationMenu(Button...buttons){
        for(int i = 0;i < 3; i++){
            buttons[i].setStyle("-fx-background-color: #1d1d1d;-fx-text-fill: #d8d8d8");
        }
    }
}

package applicationfolder;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import javax.swing.text.html.HTML;
import java.awt.*;

public class MainMenu {
    private UserTest userTest = new UserTest();
    private ApplicationSound sound = new ApplicationSound();
    private StackPane root = new StackPane();
    private MenuLanguage menuLanguage = new MenuLanguage();
    private TestCreator testCreator = new TestCreator();
    private TestGUI testGUI = new TestGUI();
    private WindowStudy windowStudy = new WindowStudy();
    private VBox vBox = new VBox();
    private HBox hBox = new HBox();

    public void appearanceMenu(Stage primaryStage) {
        root.getStyleClass().add("background");

        vBox.getStyleClass().add("accountInfo");

        ////////////////////////////////////////////////////////////////////////////////
        //////////////////////////Button_option////////////////////////////////////////////////////////
        Button goTesting = new Button("Go Testing");
        Label testTesting = new Label("Перейшовши по цій кнопці ви порпте на меню. Де ви можете вибрати мову по якій хочете пройти тест");
        testTesting.getStyleClass().add("labelTesting");
        goTesting.getStyleClass().add("button");

        Button goStudy = new Button("Go study");
        Label testStudy = new Label("Чогось не знаєте? Перейдіть по цій кнопці, та виберіть мову і тему яку ви не розумієте");
        goStudy.getStyleClass().add("button");
        testStudy.getStyleClass().add("labelStudy");

        Button createTest = new Button("Create Test");
        Label testCreateTest = new Label("Бажаєте провірити друга? Можете створити свої тести і дати пройти його своєму другові)))");
        createTest.getStyleClass().add("button");
        testCreateTest.getStyleClass().add("labelTesting");

        Button exit = new Button("");
        exit.getStyleClass().add("exit");

        Button settings = new Button("");
        settings.getStyleClass().add("settings");

        Button swapAccount = new Button("");
        swapAccount.getStyleClass().add("swapAccount");

        Button user = new Button("User file Test");
        Label testUser = new Label("Якщо у вас є готовий файл з тестами! То ви можете загрузити в програму перейшовши по цій кнопці");
        user.getStyleClass().add("button");
        testUser.getStyleClass().add("labelStudy");

        Label label12 = new Label();
        label12.getStyleClass().add("label2");

       /* swapAccount.setOnKeyPressed(event -> {
            if(KeyCode.getKeyCode("Enter") == )
        });*/

//        hBox.getChildren().add(exit);

        root.getChildren().addAll(testTesting);
        root.getChildren().addAll(testStudy);
        root.getChildren().addAll(testCreateTest);
        root.getChildren().addAll(testUser);
        root.getChildren().addAll(label12);
//        vBox.getChildren().addAll(root, hBox);



        testTesting.setWrapText(true);
        testStudy.setWrapText(true);
        testCreateTest.setWrapText(true);
        testUser.setWrapText(true);

        label12.setTranslateX(-380);
        label12.setTranslateY(-290);

        goTesting.setTranslateX(-110);
        goTesting.setTranslateY(-130);
        testTesting.setTranslateX(85);
        testTesting.setTranslateY(-130);

        goStudy.setTranslateX(120);
        goStudy.setTranslateY(-28);
        testStudy.setTranslateX(-68);
        testStudy.setTranslateY(-28);

        createTest.setTranslateX(-110);
        createTest.setTranslateY(70);
        testCreateTest.setTranslateX(86);
        testCreateTest.setTranslateY(70);


        user.setTranslateX(120);
        user.setTranslateY(165);
        testUser.setTranslateX(-68);
        testUser.setTranslateY(165);

        exit.setTranslateY(-289);
        exit.setTranslateX(-275);

        swapAccount.setTranslateY(-289);
        swapAccount.setTranslateX(-303);

        settings.setTranslateY(-289);
        settings.setTranslateX(-330);

        root.getChildren().addAll(goTesting, goStudy, createTest, user, exit, settings, swapAccount);

        exit.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            System.exit(0);
        });

        goTesting.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            menuLanguage.menuLanguageBackground(primaryStage);
        });
        goStudy.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            windowStudy.startStudy(primaryStage);
        });
        createTest.setOnAction(event -> {
            sound.clickSound();
            testCreator.createFileWindow(primaryStage);
        });
        exit.setOnAction(event -> {
            sound.clickSound();
            primaryStage.close();
            System.exit(0);
        });
        user.setOnAction(event -> {
            String pathOfFile = userTest.getPathOfChosenFile(primaryStage);
            if (pathOfFile != null)
                testGUI.playtest(primaryStage, pathOfFile, false);
        });

        final Scene scene = new Scene(root, 900, 600);


        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        primaryStage.setResizable(false);

        primaryStage.setTitle("Main Menu");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}


package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class WindowStudy {
    private Menu menuThemes = new Menu("Меню тем");
    private MenuBar menuBar = new MenuBar();

    private String getStudyFile(String fileName) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(fileName)))) {
            String strLine;
            while ((strLine = in.readLine()) != null) {
                sb.append(strLine);
                sb.append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString();
    }


    private MenuItem[] themeStudy(String... strings) {

        MenuItem[] menuItems = new MenuItem[strings.length];

        for (int i = 0; i < strings.length; i++) {
            menuItems[i] = new MenuItem(strings[i]);
            menuThemes.getItems().addAll(menuItems[i]);
        }
        return menuItems;
    }

    public void startStudy(Stage primaryStage) {
        /*
        *
        * Cpp
        *
         */
        Pane root = new Pane();
        TextArea textArea = new TextArea();
        Scene scene = new Scene(root, 800, 600);
        MainMenu mainMenu = new MainMenu();
        Menu menuExit = new Menu("Вихід");
        MenuItem[] menuItems;
        MenuItem itemExit = new MenuItem("Вихід");
        menuThemes.getStyleClass().add("menu");

        menuBar.getMenus().add(menuThemes);
        menuBar.getMenus().add(menuExit);
        menuExit.getItems().add(itemExit);

        menuBar.setPrefWidth(root.getWidth());

        menuItems = themeStudy("Тема1", "Тема2", "Тема3", "Тема4", "Тема5", "Тема6");

        menuItems[0].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text1.txt"));
        });
        menuItems[1].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text2.txt"));
        });
        menuItems[2].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text3.txt"));
        });
        menuItems[3].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text4.txt"));
        });
        menuItems[4].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text5.txt"));
        });
        menuItems[5].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/text6.txt"));
        });
        itemExit.setOnAction(event -> {
            mainMenu.appearanceMenu(primaryStage);
        });


        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setMinSize(scene.getWidth(), scene.getHeight());
        textArea.getStyleClass().add("textArea");
        textArea.setTranslateY(20);


        root.getChildren().addAll(textArea, menuBar);


        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        primaryStage.setResizable(false);
        primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Навчання");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

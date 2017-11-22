package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.*;
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
        Stage stage = new Stage();
        Pane root = new Pane();
        TextArea textArea = new TextArea();
        Scene scene = new Scene(root, 900, 600);
        MainMenu mainMenu = new MainMenu();
        Menu menuExit = new Menu("Вихід");
        MenuItem[] menuItems;
        MenuItem itemExit = new MenuItem("Вихід");
        menuThemes.getStyleClass().add("menu");

        menuBar.getMenus().addAll(menuThemes, menuExit);

        menuExit.getItems().add(itemExit);

        menuBar.setPrefWidth(root.getWidth());

        menuItems = themeStudy("Алгоритм", "Граф основні поняття та визначення", "Дек", "Дерева", "Динамічні масиви даних",
                "Динамічні структури даних", "Класифікація структур даних", "Контейнер List", "Контейнер Set");

        menuItems[0].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme1.txt"));
        });
        menuItems[1].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme2.txt"));
        });
        menuItems[2].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme3.txt"));
        });
        menuItems[3].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme4.txt"));
        });
        menuItems[4].setOnAction(event -> textArea.setText(getStudyFile("/study/cpp/theme5.txt")));
        menuItems[5].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme6.txt"));
        });
        menuItems[6].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme7.txt"));
        });
        menuItems[7].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme8.txt"));
        });
        menuItems[8].setOnAction(event -> {
            textArea.setText(getStudyFile("/study/cpp/theme9.txt"));
        });
        itemExit.setOnAction(event -> {
            stage.close();
            mainMenu.appearanceMenu(primaryStage);
        });

        textArea.setWrapText(true);
        textArea.setEditable(false);
        textArea.setMinSize(scene.getWidth(), scene.getHeight());
        textArea.getStyleClass().add("textArea");
        textArea.setTranslateY(30);

        root.getChildren().addAll(textArea, menuBar);

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png");
        stage.setResizable(false);
        stage.getIcons().add(ico);
        stage.setTitle("Навчання");
        stage.setScene(scene);
        stage.show();
    }
}

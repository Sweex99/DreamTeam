package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class WindowStudy {
    private MenuLanguage menuLanguage = new MenuLanguage();
    private BackgroundAnimation backgroundAnim = new BackgroundAnimation();
    private Menu menu = new Menu("Меню тем");
    private MenuBar menuBar = new MenuBar();
    private ApplicationSound sound = new ApplicationSound();

    private void linkStudy(String fileName) {
        StringBuilder sb = new StringBuilder();


        File file = new File(fileName);
        try {
            //Объект для чтения файла в буфер

            try (BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()))) {
                //В цикле построчно считываем файл
                String s;
                while ((s = in.readLine()) != null) {
                    sb.append(s);
                    sb.append("\n");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private MenuItem[] themeStudy(String... strings) {

        MenuItem[] menuItems = new MenuItem[strings.length];

        for (int i = 0; i < strings.length; i++) {
            menuItems[i] = new MenuItem(strings[i]);
            menu.getItems().addAll(menuItems[i]);
        }
        return menuItems;
    }

    public void startStudy(Stage primaryStage) {
        Pane root = new Pane();
        TextArea textArea = new TextArea();
        Scene scene = new Scene(root, 800, 600);
        MenuItem[] menuItems;
        menu.getStyleClass().add("menu");

        Region spacer = new Region();
        spacer.getStyleClass().add("menu-bar");
        HBox.setHgrow(spacer, Priority.SOMETIMES);
//        HBox menuBars = new HBox(menuBar, spacer);

        menuBar.getMenus().addAll(menu);

        menuBar.setPrefWidth(root.getWidth());

        menuItems = themeStudy("Коля", "Бабай", "Не чіпай мене", "flux", "block", "goodHunter", "String");

        for(MenuItem item : menuItems) {
            item.setOnAction(event -> {
                linkStudy(getClass().getResourceAsStream("/text.txt").toString());
            });
        }


        textArea.setWrapText(true);
        //textArea.setEditable(false);
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

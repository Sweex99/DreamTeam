package applicationfolder.menu;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.*;

public class WindowStudy {

    private MenuBar menuBar = new MenuBar();
    private File studyFolder = new File("study");

    WindowStudy() {
        File folder = new File("study");
        if (!folder.exists()) {
            folder.mkdir();
        }
    }

    private String getStudyFile(InputStream inputStream) {
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"))) {
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

    private String[] getFolderFileNames(File folder) {
        try {
            int i = 0;
            File[] folderEntries = folder.listFiles();
            String[] names = new String[folderEntries.length];
            for (File entry : folderEntries) {
                names[i] = entry.getName();
                names[i] = names[i].replaceAll(".txt", "");
                i++;
            }
            return names;
        } catch (NullPointerException e) {
            throw new RuntimeException(e);
        }
    }

    private MenuItem[] themeStudy(TextArea textArea, String lang, String... themeNames) {
        MenuItem[] menuItems = new MenuItem[themeNames.length];
        boolean isResource = !lang.equals("");

        for (int i = 0; i < themeNames.length; i++) {
            menuItems[i] = new MenuItem(themeNames[i]);
            String file = themeNames[i];
            menuItems[i].setOnAction(event -> {
                try {
                    textArea.setText(getStudyFile(isResource ?
                            getClass().getResourceAsStream("/study/" + lang + "/" + file + ".txt")
                            : new FileInputStream("study/" + file + ".txt")));
                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        return menuItems;
    }

    public void startStudy(Stage primaryStage) {
        Stage stage = new Stage();
        Pane root = new Pane();
        TextArea textArea = new TextArea();
        Scene scene = new Scene(root, 900, 600);
        MainMenu mainMenu = new MainMenu();
        MenuItem[] menuItems;
        MenuItem[] menuItemsUser;
        MenuItem itemExit = new MenuItem("Вихід");

        Menu menuThemes = new Menu("Меню тем");
        Menu menuUser = new Menu("Користувацькі теми");
        Menu menuExit = new Menu("Вихід");

        menuThemes.getStyleClass().add("menu");

        menuBar.getMenus().addAll(menuThemes, menuUser, menuExit);
        menuBar.setPrefWidth(root.getWidth());

        menuItems = themeStudy(textArea, "cpp", "theme1", "theme2", "theme3"); //замінити теми на пошук файлів по папці
        menuItemsUser = themeStudy(textArea, "", getFolderFileNames(studyFolder));
        itemExit.setOnAction(event -> {
            stage.close();
            mainMenu.appearanceMenu(primaryStage);
        });

        menuThemes.getItems().addAll(menuItems);
        menuUser.getItems().addAll(menuItemsUser);
        menuExit.getItems().add(itemExit);

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

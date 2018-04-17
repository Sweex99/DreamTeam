package applicationfolder;

import applicationfolder.menu.MainMenu;
import applicationfolder.menu.SoundMedia;
import applicationfolder.utils.DataBaseConnect;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        MainMenu mainMenu = new MainMenu();
        mainMenu.authorization(primaryStage);

        Image ico = new Image("/images/main_icon3.png");
        primaryStage.getIcons().add(ico);

    }
    public static void main(String[] args) { launch(args); }
}


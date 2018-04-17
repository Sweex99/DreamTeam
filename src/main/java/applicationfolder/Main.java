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
       /* SoundMedia sound = new SoundMedia();
        sound.start(primaryStage);*/
       /* DataBaseConnect dataBaseConnect = new DataBaseConnect();
        if(dataBaseConnect.authorization("Maks", "password")){
            System.out.print("Good" + "\n");
            System.out.print(dataBaseConnect.user[5]);
        }*/
        MainMenu mainMenu = new MainMenu();
        mainMenu.authorization(primaryStage);

        Image ico = new Image("/images/main_icon3.png");
        primaryStage.getIcons().add(ico);
        //Головна функція
    }
    public static void main(String[] args) { launch(args); }
}


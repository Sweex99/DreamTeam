package applicationfolder;

import applicationfolder.menu.SoundMedia;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage){
        SoundMedia sound = new SoundMedia();
        sound.start(primaryStage);
        Image ico = new Image("/images/main_icon3.png");
        primaryStage.getIcons().add(ico);
    }

    public static void main(String[] args) { launch(args); }
}

package applicationfolder;

import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
       /* MainMenu menu = new MainMenu();
        menu.appearanceMenu(primaryStage);*/

       WindowStudy windowStudy = new WindowStudy();
       windowStudy.startStudy(primaryStage);

        Image ico = new Image("/images/main_icon3.png");
        primaryStage.getIcons().add(ico);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
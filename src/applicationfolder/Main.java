package applicationfolder;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception{
        TestGUI testGUI = new TestGUI();
        testGUI.playtest(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
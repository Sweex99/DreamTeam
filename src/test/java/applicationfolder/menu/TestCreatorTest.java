package applicationfolder.menu;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;

public class TestCreatorTest extends ApplicationTest {
    private Scene scene;

//    @Test
//    public void test() throws Exception {
//        Parent root = scene.getRoot();
//        Button button = from(root).lookup("Add").queryButton();
//        clickOn(button);
//    }
//
//    @Test
//    public void y() throws Exception {
//        Parent root = scene.getRoot();
//        Button button = from(root).lookup("Save").queryButton();
//        clickOn(button);
//    }

    @Test
    public void t() throws Exception {
        Parent root = scene.getRoot();
        clickOn("");
        write("Hello");
    }
    
    @Override
    public void start(Stage stage) throws Exception {
        TestCreator testCreator = new TestCreator();
        testCreator.createFileWindow(stage);
        //scene = testCreator.getScene();
        stage.toFront();
    }
}
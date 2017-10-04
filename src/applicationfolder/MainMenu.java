package applicationfolder;

import com.sun.javafx.css.Size;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.io.IOException;
import java.util.Collection;

import static javafx.application.Application.launch;

public class  MainMenu extends TestGUI {

    public void appearance_menu (Stage primaryStage) throws IOException  {
        BorderPane root = new BorderPane();
        VBox buttom_space = new VBox(15);
        buttom_space.setAlignment(Pos.CENTER);
        //Background_menu/////////////////////////////////////////////////////////
        Image menuBackground = new Image(getClass().getClassLoader().getResourceAsStream("images/background1.png"));
        ImageView menuBackgroundView = new ImageView(menuBackground);
        menuBackgroundView.setFitHeight(menuBackground.getHeight());
        menuBackgroundView.setFitWidth(menuBackground.getWidth());

        root.setBackground(new Background(new BackgroundImage(menuBackground,
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
        ////////////////////////////////////////////////////////////////////////////////
        /////////////////////////Button////////////////////////////////////////////////////////
        Button button = new Button("Go Testing");
        button.getStyleClass().add("button");
        Button button1 = new Button("Go study");
        button1.getStyleClass().add("button1");
        Button button2 = new Button("Settings");
        button2.getStyleClass().add("button2");
        Button button3 = new Button("Exit");
        button3.getStyleClass().add("button3");

        buttom_space.getChildren().addAll(button, button1, button2, button3);
        root.setCenter(buttom_space);

        Label label = new Label("[eqsdfonobnornbienvinqevnqenvoernvnre");
        buttom_space.getChildren().addAll(label);
        ///////////////////////////////////////////////////////////////////////////////////////
        //Create scene
        final Scene scene = new Scene(root, menuBackground.getWidth(), menuBackground.getHeight());
        /////////////////////////////////////////////////////////////////////////////////////////

        scene.getStylesheets().add("/css/style.css");
        Image ico = new Image("images/main_icon3.png"); primaryStage.getIcons().add(ico);
        primaryStage.setTitle("Main Menu");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}


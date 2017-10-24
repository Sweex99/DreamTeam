package applicationfolder;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class AddBackgroundAnimation {
    private ApplicationSound sound = new ApplicationSound();


    public Image mb(String name) {
        Image menuBackground = new Image(getClass().getClassLoader().getResourceAsStream(name));
        ImageView menuBackgroundView1 = new ImageView(menuBackground);
        menuBackgroundView1.setFitHeight(menuBackground.getHeight());
        menuBackgroundView1.setFitWidth(menuBackground.getWidth());
        return menuBackground;
    }
    public void putMenuBackground(StackPane root,String fileName){
        root.setBackground(new Background(new BackgroundImage(mb(fileName),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }
    public void animetionButton(Button button1,Button button2,Button exit,Button button3,Button button4,StackPane root) {
        button1.setOnMouseExited(event -> {
            putMenuBackground(root,"images/Main_menu.png");

        });
        button1.setOnMouseEntered(event -> {
            putMenuBackground(root,"images/Main_menu2.png");
            sound.clickSound();
        });
        button2.setOnMouseExited(event -> {
           putMenuBackground(root,"images/Main_menu.png");
        });
        button2.setOnMouseEntered(event -> {
            putMenuBackground(root,"images/Main_menu3.png");
            sound.clickSound();
        });

        exit.setOnMouseExited(event -> {
            putMenuBackground(root,"images/Main_menu.png");

        });
        exit.setOnMouseEntered(event -> {
            putMenuBackground(root,"images/exit.png");
            sound.clickSound();
        });

        button3.setOnMouseExited(event -> {
            putMenuBackground(root,"images/Main_menu.png");

        });
        button3.setOnMouseEntered(event -> {
            putMenuBackground(root,"images/Main_menu4.png");
            sound.clickSound();
        });

        button4.setOnMouseExited(event -> {
            putMenuBackground(root,"images/Main_menu.png");

        });
        button4.setOnMouseEntered(event -> {
            putMenuBackground(root,"images/Main_menu5.png");
            sound.clickSound();
        });
    }
}

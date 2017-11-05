package applicationfolder;

import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class BackgroundAnimation {
    private ApplicationSound sound = new ApplicationSound();


    public Image loadedImage(String name) {
        Image menuBackground = new Image(getClass().getClassLoader().getResourceAsStream(name));
        ImageView menuBackgroundView1 = new ImageView(menuBackground);
        menuBackgroundView1.setFitHeight(menuBackground.getHeight());
        menuBackgroundView1.setFitWidth(menuBackground.getWidth());
        return menuBackground;
    }

    public void putMenuBackground(StackPane root, String fileName) {
        root.setBackground(new Background(new BackgroundImage(loadedImage(fileName),
                BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT)));
    }

    public void animationButton(StackPane root, Button...buttons) {
        buttons[0].setOnMouseExited(event -> {
            putMenuBackground(root, "images/Main_menu.png");

        });
        buttons[0].setOnMouseEntered(event -> {
            putMenuBackground(root, "images/Main_menu2.png");
            sound.clickSound();
        });
        buttons[1].setOnMouseExited(event -> {
            putMenuBackground(root, "images/Main_menu.png");
        });
        buttons[1].setOnMouseEntered(event -> {
            putMenuBackground(root, "images/Main_menu3.png");
            sound.clickSound();
        });

        buttons[2].setOnMouseExited(event -> {
            putMenuBackground(root, "images/Main_menu.png");

        });
        buttons[2].setOnMouseEntered(event -> {
            putMenuBackground(root, "images/exit.png");
            sound.clickSound();
        });

        buttons[3].setOnMouseExited(event -> {
            putMenuBackground(root, "images/Main_menu.png");

        });
        buttons[3].setOnMouseEntered(event -> {
            putMenuBackground(root, "images/Main_menu4.png");
            sound.clickSound();
        });

        buttons[4].setOnMouseExited(event -> {
            putMenuBackground(root, "images/Main_menu.png");

        });
        buttons[4].setOnMouseEntered(event -> {
            putMenuBackground(root, "images/Main_menu5.png");
            sound.clickSound();
        });
    }
}

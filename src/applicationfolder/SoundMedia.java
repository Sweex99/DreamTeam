package applicationfolder;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import javafx.util.Duration;
import static javafx.util.Duration.*;

public class SoundMedia {
    private MediaPlayer player;
    private MainMenu mainMenu = new MainMenu();

    public void clickSound() {
        AudioClip sound = new AudioClip(this.getClass().getResource("/sound/click_sound.mp3").toExternalForm());
        sound.play();
    }

    public void start(Stage primaryStage) {
        StackPane root = new StackPane();

        player = new MediaPlayer(new Media(getClass().getResource("/sound/intro/intro.mp4").toExternalForm()));
        MediaView mediaView = new MediaView(player);

        Label pressEnter = new Label("Press [Mouse] Key");
        pressEnter.setStyle("-fx-text-fill: #d8d8d8;-fx-font-size: 20pt");


        FadeTransition fadeTransition = new FadeTransition(seconds(2.0), pressEnter);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        player.play();
        pressEnter.setVisible(false);
        player.getBufferProgressTime();

        if (player.getStatus().equals(MediaPlayer.Status.READY)) {}
        else {
            Timeline timeline = new Timeline(new KeyFrame(
                    Duration.seconds(10),
                    ae -> {
                        pressEnter.setVisible(true);
                        mediaView.setOnMouseClicked((MouseEvent event) -> {
                            player.stop();
                            mediaView.setVisible(false);
                            mainMenu.authorization(primaryStage);
                        });
                    }));
            timeline.play();
            player.setOnEndOfMedia(new Runnable() {
                @Override
                public void run() {
                    player.stop();
                    mainMenu.authorization(primaryStage);
                }
            });
        }
        pressEnter.setTranslateY(180);
        pressEnter.setTranslateX(30);

        root.getChildren().addAll(mediaView, pressEnter);

        final Scene scene = new Scene(root, 900, 580);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Log in");
        primaryStage.setFullScreen(false);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

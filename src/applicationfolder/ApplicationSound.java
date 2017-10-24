package applicationfolder;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class ApplicationSound {
    public  void clickSound() {
        AudioClip sound = new AudioClip(this.getClass().getResource("/sound/click_sound.mp3").toExternalForm());
        sound.play();
    }

}

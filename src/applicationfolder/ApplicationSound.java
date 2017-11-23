package applicationfolder;

import javafx.scene.media.AudioClip;


public class ApplicationSound {
    public void clickSound() {
        AudioClip sound = new AudioClip(this.getClass().getResource("/sound/click_sound.mp3").toExternalForm());
        sound.play();
    }

}

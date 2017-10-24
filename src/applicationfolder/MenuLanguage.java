package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;

public class MenuLanguage extends MainMenu {
    StackPane root = new StackPane();
    
    @Override
    public void putMenuBackground(String fileName) {
        super.putMenuBackground("images/Main_menu.png");
    }
    final Scene scene = new Scene(root, mb("images/Main_menu.png").getWidth(), mb("images/Main_menu.png").getHeight());
}

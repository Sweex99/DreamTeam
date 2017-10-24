package applicationfolder;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class UserTest {
    public String chooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Test file (.txt)", "*txt")
        );
        File file = fileChooser.showOpenDialog(stage);
        if(isValidate(file)) {
            return file.getPath();
        }
        else {
            WindowMessage.winalert("File Error!");

            throw new RuntimeException();
        }

    }

    private boolean isValidate (File file) {
        int lines = 0;
        boolean is_next_ans = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(file.getPath())))){
            String strRead;

            while((strRead = br.readLine()) != null) {
                if(lines == 0 && !strRead.equals("<TST>")) return false;
                if((lines-1)%5 == 0 && strRead.charAt(strRead.length() - 1) != '?') {
                    return false;
                }
                lines++;
            }
        }catch(IOException e){
            throw new UncheckedIOException(e);
        }

        if(lines >= 51 && (lines-1)%5 == 0) {
            return true;
        }
        else return false;
    }
}
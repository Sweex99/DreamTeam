package applicationfolder;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.*;

public class UserTest {
    public String chooser(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Test file", "*txt")
        );
        File file = fileChooser.showOpenDialog(stage);
        if(is_validate(file)) {
            return file.getPath();
        }
        return "";
    }

    private boolean is_validate (File file) {
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
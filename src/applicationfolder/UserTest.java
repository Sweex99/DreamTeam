package applicationfolder;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;

public class UserTest {
    public String getPathOfChosenFile(Stage stage) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Test file (.tst)", "*tst")
        );
        File file = fileChooser.showOpenDialog(stage);
        if (file == null) return null;
        if (isValidate(file)) {
            return file.getPath();
        } else {
            WindowMessage.winAlert("Несумісність файлу!");

            return null;
        }

    }

    private boolean isValidate(File file) {
        int lines = 0;

        if (!file.getName().endsWith(".tst")) return false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(file.getPath())))) {
            String strRead;

            while ((strRead = br.readLine()) != null) {
                if (lines == 0 && !strRead.equals("<TST>")) return false;
                if ((lines - 1) % 5 == 0 && strRead.charAt(strRead.length() - 1) != '?') {
                    return false;
                }
                lines++;
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }

        return (lines >= 51 && (lines - 1) % 5 == 0);
    }
}
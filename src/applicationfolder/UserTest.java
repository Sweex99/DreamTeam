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
        if(is_validate(file)) {
            return file.getPath();
        }
        else {
            winalert();
            throw new RuntimeException();
        }
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

    /*private boolean is_validate (File file) {
        int lines = 0;
        boolean is_next_ans = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new
                FileInputStream(file.getPath())))){
            String strRead;

            while((strRead = br.readLine()) != null) {
                if((lines)%5 == 0 && strRead.charAt(strRead.length() - 1) != '?') {
                    return false;
                }
                lines++;
            }
        }catch(IOException e){
            throw new UncheckedIOException(e);
        }

        if(lines >= 50 && (lines)%5 == 0) {
            return true;
        }
        else return false;
    }*/

    private void winalert () {
        Stage stage = new Stage();
        Pane message = new Pane();
        Text warning = new Text("File Error!");
        warning.setTranslateX(50);
        warning.setTranslateY(25);
        Button exit = new Button("OK");
        exit.setTranslateX(58);
        exit.setTranslateY(40);
        message.getChildren().addAll(exit, warning);
        exit.setOnAction(event -> {
            stage.close();
        });
        Scene scn = new Scene(message, 150, 100);
        stage.setScene(scn);
        stage.show();
    }
}
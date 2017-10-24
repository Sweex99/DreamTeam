package applicationfolder;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class WindowMessage {
    final public static void winAlert (String alert) {
        Stage stage = new Stage();
        Pane message = new Pane();
        Text warning = new Text(alert);
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

    /*final public static String inputString () {
        Stage stage = new Stage();
        Pane message = new Pane();
        TextField textField = new TextField();
        String fileName = textField.getText();
        textField.setTranslateX(50);
        textField.setTranslateY(25);
        Button submit = new Button("Submit");
        submit.setTranslateX(58);
        submit.setTranslateY(40);
        message.getChildren().addAll(submit, textField);
        submit.setOnAction(event -> {
            stage.close();
        });
        Scene scn = new Scene(message, 150, 100);
        stage.setScene(scn);
        stage.show();
        return fileName;
    }*/
}

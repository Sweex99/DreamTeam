package applicationfolder;

import javafx.scene.control.Alert;

public class WindowMessage {
    final public static void winAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        alert.showAndWait();
    }
}

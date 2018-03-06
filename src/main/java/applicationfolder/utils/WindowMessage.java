package applicationfolder.utils;

import javafx.scene.control.Alert;

public class WindowMessage {
    final public static void winAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        alert.showAndWait();
    }

    final public static void winInfo(String infoText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(infoText);

        alert.showAndWait();
    }
}

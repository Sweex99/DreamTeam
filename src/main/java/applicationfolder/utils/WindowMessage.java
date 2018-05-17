package applicationfolder.utils;

import javafx.scene.control.Alert;

public class WindowMessage {
    private WindowMessage(){
        throw new IllegalStateException("Utility class");
    }

    public static final void winAlert(String alertText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(alertText);

        alert.showAndWait();
    }

    public static final void winInfo(String infoText) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(infoText);

        alert.showAndWait();
    }
}

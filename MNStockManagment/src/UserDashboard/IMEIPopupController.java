package UserDashboard;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.*;

public class IMEIPopupController {

    @FXML private VBox imeiContainer;

    private int expectedQuantity;
    private Set<String> existingIMEIs;
    private final List<TextField> imeiFields = new ArrayList<>();
    private List<String> validIMEIs;

    public void setExpectedQuantity(int quantity) {
        this.expectedQuantity = quantity;
        for (int i = 0; i < quantity; i++) {
            TextField tf = new TextField();
            tf.setPromptText("IMEI " + (i + 1));
            imeiFields.add(tf);
            imeiContainer.getChildren().add(tf);
        }
    }

    public void setExistingIMEIs(Set<String> existing) {
        this.existingIMEIs = existing;
    }

    @FXML
    private void onSubmit() {
        Set<String> uniqueIMEIs = new HashSet<>();
        List<String> collected = new ArrayList<>();

        for (TextField tf : imeiFields) {
            String val = tf.getText().trim();
            if (val.isEmpty()) {
                showError("All IMEIs must be filled");
                return;
            }
            if (!uniqueIMEIs.add(val) || (existingIMEIs != null && existingIMEIs.contains(val))) {
                showError("Duplicate IMEI: " + val);
                return;
            }

            // TODO: check against DB for duplication if needed

            collected.add(val);
        }

        this.validIMEIs = collected;
        ((Stage) imeiContainer.getScene().getWindow()).close();
    }

    public List<String> getValidIMEIs() {
        return validIMEIs;
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR, msg, ButtonType.OK);
        alert.showAndWait();
    }
}

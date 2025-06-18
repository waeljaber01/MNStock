package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Utility class to switch scenes in the application.
 */
public class SceneNavigator {

    /**
     * Switch to a new FXML scene.
     *
     * @param event     The ActionEvent triggered by a button click (can be null for manual navigation)
     * @param fxmlPath  The path to the FXML file (example: "/UserDashboard/Dashboard.fxml")
     * @param title     The title of the new window
     */
    public static void navigateTo(ActionEvent event, String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage stage;
            if (event != null) {
                // Get the window from the button event
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            } else {
                // Fallback for non-button based navigation
                stage = new Stage();
            }

            stage.setTitle(title);
            stage.setScene(new Scene(root));
            stage.show();

        } catch (IOException e) {
            System.err.println("❌ Error loading scene: " + fxmlPath);
            e.printStackTrace();
        }
    }
    /**
     * Opens a new modal window (popup) for forms like Add Incoming.
     *
     * @param fxmlPath Path to the FXML file (e.g., "/UserDashboard/AddIncoming.fxml")
     * @param title    Title of the popup window
     */
    public static void openPopup(String fxmlPath, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(SceneNavigator.class.getResource(fxmlPath));
            Parent root = loader.load();

            Stage popupStage = new Stage();
            popupStage.setTitle(title);
            popupStage.setScene(new Scene(root));
            popupStage.initModality(javafx.stage.Modality.APPLICATION_MODAL);
            popupStage.showAndWait();

        } catch (IOException e) {
            System.err.println("❌ Error loading popup: " + fxmlPath);
            e.printStackTrace();
        }
    }

}

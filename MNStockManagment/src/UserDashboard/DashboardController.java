package UserDashboard;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import utils.SceneNavigator;

public class DashboardController {

    @FXML
    private ComboBox<String> cmbFeature;

    @FXML
    private TextField txtSearch;

    @FXML
    private Button btnAddIncoming, btnAddOutgoing, btnAddWarranty;

    @FXML
    private TabPane mainTabPane;

    @FXML
    private TableView<?> mainTable;

    @FXML
    public void initialize() {
        cmbFeature.setItems(FXCollections.observableArrayList(
            "Product Name", "IMEI", "Color", "Size", "Worker", "Customer", "Provider"
        ));
        cmbFeature.setValue("Product Name");

        // Optional: load data when tab is selected
        mainTabPane.getSelectionModel().selectedItemProperty().addListener((obs, oldTab, newTab) -> {
            String selectedTab = newTab.getText();
            System.out.println("Switched to tab: " + selectedTab);
            // TODO: call method to load matching table data
        });
    }

    @FXML
    private void onSearchClicked() {
        System.out.println("🔍 Search: " + txtSearch.getText() + " by " + cmbFeature.getValue());
        // TODO: implement search logic
    }

    @FXML
    private void onClearClicked() {
        txtSearch.clear();
        cmbFeature.getSelectionModel().selectFirst();
        // TODO: reset table to full data
    }

    @FXML
    private void onAddIncoming(ActionEvent event) {
        System.out.println("➕ Add Incoming clicked");
        SceneNavigator.openPopup("/UserDashboard/AddIncoming.fxml", "Add Incoming Entry");
    }

    @FXML
    private void onAddOutgoing(ActionEvent event) {
        System.out.println("➕ Add Outgoing clicked");
        // TODO: open AddOutgoing form
    }

    @FXML
    private void onAddWarranty(ActionEvent event) {
        System.out.println("🛠 Add Warranty clicked");
        // TODO: open AddWarranty form
    }
}

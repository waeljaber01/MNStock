package UserDashboard;

import Controller.DBController;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.*;

public class AddIncomingController {

    @FXML private TextField txtPhoneName, txtColor, txtSize, txtQuantity;
    @FXML private ComboBox<String> cmbAuthorized;
    @FXML private ComboBox<String> cmbSupplier;
    @FXML private DatePicker datePicker;
    @FXML private TableView<TempIncomingEntry> tempTable;

    private final List<TempIncomingEntry> tempList = new ArrayList<>();

    @FXML
    public void initialize() {
        cmbAuthorized.getItems().addAll("Yes", "No");
        cmbAuthorized.setValue("Yes");
        datePicker.setValue(LocalDate.now());

        // TODO: Load suppliers from DB (example: cmbSupplier.getItems().addAll(...))

        initTableColumns();
    }

    private void initTableColumns() {
        TableColumn<TempIncomingEntry, String> phoneCol = new TableColumn<>("Phone");
        phoneCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getPhoneName()));

        TableColumn<TempIncomingEntry, String> colorCol = new TableColumn<>("Color");
        colorCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getColor()));

        TableColumn<TempIncomingEntry, String> sizeCol = new TableColumn<>("Size");
        sizeCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSize()));

        TableColumn<TempIncomingEntry, String> authCol = new TableColumn<>("Authorized");
        authCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getAuthorized()));

        TableColumn<TempIncomingEntry, String> supplierCol = new TableColumn<>("Supplier");
        supplierCol.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().getSupplier()));

        TableColumn<TempIncomingEntry, LocalDate> dateCol = new TableColumn<>("Date");
        dateCol.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().getDate()));

        TableColumn<TempIncomingEntry, Integer> imeiCountCol = new TableColumn<>("IMEIs");
        imeiCountCol.setCellValueFactory(data -> new SimpleIntegerProperty(data.getValue().getImeis().size()).asObject());

        tempTable.getColumns().addAll(phoneCol, colorCol, sizeCol, authCol, supplierCol, dateCol, imeiCountCol);
    }

    @FXML
    private void onAddClicked() {
        String phone = txtPhoneName.getText();
        String color = txtColor.getText();
        String size = txtSize.getText();
        String auth = cmbAuthorized.getValue();
        String supplier = cmbSupplier.getValue();
        LocalDate date = datePicker.getValue();

        int qtty;
        try {
            qtty = Integer.parseInt(txtQuantity.getText());
        } catch (NumberFormatException e) {
            showAlert("❌ Quantity must be a number.");
            return;
        }

        // Show IMEI popup
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserDashboard/IMEIPopup.fxml"));
            Parent root = loader.load();

            IMEIPopupController controller = loader.getController();
            controller.setExpectedQuantity(qtty);
            controller.setExistingIMEIs(getAllIMEIsInTemp());

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Enter IMEIs");
            stage.showAndWait();

            List<String> imeis = controller.getValidIMEIs();
            if (imeis != null && !imeis.isEmpty()) {
                TempIncomingEntry entry = new TempIncomingEntry(phone, color, size, auth, supplier, date, imeis);
                tempList.add(entry);
                tempTable.getItems().add(entry); // ✅ display in table
            }

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("❌ Failed to load IMEI popup.");
        }
    }

    private Set<String> getAllIMEIsInTemp() {
        Set<String> all = new HashSet<>();
        for (TempIncomingEntry entry : tempList) {
            all.addAll(entry.getImeis());
        }
        return all;
    }

    @FXML
    private void onSaveClicked() {
        // TODO: validate and insert tempList to database using DBController
        showAlert("✅ Saved to database!");
    }

    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, msg, ButtonType.OK);
        alert.showAndWait();
    }
}

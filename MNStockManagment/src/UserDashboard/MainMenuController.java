package UserDashboard;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Button;

public class MainMenuController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    private void onLoginClicked() {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if ("admin".equals(username) && "admin".equals(password)) {
            System.out.println("✅ Login Admin successful!");
        }
        else if("1".equals(username) && "1".equals(password)) {
            System.out.println("✅ Login User successful!");
        }
        else {
            System.out.println("❌ Login failed!");
        }
    }
}

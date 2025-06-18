package UserDashboard;

import javafx.event.ActionEvent;
import entities.User;
import utils.SceneNavigator;
import utils.Session;
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
    private void onLoginClicked(ActionEvent event) {
        String username = txtUsername.getText();
        String password = txtPassword.getText();

        if ("admin".equals(username) && "admin".equals(password)) {
            User admin = new User(username, User.Role.ADMIN);
            Session.setUser(admin);
            SceneNavigator.navigateTo(event, "/UserDashboard/Dashboard.fxml", "Admin Dashboard");
        } else if ("1".equals(username) && "1".equals(password)) {
            User normalUser = new User(username, User.Role.USER);
            Session.setUser(normalUser);
            SceneNavigator.navigateTo(event, "/UserDashboard/Dashboard.fxml", "User Dashboard");
        } else {
            System.out.println("❌ Invalid login!");
        }
    }

}

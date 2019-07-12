package Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class DialogController implements Initializable {
    @FXML
    Button btnOk, btnCancel;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnOk.setOnAction(event -> {
            Stage stage = (Stage) btnOk.getScene().getWindow();
            stage.close();
        });
        btnCancel.setOnAction(event -> {
            Stage stage = (Stage) btnCancel.getScene().getWindow();
            stage.close();
        });
    }
}

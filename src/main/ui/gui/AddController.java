package ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;

import model.ListOfRecipe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AddController implements Initializable {
    Controller main;
    String termInput;
    String defnInput;
    ListOfRecipe recipelist;
    Stage stage;

    //FXML's
    @FXML
    private TextField termField;
    @FXML
    private TextArea defnField;
    @FXML
    public Button closeButton;

    public AddController() {
        recipelist = new ListOfRecipe();
        main = new Controller();
        System.out.println(recipelist.getListOfKeys());
    }

    // Popup window for adding
    public void display() { // Reference 1
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("fxml/AddWindow.fxml"));
            Parent root1 = fxmlLoader.load();

            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addRecipe() throws IOException {
        termInput = termField.getText();
        defnInput = defnField.getText();
        if (recipelist.addRecipe(termInput, defnInput)) {
            System.out.println(recipelist.getListOfKeys());
            System.out.println(recipelist.getRecipeDefn(termInput));
            termField.clear();
            defnField.setText("Recipe Added!");
            main.autoSave(termInput, defnInput);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Recipe already added!");
            alert.show();
        }
    }

    public void handleCloseButtonAction() {
        stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}


// REFERENCE
// display():
// https://stackoverflow.com/questions/27160951/javafx-open-another-fxml-in-the-another-window-with-button
// Alert box in addRecipe()
// https://stackoverflow.com/questions/8309981/how-to-create-and-show-common-dialog-error-warning-confirmation-in-javafx-2/28887273#28887273
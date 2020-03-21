package ui.gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ListOfRecipe;
import ui.DesignRecipeApp;

import java.io.IOException;

public class AddController {
    ListOfRecipe recipelist = new ListOfRecipe();
    Stage stage;

    //FXML's
    @FXML
    TextField termField;
    @FXML
    TextArea defnField;

    // Popup window for adding
    public void display() { // Reference 1
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/AddWindow.fxml"));
            Parent root1 = (Parent) fxmlLoader.load();
            stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO WHY WON"T THIS WORK LOL pls..... null pointer exception????

    public void addRecipe() {
        String termInput = termField.getText();
        String defnInput = defnField.getText();
        if (recipelist.containsRecipeKey(termField.getText())) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Recipe already added!");
            alert.show();
        } else {
            //??
        }
    }
}

// REFERENCE
// display():
// https://stackoverflow.com/questions/27160951/javafx-open-another-fxml-in-the-another-window-with-button
// Alert box in addRecipe()
// https://stackoverflow.com/questions/8309981/how-to-create-and-show-common-dialog-error-warning-confirmation-in-javafx-2/28887273#28887273
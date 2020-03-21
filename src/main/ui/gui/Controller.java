package ui.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.ListOfRecipe;
import ui.DesignRecipeApp;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ListView<String> initialList;
    @FXML
    private TextArea defnArea;

    @FXML
    private TextField searchBar;

    @FXML
    private Button searchButton;

    @FXML
    private MenuItem menuAdd;

    //******* ListView and TextArea ******* \\

    //EFFECT: print the corresponding definition from the chosen recipe
    public void clickRecipe() {
        String clicked = initialList.getSelectionModel().getSelectedItem();
        this.defnArea.setText(new ListOfRecipe().getRecipeDefn(clicked));
    }

    //******* MenuItem "Add" ******* \\
    public void menuAdd() {
        AddController add = new AddController();
        add.display();
    }

    //******* MenuItem "Revert to Default" ******* \\
    public List<String> helperDefault() throws IOException {
        new DesignRecipeApp().resetRecipeDefault();
        List reset = new ListOfRecipe().getListOfKeys();
        return reset;
    }

    public void revertDefault() throws IOException {
        initialList.getItems().clear();
        initialList.getItems().addAll(helperDefault());
    }

    //******* MenuItem "Quit" ******* \\
    public void exitProgram() {
        Platform.exit();
    }



    // *******  SearchBar and Search Button ******* \\

    //EFFECT: initializes the list of recipes on the listview
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //These items are for configuring the ListView
        initialList.getItems().addAll(new ListOfRecipe().getListOfKeys());
        initialList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        //These items are for resetting the ListView

    }
}

//TODO: lol reformat definitions plssdfsalkdj make sure they space downwards so they all fit in view

// REFERENCES
// https://www.youtube.com/watch?v=tAcHLWyO6jg - ListView/TextArea Tutorial

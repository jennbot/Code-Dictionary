package ui.gui;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import model.ListOfRecipe;
import persistence.OverWriter;
import persistence.Save;
import persistence.Writer;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class Controller implements Initializable {
    ListOfRecipe recipelist;

    public Controller() {
        recipelist = new ListOfRecipe();
    }

    public void loadRecipes() {
        recipelist = new ListOfRecipe();
        recipelist.loadDesignRecipeIntoHM();
    }

    @FXML
    private ListView<String> mainList = new ListView<>();

    @FXML
    private TextArea defnArea;

    @FXML
    private TextField searchBar;

    //******* ListView and TextArea ******* \\

    //EFFECT: print the corresponding definition from the chosen recipe
    public void clickRecipe() {
        String clicked = mainList.getSelectionModel().getSelectedItem();
        this.defnArea.setText(recipelist.getRecipeDefn(clicked));
    }

    //******* MenuItem "Add" ******* \\

    //EFFECT: menu bar "add" button action
    public void menuAdd() {
        AddController add = new AddController();
        add.display();
        System.out.println(recipelist.getListOfKeys());
    }

    //EFFECT: write new recipe into termList.txt
    public void autoSave(String term, String defn) throws IOException {
        Writer writer = new Writer();
        writer.write(term, defn);
        writer.close();
    }

    //EFFECT: refresh the ListView
    public void updateListView() {
        loadRecipes();
        mainList.setItems(FXCollections.observableArrayList(recipelist.getListOfKeys()));
        mainList.refresh();
    }

    //******* MenuItem "Delete" ******* \\

    //EFFECT: menu bar "delete" button action
    public void menuDelete() {
        String selected = mainList.getSelectionModel().getSelectedItem();
        mainList.getItems().remove(selected);
        recipelist.deleteRecipe(selected);
    }


    //******* MenuItem "Revert to Default" ******* \\

    //EFFECT: overrides termList.txt with defaultList.txt
    public void reset() throws IOException {
        OverWriter overwriter = new OverWriter();
        overwriter.overWrite();
        overwriter.close();
        loadRecipes();
    }

    //EFFECT: menu bar "revert" button action
    public void revertDefault() throws IOException {
        reset();
        mainList.setItems(FXCollections.observableArrayList(recipelist.getListOfKeys()));
    }

    // ******* Menu "Save" ******* \\

    //EFFECT: override termlist.txt and writes current recipelist into the text file.
    public void save() throws IOException {
        Save save = new Save();
        save.write(recipelist);
        save.close();

        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
        alert.show();
    }

    // *******  SearchBar and Search Button ******* \\

    //EFFECT: "search" button action
    public void searchButton() {
        List<String> listOfOne = new ArrayList<>();
        String input = searchBar.getText();
        listOfOne.add(input);
        if (recipelist.containsRecipeKey(input)) {
            mainList.setItems(FXCollections.observableArrayList(listOfOne));
            mainList.refresh();
        } else {
            searchBar.setText("Recipe not found!");
        }
    }


    //******* MenuItem "Quit" ******* \\

    //EFFECT: menu bar "quit" button action
    public void exitProgram() {
        Platform.exit();
    }

    //******* Music/Soundbites ******* \\

    //EFFECT: "tobs" button action
    public void playTobs() {
        play("./data/ferretdancetiktok.wav");
    }

    //EFFECT: program start soundbite
    public void playBing() {
        play("./data/Ring.wav");
    }

    //EFFECT: sound action event
    private void play(String s) {
        try {
            AudioInputStream stream;
            AudioFormat format;
            DataLine.Info info;
            Clip clip;

            stream = AudioSystem.getAudioInputStream(new File(s));
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        } catch (Exception e) {
            //whatever
        }
    }


    //EFFECT: initializes the list of recipes on the listview
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //These items are for configuring the ListView
        mainList.getItems().addAll(recipelist.getListOfKeys());
        mainList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        //enter noise
        playBing();
    }


}



// REFERENCES
// https://www.youtube.com/watch?v=tAcHLWyO6jg - ListView/TextArea Tutorial
// https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java - Play audio


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
import java.io.FileNotFoundException;
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

    @FXML
    private Button searchButton;

    @FXML
    private MenuItem menuAdd;

    @FXML
    private Button updateButton;

    @FXML
    private Button deleteButton;

    @FXML
    private MenuItem tobs;


    //******* ListView and TextArea ******* \\

    //EFFECT: print the corresponding definition from the chosen recipe
    public void clickRecipe() {
        String clicked = mainList.getSelectionModel().getSelectedItem();
        this.defnArea.setText(recipelist.getRecipeDefn(clicked));
    }

    //******* MenuItem "Add" ******* \\
    public void menuAdd() {
        AddController add = new AddController();
        add.display();
        System.out.println(recipelist.getListOfKeys());
    }

    public void addIntoList(String term, String defn) {
        recipelist.addRecipe(term, defn);
        System.out.println(recipelist.getListOfKeys());
        System.out.println(recipelist.getRecipeDefn(term));
        autoSave(term, defn);
    }

    public void autoSave(String term, String defn) {
        try {
            Writer writer = new Writer();
            writer.write(term, defn);
            writer.close();
        } catch (FileNotFoundException e) {
            System.out.println("Sorry, unable to save" + " " + term);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateListView() {
        loadRecipes();
        mainList.setItems(FXCollections.observableArrayList(recipelist.getListOfKeys()));
        mainList.refresh();
    }

    //******* MenuItem "Delete" ******* \\
    public void menuDelete() {
        String selected = mainList.getSelectionModel().getSelectedItem();
        mainList.getItems().remove(selected);
        recipelist.deleteRecipe(selected);
    }


    //******* MenuItem "Revert to Default" ******* \\
    public void reset() {
        try {
            OverWriter overwriter = new OverWriter();
            overwriter.overWrite();
            overwriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        loadRecipes();
        System.out.println("Default recipes restored!");
    }

    public void revertDefault() {
        reset();
        mainList.setItems(FXCollections.observableArrayList(recipelist.getListOfKeys()));
    }

    // ******* Menu "Save" ******* \\
    public void save() {
        try {
            Save saver = new Save();
            saver.write(recipelist);
            saver.close();
            System.out.println("DesignRecipe Saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Saved!");
        alert.show();
    }

    // *******  SearchBar and Search Button ******* \\
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
    public void exitProgram() {
        Platform.exit();
    }

    //******* TOBS ******* \\
    public void playTobs() {
        play("./data/ferretdancetiktok.wav");
    }

    public void playBing() {
        play("./data/Ring.wav");
    }

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

//TODO: lol reformat definitions plssdfsalkdj make sure they space downwards so they all fit in view
//TODO: test added methods in (saver, and in ListOfRecipe model)

// REFERENCES
// https://www.youtube.com/watch?v=tAcHLWyO6jg - ListView/TextArea Tutorial
// https://stackoverflow.com/questions/2416935/how-to-play-wav-files-with-java - Play audio


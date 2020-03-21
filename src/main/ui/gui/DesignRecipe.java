package ui.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class DesignRecipe extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        VBox mainVBox = FXMLLoader.load(DesignRecipe.class.getResource("fxml/DesignRecipeUI.fxml"));
        primaryStage.setScene(new Scene(mainVBox));
        primaryStage.setTitle("Design Recipe");
        primaryStage.show();
    }
}

// REFERENCES
// https://www.youtube.com/watch?v=uPm1n-cyupU javafx setup tutorial

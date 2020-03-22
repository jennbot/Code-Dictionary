package ui.gui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SplashScreenController implements Initializable {

    @FXML
    private StackPane rootPane;

    @FXML
    private ImageView imageview;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        new SplashScreen().start();

        Image i = new Image(new File("data/SpashScreen.gif").toURI().toString());
        imageview.setImage(i);
    }

    class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(5000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("fxml/DesignRecipeUI.fxml"));
                        } catch (IOException ex) {
                            Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        assert root != null;
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                        rootPane.getScene().getWindow().hide();
                    }
                });
            } catch (InterruptedException ex) {
                Logger.getLogger(SplashScreenController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}

//REFERENCES:
// https://www.youtube.com/watch?v=f06uUtkmtDE  Loading/Splash Screen Tutorial
// https://www.dafont.com/pixeled.font Font for loading screen
// https://www.pinclipart.com/pindetail/iiixxRx_books-book-pixel-art-png-clipart/ book pixel clip art



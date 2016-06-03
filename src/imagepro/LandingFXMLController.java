/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagepro;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author panda
 */
public class LandingFXMLController implements Initializable {
    @FXML
    private Label appTitle;
    @FXML
    private Button addImageAndApplyEffectButton;
    @FXML
    private VBox vBoxButtonGroup;
    @FXML
    private Button beforeButton;
    @FXML
    private Button afterButton;
    @FXML
    private ImageView imageView;
    
//    @FXML
//    private Label label;
//    
//    @FXML
//    private void handleButtonAction(ActionEvent event) {
//        System.out.println("You clicked me!");
//        label.setText("Hello World!");
//    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addImageAndApplyEffectButtonAction(ActionEvent event) {
        //File file = new File("/src/sample.png");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Please Select An Image");
        File fileb = chooser.showOpenDialog(new Stage());
        String imagepath = fileb.getPath();
        System.out.println("file:"+imagepath);
        Image image = new Image(fileb.toURI().toString());
        //int w = (int)imageView.getFitWidth();
        //int h = (int)imageView.getFitHeight();
        imageView.setImage(image);
        //imageView.setFitHeight(h);
        //imageView.setFitWidth(w);
        //imageView.setPreserveRatio(true);
        centerImage();
        System.out.println("image set");
    }

    @FXML
    private void beforeButtonAction(ActionEvent event) {
    }

    @FXML
    private void afterButtonAction(ActionEvent event) {
    }
    
    public void centerImage() {
        Image img = imageView.getImage();
        if (img != null) {
            double w = 0;
            double h = 0;

            double ratioX = imageView.getFitWidth() / img.getWidth();
            double ratioY = imageView.getFitHeight() / img.getHeight();

            double reducCoeff = 0;
            if(ratioX >= ratioY) {
                reducCoeff = ratioY;
            } else {
                reducCoeff = ratioX;
            }

            w = img.getWidth() * reducCoeff;
            h = img.getHeight() * reducCoeff;

            imageView.setX((imageView.getFitWidth() - w) / 2);
            imageView.setY((imageView.getFitHeight() - h) / 2);

        }
    }
    
}

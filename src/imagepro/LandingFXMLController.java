/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imagepro;

import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingFXUtils;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void addImageAndApplyEffectButtonAction(ActionEvent event) {
        //File fileb = new File("/src/sample.png");
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Please Select An Image");
        File file = chooser.showOpenDialog(new Stage());
        String imagepath = file.getPath();
        System.out.println("file:"+imagepath);
        Image image = new Image(file.toURI().toString());
        //int w = (int)imageView.getFitWidth();
        //int h = (int)imageView.getFitHeight();
        image = SwingFXUtils.toFXImage(colorToGray(image), null);
        imageView.setImage(image);
        //imageView.setFitHeight(h);
        //imageView.setFitWidth(w);
        //imageView.setPreserveRatio(true);
        centerImage();
        System.out.println("image set");
    }
    private BufferedImage colorToGray(Image img){
        BufferedImage bimg = SwingFXUtils.fromFXImage(img, null);
        int height = bimg.getHeight();
        int width = bimg.getWidth();
        //System.out.printf("[%d x %d]\n", width, height);

        for (int c = 0; c < width; c++)
            for (int r = 0; r < height; r++) {
                int rgb = bimg.getRGB(c, r);
                int red   = (rgb >> 16) & 0xFF;
                int green = (rgb >>  8) & 0xFF;
                int blue  = (rgb >>  0) & 0xFF;

                //int average = (int) (0.21 * red + 0.72 * green + 0.07 * blue);
                int average = green;
                int rr = average;
                int gg = average;
                int bb = average;
//                if (red > 200)
//                    rr = (int) (red * 1.5);
//                if (rr > 255)
//                    rr = 255;
                rgb = (rr << 16) | (gg << 8) | bb;

                bimg.setRGB(c, r, rgb);
            }
        return bimg;
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

/* buffered image to javafx image ***** alternative: SwingFXUtils
ex: Image img = SwingFXUtils.toFXImage(hereIsTheBufferedImage, img);
inv: fromFXImage(Image img, java.awt.image.BufferedImage bimg)
BufferedImage bf = null;
        try {
            bf = ImageIO.read(new File("C:/location/of/image.png"));
        } catch (IOException ex) {
            System.out.println("Image failed to load.");
        }
 
        WritableImage wr = null;
        if (bf != null) {
            wr = new WritableImage(bf.getWidth(), bf.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < bf.getWidth(); x++) {
                for (int y = 0; y < bf.getHeight(); y++) {
                    pw.setArgb(x, y, bf.getRGB(x, y));
                }
            }
        }
 
        ImageView imView = new ImageView(wr);
*/
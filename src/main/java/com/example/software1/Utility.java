package com.example.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
/**This class contains repeated used methods*/
public class Utility implements Initializable {
/** Parameterless constructor.*/
    public Utility()  {
    }
    /** This method displays confirmation pop-up.
      @param message message that needs to be displayed.
     @return Returns whether operation was successful or not.*/
    public static boolean confDisplay(String message){

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,message);

            Optional<ButtonType> result = alert.showAndWait();

            if(result.isPresent() && result.get() == ButtonType.OK) {

                return true;

            } else {

                return false;

            }

        }
/** This method initialize the utility class.
 @param url a pointer to a resource.
 @param resourceBundle contains locale specific objects.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
/** This method switches views.
 @param actionEvent action taken when needs to switch.
 @param screen address of view.
 @param title setting title of view.
 @param v setting height of view.
 @param v1 setting width of view.
 @throws IOException throws exception in case of I/O exception.*/
    public void switchScreen(ActionEvent actionEvent, String screen, int v, int v1, String title) throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource(screen));

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        Scene scene = new Scene(root, v, v1);

        stage.setTitle(title);

        stage.setScene(scene);

        stage.show();

    }
/** This method displays an error pop-up.
  @param message message that needs to be displayed.*/
   public static void errorDisplay(String message) {

       Alert alert = new Alert(Alert.AlertType.ERROR,message);

        alert.setTitle("Error Dialog");
        alert.setContentText(message);
        alert.showAndWait();

    }
/** This method is used to search for a part.
 The method searches through the all parts list to find a part that matches the input.
 @param partialName name or partial name of the part.
 @return Returns a list of search results.*/
    public static ObservableList<Part> partSearch(String partialName) {

        ObservableList<Part>namedParts = FXCollections.observableArrayList();

        ObservableList<Part> allParts = Inventory.getAllParts();

        for(Part nP : allParts) {

            if(nP.getName().contains(partialName)) {

                namedParts.add(nP);

            }

        }

        return namedParts;

    }
    /** This method is used to search for a product.
     The method searches through the all product list to find a product that matches the input.
     @param partialName  name or partial name of the part.
     @return Returns a list of search results.*/
    public static ObservableList<Product> productSearch(String partialName) {

        ObservableList<Product>namedParts = FXCollections.observableArrayList();

        ObservableList<Product> allProduct = Inventory.getAllProducts();

        for(Product nP : allProduct) {

            if(nP.getName().contains(partialName)) {

                namedParts.add(nP);

            }

        }

        return namedParts;

    }
/** This method finds the index of a part.
  The method iterates through a copy of the all parts list, then finds the matching id and return its index.
 @param id id of the part.
 @return Returns the index.*/
    public static Integer findPartIndex(Integer id) {

        ObservableList<Part> allParts = Inventory.getAllParts();

        int pos = 0;

        for(Part iP : allParts) {

            if(id == iP.getId()) {

                return pos;

            }

            pos++;
        }

        return -1;

    }
    /** This method finds the index of a product.
     The method iterates through a copy of the all products list, then finds the matching id and return its index.
     @param id id of the product.
     @return Returns the index.*/
    public static Integer findProductIndex (Integer id) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        int pos = 0;

        for(Product iP : allProducts) {

            if(id == iP.getId()) {

                return pos;
            }

            pos++;
        }

        return -1;


    }

}


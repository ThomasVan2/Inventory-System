package com.example.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.example.software1.Inventory.*;

/**This class controls the data for the modify product view.*/
public class ModifyProductController implements Initializable {
    public Button ModProductCancel;
    private static Product theProductPass = null;
    private static ObservableList<Part> PartModList = FXCollections.observableArrayList();
    private static ObservableList<Part> partIdList = FXCollections.observableArrayList();
    public TextField ModProdMax;
    public TextField ModProdPrice;
    public TextField ModProdInv;
    public TextField ModProdName;
    public TextField ModProdID;
    public TextField ModProdMin;
    public TableColumn ModIDCol;
    public TableColumn ModNameCol;
    public TableColumn ModInvCol;
    public TableColumn ModPriceCol;
    public Button ModProdRemBut;
    public TableColumn ModID2;
    public TableColumn ModPart2;
    public TableColumn ModInv2;
    public TableColumn ModPrice2;
    public Button ModPordsave;
    public TableView Table1;
    public TableView asPartList;
    public Button ModProdAdd;
    public TextField SearchField;
    Utility util = new Utility();
    /** This method sets the data in the form.
     @param url a pointer to a resource.
     @param resourceBundle contains locale specific objects.*/
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setFields();

    }
    /** This method sets the text fields with the product data.
     The method will get the transferred data and put it in the correct text fields.*/
    public void setFields() {
        //Setting textfields
        ModProdID.setText(String.valueOf(theProductPass.getId()));
        ModProdName.setText(theProductPass.getName());
        ModProdInv.setText(String.valueOf(theProductPass.getStock()));
        ModProdPrice.setText(String.valueOf(theProductPass.getPrice()));
        ModProdMax.setText(String.valueOf(theProductPass.getMax()));
        ModProdMin.setText(String.valueOf(theProductPass.getMin()));
        //Setting rows of upper Table
        ModIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModInvCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        Table1.setItems(PartModList);
        //Setting rows of second table
        ModID2.setCellValueFactory(new PropertyValueFactory<>("id"));
        ModPart2.setCellValueFactory(new PropertyValueFactory<>("name"));
        ModPrice2.setCellValueFactory(new PropertyValueFactory<>("price"));
        ModInv2.setCellValueFactory(new PropertyValueFactory<>("stock"));

        asPartList.setItems(partIdList);
    }
/** This method receives data.
  This method receives the selected product data from the inventory.
 @param product the selected product.*/
    public static void passProduct(Product product) {

        theProductPass = product;

    }
    /** This method receives data.
      This method receives all the parts from the inventory.
     @param passList list of parts.*/
    public static void passPartList(ObservableList<Part> passList) {

        PartModList = passList;
    }
    /** This method receives the associated parts data.
      This method receives the parts associated with the product.
     @param asIDListPass list of associated parts.*/
    public static void getAsList(ObservableList<Part> asIDListPass){

       partIdList = asIDListPass;

    }
    /** This method redirects back to main form without saving newly input.
      @param actionEvent click of cancel button.*/
    public void ModProductCancelBut(ActionEvent actionEvent) throws IOException {

        if(Utility.confDisplay("Exit to Main menu")) {

            util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");

        }

    }
    /** This method removes a part.
     This method removes an associated part.
     @param actionEvent click of remove button.*/
    public void RemoveBut(ActionEvent actionEvent) {

         Part remove = (Part) asPartList.getSelectionModel().getSelectedItem();

        if(remove == null) {

            Utility.errorDisplay("Nothing Selected");

        } else if(Utility.confDisplay("Are you sure you want to delete this part?")) {

            partIdList.remove(remove);

        }

    }
    /** This method will save the inputted data.
      This method will validate the data and if correct save the modified product data.
     @param actionEvent click f save button.
     @throws IOException throws exception in case of I/O exception.*/
    public void SaveBut(ActionEvent actionEvent) throws IOException {

        String name = ModProdName.getText();
        String s = ModProdInv.getText();
        String p = ModProdPrice.getText();
        String ma = ModProdMax.getText();
        String mi = ModProdMin.getText();
        String id = ModProdID.getText();

        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;
        int ID = 0;

        try {
            stock = Integer.parseInt(s);
            price = Double.parseDouble(p);
            max = Integer.parseInt(ma);
            min = Integer.parseInt(mi);
            ID = Integer.parseInt(id);

        } catch (NumberFormatException e) {

            Utility.errorDisplay("Input Invalid");
            return;
        }
        if (min > max) {

            Utility.errorDisplay("Min has to be smaller than Max");

            return;
        } else if (stock > max) {

            Utility.errorDisplay("Inventory needs to be smaller than max");

            return;
        } else if (stock < min) {

            Utility.errorDisplay("Inventory needs to be bigger than min");

            return;
        }

       int productIndex = Utility.findProductIndex(ID);

      Product adding = new Product(ID, name, price, stock, max, min);

        Inventory.updateProduct(productIndex, adding);

        for(int i = 0; i < partIdList.size(); i++) {

            Part newPart = partIdList.get(i);

            adding.addAssociatedPart(newPart);

        }

        util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");
    }
    /** This method adds an associated part.
      This method add the selected part to the associated parts list.
     @param actionEvent click of add button.*/
    public void ModProdAddBut(ActionEvent actionEvent) {

        Part swapPart = (Part) Table1.getSelectionModel().getSelectedItem();

        if(swapPart == null) {

            Utility.errorDisplay("Nothing Selected");

        } else {

            partIdList.add(swapPart);

        }

    }
    /** This method returns a search list.
     This method returns a list of products the matches the input of the user.
     @param actionEvent clicking enter key.*/
    public void SearchFieldEnter(KeyEvent actionEvent) {

        String q = SearchField.getText();

        ObservableList<Part>parts = Utility.partSearch(q);

        if(parts.size() == 0) {

            try {

                int ID = Integer.parseInt(q);

                Part pa = lookupPart(ID);

                if (pa != null) {

                    parts.add(pa);

                }

            } catch (NumberFormatException e) {

            }

        }

        if(parts.size() == 0) {

            Utility.errorDisplay("No search results found");

        } else {

            Table1.setItems(parts);

        }

    }

}

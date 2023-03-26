package com.example.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.software1.Inventory.*;
/**This class controls the data of the main view.*/
public class mainController implements Initializable {
    public TableColumn PartIDCol;
    public TableColumn PartNameCOL;
    public TableColumn PartinvenCol;
    public TableColumn PartPricecol;
    public TableColumn ProdIDCol;
    public TableColumn ProdNameCOL;
    public TableColumn ProductInvCOL;
    public TableColumn ProdPriceCOL;
    public Label TopPartsLabel;
    public Button PartModify;
    public Button PartsAdd;
    public Button ProductsDelete;
    public Button ProductsModify;
    public Button ProductsADD;
    public Button MainExit;
    public TableView ProductsTable;
    public TableView PartTable;
    public TextField MainSearchPart;
    public TextField MainSearchprod;
    public Button PartsDelete;
    Utility util = new Utility();
/** This method set the data in the main form. Places parts and products data in tables.
  @param url a pointer to a resource.
 @param resourceBundle contains locale specific objects.*/
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Making table
        PartIDCol.setCellValueFactory(new PropertyValueFactory<>("id")); //String refers to name of methods
        PartinvenCol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PartNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
        PartPricecol.setCellValueFactory(new PropertyValueFactory<>("price"));


       ProdIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));
       ProductInvCOL.setCellValueFactory(new PropertyValueFactory<>("stock"));
       ProdNameCOL.setCellValueFactory(new PropertyValueFactory<>("name"));
       ProdPriceCOL.setCellValueFactory(new PropertyValueFactory<>("price"));

     //Setting items into Table
       PartTable.setItems(Inventory.allParts);

       ProductsTable.setItems(Inventory.allProducts);

    }

    /** This method switches to part modify form.
      This method will switch to part modify form if part modify button is clicked and sends selected part data to form.
     @param actionEvent action of part modify button click.
     @throws IOException throws exception in case of I/O exception.*/
    public void PartsModifyBut(ActionEvent actionEvent) throws IOException{

        Part part = (Part) PartTable.getSelectionModel().getSelectedItem();

        if(part == null) {

            Utility.errorDisplay("Nothing Selected");

            return;
        }

        ModifyPartController.passPart(part);

        util.switchScreen(actionEvent, "ModifyPart.fxml", 550, 600, "Add Part From");
    }
    /** This method will switch to parts add form.
      Method will switch to parts add form if add part button is clicked.
      @param actionEvent action of add button click.
      @throws IOException throws exception in case of I/O exception.*/
    public void PartsAddBut(ActionEvent actionEvent) throws IOException {

        util.switchScreen(actionEvent, "AddPart.fxml", 550, 600, "Add Part Form" );

    }
    /** This method will delete a product.
      The selected product will be deleted.
     @param actionEvent action of delete button clicked*/
    public void ProdDeleteBut(ActionEvent actionEvent) {

        if(Utility.confDisplay("This will delete selected Item, do you want to continue?")) {

            Product DL = (Product) ProductsTable.getSelectionModel().getSelectedItem();

            if(DL == null) {

                Utility.errorDisplay("Nothing Selected");

                return;

            } else if (DL.getAllAssociatedParts().size() > 0) {

                Utility.errorDisplay("Cannot Delete. Product is associated with a part.");

                return;
            }

            deleteProduct(DL);

            ProductsTable.getItems().remove(DL);

        }
    }
    /** This method switched to product modify form.
      Switches to product modify button by clicking on product modify button and sends selected product data to form.
     @param actionEvent action of clicking on button.
     @throws IOException throws exception in case of I/O exception.*/
    public void ProdModBut(ActionEvent actionEvent) throws IOException{

        //Send data for field
        Product product = (Product) ProductsTable.getSelectionModel().getSelectedItem();

        if(product == null) {

            Utility.errorDisplay("Nothing Selected");
            return;
        }

        ModifyProductController.passProduct(product);

        //Send List of parts
        ObservableList<Part> partList = Inventory.allParts;

        ModifyProductController.passPartList(partList);

        //Send Associated Parts List
        ObservableList<Part> AssociatedList = product.getAllAssociatedParts();

        ModifyProductController.getAsList(AssociatedList);

        util.switchScreen(actionEvent, "ModifyProduct.fxml", 1000,600,"Add Part Form");

    }

    /** This method switches to add product from. Switches to add product form by clicking on add button.
      @param actionEvent action of clicking on button.
     @throws IOException throws exception in case of I/O exception.*/
    public void ProdAddBut(ActionEvent actionEvent) throws IOException {

        //Send over List of Parts
        ObservableList<Part> part = Inventory.allParts;

        AddProductController.passList(part);

        util.switchScreen(actionEvent, "AddProduct.fxml", 1000, 600, "Add Part From");

    }
    /** This method exits the app. Exits app by clicking on button.
     @param actionEvent exit button click.*/
    public void MainExitBut(ActionEvent actionEvent) {

        if(Utility.confDisplay("Exit program")) {

            System.exit(0);

        }

    }
/**This method deletes selected part. Deletes selected part by clicking on part remove button.
  @param actionEvent is the click of the button.*/
    public void PartRemoveBut(ActionEvent actionEvent) {
           //getting selected part
           Part DL = (Part) PartTable.getSelectionModel().getSelectedItem();

           if(DL == null) {

               Utility.errorDisplay("Nothing Selected");
               return;
           }

           if(Utility.confDisplay("This will delete selected Item, do you want to continue?"))

           deletePart(DL);

           PartTable.getItems().remove(DL);
    }
    /** This method set the prompt text inside the part search bar.
     @param actionEvent Text field input. */
    public void MainSearchPartBut(ActionEvent actionEvent) {

        MainSearchPart.setPromptText("Search by Part ID or Name");

    }
    /** This method sets prompt text for product search bar.
      @param actionEvent Text field input.*/
    public void MainSearchprodBut(ActionEvent actionEvent) {

        MainSearchprod.setPromptText("Search by Product ID or Name");

    }
    /** This is a search method.
      Returns a list of the product list that matches the input of the user
     @param keyEvent returns the list when Enter key is pressed.*/
    public void productSearchField(KeyEvent keyEvent) {

        String q = MainSearchprod.getText();

        ObservableList<Product>products = Utility.productSearch(q);

        if(products.size() == 0) {

            try {

                int ID = Integer.parseInt(q);

                Product pa = lookupProduct(ID);

                if (pa != null) {

                    products.add(pa);

                }

            } catch (Exception e) {

            }

        }

        if(products.isEmpty()) {

            Utility.errorDisplay("No search results found");

        } else {

            ProductsTable.setItems(products);

        }

    }
    /** This is a search method.
      Returns a list of the part list that matches the input of the user
     @param keyEvent returns the list when Enter key is pressed.*/
    public void SearchPartEnter(KeyEvent keyEvent) {

        String q = MainSearchPart.getText();

        ObservableList<Part>parts = Utility.partSearch(q);

        if (parts.size() == 0) {

            try {
                int Id = Integer.parseInt(q);

                Part pa = lookupPart(Id);

                if (pa != null)

                    parts.add(pa);

            } catch (NumberFormatException e) {

            }

        }

        if(parts.size() == 0) {

            Utility.errorDisplay("No search results found");

        } else {

            PartTable.setItems(parts);

        }

    }
}
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

import static com.example.software1.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**This class controls the data for the add product form.*/
public class AddProductController implements Initializable {
    public Button AddProductCancel;
    public TableColumn partIDcol;
    public TableColumn PartNamecol;
    public TableColumn InvLevelcol;
    public TableColumn Pricecol;
    private static ObservableList<Part> PartAddPass = FXCollections.observableArrayList();
    private static ObservableList<Part> tempAddAssociatedPart = FXCollections.observableArrayList();
    public TableView PartTable1;
    public Button saveProdbut;
    public TextField AddProdMax;
    public TextField AddProdPrice;
    public TextField AddProdStock;
    public TextField AddProdName;
    public TextField AddProdMin;
    public Button AddProd;
    public TableView AsPartTable;
    public TableColumn asIdCol;
    public TableColumn asNameCol;
    public TableColumn asIncCol;
    public TableColumn asPriceCol;
    public Button AddProdRem;
    public TextField Searchfield;
    Utility util = new Utility();
/** This method fills up the parts tables.
  This method will put the parts from the all parts into the first table and then the selected parts in the second table.
 @param resourceBundle contains locale specific objects.
 @param url a pointer to a resource. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //Setting Parts in table
        partIDcol.setCellValueFactory(new PropertyValueFactory<>("id"));
        PartNamecol.setCellValueFactory(new PropertyValueFactory<>("name"));
        InvLevelcol.setCellValueFactory(new PropertyValueFactory<>("stock"));
        Pricecol.setCellValueFactory(new PropertyValueFactory<>("price"));

        PartTable1.setItems(PartAddPass);

        //Setting parts in Associates Part table
        asIdCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        asNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        asPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        asIncCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        AsPartTable.setItems(tempAddAssociatedPart);

    }
/** This method receives data.
  This method receives data of the parts from the inventory class.
 @param pass list of parts. */
    public static void passList(ObservableList<Part> pass) {

        PartAddPass = pass;

    }
    /** This method cancels the input and redirects to main form.
     This method won't save the given input and will redirect to the main form.
     @param actionEvent click of cancel button.
     @throws IOException throws exception in case of I/O exception. */
    public void AddProductCancelBut(ActionEvent actionEvent) throws IOException {

        if(Utility.confDisplay("Exit to main menu")) {

            tempAddAssociatedPart.clear();

            util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");

        }

    }
    /** This method will save the given input.
      This method will validate the input and if correct add the product to the list and then redirect to the main form.
     @param actionEvent clicking of save button.
     @throws IOException throws exception in case of I/O exception.*/
    public void saveProdBut(ActionEvent actionEvent) throws IOException {

        String name = AddProdName.getText();
        String s = AddProdStock.getText();
        String p = AddProdPrice.getText();
        String ma = AddProdMax.getText();
        String mi = AddProdMin.getText();

        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;

        try {
            stock = Integer.parseInt(s);
            price = Double.parseDouble(p);
            max = Integer.parseInt(ma);
            min = Integer.parseInt(mi);

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

        Product adding = new Product(generateProductID(), name, price, stock, max, min);

        AddProduct(adding);

        for(int i = 0; i < tempAddAssociatedPart.size(); i++) {

            Part newPart = tempAddAssociatedPart.get(i);

            adding.addAssociatedPart(newPart);

        }

        tempAddAssociatedPart.clear();

        util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");
    }
/** This method will add a part to the associated parts.
  This method will add the selected part to the associated parts list.
 @param actionEvent click of add button.*/
    public void AddProdBut(ActionEvent actionEvent) {

        Part part = (Part) PartTable1.getSelectionModel().getSelectedItem(); //Get the selected item from table 1

        if(part == null) {

            Utility.errorDisplay("Nothing Selected");
            return;
        }
        //Add selected item to list
        tempAddAssociatedPart.add(part);

    }
/** This method will remove an associated part.
  This method will remove the selected associated part from the associated parts table.
 @param actionEvent click of remove button.*/
    public void RemoveBut(ActionEvent actionEvent) {

        Part part = (Part) AsPartTable.getSelectionModel().getSelectedItem();

        if(part == null) {

            Utility.errorDisplay("Nothing Selected");
            return;
        }

        if(Utility.confDisplay("Are you sure you want to delete this part?")) {

            tempAddAssociatedPart.remove(part);

        }

    }
    /** This method returns a search result.
      This method returns a list of products the matches the input of the user.
     @param keyEvent clicking enter key.*/
    public void SearchFieldEnter(KeyEvent keyEvent) {

        String q = Searchfield.getText();

        ObservableList<Part>parts = Utility.partSearch(q);

        if(parts.size() == 0) {

            try {

                int ID = Integer.parseInt(q);

                Part pa = lookupPart(ID);

                if (pa != null) {

                    parts.add(pa);

                }

            } catch (Exception e) {

            }
        }

        if(parts.size() == 0) {

            Utility.errorDisplay("No search results found");

        } else {

            PartTable1.setItems(parts);

        }

    }

}


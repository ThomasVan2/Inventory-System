package com.example.software1;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import static com.example.software1.Inventory.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**This class controls the data for the add part form.*/
public class AddPartController implements Initializable {
    public RadioButton PartHouseRad;
    public ToggleGroup Tgroup;
    public RadioButton PartOutRad;
    public TextField AddPartName;
    public TextField AddInvPart;
    public TextField AddPricePart;
    public Button AddPartSave;
    public Button AddPartCancel;
    public TextField AddPartMax;
    public TextField AddPartMin;
    public Label ErrorLabel;
    public TextField ChangeID;
    public Label ChangeIDtext;
    Utility util = new Utility();
    /** This method set the data in the main form. Places parts and products data in tables.
     @param url a pointer to a resource
     @param resourceBundle contains locale specific objects. */
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
/** This method sets text label to Machine ID.
  When the in-house radio button is clicked the text label will switch to Machine ID.
 @param actionEvent clicking of in-house radio button. */
    public void HouseBut(ActionEvent actionEvent) {
        ChangeIDtext.setText("Machine ID");

    }
    /** This method sets text label to Company Name.
     When the outsourced radio button is clicked the text label will switch to Company Name.
     @param actionEvent clicking of outsourced radio button. */
    public void OutBut(ActionEvent actionEvent) {

        ChangeIDtext.setText("Company Name");

    }
/** This method will save the data in the parts list.
  When save button is clicked this method will scan the input to check if it's valid, then save the data to the parts list and then switch to the main form.
 @param actionEvent clicking of save button.
 @throws IOException if exception occurs during switching of view. */
    public void AddPartSaveBut(ActionEvent actionEvent) throws IOException {

        String name = AddPartName.getText();

        String i = AddInvPart.getText();

        String p = AddPricePart.getText();

        String m = AddPartMax.getText();

        String mi = AddPartMin.getText();

        String mID = ChangeID.getText();

        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;

        try {
            price = Double.parseDouble(p);
            stock = Integer.parseInt(i);
            max = Integer.parseInt(m);
            min = Integer.parseInt(mi);
        } catch (NumberFormatException e) {

            Utility.errorDisplay("Invalid Input");

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

        if (ChangeIDtext.getText().equals("Machine ID")) {

            int machineID = 0;

            try {
                machineID = Integer.parseInt(mID);

              } catch (NumberFormatException e) {

                Utility.errorDisplay("Invalid input");

                return;
            }

            AddPart(new inHouse(generatePartID(), name, price, stock, min, max, machineID));

        }
        else if (ChangeIDtext.getText().equals("Company Name")) {

             AddPart(new Outsourced(generatePartID(), name, price, stock, min, max, mID));

            }

            util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");
    }
    /** This method will redirect back to main form.
      This method will redirect to main form and not save the newly given input.
     @param actionEvent click of cancel button.
     @throws IOException if exception occurs during switching of view. */
    public void AddPartCancelBut(ActionEvent actionEvent) throws IOException {

        if(Utility.confDisplay("Exit to Main menu")) {

            util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");

        }
    }

}

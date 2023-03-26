package com.example.software1;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import static com.example.software1.Inventory.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**This class controls the data for the modify part form.*/
public class ModifyPartController implements Initializable {
    public RadioButton PartHouseRad;
    public ToggleGroup Tgroup;
    public RadioButton PartOutRad;
    public Label ChangeID;
    public TextField ModInvPart;
    public TextField ModPartName;
    public TextField ModPricePart;
    public Button ModPartSave;
    public Button ModPartCancel;
    private static Part thePass = null;
    public TextField ModPartMax;
    public TextField ModPartMin;
    public TextField ModChangeField;
    public TextField ModPartID;
    Utility util = new Utility();
/** This method receives data.
 This method receives data from inventory of the selected part.
 @param pass the selected part.*/
    public static void passPart(Part pass) {

        thePass = pass;

    }
/** This method sets the data in the form.
 @param url a pointer to a resource.
 @param resourceBundle contains locale specific objects.*/
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setFields();

    }
/** This method sets the Machine ID label.
  If house radio button is clicked the method will change the label to Machine ID.
 @param actionEvent clicking of house radio button.*/
    public void HouseBut(ActionEvent actionEvent) {

        ChangeID.setText("Machine ID");

    }
/** This method sets the text fields with the part data.
  The method will get the transferred data and put it in the correct text fields.*/
    public void setFields() {

        ModPartID.setText(String.valueOf(thePass.getId()));
        ModPartName.setText(thePass.getName());
        ModInvPart.setText(String.valueOf(thePass.getStock()));
        ModPricePart.setText(String.valueOf(thePass.getPrice()));
        ModPartMax.setText(String.valueOf(thePass.getMax()));
        ModPartMin.setText(String.valueOf(thePass.getMin()));

        if (thePass instanceof inHouse) {

            ModChangeField.setText(String.valueOf(((inHouse) thePass).getMachineId()));

            PartHouseRad.setSelected(true);

            ChangeID.setText("Machine ID");

        } else {

            Outsourced part = (Outsourced) thePass;

            ModChangeField.setText(part.getCompanyName());

            PartOutRad.setSelected(true);

            ChangeID.setText("Company Name");

        }
    }
    /** This method sets the Company name label.
     If outsourced radio button is clicked the method will change the label to Company Name.
     @param actionEvent clicking of outsourced button radio button.*/
    public void OutBut(ActionEvent actionEvent) {

        ChangeID.setText("Company Name");

    }
/** This method will save the data.
  This method will validate the inputted data, check if the part in-house or outsourced, add the modified part back to the list and redirect back to the main form.
 @param actionEvent click of save button.
 @throws IOException throws exception in case of I/O exception.*/
    public void ModPartSaveBut(ActionEvent actionEvent) throws IOException {

        //Get input from fields
        int id = Integer.parseInt(ModPartID.getText());
        String name = ModPartName.getText();
        String s = ModInvPart.getText();
        String p = ModPricePart.getText();
        String m = ModPartMax.getText();
        String mi = ModPartMin.getText();
        String change = ModChangeField.getText();

        int stock = 0;
        double price = 0;
        int max = 0;
        int min = 0;

        try {
            price = Double.parseDouble(p);
            stock = Integer.parseInt(s);
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

        //Check to see if the part is in-house or outsourced
        if (ChangeID.getText().equals("Machine ID")) {

            int machineID = 0;

            try {
                machineID = Integer.parseInt(change);

            } catch (NumberFormatException e) {

                Utility.errorDisplay("Invalid input");

                return;
            }

            int partIndex = Utility.findPartIndex(id);

            updatePart(partIndex, new inHouse(id, name, price, stock, min, max, machineID));

        }
        else {

            int partIndex = Utility.findPartIndex(id);

            updatePart(partIndex, new Outsourced(id, name, price, stock, min, max, change));

        }

        util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");

    }
/** This method will cancel the input and redirect to main form.
  This method will not save any input, instead it will keep the original input and then redirect to the main form.
 @param actionEvent click of cancel button.
 @throws IOException throws exception in case of I/O exception.*/
    public void ModPartCancelBut(ActionEvent actionEvent) throws IOException {

        if(Utility.confDisplay("Exit to Main Menu")) {

            util.switchScreen(actionEvent, "Main.fxml", 853, 295, "Back to Main Screen");

        }

    }

}





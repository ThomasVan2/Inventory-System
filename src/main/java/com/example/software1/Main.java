package com.example.software1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import java.io.IOException;
/**This class creates an app that manages inventory.
 @author Thomas van Eekelen
 JavaDocs are found in the javadoc folder.
 <h2> Logical Error</h2>
 <p> I encountered an error when I was trying to assign an associated part to a product.
 At first, I was overthinking this way too much. I made a list that would change the name of a part to the ID of the product and the name of the part itself and then put it in another list.
 That way, I thought I could parse it and know which part belongs to which product but I instantly encountered some problems.
 When I changed the name of the part, I didn't mean to change the actual part just a copy of this but I realized quickly that this attempt was futile.
 So, I read the requirements again and watched the webinars again. Then in one of the webinar the instructor talked about how the associated list is private.
 Then I understood how the private list worked and changed the code to prefix the product whenever adding an associated part.</p>
 <h2>Future Enhancement</h2>
 <p>A feature I would add would be a historical list or log. This way, when deleting a part or product for any reason, the user can just search for past products or parts that were in the inventory
 and quickly add them back or extract information about the historical trend of the products and parts.</p>*/
public class Main extends Application {
    /** This method sets a stage. This method will set the main stage.
      @param PrimaryStage the first stage. */
    @Override
    public void start(Stage PrimaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
        PrimaryStage.setTitle("Inventory Management System");
        PrimaryStage.setScene(new Scene(root, 853, 295));
        PrimaryStage.show();
    }
    /** This is the main method. This is the first method that gets called when you run your java program. */
    public static void main(String[] args) {
        launch();
    }

}
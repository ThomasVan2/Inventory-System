package com.example.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**This class acts as the inventory of the app.*/
public class Inventory {
   public static ObservableList<Part> allParts = FXCollections.observableArrayList();
   public static ObservableList<Product> allProducts = FXCollections.observableArrayList();
/** This method returns all parts.
  When called this method will return al parts in the allParts list.
 @return allParts. */
    public static ObservableList<Part> getAllParts() {

        return allParts;

    }
    /** This method returns all products.
      When called this method will return all products from the allProducts list.
     @return allProducts. */
    public static ObservableList<Product> getAllProducts() {

        return allProducts;

    }
/** This method will add a part to the allParts list.
  @param newPart the part to be added. */
    public static void AddPart(Part newPart) {

        allParts.add(newPart);
    }
/** This method will add a product to the allProducts list.
  @param newProduct the product to be added. */
    public static void AddProduct(Product newProduct) {

        allProducts.add(newProduct);

    }
/** This method looks up a part.
  This method will iterate through the allParts list to find the matching part by ID.
 @param partId ID of the part.
 @return Returns a part or null. */
   public static Part lookupPart(int partId) {

       ObservableList<Part> allParts = Inventory.getAllParts();

       for(int i = 0; i < allParts.size(); i++) {

           Part pa = allParts.get(i);

           if(pa.getId() == partId) {

               return pa;

           }

       }

       return null;

    }
/** This method will look up a product.
  This method will look up a product by iterating through the products list to find a matching ID.
 @param productId ID of the product.
 @return returns the product or null. */
    public static Product lookupProduct(int productId) {

        ObservableList<Product> allProducts = Inventory.getAllProducts();

        for(int i = 0; i < allProducts.size(); i++) {

            Product prod = allProducts.get(i);

            if(prod.getId() == productId) {

                return prod;

            }

        }

        return null;

    }
/** This method will look up a part.
  This method iterates through the parts list to find a matching name.
 @param name Name of the part.
 @return Returns an observable list of parts. */
   public static ObservableList<Part> lookupPart(String name) {

       //Make Part list
       ObservableList<Part> namedParts = FXCollections.observableArrayList();
       //Make Part list consisting of all parts
       ObservableList<Part> allParts = Inventory.getAllParts();
       //Iterating through allPart list
       for(Part pa : allParts) {
           //if the input string contains
           if(pa.getName().contains(name)) {

               namedParts.add(pa);

           }

       }

       return namedParts;

    }
/** This method looks up a product.
  This method will iterate through the products list to find a matching name.
 @param name Name of product.
 @return Returns an Observable list of products. */
   public static ObservableList<Product> lookupProduct(String name) {

       ObservableList<Product> namedProd = FXCollections.observableArrayList();

       ObservableList<Product> allProducts = Inventory.getAllProducts();

       for(Product prod : allProducts) {

           if(prod.getName().contains(name)) {

               namedProd.add(prod);

           }

       }

       return namedProd;

    }

/** This method will modify a part.
  This method will find the part by iterating through the parts list and if found change the values.
 @param index index of the part in the all part list.
 @param selectedPart the updated part. */
    public static void updatePart(int index, Part selectedPart) {

        if(!getAllParts().isEmpty()) {

            for (int i = -1; i <getAllParts().size(); i++) {

                if(index == i) {

                    getAllParts().set(index, selectedPart);

                }

            }

        }

    }
/** This method will update a product.
  This method will find the product by iterating through the products list and modify the product.
 @param index index of given product.
 @param newProduct the updated product. */
    public static void updateProduct(int index, Product newProduct) { //Use this when the part get send back

        if(!getAllProducts().isEmpty()) {

            for (int i = -1; i <getAllProducts().size(); i++) {

                if(index == i) {
                    getAllProducts().set(index, newProduct);

                }

            }

        }
    }
/** This method will delete a part.
  This method will delete the selected part from the all parts list.
 @param selectedPart the part to be deleted.
 @return boolean returns true or false based on success of delete operation. */
   public static boolean deletePart(Part selectedPart) {

        if(allParts.isEmpty()) {

            return false;

        }
        allParts.remove(selectedPart);

        return true;

    }
/** This method will delete a product.
  This method will delete the selected product from the all products list.
 @param selectedProduct the selected product.
 @return boolean returns whether delete operation was successful or not. */
    public static boolean deleteProduct(Product selectedProduct) {

        if(allProducts.isEmpty()) {

            return false;

        }

        allProducts.remove(selectedProduct);

        return true;


    }
/** This method will generate an ID.
  This method will generate an ID for a part based on index of the parts list.
 @return Integer returns the generated ID. */
    public static Integer generatePartID() {

       int iD = 0;

       if(allParts.size() == 0) {

           return 0;

       } else

       for(int i = 0; i < allParts.size(); i++) {

           iD = allParts.get(i).getId() + 1;

       }

       return iD;

    }
/** This method will generate an ID for the product.
  This method will generate and ID for the product based on the index of the products list.
 @return Integer returns the generated ID. */
    public static Integer generateProductID() {

        int iD = 0;

        if(allProducts.size() == 0) {

            return 0;

        } else

            for(int i = 0; i < allProducts.size(); i++) {

                iD = allProducts.get(i).getId() + 1;

            }

        return iD;

    }
/** This method will initialize the list. */
    static {
        init();
    }
/** This method fills in list for parts and products.
  This method will fill in the list with pre-defined parts and products. */
    private static void init() {

        allParts.add(new inHouse(0,"wheel",12, 40,2, 90, 6620));
        allParts.add(new inHouse(1, "engine", 45, 30,4,120,7509));
        allParts.add(new Outsourced(2, "window", 55, 32, 12, 90, "AllParts"));
        allParts.add(new Outsourced(3, "tire", 55, 79, 30, 140, "NoParts"));

        allProducts.add(new Product(0,"bicycle",1200,24, 120, 5  ));
        allProducts.add(new Product(1,"car",43000,300, 550, 20  ));
        allProducts.add(new Product(2,"scooter",5000,50, 200, 10  ));
    }


}

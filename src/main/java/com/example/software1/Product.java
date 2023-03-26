package com.example.software1;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**This is the product class.*/
public class Product {
    private ObservableList<Part> associatedParts =  FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int max;
    private int min;
    /** Constructor that defines a product.
      @param stock Quantity if product.
     @param max Maximum storage of product.
     @param min Minimum storage of product.
     @param id ID of product.
     @param name Name of product.
     @param price price of product.*/
    public Product (int id, String name, double price, int stock, int max, int min) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.max = max;
        this.min = min;

        }
/** Parameterless constructor.*/
     public Product(){

     }
/** This method sets price for the product.
  @param price Price of product.*/
    public void setPrice(double price) {
        this.price = price;
    }
/** This method sets name for the product.
  @param name sets name for the product.*/
    public void setName(String name) {
        this.name = name;
    }
/** This method sets the ID for the product.
  @param id ID for the product.*/
    public void setId(int id) {
        this.id = id;
    }
/** This method sets the minimum storage for the product.
  @param min Minimum storage for the product.*/
    public void setMin(int min) {
        this.min = min;
    }
/** This method sets the maximum storage for the product.
  @param max Maximum storage for the product.*/
    public void setMax(int max) {
        this.max = max;
    }
/** This method sets the quantity of products in stock.
  @param stock number products in stock*/
    public void setStock(int stock) {
        this.stock = stock;
    }
/** This method returns name of the product.
  @return name of product.*/
    public String getName() {
        return name;
    }
/** This method return minimum stock for the product.
  @return minimum storage.*/
    public int getMin() {
        return min;
    }
/** This method returns price of the product.
  @return price of product.*/
    public double getPrice() {
        return price;
    }
/** This method returns ID of the product.
  @return ID of product.*/
    public int getId() {
        return id;
    }
/** This method returns maximum storage of the product.
  @return maximum storage for product.*/
    public int getMax() {
        return max;
    }
/** This method returns the quantity of products.
  @return quantity of the product*/
    public int getStock() {
        return stock;
    }
/** This method adds a part to the associated parts list of the product.
  @param part associated part.*/
    public void addAssociatedPart(Part part) {

        associatedParts.add(part);

    }
/** This method removes an associated part of a product.
  @param selectedAssociatedPart The associated part the needs to be deleted.
 @return Returns of operation as a succes or not.*/
    public boolean deleteAssociatedPart(Part selectedAssociatedPart) {

        if(associatedParts.contains(selectedAssociatedPart)) {

            associatedParts.remove(selectedAssociatedPart);

            return true;
        }

        return false;
    }
/** This method returns a list of all associated parts.
  @return list of associated parts.*/
    public ObservableList<Part> getAllAssociatedParts() {

        return associatedParts;

    }

}



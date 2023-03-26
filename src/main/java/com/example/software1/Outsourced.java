package com.example.software1;
/**This class inherits attributes from the part class and is a subclass of the part class.*/
public class Outsourced extends Part{
    String companyName;
    /** Constructor that defines an outsourced part.
      @param name name of part.
      @param id ID of part.
     @param max maximum storage of part.
     @param min minimum storage of part.
     @param price Price of part.
     @param stock Inventory of part.
     @param companyName name of company that produced the part.*/
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);

        this.companyName = companyName;

    }
/** This method sets the company name for the part.
  @param companyName company name.*/
    public void setCompanyName(String companyName) {

        this.companyName = companyName;

    }
/** This method return the company name of the part.
  @return returns the company name.*/
   public String getCompanyName() {

    return this.companyName;

    }

}

package com.example.software1;
/**This class inherits attributes from the part class and is a subclass of the part class.*/
public class inHouse extends Part{
    int machineId;
    /** Constructor that defines an inHouse part.
     @param name name of part.
     @param id ID of part.
     @param max maximum storage of part.
     @param min minimum storage of part.
     @param price Price of part.
     @param stock Inventory of part.
     @param machineId Machine ID that produced the part.*/
    public inHouse(int id, String name, double price, int stock, int min, int max, int machineId) {
        super(id, name, price, stock, min, max);

        this.machineId = machineId;
    }
/** This method sets the machine ID.
 @param machineId integer input.*/
    public void setMachineId(int machineId) {

        this.machineId = machineId;

    }
/** This method returns the machine ID.
  @return returns machine ID integer.*/
  public int getMachineId() {

        return this.machineId;

    }

}

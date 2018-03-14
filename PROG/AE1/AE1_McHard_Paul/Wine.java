public class Wine {
	//The Wine class contains the constructor for the object and methods to return instance variables

	//Declare instance variable pointers.
	private String name;
	private double bottleCost;
	private int quantity;

	public Wine(String name, double bottleCost, int quantity) {
		//Constructor initialised instance variables with the values passed into it.
		this.name = name;
		this.bottleCost = bottleCost;
		this.quantity = quantity;
	}

	public String getName() { //accessor method for wine name
		return name;
	}

	public double getBottleCost() { //accessor method for cost per bottle
		return bottleCost;
	}

	public int getQuantity() {//accessor method for quantity
		return quantity;
	}

}

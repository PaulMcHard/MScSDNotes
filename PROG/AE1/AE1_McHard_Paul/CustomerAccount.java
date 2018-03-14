public class CustomerAccount {
	/* The CustomerAccount class contains the constructor, methods to return instance variables
	 * and handles balance updating in both sales and returns.
	 * Declaration of name and balance instance variables. 
	 * serviceCharge is final as value not expected to change during operation,
	 * declared here for ease of later alteration and readability.*/

	private final double serviceCharge = 0.8; //20% service charge on returns
	private String name;
	private double balance;

	public CustomerAccount(String name, double balance) { 
		//Constructor initialises instance variables with the values passed into method.
		this.name = name;
		this.balance = balance;
	}

	public String getName() {//Accessor method for Customer Name
		return name;
	}

	public double getBalance() {//Accessor method for Account Balance
		return balance;
	}


	public double updateBalanceSale(Wine wine) { //Processes a sale based on wine object passed by LWMGUI class
		//updates the instance balance but also returns the salePrice for purposes of user feedback on infoPanel.
		double salePrice = wine.getBottleCost()*wine.getQuantity();
		this.balance += salePrice;
		return salePrice;
	}

	public double updateBalanceReturn(Wine wine) {//Processes a return based on wine object passed by LWMGUI class
		//updates the instance balance but also returns the returnPrice for purposes of user feedback on infoPanel.
		double returnPrice = wine.getBottleCost()*wine.getQuantity()*serviceCharge;
		this.balance -= returnPrice;
		return returnPrice;
	}

}

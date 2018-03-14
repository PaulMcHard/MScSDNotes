import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class LWMGUI implements ActionListener {
	/* LWMGUI method creates the GUI of the main window, not initial dialog boxes - handled by AssEx1 class.
	 *  Handles events for sale or return button presses. Transaction calculations in CustomerAccount class.
	 *  CustomerAccount object passed from AssEx1 in this class's constructor.
	 *  Wine object created in object, passed to CustomerAccount in actionPerformed for both sale and return.
	 *  Class has a constructor which creates GUI, adds starting balance.
	 *  Three methods set'X'Panel create the three main JPanels and add their constituent parts. Done for ease of problem solving and readability.
	 *  Event handling method processes sale or return buttons being pressed
	 *  clearInputs and update methods involved with processing inputs.*/

	//Declare and initialise GUI elements which change in program operation, ie. need to be passed between methods.
	private	JFrame backFrame = new JFrame(); //backFrame 
	private	JButton returnButton = new JButton("Process Return");
	private	JButton saleButton = new JButton("Process Sale");

	private	TextField wineInput = new TextField();
	private	TextField quantityInput = new TextField();
	private	TextField priceInput = new TextField();	

	private	TextField lastWine = new TextField();
	private	TextField lastCost = new TextField();
	private	TextField balanceRemaining = new TextField();	

	//Declare pointers to the Customer Account and Wine objects initialised later on.
	public CustomerAccount user;
	public Wine wine;


	public LWMGUI(CustomerAccount user) { //Constructor Method

		this.user = user; //Initialise the customer account as the one passed from main
		String username = this.user.getName(); //access username
		backFrame.setSize(640, 200);
		backFrame.setResizable(false);
		backFrame.setTitle("Lilybank Wine Merchants: "+username); //put username into title bar.
		backFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*  Instructions in these methods could easily be in constructor.
		 *  Parcelled off for ease of error finding in creating GUI.
		 *  Helps with readability of how GUI is constructed also.  */
		setInputPanel();
		setButtonPanel();
		setInfoPanel();

		balanceRemaining.setText(formatBalance());  //Displays initial balance  immediately
		backFrame.setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent e) { //handles pressing of the sale and return buttons.

		if (this.checkInput()) {//checkInput returns true if all inputs valid. Passes inputs to new wine object.

			if (e.getSource() == saleButton) {//procedure for sale button pressed
				lastCost.setText(String.format("%9.02f", this.user.updateBalanceSale(wine))); 
				/*  calling either updateBalance methods in this manner processes the sale/return of the wine object passed into it
				 * 	wine object is updated prior by checkInput.
				 *  method also returns the total cost of that sale/return, which is formatted and set in the lastCost box */
			}
			else if(e.getSource() == returnButton) {
				lastCost.setText(String.format("%9.02f", this.user.updateBalanceReturn(wine)));

			}
			purchaseFeedback(); 
			/* Irrespective of which button is pressed, wine name and current balance (latter handled by CustomerAccount class).
			 * Only done if input determined to be valid, therefore within bounds of if statement on checkInput.*/
		}
		this.clearInputs(); //clear inputs irrespective of which of the two buttons is pressed and whether input is valid or not

	}

	private void setButtonPanel() { //Sets up the layout of central panel on window, which contains the two buttons
		JPanel buttonPanel = new JPanel();
		returnButton.setSize(10, 10);
		saleButton.setSize(10, 10);
		returnButton.addActionListener(this); 
		saleButton.addActionListener(this);
		buttonPanel.setLayout(new GridBagLayout());
		buttonPanel.add(saleButton);
		buttonPanel.add(returnButton);
		backFrame.add(buttonPanel, BorderLayout.CENTER);
	}
	private void setInputPanel() { //Sets up the layout of top panel, which contains the three input boxes and their labels
		JPanel inputPanel = new JPanel();
		JLabel wineLabel = new JLabel("Wine Name: ");
		wineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel quantityLabel = new JLabel("Quantity: ");
		quantityLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel priceLabel = new JLabel("Price: £");
		priceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		inputPanel.setLayout(new GridLayout(1, 6, 0, 0));
		inputPanel.add(wineLabel);
		inputPanel.add(wineInput);
		inputPanel.add(quantityLabel);
		inputPanel.add(quantityInput);
		inputPanel.add(priceLabel);
		inputPanel.add(priceInput);
		backFrame.add(inputPanel, BorderLayout.NORTH);
	}
	private void setInfoPanel() {//Sets up the layout of bottom panel, which contains the user feedback on balance and last purchase.
		JPanel infoPanel = new JPanel();
		JLabel lastWineLabel = new JLabel("Last Wine Purchased: ");
		lastWineLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel lastCostLabel = new JLabel("Last Purchase Cost: £");
		lastCostLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		JLabel balanceLabel = new JLabel("Balance(Debit +ve): £");
		balanceLabel.setHorizontalAlignment(SwingConstants.RIGHT);

		JPanel top = new JPanel();
		top.setLayout(new GridLayout(1,2, 0, 0));
		JPanel bottom = new JPanel();
		bottom.setLayout(new GridLayout(1,4, 0, 0));
		JPanel middle = new JPanel();
		middle.setSize(0, 5);
		GridBagLayout infoLayout = new GridBagLayout();
		GridBagConstraints con = new GridBagConstraints();
		infoPanel.setLayout(infoLayout);

		top.add(lastWineLabel);
		top.add(lastWine);
		lastWine.setEditable(false);
		lastWine.setBackground(Color.lightGray);
		bottom.add(lastCostLabel);
		bottom.add(lastCost);
		lastCost.setEditable(false);
		lastCost.setBackground(Color.lightGray);
		bottom.add(balanceLabel);
		bottom.add(balanceRemaining);
		balanceRemaining.setEditable(false);
		balanceRemaining.setBackground(Color.lightGray);
		con.gridx = 0;
		con.gridy = 0;
		infoLayout.setConstraints(top, con);
		infoPanel.add(top);
		con.gridx = 0;
		con.gridy = 1;
		infoLayout.setConstraints(middle, con);
		infoPanel.add(middle);
		con.gridx = 0;
		con.gridy = 2;
		infoLayout.setConstraints(bottom, con);
		infoPanel.add(bottom);
		backFrame.add(infoPanel, BorderLayout.SOUTH);

	}

	private boolean checkInput() {
		/* Method takes the inputs from each text field.
		 * It checks the validity as per the specification of all three
		 * If all three are valid it creates a creates a new wine object with the given input parameters
		 * directs the class global wine pointer to this new wine, so that it maybe used for other methods.
		 * This is fine as program only ever needs to keep track of a single wine at a time, as it can only process sales item by item.
		 * Method returns a boolean variable to tell actionPerfomed method if inputs were valid at time of button press. 
		 * True only if all inputs are valid. */

		int quantity = 0;
		double bottleCost = 0;
		String name = wineInput.getText();
		if (name.equals("")) {//Only criteria on name is to not be empty.
			JOptionPane.showMessageDialog(null, "Require a Wine Name input","Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			quantity = Integer.parseInt(quantityInput.getText());
		}
		catch (NumberFormatException nfx) {//stops the method here if quantity not an integer, gives according error message
			JOptionPane.showMessageDialog(null, "Require an integer value for Quantity input", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
		try {
			bottleCost = Double.parseDouble(priceInput.getText());
		}
		catch (NumberFormatException nfx) {//stops method here if price not a double, gives according error message.
			JOptionPane.showMessageDialog(null, "Require a valid Price input", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}


		if ( quantity > 0 && bottleCost > 0) {
			/* Only if input types are all valid; quantity and cost are positive valued, then wine object is created and method returns true
			 * processing either a sale or return respectively in actionPerformed. */
			wine = new Wine(wineInput.getText(),bottleCost,quantity);
			return true;
		}
		else { //Shows an error method if types are valid but negative/zero entries present for quantity or cost
			JOptionPane.showMessageDialog(null, "Quantity and Price require positive valued, non-zero inputs.", "Error Message", JOptionPane.ERROR_MESSAGE);
			return false;
		}
	}

	private void clearInputs() { //Clears the inputs 
		wineInput.setText(" ");
		quantityInput.setText(" ");
		priceInput.setText(" ");
	}

	private void purchaseFeedback() {
		/* Updates text in wine name and balance remaining.
		 * Both are independent of whether sale or return processed:
		 * 		wine name is not involved in calculations
		 * 		balance is an instance variable of CustomerAccount and can be accessed the same regardless of which transaction is processed
		 * Last cost is not updated by this method as it is passed from the sale/return methods respectively in both cases */
		lastWine.setText(wine.getName());
		balanceRemaining.setText(formatBalance());
	}

	public String formatBalance() {//Formats balance display to two decimal places, and negative balances as positive with CR (credit)
		if (this.user.getBalance() < 0) {
			String output = String.format("%9.02f", -this.user.getBalance())+"CR";
			/*number will be negative, so invert to remove minus sign and add CR. 
			 * Inversion only part of string formatting so does not affect stored balance value for futher transactions.*/
			return output; //returns balance formatted as a strung
		}
		else {
			return String.format("%9.02f", this.user.getBalance()); //simply format to two dp for positive (debit) balanaces.
		}
	}


}

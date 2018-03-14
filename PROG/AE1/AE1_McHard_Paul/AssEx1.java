import javax.swing.JOptionPane;
public class AssEx1 {
	/*Single lined style in main removes unnecessary pointers which would exist in main method only to pass objects to ultimately the GUI.
	 *Obtaining name and balance by calling methods with String and double returns respectively directly in the Customer Account constructor
	 *saves use of two unneeded name and balance pointers in the main.
	 *Likewise, creating the customer account within the constructor of the GUI saves on a redundant pointer to the Customer Account object,
	 *as this object is only accessed in the LWMGUI class, and its only purpose in the main is to be passed to the GUI, thus a pointer within the GUI
	 *is sufficient, and one here would be redundant */
	public static void main(String[] args) {
		new LWMGUI(new CustomerAccount(promptName(), promptBalance()));
	}

	public static String promptName() {
		//prompts user for name with dialog box.
		//handles quit options for first dialog box as per specification
		String name = JOptionPane.showInputDialog("Please enter the name on the account:");
		if (name.equals(JOptionPane.CLOSED_OPTION) || name.equals(JOptionPane.CANCEL_OPTION) || name.equals("")) {
			System.exit(0);
			return null;
		}
		return name; //return name ends method, gives name String as result of method.
	}

	public static double promptBalance() {
		//Prompts user for balance with dialog box.
		//handles quit options for second dialog box as per specification.

		double balance;
		for(;;) {//Infinite for loop will continue to prompt user until a valid input, or quit, is received.
			//showInputDialog returns a String, double value must be extracted from this input, use parseDouble.
			String balinput = JOptionPane.showInputDialog("Enter inital credit balance of account: ");
			if (balinput.equals(JOptionPane.CLOSED_OPTION) || balinput.equals(JOptionPane.CANCEL_OPTION)) {
				System.exit(0);
				return 0;
			}
			try {
				balance = Double.parseDouble(balinput);
				//Customer enters initial value as credit +ve, need to invert for specified debit +ve system.
				//The IF is just to prevent -0, which is bad maths.
				if (balance != 0) {
					balance *= -1;
				}
				return balance;//return ends the method and breaks infinite loop, so break; statement redundant.
				//gives value of balance as result of method.
			}
			catch (NumberFormatException nfx) {//message displayed when an invalid input received, will repeat indefinitely until input valid.
				JOptionPane.showMessageDialog(null, "Initial balance must be a double:", "Error Report", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}

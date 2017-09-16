
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BankInterestViewer 
{
	public static void main(String[] args)
	{
		JFrame frame = new JFrame();
		frame.setSize(200, 100);
		frame.setTitle("Bank Viewer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JButton button = new JButton("Add Interest");

		//Label for displaying the balance.

	
		JPanel panel = new JPanel();

		final BankAccount account = new BankAccount(1000);
		final JLabel label = new JLabel("Banalce: $" + account.getBalance());

		class addInterestListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				//The listener method accesses the account variable from the surrounding block. 
				double interest = account.getBalance() * 0.005; //10%
				account.deposit(interest);
				//The listener method also accesses the label variable.
				label.setText("Balance: $" + account.getBalance());

			}

		};
		panel.add(button);
		panel.add(label);
		frame.add(panel);
		ActionListener listener = new addInterestListener();
		button.addActionListener(listener);
		frame.setVisible(true);

	}
}

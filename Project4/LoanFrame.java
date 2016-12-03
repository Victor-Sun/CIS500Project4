import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 * 
 * @author Victor
 *
 */
public class LoanFrame extends JFrame{
	private JButton add, search, save, summary, calculate;
	private Panel panel;
	private JPanel aNorth, aCenter, aSouth;
	private JInternalFrame aFrame, seFrame, suFrame;
	private JDesktopPane desktop;
	private ButtonGroup b;
	private JRadioButton simple, amortized;
	private JLabel alName, alPrincipal, alLength, alRate, alPayment, alPay;
	private JTextField atName, atPrincipal, atLength, atRate;
	private JComboBox cRate;
	private JOptionPane alError;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEGHT = 700;

	public LoanFrame(){
		super("Loan Manager");
		setSize(FRAME_WIDTH,FRAME_HEGHT);
		
		//Initializing variables
		add = new JButton("Add");
		search = new JButton("Search");
		save = new JButton("Save");
		summary = new JButton("Summary");
		calculate = new JButton("Calculate");
		simple = new JRadioButton("Simple");
		amortized = new JRadioButton("Amortized");

		alName = new JLabel("Name: ");
		alPrincipal = new JLabel("Principal: ");
		alLength = new JLabel("Length: ");
		alRate = new JLabel("Interest Rate: ");
		alPay = new JLabel("Montly Payment: ");
		alPayment = new JLabel("$$$$.$$");

		atName = new JTextField();
		atPrincipal = new JTextField();
		atLength = new JTextField();
		atRate = new JTextField();

		alError = new JOptionPane("Error");
		
		Double arRate[] = {0.03, 0.04, 0.05, 0.06, 0.07};
		JComboBox<Double> cRate = new JComboBox<>(arRate);
		
		aNorth = new JPanel();
		aCenter = new JPanel();
		aCenter.setLayout(new GridLayout(0,2));
		aSouth = new JPanel();
		panel = new Panel();

		b = new ButtonGroup();
		b.add(amortized);
		b.add(simple);
		simple.setSelected(true);

		//Add buttons to the desktop panel
		panel.add(add);
		panel.add(search);
		panel.add(save);
		panel.add(summary);

		//Add Loan Internal Frame
		aNorth.setBorder(new TitledBorder(new EtchedBorder(), "Loan Type"));
		aCenter.setBorder(new TitledBorder(new EtchedBorder(), "Loan Info"));
		aNorth.add(simple);
		aNorth.add(amortized);
		aCenter.add(alName);
		aCenter.add(atName);
		aCenter.add(alPrincipal);
		aCenter.add(atPrincipal);
		aCenter.add(alLength);
		aCenter.add(atLength);
		aCenter.add(alRate);
		aCenter.add(cRate);
		aCenter.add(alPay);
		aCenter.add(alPayment);
		aSouth.add(calculate);

		add(panel,BorderLayout.SOUTH);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
		this.setLocationRelativeTo(null);
		desktop = new JDesktopPane();
		desktop.setOpaque(true);
		add(desktop, BorderLayout.CENTER);

		ButtonListener l = new ButtonListener();
		add.addActionListener(new OpenListener());
		search.addActionListener(new OpenListener());
		summary.addActionListener(new OpenListener());
		calculate.addActionListener(l);
		save.addActionListener(l);
	}

	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == add){
				if ((aFrame == null) || (aFrame.isClosed())) {
					aFrame = new JInternalFrame("Add Loan", true, true, true, true);
					aFrame.setBounds(50, 50, 600, 300);
					desktop.add(aFrame, new Integer(1));
					aFrame.setVisible(true);
					aFrame.setLayout(new BorderLayout());

					aFrame.add(aNorth, BorderLayout.NORTH);
					aFrame.add(aCenter, BorderLayout.CENTER);
					aFrame.add(aSouth, BorderLayout.SOUTH);
				}
			}
			if(e.getSource() == search){
				if ((seFrame == null) || (seFrame.isClosed())) {
					seFrame = new JInternalFrame("Search Loans", true, true, true, true);
					seFrame.setBounds(50, 50, 200, 100);
					desktop.add(seFrame, new Integer(1));
					seFrame.setVisible(true);
				}
			}
			if(e.getSource() == summary){
				if ((suFrame == null) || (suFrame.isClosed())) {
					suFrame = new JInternalFrame("Loan Summary", true, true, true, true);
					suFrame.setBounds(50, 50, 200, 100);
					desktop.add(suFrame, new Integer(1));
					suFrame.setVisible(true);
				}
			}			
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == calculate){
				if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(aFrame, "Principal and Length cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty() == false){
					JOptionPane.showMessageDialog(aFrame, "Principal cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() == false && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(aFrame, "Length cannot be empty!");
				} else {
					try{
						double iP = Double.parseDouble(atPrincipal.getText());
					} catch(Exception exc){
						JOptionPane.showMessageDialog(aFrame, "Principal can only be a number!");
					}

					try{
						int iL = Integer.parseInt(atLength.getText());
					} catch(Exception exc){
						JOptionPane.showMessageDialog(aFrame, "Length can only be a number!");
					}
				}
				
				
			}
		}
	}
}

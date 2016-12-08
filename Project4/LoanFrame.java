import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;

import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
/**
 * 
 * @author Victor
 *
 */
public class LoanFrame extends JFrame{
	private JButton add, search, save, summary, aCalculate, sEdit, sDelete, sSearch;
	private Panel panel;
	private JPanel aNorth, aCenter, aSouth, sNorth, sCenter, sSouth;
	private JInternalFrame aFrame, seFrame, suFrame;
	private JDesktopPane desktop;
	private ButtonGroup b, sb;
	private JRadioButton aSimple, aAmortized, sSimple, sAmortized;
	private JLabel alPayment;
	private JTextField atName, atPrincipal, atLength, sName, sPrinciple, sLength, sPayment;
	private static final int FRAME_WIDTH = 1000;
	private static final int FRAME_HEGHT = 800;
	private Double arRate[] = {0.03, 0.035, 0.04, 0.045, 0.05, 0.055, 0.06, 0.065, 0.07};
	private JComboBox<Double> cRate = new JComboBox<>(arRate);
	private JComboBox<Double> scInterestRate = new JComboBox<>(arRate);
	private LoanManager loan = new LoanManager();
	private JTextArea sSummary = new JTextArea();

	public LoanFrame(){
		super("Loan Manager");
		setSize(FRAME_WIDTH,FRAME_HEGHT);
		//		loan.loadLoan();

		//Initializing variables
		add = new JButton("Add");
		search = new JButton("Search");
		save = new JButton("Save");
		summary = new JButton("Summary");
		aCalculate = new JButton("Calculate");

		sEdit = new JButton("Edit");
		sEdit.setEnabled(false);
		sDelete = new JButton("Delete");
		sDelete.setEnabled(false);
		sSearch = new JButton("Search");

		aSimple = new JRadioButton("Simple");
		aAmortized = new JRadioButton("Amortized");
		sSimple = new JRadioButton("Simple");
		sAmortized = new JRadioButton("Amortized");

		//Add Loan Labels
		alPayment = new JLabel("$$$$.$$");

		atName = new JTextField();
		atPrincipal = new JTextField();
		atLength = new JTextField();
		sName = new JTextField();
		sPrinciple = new JTextField();
		sLength = new JTextField();
		sPayment = new JTextField();

		aNorth = new JPanel();
		aCenter = new JPanel();
		aCenter.setLayout(new GridLayout(0,2));
		aSouth = new JPanel();

		sNorth = new JPanel();
		sCenter = new JPanel();
		sCenter.setLayout(new GridLayout(0,2));
		sSouth = new JPanel();

		panel = new Panel();

		b = new ButtonGroup();
		b.add(aAmortized);
		b.add(aSimple);
		aSimple.setSelected(true);
		sb = new ButtonGroup();
		sb.add(sAmortized);
		sb.add(sSimple);
		sSimple.setSelected(true);

		//Add buttons to the desktop panel
		panel.add(add);
		panel.add(search);
		panel.add(save);
		panel.add(summary);

		//Add Loan Internal Frame
		aNorth.setBorder(new TitledBorder(new EtchedBorder(), "Loan Type"));
		aCenter.setBorder(new TitledBorder(new EtchedBorder(), "Loan Info"));
		aNorth.add(aSimple);
		aNorth.add(aAmortized);
		aCenter.add(new JLabel("Name: "));
		aCenter.add(atName);
		aCenter.add(new JLabel("Principal: "));
		aCenter.add(atPrincipal);
		aCenter.add(new JLabel("Length: "));
		aCenter.add(atLength);
		aCenter.add(new JLabel("Interest Rate: "));
		aCenter.add(cRate);
		aCenter.add(new JLabel("Montly Payment: "));
		aCenter.add(alPayment);
		aSouth.add(aCalculate);

		//Search Loan Internal Frame
		sNorth.setBorder(new TitledBorder(new EtchedBorder(), "Loan Type"));
		sNorth.add(new JLabel("Loan Type: "));
		sNorth.add(sSimple);
		sNorth.add(sAmortized);
		sCenter.setBorder(new TitledBorder(new EtchedBorder(), "Loan Info"));
		sCenter.add(new JLabel("Name: "));
		sCenter.add(sName);
		sCenter.add(new JLabel("Principal: "));
		sCenter.add(sPrinciple);
		sCenter.add(new JLabel("Interest Rate: "));
		sCenter.add(scInterestRate);
		sCenter.add(new JLabel("Length: "));
		sCenter.add(sLength);
		sCenter.add(new JLabel("Monthly Payment: "));
		sCenter.add(sPayment);
		sSouth.add(sEdit);
		sSouth.add(sDelete);
		sSouth.add(sSearch);

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
		aCalculate.addActionListener(l);
		save.addActionListener(l);
		sSearch.addActionListener(l);
		sDelete.addActionListener(l);


	}

	public void refresh(){
		sSummary.setText("Total Loans: " + loan.totalLoan() + 
				"\nAmount of Simple Loans: " + loan.totalSimpleLoan() + 
				"\nAmount of Amortized Loans: " + loan.totalAmortizedLoan() + 
				"\nTotal amount of money borrowed: " + loan.totalMoney());
	}

	class OpenListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == add){
				if ((aFrame == null) || (aFrame.isClosed())) {
					aFrame = new JInternalFrame("Add Loan", true, true, true, true);
					aFrame.setBounds(50, 50, 400, 300);
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
					seFrame.setBounds(450, 50, 400, 300);
					desktop.add(seFrame, new Integer(1));
					seFrame.setVisible(true);

					seFrame.add(sNorth, BorderLayout.NORTH);
					seFrame.add(sCenter, BorderLayout.CENTER);
					seFrame.add(sSouth, BorderLayout.SOUTH);

					sPrinciple.setEnabled(false);
					scInterestRate.setEnabled(false);
					sLength.setEnabled(false);
					sPayment.setEnabled(false);
				}
			}
			if(e.getSource() == summary){
				if ((suFrame == null) || (suFrame.isClosed())) {
					suFrame = new JInternalFrame("Loan Summary", true, true, true, true);
					suFrame.setBounds(250, 350, 400, 300);
					desktop.add(suFrame, new Integer(1));
					suFrame.setVisible(true);

					sSummary.setEditable(false);
					sSummary.setText("Total Loans: " + loan.totalLoan() + 
							"\nAmount of Simple Loans: " + loan.totalSimpleLoan() + 
							"\nAmount of Amortized Loans: " + loan.totalAmortizedLoan() + 
							"\nTotal amount of money borrowed: " + loan.totalMoney());
					suFrame.add(sSummary);
				}
			}			
		}
	}

	private class ButtonListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			if(e.getSource() == aCalculate){
				if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Principal and Length cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty() == false){
					JOptionPane.showMessageDialog(null, "Principal cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() == false && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Length cannot be empty!");
				} else if(!atPrincipal.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Principal can only be a number!");
				} else if(!atLength.getText().matches("[0-9]+")){		
					JOptionPane.showMessageDialog(null, "Length can only be a number!");	
				} else {
					if(aSimple.isSelected()){
						loan.addLoan(atName.getText(), (Double) cRate.getSelectedItem() , Integer.parseInt(atLength.getText()), Double.parseDouble(atPrincipal.getText()), "Simple");
						SimpleLoan l = new SimpleLoan(atName.getText(), (Double) cRate.getSelectedItem() , Integer.parseInt(atLength.getText()), Double.parseDouble(atPrincipal.getText()));
						l.calcMonthPayment();
						DecimalFormat format = new DecimalFormat(".##");
						alPayment.setText((format.format(l.getMonthlyPayment())));
					} else {
						loan.addLoan(atName.getText(), (Double) cRate.getSelectedItem() , Integer.parseInt(atLength.getText()), Double.parseDouble(atPrincipal.getText()), "Amortized");
						AmortizedLoan l = new AmortizedLoan(atName.getText(), (Double) cRate.getSelectedItem() , Integer.parseInt(atLength.getText()), Double.parseDouble(atPrincipal.getText()));
						l.calcMonthPayment();
						DecimalFormat format = new DecimalFormat(".##");
						alPayment.setText((format.format(l.getMonthlyPayment())));
					}
					refresh();
				}
			}

			//Search Loan
			if(e.getSource() == sSearch){
				if(sName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Name cannot be empty when searching!");
				} else if(!loan.exists(sName.getText())){
					JOptionPane.showMessageDialog(null, "Loan does not exist!");
				} else {
					JOptionPane.showMessageDialog(null, "Loan found!");
					if(loan.isSimple(sName.getText())){
						sSimple.setSelected(true);
					} else {
						sAmortized.setSelected(true);
					}
					loan.SearchLoan(sName.getText());
					sPrinciple.setText(Double.toString(loan.getPrinciple(sName.getText())));
					scInterestRate.setSelectedItem((double)loan.getInterest(sName.getText()));
					sLength.setText(Integer.toString(loan.getLength(sName.getText())));
					sPayment.setText(Double.toString(loan.getPayment(sName.getText())));

					sPrinciple.setEnabled(true);
					scInterestRate.setEnabled(true);
					sLength.setEnabled(true);
					sPayment.setEnabled(true);
					sEdit.setEnabled(true);
					sDelete.setEnabled(true);
				}
			}
			if(e.getSource() == sDelete){
				int d = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the record?", "Delete", JOptionPane.YES_NO_OPTION);
				if (d == JOptionPane.YES_OPTION) {
					loan.deleteLoan(sName.getText());
					JOptionPane.showMessageDialog(null, "Record Deleted!");
				} else {
					JOptionPane.showMessageDialog(null, "Nothing Happened!");
				}
				refresh();
			}
			if(e.getSource() == sEdit){
				if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Principal and Length cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty() == false){
					JOptionPane.showMessageDialog(null, "Principal cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() == false && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Length cannot be empty!");
				} else if(!atPrincipal.getText().matches("[0-9]+")){
					JOptionPane.showMessageDialog(null, "Principal can only be a number!");
				} else if(!atLength.getText().matches("[0-9]+")){		
					JOptionPane.showMessageDialog(null, "Length can only be a number!");	
				} else {
					//TODO Add method to edit record
				}
				refresh();
			}
			if(e.getSource() == save){
				loan.saveSession();
				JOptionPane.showMessageDialog(null, "Session saved!");
			}
		}
	}
}
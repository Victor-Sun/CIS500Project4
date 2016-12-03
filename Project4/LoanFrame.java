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
	private JButton add, search, save, summary, aCalculate, sEdit, sDelete, sSearch;
	private Panel panel;
	private JPanel aNorth, aCenter, aSouth, sNorth, sCenter, sSouth;
	private JInternalFrame aFrame, seFrame, suFrame;
	private JDesktopPane desktop;
	private ButtonGroup b, sb;
	private JRadioButton aSimple, aAmortized, sSimple, sAmortized;
	private JLabel alPayment, alPay;
	private JTextField atName, atPrincipal, atLength, sName, sPrincipal, sLength, sPayment;
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
		sPrincipal = new JTextField();
		sLength = new JTextField();
		sPayment = new JTextField();
		
		alError = new JOptionPane("Error");
		
		Double arRate[] = {0.03, 0.04, 0.05, 0.06, 0.07};
		JComboBox<Double> cRate = new JComboBox<>(arRate);
		JComboBox<Double> scInterestRate = new JComboBox<>(arRate);
		
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
		sCenter.add(sPrincipal);
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
					seFrame.setBounds(50, 50, 400, 300);
					desktop.add(seFrame, new Integer(1));
					seFrame.setVisible(true);
					
					seFrame.add(sNorth, BorderLayout.NORTH);
					seFrame.add(sCenter, BorderLayout.CENTER);
					seFrame.add(sSouth, BorderLayout.SOUTH);
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
			//Add Loan
			if(e.getSource() == aCalculate){
				if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Principal and Length cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() && atLength.getText().isEmpty() == false){
					JOptionPane.showMessageDialog(null, "Principal cannot be empty!");
				} else if(atPrincipal.getText().isEmpty() == false && atLength.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Length cannot be empty!");
				} else {
					try{
						double iP = Double.parseDouble(atPrincipal.getText());
					} catch(Exception exc){
						JOptionPane.showMessageDialog(null, "Principal can only be a number!");
					}

					try{
						int iL = Integer.parseInt(atLength.getText());
					} catch(Exception exc){
						JOptionPane.showMessageDialog(null, "Length can only be a number!");
					}
				}
				//TODO Calculate
			}
			
			//Search Loan
			if(e.getSource() == sSearch){
				if(sName.getText().isEmpty()){
					JOptionPane.showMessageDialog(null, "Name cannot be empty when searching!");
				} else {
					sEdit.setEnabled(true);
					sDelete.setEnabled(true);
				}
			}
			if(e.getSource() == sDelete){
				int d = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete the record?", "Delete", JOptionPane.YES_NO_OPTION);
		        if (d == JOptionPane.YES_OPTION) {
		        	//TODO Add method to delete record
		        	JOptionPane.showMessageDialog(null, "Record Deleted!");
		          }
		          else {

		          }
			}
			if(e.getSource() == sEdit){
				//TODO Add method to edit record
			}
		}
	}
}

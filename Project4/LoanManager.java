import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.List;

import javax.swing.JOptionPane;

public class LoanManager {

	ArrayList <Loan> loans;
	Scanner in = new Scanner(System.in);
	File fileName = new File("Loan.txt");
	String line;
	ArrayList fileloans = new ArrayList();
	String loanType = "Simple";
	double monthlyPayment;
	
	public LoanManager() {
		loans = new ArrayList <Loan>();
	}

	public String getLoanType(){
		return this.loanType;
	}
	
	public void setLoanType(String type){
		this.loanType = type;
	}
	
	public void loadLoan() {
		try{
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			if (!input.ready()){
				throw new IOException();
			}
			while ((line = input.readLine()) != null){
				fileloans.add(line);
			}
			input.close();
		}
		catch (IOException e)
		{
			System.out.println(e);
		}

		int sz = fileloans.size();
		for (int i = 0; i < sz; i++){
			System.out.println(fileloans.get(i));
		}
	}


	public void addLoan(String name, double interestRate, int length, double principle) {
		//		System.out.println("What type of Loan do you need?");
		if (loanType.equals("Simple")) {
			SimpleLoan simple = new SimpleLoan(name, interestRate, length, principle);
			simple.calcMonthPayment();
			loans.add(simple);
//			System.out.println(simple.toString());
//			System.out.println(loans);
			Collections.sort(loans);
		} else if (loanType.equals("Amortized")) {
			AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
			amortized.calcMonthPayment();
			loans.add(amortized);
//			System.out.println(amortized.toString());
//			System.out.println(loans);
			Collections.sort(loans);
		} else {
			JOptionPane.showMessageDialog(null, "Loan not Supported. Please Enter either Simple or Amortized");
		}
		try {

			FileWriter fw = new FileWriter(fileName, true);
			Writer output = new BufferedWriter(fw);
			int sz = loans.size();
			for (int i = 0; i < sz; i++) {
				output.append(loans.get(i) + "\n");
				loans.clear();
			}
			output.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Creating File!");
		}

	}



	public void deleteLoan(String name) {
		loadLoan();
		int i;

		for (i = 0; i < fileloans.size(); ++i) {
			if (fileloans.contains(name)) {
				System.out.println("Present! at " + i);
				fileloans.remove(i);
				System.out.println(fileloans);
				break;
			}

			//else JOptionPane.showMessageDialog(null,"User does not exist!");

		}
	}

	public void SearchLoan(String names)
	{

		int i;
		Loan search;

		for (i = 0; i < fileloans.size(); ++i) {
			if (fileloans.contains(names)) {
				search = loans.get(i);
				System.out.println(search);
				break;
			}
			//else JOptionPane.showMessageDialog(null,"User does not exist!");
		}


	}

	public void totalMoney()
	{
		int i;
		double totalBorrowed = 0;

		for (i = 0; i < loans.size(); ++i) {

			totalBorrowed = totalBorrowed + loans.get(i).principle;

			//break;
		}
		System.out.println(totalBorrowed);

	}

	public void totalLoan()
	{
		int i;

		for (i = 0; i < loans.size(); ++i) {}
		System.out.println(i);

	}

	public void totalSimpleLoan()

	{

		int i;
		int x = 0;

		for (i = 0; i < loans.size(); ++i) {
			if (loans.get(i).toString().contains("Simple")) {
				x = x + 1;
			}
			System.out.println(x);
		}
	}

	public void totalAmortizedLoan()
	{

		int i;
		int x = 0;

		for (i = 0; i < loans.size(); ++i) {
			if (loans.get(i).toString().contains("Amortized")) {
				x = x + 1;
			}
			System.out.println(x);
		}
	}

	public String toString()
	{

		return "List";
	}
}

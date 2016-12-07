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
import java.util.Map;

import javax.swing.JOptionPane;

public class LoanManager {

	ArrayList <Loan> loans;
	Scanner in = new Scanner(System.in);
	File fileName = new File("Loan.txt");
	String line;
	ArrayList fileloans = new ArrayList();
	String loanType = "Simple";
	double monthlyPayment;
	int pos=0;
	
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
			Collections.sort(loans);
		} else if (loanType.equals("Amortized")) {
			AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
			amortized.calcMonthPayment();
			loans.add(amortized);
			Collections.sort(loans);
		} else {
			JOptionPane.showMessageDialog(null, "Loan not Supported. Please Enter either Simple or Amortized");
		}
		
		//This needs to be in a seperate method/function
//		try {
//			FileWriter fw = new FileWriter(fileName, true);
//			Writer output = new BufferedWriter(fw);
//			int sz = loans.size();
//			for (int i = 0; i < sz; i++) {
//				output.append(loans.get(i) + "\n");
//				loans.clear();
//			}
//			output.close();
//		} catch (Exception e) {
//			JOptionPane.showMessageDialog(null, "Error Creating File!");
//		}
	}
	
	public void addLoan(Loan loan){
		if (loanType.equals("Simple")) {
			SimpleLoan simple = (SimpleLoan) loan;
			simple.calcMonthPayment();
			loans.add(simple);
			Collections.sort(loans);
			
			
		} else if (loanType.equals("Amortized")) {
			AmortizedLoan amortized = (AmortizedLoan) loan;
			amortized.calcMonthPayment();
			loans.add(amortized);
			Collections.sort(loans);
		} else {
			JOptionPane.showMessageDialog(null, "Loan not Supported. Please Enter either Simple or Amortized");
		}
	}

	public boolean exists(String names){
		for (int i = 0; i < loans.size(); ++i) {
			Loan tmpL = loans.get(i);
			if (tmpL.getName() == names) {
				return true;
			}
		}
		
		return false;
	}

	public void deleteLoan(String name){
		for(int i = 0; i < loans.size(); ++i) {
			Loan tmpL = loans.get(i);
			if (tmpL.getName() == name){
				loans.remove(i);
				break;
			}
		}
	}

	public Loan SearchLoan(String names){
		Loan search = null;
		for(int i = 0; i < loans.size(); ++i){
			Loan tmpL = loans.get(i);
			if(tmpL.getName() == names) {
				search = loans.get(i);
				break;
			}
		}
		return search;
	}

	public double totalMoney(){
		double totalBorrowed = 0;

		for (int i = 0; i < loans.size(); ++i) {
			totalBorrowed += loans.get(i).principle;
		}
		return totalBorrowed;
	}

	public int totalLoan(){
		return loans.size();
	}

	public int totalSimpleLoan(){
		int x = 0;

		for (int i = 0; i < loans.size(); ++i) {
			if (loans.get(i).toString().contains("Simple")) {
				x++;
			}
		}
		return x;
	}

	public int totalAmortizedLoan(){
		int x = 0;

		for (int i = 0; i < loans.size(); ++i) {
			if (loans.get(i).toString().contains("Amortized")) {
				x++;
			}
		}
		return x;
	}

	public String toString(){
		return loans.toString();	
	}
}

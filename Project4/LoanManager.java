//Silll Incomplete. Working on Completing the search and edit Methods

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class LoanManager 
{
	private ArrayList<Loan> loans;
	Scanner in = new Scanner(System.in);
	File fileName = new File("Loan.txt");
	String currentSelectedLoanType;


public LoanManager() 
{
	this.loans = new ArrayList<Loan>();
	this.currentSelectedLoanType = "";
	// add methods as needed

}

public void addLoan(String name, double interestRate, int length, double principle)
{

	String loanType = this.GetCurrentLoanType();
	
	if(loanType.equals("Simple"))
	{				
		simpleLoan simple = new simpleLoan(name, interestRate, length, principle);
		simple.calcMonthPayment(); 
		//System.out.println(simple.toString());
		loans.add(new simpleLoan(name, interestRate, length, principle));
	}
	
	else if (loanType.equals("Amortized"))
	{
		AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
		amortized.calcMonthPayment(); 
		//System.out.println(amortized.toString());
		loans.add(amortized);	
	}
	
	else
	{
		JOptionPane.showMessageDialog(null,"Loan not Supported. Please Enter either Simple or Amortized");
	}
	
	
	try {
		FileWriter fw = new FileWriter(fileName, true);
		Writer output = new BufferedWriter(fw);
		int sz = loans.size();
		for (int i = 0; i < sz; i++){
		output.write(loans.get(i).toString() + "\n");
		output.close();
		}
	}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, "Error Creating File!");
		}
	
	// Finally reset the currentSelectedLoanType after adding
		this.GetCurrentLoanType();
}

public void editLoan(String name, double interestRate, int length, double principle)
{
	//System.out.println("What type of Loan do you need?");
	String loanType = this.GetCurrentLoanType();
	
	int pos = loans.indexOf(name);
	
	if(loanType.equals("Simple"))
	{
					
	simpleLoan simple = new simpleLoan(name, interestRate, length, principle);
	simple.calcMonthPayment(); 
	loans.set(pos, new simpleLoan(name, interestRate, length, principle));
	}
	
	else if (loanType.equals("Amortized"))
	{
	
		AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
		amortized.calcMonthPayment(); 
		System.out.println(amortized.toString());
		loans.set(pos, new AmortizedLoan(name, interestRate, length, principle));
		
	}

		Collections.sort(loans);
		

			
		}


public void deleteLoan(String name)
{
	
	if(loans.contains(name))
	{
	loans.remove(name);
	}
	else System.out.println("User does not exist!");



}

// This should if found return a loan type object
public void SearchLoan(String name)

{
	if(loans.contains(name))
	{
	loans.remove(name);
	}
	else System.out.println("User does not exist!");


}

// This can be also called search loan and the above method can be replaced with this
// Returns a specific loan or loans when found or not found it will return empty list
public ArrayList<Loan> GetLoan(String name){
	ArrayList<Loan> foundResults = new ArrayList<Loan>();
	
	for (Loan loan : this.loans){
			if(loan.name == name){
				foundResults.add(loan);
			}
		}
		
		return foundResults;	
}

// Returns list of all loans
public ArrayList<Loan> GetAllLoansList(){
	return this.loans;
}


// get the current loan type status | empty means nothing selected
public String GetCurrentLoanType(){
	return this.currentSelectedLoanType;
}

// Set the current loan type, based on which radio button the user selects
// Options: Pass in either Simple or Amortized
public void SetCurrentLoanType(String loanType){
	this.currentSelectedLoanType = loanType;
}

// Reset the current loan type property
public void ResetCurrentLoanType(){
	this.currentSelectedLoanType = "";
}

public String toString() 

{

	return "List";
}
}


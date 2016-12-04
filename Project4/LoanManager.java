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


public LoanManager() 
{
	loans = new ArrayList<Loan>();
	// add methods as needed

}

public void addLoan(String name, double interestRate, int length, double principle)
{

System.out.println("What type of Loan do you need?");
String loanType = in.next();

{
	
	if(loanType.equals("Simple"))
	{
					
	simpleLoan simple = new simpleLoan(name, interestRate, length, principle);
	simple.calcMonthPayment(); 
	System.out.println(simple.toString());
	loans.add(new simpleLoan(name, interestRate, length, principle));
	}
	
	else if (loanType.equals("Amortized"))
	{
	
		AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
		amortized.calcMonthPayment(); 
		System.out.println(amortized.toString());
		loans.add(amortized);	

}
	else
	{
		JOptionPane.showMessageDialog(null,"Loan not Supported. Please Enter either Simple or Amortized");
	}
	
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
}

public void editLoan(String name, double interestRate, int length, double principle)
{
	//System.out.println("What type of Loan do you need?");
	String loanType = in.next();
	
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

public void SearchLoan(String name)

{
	if(loans.contains(name))
	{
	loans.remove(name);
	}
	else System.out.println("User does not exist!");


}




public String toString() 

{

	return "List";
}
}


//Added more methods to load the file into the array list when the program starts, cleaned up the add loan method, 
//updated the delete and search loan methods. What is left is to read the data from file into the arraylist to update, search and
//delete while it is in memory and then write updated arraylist back to file.


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

public class LoanManager 
{
	private ArrayList<Loan> loans;
	Scanner in = new Scanner(System.in);
	static File fileName = new File("Loan.txt");


public LoanManager() 
{
	loans = new ArrayList<Loan>();

}


public static ArrayList <Loan> loadLoan() {
	
	ArrayList <Loan> loans = new ArrayList();
	

try {
		
		BufferedReader input = new BufferedReader(new FileReader(fileName));
		String line = input.readLine();
		if (!input.ready()){
			throw new IOException();
		}
		while ((line = input.readLine()) != null){
		
			loans.add(line);
			
            
		}
		input.close();
}
catch (IOException e){
	System.out.println(e);
}

int sz = loans.size();
for (int i = 0; i < sz; i++)
{
	System.out.println(loans.get(i).toString());
}
return loans;
		

}

	
public static ArrayList<Loan> GetArrayList()
{
	return loadLoan();
}

public static void viewArrayList() {
    System.out.println(GetArrayList());
}

public void addLoan(String name, double interestRate, int length, double principle)
{
	System.out.println("What type of Loan do you need?");
	String loanType = in.next();	
		
	if(loanType.equals("Simple"))
	{
					
	simpleLoan simple = new simpleLoan(name, interestRate, length, principle);
	simple.calcMonthPayment(); 
	loans.add(simple);
	System.out.println(simple.toString());
	System.out.println(loans);
	Collections.sort(loans);
	}
	
	else if (loanType.equals("Amortized"))
	{
	
		AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
		amortized.calcMonthPayment(); 
		loans.add(amortized);
		System.out.println(amortized.toString());
		System.out.println(loans);
		Collections.sort(loans);

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
}


public void deleteLoan(String name)
{
	
	
	int i;

		for(i=0;i<loans.size();++i){
        	if(loans.get(i).name.contains(name))
        	{
        	System.out.println("Present! at " + i);
        	loans.remove(i);
        	System.out.println(loans);
        	break;
		}
      	
        	else JOptionPane.showMessageDialog(null,"User does not exist!");
        		
        
		}
}
        	
public void SearchLoan(ArrayList<Loan> loans, String names)

{
	int i;
	Loan search;

	for(i=0;i<loans.size();++i){
    	if(loans.get(i).name.contains(names)){
    	search = loans.get(i);
    	System.out.println(search);
    	break;
	}
  	
    	else JOptionPane.showMessageDialog(null,"User does not exist!");
    
	}


}

public String toString() 

{

	return "List";
}
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


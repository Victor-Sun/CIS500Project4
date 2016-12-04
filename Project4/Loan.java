public abstract class Loan implements Comparable<Loan>
{
protected String name; // the applicant’s name
protected double interestRate; // the annual interest rate
protected int length; // the length of the load in years
protected double principle; // the principle
protected double monthlyPayment; // the monthly payment

public Loan (String name, double rate, int years, double amount) 

{
	this.name = name;
	this.interestRate = rate;
	this.length = years;
	this.principle = amount;
	
// constructor
	
}
public String process() 
{
	calcMonthPayment();
	
	return makeSummary();
	
	
// call method calcMonthlyPayment()
// call method makeSummary()
// return the summary
}

abstract public void calcMonthPayment(); // an abstract method


public String makeSummary() 

{
	String summary = "";
	
	return summary;
	
// make and return a summary on the loan
}
public String toString() 

{ 

	return name + interestRate + length + principle; 

}

}

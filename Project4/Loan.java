public abstract class Loan implements Comparable<Loan>{
	protected String name; // the applicant’s name
	protected double interestRate; // the annual interest rate
	protected int length; // the length of the load in years
	protected double principle; // the principle
	protected double monthlyPayment; // the monthly payment

	public Loan (String name, double rate, int years, double amount ) {
		this.name = name;
		this.interestRate = rate;
		this.length = years;
		this.principle = amount;
	}

	public String process() {
		// call method calcMonthlyPayment()
		// call method makeSummary()
		// return the summary
		calcMonthPayment();
		makeSummary();
		//TODO
		
		return null;
	}

	abstract public void calcMonthPayment(); // an abstract method

	public String makeSummary() {
		// make and return a summary on the loan
		String summary =  "Name: " + name + "\n Interest Rate: " + interestRate + "\nAmount of Years: " + length + "\nAmount: " + principle;
		
		return summary;
	}

	public String toString() {
		//TODO
		return name; 
	}
}

public class SimpleLoan extends Loan {
	public void calcMonthPayment () {
		// calculate the monthly payment using the appropriate formula
		// assign the result to the data field monthlyPayment
	}
	
	public String toString() { 
		return "Simple Interest Loan"; 
	}
}

public class AmortizedLoan extends Loan {
	public void calcMonthPayment () {
		// calculate the monthly payment using the appropriate formula
		// assign the result to the data field monthlyPayment
		return null;
	}
	
	public String toString() { 
		return "Full Amortized Loan"; 
	}
}
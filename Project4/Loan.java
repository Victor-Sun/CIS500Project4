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
		
		return makeSummary();
	}

	abstract public void calcMonthPayment(); // an abstract method

	public String makeSummary() {
		// make and return a summary on the loan
		String summary = "";
		
		return summary;
	}

	public String toString() {
		//TODO
		return name + interestRate + length + principle; 
	}
}

public class SimpleLoan extends Loan {
	public void calcMonthPayment () {
		// calculate the monthly payment using the appropriate formula
		// assigns the result to the data field monthlyPayment
		monthlyPayment = (principle * ((interestRate / 12) * length + 1)) / length;
	}
	
	public String toString() { 
		return "Simple Interest Loan: " + monthlyPayment; 
	}
}

public class AmortizedLoan extends Loan {
	public void calcMonthPayment () {
		// calculate the monthly payment using the appropriate formula
		// assign the result to the data field monthlyPayment
		
		return null;
	}
	
	public String toString() { 
		return "Full Amortized Loan: " + monthlyPayment; 
	}
}
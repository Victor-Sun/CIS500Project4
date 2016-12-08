public abstract class Loan implements Comparable<Loan>
{
	protected String name; // the applicant’s name
	protected double interestRate; // the annual interest rate
	protected int length; // the length of the load in years
	protected double principle; // the principle
	protected double monthlyPayment; // the monthly payment
	
	public Loan (String name, double rate, int years, double amount) {
		this.name = name;
		this.interestRate = rate;
		this.length = years;
		this.principle = amount;
	}
	
	public String getName(){
		return this.name;
	}
	
	public double getRate(){
		return this.interestRate;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public double getPrinciple(){
		return this.principle;
	}

	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	public String process() 
	{
		calcMonthPayment();

		return makeSummary();
	}

	abstract public void calcMonthPayment(); // an abstract method

	public int compareTo(){
		return 0;
	}
	
	public String makeSummary(){
		String summary = "";

		return summary;

		// make and return a summary on the loan
	}
	public String toString(){ 

		return name + interestRate + length + principle; 
	}

}

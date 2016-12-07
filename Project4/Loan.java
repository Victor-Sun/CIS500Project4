public abstract class Loan implements Comparable<Loan>
{
	protected String name; // the applicant’s name
	protected double interestRate; // the annual interest rate
	protected int length; // the length of the load in years
	protected double principle; // the principle
	protected double monthlyPayment; // the monthly payment
	protected String type;
	
	
	public Loan(){
		
	}
	public Loan (String name, double rate, int years, double amount) {
		this.name = name;
		this.interestRate = rate;
		this.length = years;
		this.principle = amount;
		// constructor

	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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
		// call method calcMonthlyPayment()
		// call method makeSummary()
		// return the summary
	}

	abstract public void calcMonthPayment(); // an abstract method

	public int compareTo(){
		return 0;
	}
	
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

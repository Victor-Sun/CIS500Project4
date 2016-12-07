public class SimpleLoan extends Loan {


	public SimpleLoan(String name, double rate, int years, double amount){
		super(name, rate, years, amount);

	}

	public void calcMonthPayment(){
		monthlyPayment = (principle * (interestRate*length + 1))/(12*length) ;
		// calculate the monthly payment using the appropriate formula
		// assign the result to the data field monthlyPayment
	}

	public String toString(){ 
		return "Simple Interest Loan"  + "\n" + "Name: " + "\t" + name + "\n" + "Principle: " + "\t" + principle + "\n" + "Interest Rate: " + "\t" + interestRate + "\n" + "Length of Loan: " + "\t" + length + "\n"  + "Payment: " + "\t" + monthlyPayment + "\n" + "\n"; 

	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
	}
	
	@Override
	public int compareTo(Loan arg0) {

		return 0;
	}
}

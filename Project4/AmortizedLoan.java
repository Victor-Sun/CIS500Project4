public class AmortizedLoan extends Loan {
	
public AmortizedLoan(String name, double rate, int years, double amount) {
		super(name, rate, years, amount);
		
	}
public void calcMonthPayment() {
	double monthlyIntRate = (double)(interestRate / 12);
	
	monthlyPayment = (principle * monthlyIntRate) / (1 - Math.pow(1 + monthlyIntRate, -length*12));
	
// calculate the monthly payment using the appropriate formula
// assign the result to the data field monthlyPayment
}
public String toString() 
{ 
	return "Full Amortized Loan"  + "\n" + "Name: " + "\t" + name + "\n" + "Principle: " + "\t" + principle + "\n" + "Interest Rate: " + "\t" + interestRate + "\n" + "Length of Loan: " + "\t" + length + "\n"  + "Payment: " + "\t" + monthlyPayment + "\n" + "\n"; 
	
}
@Override
public int compareTo(Loan arg0) {
	
	return 0;
}
}

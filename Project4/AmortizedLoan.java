public class AmortizedLoan extends Loan {
	
public AmortizedLoan(String name, double rate, int years, double amount) {
		super(name, rate, years, amount);
		
	}
public void calcMonthPayment() 

{
	double n = Math.pow((1 + interestRate), length);
	monthlyPayment = (principle * interestRate* n)/(n - 1);
	
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

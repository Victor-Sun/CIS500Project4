public class simpleLoan extends Loan 

{


public simpleLoan(String name, double rate, int years, double amount) {
		super(name, rate, years, amount);
		
	}

public void calcMonthPayment() 

{
	
	monthlyPayment = (principle * (interestRate*length + 1))/length ;
// calculate the monthly payment using the appropriate formula
// assign the result to the data field monthlyPayment
}

public String toString() { 
	
	return "Monthly Payment for the simple loan is:" + monthlyPayment; 
	}

@Override
public int compareTo(Loan o) {
	
	return 0;
}
}
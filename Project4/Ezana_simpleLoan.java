import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

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

public String toString() 
{ 
	return "Simple Interest Loan"  + "\n" + "Name: " + "\t" + name + "\n" + "Principle: " + "\t" + principle + "\n" + "Interest Rate: " + "\t" + interestRate + "\n" + "Length of Loan: " + "\t" + length + "\n"  + "Payment: " + "\t" + monthlyPayment + "\n" + "\n"; 
	
}
@Override
public int compareTo(Loan arg0) {
	
	return 0;
}

	

}


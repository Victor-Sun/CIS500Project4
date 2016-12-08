import java.text.DecimalFormat;

public class SimpleLoan extends Loan {


	public SimpleLoan(String name, double rate, int years, double amount){
		super(name, rate, years, amount);

	}

	public void calcMonthPayment(){
		DecimalFormat format = new DecimalFormat(".##");
		monthlyPayment = Double.parseDouble(format.format((principle * (interestRate*length + 1))/(12*length))) ;
	}

	public String toString(){ 
		return "Simple Interest Loan"  + "\n" + "Name: " + "\t" + name + "\n" + 
				"Principle: " + "\t" + principle + "\n" + 
				"Interest Rate: " + "\t" + interestRate + "\n" + 
				"Length of Loan: " + "\t" + length + "\n"  + 
				"Payment: " + "\t" + monthlyPayment + "\n"; 

	}
	
	public double getMonthlyPayment() {
		return monthlyPayment;
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

	public int compareTo(Loan arg0) {

		return 0;
	}
}

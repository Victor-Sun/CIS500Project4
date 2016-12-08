import java.text.DecimalFormat;

public class AmortizedLoan extends Loan {

	public AmortizedLoan(String name, double rate, int years, double amount) {
		super(name, rate, years, amount);

	}
	public void calcMonthPayment(){
		double monthlyIntRate = (double)(interestRate / 12);
		DecimalFormat format = new DecimalFormat(".##");
		
		monthlyPayment = Double.parseDouble(format.format((principle * monthlyIntRate) / (1 - Math.pow(1 + monthlyIntRate, -length*12))));
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
	
	@Override
	public int compareTo(Loan arg0) {

		return 0;
	}
}

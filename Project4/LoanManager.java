import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Stream;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

public class LoanManager {

	ArrayList <Loan> al, sl;
	Scanner in = new Scanner(System.in);
	String line;
	double monthlyPayment;

	public LoanManager() {
		sl = new ArrayList <Loan>();
		al = new ArrayList <Loan>();
	}

	public void loadLoan() {
		String iName, iRate, iLength, iPrinciple, iType;
		try{
			Scanner read = new Scanner(new File("Loan.txt"));
			read.useDelimiter(",");
			while (read.hasNext()){
				iType = read.next();
				iName = read.next();
				iRate = read.next();
				iLength = read.next();
				iPrinciple = read.next();
				this.addLoan(iName, Double.parseDouble(iRate), Integer.parseInt(iLength), Double.parseDouble(iPrinciple), iType);
			}
			read.close();
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Previous Session could not be found, starting new session.");
		}
	}

	public void saveSession(){
		try {
			FileWriter fw = new FileWriter("Loan.txt", false);
			File f = new File("Loan.txt");
			f.delete();
			Writer output = new BufferedWriter(fw);
			
			for (int i = 0; i < sl.size(); i++) {
				output.write("Simple," + 
						sl.get(i).name + "," + 
						sl.get(i).interestRate +  "," +
						sl.get(i).length + "," +
						sl.get(i).principle + ",");
			}
			for (int i = 0; i < al.size(); i++) {
				output.write("Amortized," + 
						al.get(i).name + "," + 
						al.get(i).interestRate +  "," +
						al.get(i).length + "," +
						al.get(i).principle + ",");
			}
			output.close();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error Creating File!");
		}
	}

	public void addLoan(String name, double interestRate, int length, double principle, String type) {
		if(this.exists(name)){
			JOptionPane.showMessageDialog(null, name + ", you can only have one loan at a time!");
		} else {
			if(type.equals("Simple")){
				SimpleLoan simple = new SimpleLoan(name, interestRate, length, principle);
				simple.calcMonthPayment();
				sl.add(simple);
			} else {
				AmortizedLoan amortized = new AmortizedLoan(name, interestRate, length, principle);
				amortized.calcMonthPayment();
				al.add(amortized);
			}
		}
	}

	public void editLoanType(String n, String t){
		Loan l;
		l = SearchLoan(n);
		deleteLoan(n);
		addLoan(n,l.getRate(),l.getLength(),l.getPrinciple(),t);
	}
	
	public void newSession(){
		al.clear();
		sl.clear();
	}
	
	public void deleteSession(){
		File f = new File("Loan.txt");
		f.delete();
		this.newSession();
	}
	
	public boolean exists(String names){
		for (int i = 0; i < sl.size(); ++i) {
			Loan tmpL = sl.get(i);
			if (tmpL.getName().toLowerCase().equals(names.toLowerCase())) {
				return true;
			}
		}
		for (int i = 0; i < al.size(); ++i) {
			Loan tmpL = al.get(i);
			if (tmpL.getName().toLowerCase().equals(names.toLowerCase())) {
				return true;
			}
		}
		return false;
	}

	public void deleteLoan(String names){
		for(int i = 0; i < sl.size(); ++i) {
			Loan tmpL = sl.get(i);
			if (tmpL.getName().toLowerCase().equals(names.toLowerCase())){
				sl.remove(i);
				break;
			}
		}
		for(int i = 0; i < al.size(); ++i) {
			Loan tmpL = al.get(i);
			if (tmpL.getName().toLowerCase().equals(names.toLowerCase())){
				al.remove(i);
				break;
			}
		}
	}

	public Loan SearchLoan(String names){
		Loan search = null;
		for(int i = 0; i < al.size(); ++i){
			Loan tmpL = al.get(i);
			if(tmpL.getName().toLowerCase().equals(names.toLowerCase())) {
				search = al.get(i);
				break;
			}
		}
		for(int i = 0; i < sl.size(); ++i){
			Loan tmpL = sl.get(i);
			if(tmpL.getName().toLowerCase().equals(names.toLowerCase())) {
				search = sl.get(i);
				break;
			}
		}
		return search;
	}

	public double totalMoney(){
		double totalBorrowed = 0;

		for (int i = 0; i < sl.size(); ++i) {
			totalBorrowed += sl.get(i).principle;
		}
		for (int i = 0; i < al.size(); ++i) {
			totalBorrowed += al.get(i).principle;
		}
		return totalBorrowed;
	}

	public int totalLoan(){
		return al.size() + sl.size();
	}

	public int totalSimpleLoan(){
		return sl.size();
	}
	
	public int totalAmortizedLoan(){
		return al.size();
	}
	
	public boolean isSimple(String name){
		for(int i = 0; i < sl.size(); i++){
			if(sl.get(i).name.toLowerCase().equals(name.toLowerCase())){
				return true;
			}
		}
		return false;
	}
	
	public String getName(){
		String str = "";
		for(int i = 0; i < sl.size(); i++){
			str += sl.get(i).getName() + ", ";	
		}
		for(int i = 0; i < al.size(); i++){
			str += al.get(i).getName() + ", ";	
		}		
		return str;
	}
	
	public double getPrinciple(String name){
		return SearchLoan(name).getPrinciple();
	}

	public void setPrinciple(String name, double p){
		SearchLoan(name).setPrinciple(p);
	}
	
	public double getInterest(String name){
		return SearchLoan(name).getRate();
	}

	public void setInterest(String name, double i){
		SearchLoan(name).setRate(i);;
	}
	
	public int getLength(String name){
		return SearchLoan(name).getLength();
	}
	
	public void setLength(String name, int l){
		SearchLoan(name).setLength(l);
	}
	
	public double getPayment(String name){
		return SearchLoan(name).getMonthlyPayment();
	}

	public String toString(){
		return sl.toString() + al.toString();
	}
}

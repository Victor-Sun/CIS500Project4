import static org.junit.Assert.*;
import org.junit.Test;

public class LoanManagerTest {
	LoanManager loans = new LoanManager();
	
	private void setupTest(){		
		loans.SetCurrentLoanType("Simple");
		loans.addLoan("Egide Nsenga", .08, 1, 10000);	
		loans.SetCurrentLoanType("Amortized");
		loans.addLoan("Jon Watson", .08, 1, 10000);	
	}
	
	@Test
	public void testAddLoan() {
		this.setupTest();
		assertTrue(this.loans.GetAllLoansList().size() >= 1);		
	}

	@Test
	public void testEditLoan() {
	}

	@Test
	public void testDeleteLoan() {
	}

	@Test
	public void testSearchLoan() {
	}

	@Test
	public void testToString() {
	}	
	
	@Test
	public void testGetLoan() {
		this.setupTest();
		loans.SetCurrentLoanType("Amortized");
		loans.addLoan("Egide Nsenga", .08, 2, 20000);
		
		assertTrue(loans.GetLoan("Egide Nsenga").size() == 2);
	}	

}

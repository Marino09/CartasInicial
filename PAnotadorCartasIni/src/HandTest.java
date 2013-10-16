import static org.junit.Assert.*;

import org.junit.Test;


public class HandTest {

	@Test
	public void validateTest() {
		
		Hand TestHand;
		
		TestHand = new Hand(" ","4D KS 8D AD 3H 5H 10H AH");
		assertFalse(TestHand.isValida());
		
		TestHand = new Hand("10S 7C 7H 7D","4D KS 8D AD 3H 5H 10H AH 4C 3S 8C 6S 10S");
		assertFalse(TestHand.isValida());
		
		TestHand = new Hand("3","");
		assertFalse(TestHand.isValida());
		
		TestHand = new Hand("$dbnskwkw83.df","4D KS 8D AD 3H 5H 10H AH");
		assertFalse(TestHand.isValida());
		
		TestHand = new Hand("10S 7C 7H 7D 3H 5H 10H AH","4D KS 8D AD 3H 5H 10H AH");
		assertFalse(TestHand.isValida());
		
		TestHand = new Hand("AH 3D 10S 8C","4D KS 8D AD 3H 5H 10H ");
		assertTrue(TestHand.isValida());
		
		
		
	}
	
	@Test
	public void scoreTest() {
		
	}

}

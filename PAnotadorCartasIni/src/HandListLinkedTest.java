import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class HandListLinkedTest {
	
	public HandListLinked hlist;
	
	@Before
	public void beforeTest(){
		
		hlist = new HandListLinked();
	}
	
	@Test
	public void Addtest() {
		Hand mano = new Hand("3H 5H 10H AH","");
		
		hlist.add(mano);
		assertTrue(hlist.getHead() != null);	
		assertTrue(hlist.getHead().handToString().equals("3H 5H 10H AH"));	
	}
	
	@Test
	public void Removetest() {
		
		hlist.add(new Hand("8S 5H 3H 4C",""));
		hlist.add(new Hand("3H 5H 10H AH",""));
		hlist.add(new Hand("4C 3S 8C 6S",""));
		
		hlist.removeBack();
		
		assertFalse(hlist.getTail().handToString().equals("4C 3S 8C 6S"));	
		assertTrue(hlist.getTail().handToString().equals("3H 5H 10H AH"));	

		
	}
	
	@Test
	public void Cleartest() {
		hlist.add(new Hand("8S 5H 3H 4C",""));
		hlist.add(new Hand("3H 5H 10H AH",""));
		hlist.add(new Hand("4C 3S 8C 6S",""));
		
		hlist.Clear();
		
		assertTrue(hlist.getHead() == null && hlist.getTail() == null);
	}

}

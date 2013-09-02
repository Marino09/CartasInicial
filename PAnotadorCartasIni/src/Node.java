
public class Node {
	
	private Hand hand;
	private Node next;
	private Node prev;
	
	
	public Node(Hand hand, Node next, Node prev) {
		this.hand = hand;
		this.next = next;
		this.prev = prev;
	}
	
	public String getString (){
		
		return this.hand.getHandGame();
	}
	
	public Hand getHand() {
		return hand;
	}
	public void setHand(Hand hand) {
		this.hand = hand;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	public Node getPrev() {
		return prev;
	}
	public void setPrev(Node prev) {
		this.prev = prev;
	}
	
	

}

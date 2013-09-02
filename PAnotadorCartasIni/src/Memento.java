
public class Memento {
	//Memento Class.
	private Hand hand;
	
	public Memento(Hand handSave)
	{
		hand = handSave;
	}
	
	public Hand getSavedHand()
	{
		return hand;
	}
}

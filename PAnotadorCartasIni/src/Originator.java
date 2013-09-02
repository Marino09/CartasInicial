
public class Originator {
	
	private Hand hand;
	
		public void set(Hand newHand)
		{
			//this console print should be deleted, is just for Ernesto & Lucas
			System.out.println("Originator: Version actual del articulo\n"
					+ newHand+ "]n");
			hand = newHand;
		}
		
		public Memento storeInMemento()
		{
			//this console print should be deleted, is just for Ernesto & Lucas
			System.out.println("Originator: Salvando memento.");
			return new Memento(hand);
		}
		
		public Hand restoreFromMemento(Memento memento)
		{
			hand  = memento.getSavedHand();
			//this console print should be deleted, is just for Ernesto & Lucas
			System.out.println("Originator: Version anterior del articulo");
			return hand;
		}

}

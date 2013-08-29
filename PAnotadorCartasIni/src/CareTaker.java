
import java.util.ArrayList;

public class CareTaker {
	
	ArrayList<Memento> savedHands = new ArrayList<Memento>();
	
	public void addMemento(Memento m)
	{
		savedHands.add(m);
	}
	
	public Memento getMemento(int index)
	{
		return savedHands.get(index);
	}
}


public class HandListLinked {
	
	private Node Head;
	private Node Tail;
	private int size;
	
	public HandListLinked() {

		this.Head = null;
		this.Tail = null;
		size = 0;
	}
	
	public void add (Hand item )
	{
		if (Tail == null)
		{
			Tail = new Node(item, Head, Tail);
			Head = Tail;
			size++;
			
		} else {
			Node nuevo = new Node(item ,null, Tail);
            Tail.setNext(nuevo); 
            Tail = nuevo;
            size++;
		}
		
	}
	
	public void removeBack ()
	{
		Tail = Tail.getPrev();
		size--;
	}
	
	public Node Find(int index)
	{
		Node temp = null;
		
		return temp;
	}
	
	
	
	public Node getHead() {
		return Head;
	}

	public Node getTail() {
		return Tail;
	}

	public int getSize() {
		return size;
	}

	
	

}

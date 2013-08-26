
import java.awt.Event;
import javax.swing.*;

public class TestMementoCards extends JFrame {

	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new TestMementoCards();
	}
	
	private JButton saveBut, UndoBut, redoBut;
	private JTextArea theArticle = new JTextArea(40, 60);
	
	CareTaker careTaker = new CareTaker();
	Originator originator = new Originator();
	
	int saveFiles = 0, currentArticle = 0;
	
	public TestMementoCards()
	{
		
	}
}
   	

//import java.awt.Event;
import java.awt.event.*;

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
	//The save button shouldn't be here, just for clarity: Fix it!
	private JButton saveBut, undoBut, redoBut;
	private JTextArea theHand = new JTextArea(40, 60);
	
	CareTaker careTaker = new CareTaker();
	Originator originator = new Originator();
	
	int saveFiles = 0, currentHand = 0;
	
	public TestMementoCards()
	{
		this.setSize(750, 780);
		this.setTitle("Anotador jugadas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel jp = new JPanel();
		jp.add(new JLabel("Primer entregable"));
		jp.add(theHand);
		 	 	
		ButtonListener saveListener = new ButtonListener();
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
		
		saveBut = new JButton("salvar");
		saveBut.addActionListener(saveListener);
		
		undoBut = new JButton("Jugada anterior");
		undoBut.addActionListener(undoListener);
		
		redoBut = new JButton("Jugada siguiente");
		redoBut.addActionListener(redoListener);
		
		jp.add(saveBut);
		jp.add(undoBut);
		jp.add(redoBut);
		this.add(jp);
		this.setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(e.getSource() == saveBut)
			{
				String textInTextArea = theHand.getText();
				originator.set(textInTextArea);
				careTaker.addMemento(originator.storeInMemento());
				saveFiles++;
				currentHand++;
				//this console print should be deleted, is just for Ernesto & Lucas
				System.out.println("save files" + saveFiles);
				
				undoBut.setEnabled(true);
			} else
				if(e.getSource() == undoBut)
				{
					if(currentHand >= 1)
					{
						currentHand--;
						String textBoxString = originator.restoreFromMemento(careTaker.getMemento(currentHand));
						theHand.setText(textBoxString);
						redoBut.setEnabled(true);
					} else
					{
						undoBut.setEnabled(false);
					}
				} else 
					if(e.getSource() == redoBut)
					{
						if((saveFiles - 1) > currentHand)
						{
							currentHand++;
							
							String textBoxString = originator.restoreFromMemento(careTaker.getMemento(currentHand));
							theHand.setText(textBoxString);
							undoBut.setEnabled(true);
						} else
						{
							redoBut.setEnabled(false);
						}
					}
		}
	}
}
   	
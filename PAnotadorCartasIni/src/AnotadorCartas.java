import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.*;

public class AnotadorCartas extends JFrame {

	public static void main(String[] args) {
		new AnotadorCartas();
	}
	//The save button shouldn't be here, just for clarity: Fix it!
	private JButton  undoBut, redoBut, openBut;
	private JTextArea theHand, scoreNow;

	HandListLinked list = new HandListLinked();
	Node actual;
	String playedCards = "";
	
	
	int actualScore = 0;
	private JLabel lblNewLabel;
	private JLabel label;
	private JTextArea combinacion;
	
	public AnotadorCartas()
	{
		this.setSize(450, 300);
		this.setTitle("Anotador de jugadas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		
		JPanel jp = new JPanel();
        this.theHand = new JTextArea(5, 25);
        theHand.setFont(new Font("Arial", Font.BOLD, 17));
        theHand.setWrapStyleWord(true);

        jp.add(theHand);

		 	 	
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
        ButtonListener openListener = new ButtonListener();
		
		undoBut = new JButton("Jugada anterior");
		undoBut.setEnabled(false);
		undoBut.addActionListener(undoListener);
		
		redoBut = new JButton("Jugada siguiente");
		redoBut.setEnabled(false);
		redoBut.addActionListener(redoListener);
		jp.add(undoBut);
		jp.add(redoBut);
        getContentPane().add(jp, BorderLayout.CENTER );
        
        openBut = new JButton("Abrir");
        openBut.addActionListener(openListener);
        
        jp.add(openBut);
        
        label = new JLabel("Combinacion");
        jp.add(label);
        
        combinacion = new JTextArea(2, 5);
        combinacion.setFont(new Font("Arial", Font.BOLD, 16));
        jp.add(combinacion);
        
        lblNewLabel = new JLabel("Puntuacion");
        jp.add(lblNewLabel);
        this.scoreNow = new JTextArea(2,5);
        scoreNow.setFont(new Font("Arial", Font.BOLD, 16));
        jp.add(scoreNow);
        setLocationRelativeTo( null );
        this.setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener
	{
        public void readFile( String path ){
            FileReader fr = null;
            BufferedReader br = null;
            
            try
            {
            	list.Clear();
            	playedCards = "";                	
            	actualScore = 0;
                fr = new FileReader( path );
                br = new BufferedReader( fr );
                
                String linea;
                int cont = 0;
                
                while( br.ready() && cont < 13 ){
                	cont++;
                	linea = br.readLine().toUpperCase();
                    Hand newHand = new Hand(linea, playedCards);
                    list.add(newHand);
                    playedCards += linea + " ";
                }

            }catch( Exception e ){  }

            finally
            {
                try{
                    br.close();
                }catch( Exception ex ){}
            }
            
        }
        
		public void actionPerformed(ActionEvent e)
		{
			if (e.getSource() == openBut) {
                    JFileChooser fileChooser = new JFileChooser();

                    int selec = fileChooser.showOpenDialog(new JPanel());

                    if (selec == JFileChooser.APPROVE_OPTION) {

                        File file = fileChooser.getSelectedFile();
                        String path = file.getAbsolutePath();

                        if (path.endsWith(".txt") ) {
                            try {
                            	this.readFile(path);
                            	actual = list.getHead();
                            	redoBut.setEnabled(true);
                            	actualScore += actual.getHand().getScoreHand();
                            	theHand.setText(actual.handToString());
                            	scoreNow.setText(Integer.toString(actualScore));
                            	combinacion.setText(actual.getHand().getTypeOfHand());


                            } catch (Exception ex) {}

                        } else
                        {
                            JOptionPane.showMessageDialog(null, "Solo archivos .txt");
                        }

                    }

                } else if (e.getSource() == undoBut) {
                	if (!redoBut.isEnabled()){
                		redoBut.setEnabled(true);
                	}
                	
                	actualScore -= actual.getHand().getScoreHand();
                	actual = actual.getPrev();
                	theHand.setText(actual.handToString());
                	scoreNow.setText(Integer.toString(actualScore));
                	combinacion.setText(actual.getHand().getTypeOfHand());

                	
                	if (actual.getPrev() == null) {
                		undoBut.setEnabled(false);
            		}
                	
                } else if (e.getSource() == redoBut) {
                	if (!undoBut.isEnabled()){
                		undoBut.setEnabled(true);
                	}
                	actual = actual.getNext();
                	theHand.setText(actual.handToString());
                	
                	if (actual.getNext() == null) {
                		redoBut.setEnabled(false);
                	}
                	else{
                    	actualScore += actual.getHand().getScoreHand();
                    	scoreNow.setText(Integer.toString(actualScore));
                    	combinacion.setText(actual.getHand().getTypeOfHand());
                	}
                }
		}
	}
}
   	
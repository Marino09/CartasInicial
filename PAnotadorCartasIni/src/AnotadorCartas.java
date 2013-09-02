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
	private JTextArea theHand, pointNow;

	HandListLinked list = new HandListLinked();
	Node actual;
	String played = "";
	
	
	int actualPoint = 0;
	
	public AnotadorCartas()
	{
		this.setSize(600, 600);
		this.setTitle("Anotador de jugadas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		
		JPanel jp = new JPanel();
        this.theHand = new JTextArea(20, 30);
        theHand.setFont(new Font("Dialog", Font.PLAIN, 16));
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
		
		openBut = new JButton("Abrir");
		openBut.addActionListener(openListener);
	    
		jp.add(openBut);
		jp.add(undoBut);
		jp.add(redoBut);
        getContentPane().add(jp, BorderLayout.CENTER );
        
        lblNewLabel = new JLabel("Puntuacion");
        jp.add(lblNewLabel);
        this.pointNow = new JTextArea(4,4);
        jp.add(pointNow);
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
                fr = new FileReader( path );
                br = new BufferedReader( fr );
                
                String linea;
                int cont = 0;
                
                while( br.ready() && cont < 13 ){
                	cont++;
                	linea = br.readLine();
                    Hand newHand = new Hand(linea );
                    list.add(newHand);
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
                            	theHand.setText(actual.handToString());

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
                	actual = actual.getPrev();
                	theHand.setText(actual.handToString());
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
                }
		}
	}
}
   	
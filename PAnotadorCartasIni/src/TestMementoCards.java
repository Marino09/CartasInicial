
//import java.awt.Event;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
	private JButton saveBut, undoBut, redoBut, openBut;
	private JTextArea theHand, pointNow, pointUndo;

	CareTaker careTaker = new CareTaker();
	Originator originator = new Originator();
	
	int saveFiles = 0, currentHand = 0;
	
	public TestMementoCards()
	{
		this.setSize(800, 800);
		this.setTitle("Anotador de jugadas");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
		
		JPanel jp = new JPanel();
		jp.add(new JLabel("Primer entregable"));
        this.theHand = new JTextArea(40, 60);
        this.pointNow = new JTextArea(4,4);
        this.pointUndo = new JTextArea(4,4);

        jp.add(theHand);
        jp.add(pointNow);
        jp.add(pointUndo);

		 	 	
		ButtonListener saveListener = new ButtonListener();
		ButtonListener undoListener = new ButtonListener();
		ButtonListener redoListener = new ButtonListener();
        ButtonListener openListener = new ButtonListener();
		
		saveBut = new JButton("salvar");
		saveBut.addActionListener(saveListener);
		
		undoBut = new JButton("Jugada anterior");
		undoBut.addActionListener(undoListener);
		
		redoBut = new JButton("Jugada siguiente");
		redoBut.addActionListener(redoListener);

        openBut = new JButton("Abrir");
        openBut.addActionListener(openListener);
		
		jp.add(saveBut);
		jp.add(undoBut);
		jp.add(redoBut);
        jp.add(openBut);
        this.add(jp, BorderLayout.CENTER );
        setLocationRelativeTo( null );
        this.setVisible(true);
		
	}
	
	class ButtonListener implements ActionListener
	{
        //-------------------------Se obtiene el contenido del Archivo----------------//
        public String getArchivo( String ruta ){
            FileReader fr = null;
            BufferedReader br = null;
            //Cadena de texto donde se guardara el contenido del archivo
            String contenido = "";
            try
            {
                //ruta puede ser de tipo String o tipo File
                fr = new FileReader( ruta );
                br = new BufferedReader( fr );

                String linea;
                //Obtenemos el contenido del archivo linea por linea
                while( ( linea = br.readLine() ) != null ){
                    contenido += linea + "\n";
                }

            }catch( Exception e ){  }
            //finally se utiliza para que si todo ocurre correctamente o si ocurre
            //algun error se cierre el archivo que anteriormente abrimos
            finally
            {
                try{
                    br.close();
                }catch( Exception ex ){}
            }
            return contenido;
        }

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
			}

            else {
                if (e.getSource() == openBut) {
                    JFileChooser fileChooser = new JFileChooser();

                    int selec = fileChooser.showOpenDialog(new JPanel());

                    if (selec == JFileChooser.APPROVE_OPTION) {
                        File file = fileChooser.getSelectedFile();
                        try {
                            String path = file.getAbsolutePath();
                            String contenido = this.getArchivo(path);
                            theHand.setText(contenido);

                        } catch (Exception ex) {}

                    }

                } else if (e.getSource() == undoBut) {
                    if (currentHand >= 1) {
                        currentHand--;
                        String textBoxString = originator.restoreFromMemento(careTaker.getMemento(currentHand));
                        theHand.setText(textBoxString);
                        redoBut.setEnabled(true);
                    } else {
                        undoBut.setEnabled(false);
                    }
                } else if (e.getSource() == redoBut) {
                    if ((saveFiles - 1) > currentHand) {
                        currentHand++;

                        String textBoxString = originator.restoreFromMemento(careTaker.getMemento(currentHand));
                        theHand.setText(textBoxString);
                        undoBut.setEnabled(true);
                    } else {
                        redoBut.setEnabled(false);
                    }
                }
            }
		}
	}
}
   	
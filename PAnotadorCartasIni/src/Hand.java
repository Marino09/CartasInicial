import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;


public class Hand {
	
	private String [] handGame = new String[4];
	private int scoreHand;
	private boolean valida = true;
	private String typeOfHand = ""; 
	
	public Hand (String handGame, String cards)
	{
		this.handGame = handGame.split(" ");
		validate(cards);
		setScore();
	}
	
	private void validate(String Cards)
	{
		String HandGame = StringUtils.join(handGame, " ");
		if ((HandGame.length() > 15 || HandGame.length() < 11 ) ) {
			this.valida = false;
			this.handGame = new String [1] ;
			this.handGame[0] = "Mano no valida";
			return;
		}
		
		for (int i=0; i<handGame.length; i++){
			if(Cards.contains(handGame[i]) ){
				this.valida = false;
				this.handGame = new String [1] ;
				this.handGame[0] = "Mano no valida";
				break;
			}
		}

		for (int i=0; i<4; i++)
		{
			if (!handGame[i].matches("^(\\d|[J|K|Q|A]){1,2}([H|C|D|S])$")){
				this.valida = false;
				this.handGame = new String [1] ;
				this.handGame[0] = "Mano no valida";
				break;
			}
		}
	}
	
	private void setScore() {
		
		boolean full = true;
		boolean flush = true;
		boolean straight = true;

		String type;
		Map<String, Integer> cards = new HashMap<String, Integer>() 
				{{put("2",2);put("3",3);put("4",4);put("5",5);put("6",6);put("7",7);put("8",8);put("8",8);put("9",9);put("10",10);put("J",11);put("Q",12);put("K",13);put("A",14);}};

		
		if (this.valida){
			if (handGame[0].length() == 3)
			{
				type = handGame[0].substring(2);
			}
			else{
				type = handGame[0].substring(1);
			}
			for (int i=0; i<4; i++){
				if (handGame[i].indexOf(type) == -1 ){
					flush = false;
					break;
				}
			}
			
			if (handGame[0].length() == 3)
			{
					type = handGame[0].substring(0,2);
			}
			else{
					type = handGame[0].substring(0,1);
				}
			for (int i = 0; i <4; i++){
				if (handGame[i].indexOf(type) == -1){
					full= false;
					break;
				}
			}
			for (int i = 1; i<4; i++){
				String l;
				String n;
				
				if (handGame[i].length() == 3)
				{
					l =  handGame[i].substring(0,2);
					n = handGame[i-1].substring(0,2);
				}
				else{
					 l = handGame[i].substring(0,1);
					 n = handGame[i-1].substring(0,1);
					}
				if ( cards.get(l)-1 != cards.get(n) ){
					straight= false;
					break;
				}
				
			}
			
			if (flush && straight){
				scoreHand = 500;
				typeOfHand = "Straight Flush";
				
			}else if (full)
			{
				scoreHand = 100;
				typeOfHand = "Full House";

			}else if (flush)
			{
				scoreHand = 10;
				typeOfHand = "Flush";
						
			}else if (straight){
				scoreHand = 300;
				typeOfHand = "Straight";

			}else{
				scoreHand = -10;
			}
			
		}else 
		{
			scoreHand = 0;
		}

	}
	
	
	public String getTypeOfHand() {
		return typeOfHand;
	}

	public String[] getHandGame() {
		return handGame;
	}

	public int getScoreHand() {
		return scoreHand;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}
	
	
}

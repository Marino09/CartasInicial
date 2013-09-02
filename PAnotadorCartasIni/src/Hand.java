import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;


public class Hand {
	
	private String [] handGame = new String[4];
	private int pointHand;
	private boolean valida = true;
	
	public Hand (String handGame)
	{
		this.handGame = handGame.split(" ");
		//cards = cards + " " + StringUtils.join(handGame, " ").toString();
		validate();
	}
	
	private void validate()
	{
		String HandGame = StringUtils.join(handGame, " ");
		if (HandGame.length() > 15 || HandGame.length() < 11) {
			this.valida = false;
			this.handGame = new String [1] ;
			this.handGame[0] = "Mano no valida";
		}
		/*
		for (int i=0; i<4; i++){
			if(cards.indexOf(handGame[i]) != -1){
				this.valida = false;
				this.handGame = new String [1] ;
				this.handGame[0] = "Mano no valida";
			}
		}*/
		
	}
	
	
	public String[] getHandGame() {
		return handGame;
	}

	public int getPointHand() {
		return pointHand;
	}

	public boolean isValida() {
		return valida;
	}

	public void setValida(boolean valida) {
		this.valida = valida;
	}
	
	
}


public class Hand {
	
	private String handGame;
	private int pointHand;
	private boolean valida = true;
	
	public Hand (String handGame)
	{
		this.handGame = handGame;
	}
	
	
	
	public String getHandGame() {
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

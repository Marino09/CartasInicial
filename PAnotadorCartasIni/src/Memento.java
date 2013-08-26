
public class Memento {
	//Memento Class.
	private String article;
	
	public Memento(String articleSave)
	{
		article = articleSave;
	}
	
	public String getSavedArticle()
	{
		return article;
	}
}

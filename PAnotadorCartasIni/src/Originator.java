
public class Originator {
	
	private String article;
	
		public void set(String newArticle)
		{
			System.out.println("Originator: Version actual del articulo\n"
					+ newArticle+ "]n");
			article = newArticle;
		}
		
		public Memento storeInMemento()
		{
			System.out.println("Originator: Salvando memento.");
			return new Memento(article);
		}
		
		public String storeFromMemento(Memento memento)
		{
			article  = memento.getSavedArticle();
			System.out.println("Originator: Version anterior del articulo");
			return article;
		}

}

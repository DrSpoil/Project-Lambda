package modeles;

public class Modele {
	
	private int vaoID;
	private int compteurVertex;
	
	public Modele(int vaoID, int compteurVertex) {
		this.vaoID = vaoID;
		this.compteurVertex = compteurVertex;
	}

	public int getVaoID() {
		return vaoID;
	}
	
	public int getCompteurVertex() {
		return compteurVertex;
	}
}

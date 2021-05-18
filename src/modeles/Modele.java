package modeles;

/* Classe qui charge des modeles en trois dimensions.
   En graphique 3D, un objet est composer de plusieur
   triangles, ces triangle sont des matrices avec des
   coordonnes qui, lorsque placer correctement, donne
   un objet en 3D. Cela permet de cree les faces d'un
   cubes par exemple. Ces triangles on une identifcation
   et un position en xyz.
*/
public class Modele {
	
	private int vaoID;
	private int compteurVertex;
	
	//Constructeur
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

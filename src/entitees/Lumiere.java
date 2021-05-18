package entitees;

import org.lwjgl.util.vector.Vector3f;

/* La classe qui se charge de l'effet de lumiere.
   On considere que chaque rayon de lumiere sont
   des vecteurs qui frappent les objets et qui sont
   reflechit ensuite.
*/
public class Lumiere {
	
	private Vector3f position;
	private Vector3f couleur;
	//Constructeur
	public Lumiere(Vector3f position, Vector3f couleur) {

		this.position = position;
		this.couleur = couleur;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public Vector3f getCouleur() {
		return couleur;
	}

	public void setCouleur(Vector3f couleur) {
		this.couleur = couleur;
	}

}

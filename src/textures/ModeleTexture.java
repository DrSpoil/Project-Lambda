package textures;
//Classe qui retourne l'id de la texture
public class ModeleTexture {
	
	private int idTexture;
	
	private float coucheBrillance = 1;
	private float reflectivite = 0;
	
	private boolean estTransparent = false;
	private boolean utiliseFausseLumiere = false;

	//Constructeur
	public ModeleTexture(int id) {
		
		this.idTexture = id;
		
	}
	
	//Setters et Getters
	public int getID() {
		return this.idTexture;
	}
	
	public float getCoucheBrillance() {
		return coucheBrillance;
	}

	public void setCoucheBrillance(float brillance) {
		this.coucheBrillance = brillance;
	}

	public float getReflectivite() {
		return reflectivite;
	}

	public void setReflectivite(float reflectivite) {
		this.reflectivite = reflectivite;
	}
	
	public boolean isEstTransparent() {
		return estTransparent;
	}
	
	public void setEstTransparent(boolean estTransparent) {
		this.estTransparent = estTransparent;
	}
	
	
	public boolean isUtiliseFausseLumiere() {
		return utiliseFausseLumiere;
	}

	public void setUtiliseFausseLumiere(boolean utiliseFausseLumiere) {
		this.utiliseFausseLumiere = utiliseFausseLumiere;
	}

	
}

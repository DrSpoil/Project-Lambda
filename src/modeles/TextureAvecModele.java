package modeles;
import textures.ModeleTexture;
//Classe qui applique la texture sur le modele en 3D
public class TextureAvecModele {
	
	private Modele rawModel;
	private ModeleTexture texture;
	
	//Constructeur
	public TextureAvecModele(Modele modele, ModeleTexture texture) {
		this.rawModel = modele;
		this.texture = texture;
	}

	public Modele getRawModel() {
		return rawModel;
	}

	public ModeleTexture getTexture() {
		return texture;
	}

}

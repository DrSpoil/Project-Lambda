package modeles;
import textures.ModeleTexture;

public class TextureAvecModele {
	
	private Modele rawModel;
	private ModeleTexture texture;
	
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

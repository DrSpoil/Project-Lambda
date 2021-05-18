package moteurAffichage;

import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;

import boite_a_outils.Mathematique;
import entitees.Entite;
import modeles.Modele;
import modeles.TextureAvecModele;
import ombrage.OmbrageStatique;
import textures.ModeleTexture;
/* Methode qui se charge d'afficher l'environnement
   graphique. L'environement graphique est compose de
   tout ce qui est entite, ombrage, texture, modele
   et lumiere. On applique une valeure pour le champ
   de vision (70 dans notre cas).
*/
public class EntiteRenduGraphique {
	
	
	/*Le fonctionnement pour affficher des objets est similaire a celui
	  des rayons de lumieres qui frappent les objets et qui
	  nous parviennent aux yeux. dans ce cas la les rayons
	  sont des matrices appele matrice de projection.
	*/
	
	private OmbrageStatique shader;
	
	//Constructeur
	public EntiteRenduGraphique(OmbrageStatique shader,Matrix4f matriceProjection) {
		this.shader = shader;
		shader.demarre();
		shader.chargeMatriceProjection(matriceProjection);
		shader.arret();
	}
	
	public void rendu(Map<TextureAvecModele,List<Entite>> entites) {
		for(TextureAvecModele modele:entites.keySet()) {
			prepareTextureAvecModele(modele);
			List<Entite> grouper = entites.get(modele);
			for(Entite entite:grouper) {
				prepareInstance(entite);
				GL11.glDrawElements(GL11.GL_TRIANGLES, modele.getRawModel().getCompteurVertex(),GL11.GL_UNSIGNED_INT,0);
			}
			defaireTextureAvecModele();
		}
	}
	
	public void prepareTextureAvecModele(TextureAvecModele modeleAvecTexture) {
		Modele modele = modeleAvecTexture.getRawModel();
		GL30.glBindVertexArray(modele.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		ModeleTexture texture = modeleAvecTexture.getTexture();
		if(texture.isEstTransparent()) {
			MasterRenduGraphique.disableCulling();
		}
		shader.chargeFausseLumiereVariable(texture.isUtiliseFausseLumiere());
		shader.chargeVariablesBrillance(texture.getCoucheBrillance(), texture.getReflectivite());
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modeleAvecTexture.getTexture().getID());
	}
	
	public void defaireTextureAvecModele() {
		MasterRenduGraphique.enableCulling();
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}
	
	public void prepareInstance(Entite entite) {
		Matrix4f transformationMatrice = Mathematique.creeTransformationMatrice(entite.getPosition(), entite.getRotX(), 
				entite.getRotY(), entite.getRotZ(), entite.getScale());
		shader.chargeTransformationMatrice(transformationMatrice);
	}

	
	
}

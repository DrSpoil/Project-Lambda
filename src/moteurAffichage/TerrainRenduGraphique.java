package moteurAffichage;

import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import boite_a_outils.Mathematique;
import modeles.Modele;
import ombrage.TerrainOmbrage;
import terrains.Terrain;
import textures.TextureTerrainPack;

public class TerrainRenduGraphique {
	
	private TerrainOmbrage ombrage;
	
	public TerrainRenduGraphique(TerrainOmbrage ombrage, Matrix4f matriceProjection) {
		this.ombrage = ombrage;
		ombrage.demarre();
		ombrage.chargeMatriceProjection(matriceProjection);
		ombrage.unitesTextureConnexe();
		ombrage.arret();
	}
	
	public void rendu(List<Terrain> terrains) {
		for(Terrain terrain:terrains) {
			prepareTerrain(terrain);
			chargeMatriceModele(terrain);
			GL11.glDrawElements(GL11.GL_TRIANGLES, terrain.getModele().getCompteurVertex(),GL11.GL_UNSIGNED_INT,0);
			defaireTextureAvecModele();
		}
	}
	
	public void prepareTerrain(Terrain terrain) {
		Modele modele = terrain.getModele();
		GL30.glBindVertexArray(modele.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		GL20.glEnableVertexAttribArray(2);
		lieTextures(terrain);
		ombrage.chargeVariablesBrillance(1, 0);
	}
	
	private void lieTextures(Terrain terrain) {
		
		TextureTerrainPack texturePack = terrain.getTexturePack();
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getBackgroundTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE1);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getrTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE2);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getgTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE3);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texturePack.getbTexture().getTextureID());
		GL13.glActiveTexture(GL13.GL_TEXTURE4);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getBlendMap().getTextureID());
	}
	
	public void defaireTextureAvecModele() {
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL20.glDisableVertexAttribArray(2);
		GL30.glBindVertexArray(0);
		
	}
	
	public void chargeMatriceModele(Terrain terrain) {
		Matrix4f transformationMatrice = Mathematique.creeTransformationMatrice(new Vector3f(terrain.getX(),0,terrain.getZ()), 0, 
				0, 0, 1);
		ombrage.chargeTransformationMatrice(transformationMatrice);
	}

}

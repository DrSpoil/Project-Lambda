package moteurAffichage;

import org.lwjgl.opengl.Display;
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

public class RenduGraphique {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;

	
	private Matrix4f matriceProjection;
	
	public RenduGraphique(OmbrageStatique shader) {
		creeMatriceProjection();
		shader.demarre();
		shader.chargeMatriceProjection(matriceProjection);
		shader.arret();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClearColor(1, 1, 1, 1);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
	}
	
	public void rendu(Entite entite, OmbrageStatique shader) {
		TextureAvecModele modeleAvecTexture = entite.getModele();
		Modele modele = modeleAvecTexture.getRawModel();
		GL30.glBindVertexArray(modele.getVaoID());
		GL20.glEnableVertexAttribArray(0);
		GL20.glEnableVertexAttribArray(1);
		Matrix4f transformationMatrice = Mathematique.creeTransformationMatrice(entite.getPosition(), entite.getRotX(), 
																				entite.getRotY(), entite.getRotZ(), entite.getScale());
		shader.chargeTransformationMatrice(transformationMatrice);
		GL13.glActiveTexture(GL13.GL_TEXTURE0);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, modeleAvecTexture.getTexture().getID());
		GL11.glDrawElements(GL11.GL_TRIANGLES, modele.getCompteurVertex(),GL11.GL_UNSIGNED_INT,0);
		GL20.glDisableVertexAttribArray(0);
		GL20.glDisableVertexAttribArray(1);
		GL30.glBindVertexArray(0);
	}
	
	private void creeMatriceProjection() {
		
		float aspectRatio = (float) Display.getWidth() / (float) Display.getHeight();
		float y_scale = (float) ((1f / Math.tan(Math.toRadians(FOV / 2f))) * aspectRatio);
		float x_scale = y_scale / aspectRatio;
		float frustum_length = FAR_PLANE - NEAR_PLANE;

		matriceProjection = new Matrix4f();
		matriceProjection.m00 = x_scale;
		matriceProjection.m11 = y_scale;
		matriceProjection.m22 = -((FAR_PLANE + NEAR_PLANE) / frustum_length);
		matriceProjection.m23 = -1;
		matriceProjection.m32 = -((2 * NEAR_PLANE * FAR_PLANE) / frustum_length);
		matriceProjection.m33 = 0;
		
		
		
	}
	
}

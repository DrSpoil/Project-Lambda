package moteurAffichage;

import ombrage.OmbrageStatique;
import ombrage.TerrainOmbrage;
import terrains.Terrain;
import modeles.TextureAvecModele;

import java.util.Map;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Matrix4f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import entitees.Camera;
import entitees.Entite;
import entitees.Lumiere;

public class MasterRenduGraphique {
	
	private static final float FOV = 70;
	private static final float NEAR_PLANE = 0.1f;
	private static final float FAR_PLANE = 1000;
	
	private static final float ROUGE = 0.25f;
	private static final float VERT = 0.65f;
	private static final float BLEU = 0.95f;
	
	private Matrix4f matriceProjection;
	
	private OmbrageStatique shader = new OmbrageStatique();
	private EntiteRenduGraphique renduGraphique;
	
	private TerrainRenduGraphique terrainRenderer;
	private TerrainOmbrage terrainOmbrage = new TerrainOmbrage();
	
	private Map<TextureAvecModele,List<Entite>> entites = new HashMap<TextureAvecModele,List<Entite>>();
	private List<Terrain> terrains = new ArrayList<Terrain>();
	
	public MasterRenduGraphique() {
		creeMatriceProjection();
		renduGraphique = new EntiteRenduGraphique(shader, matriceProjection);
		terrainRenderer = new TerrainRenduGraphique(terrainOmbrage, matriceProjection);
	}
	
	public static void enableCulling() {
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
	}
	
	public static void disableCulling() {
		GL11.glDisable(GL11.GL_CULL_FACE);
		
	}
	
	public void renduGraphique(Lumiere soleil, Camera camera) {
		prepare();
		shader.demarre();
		shader.chargeCielCouleur(ROUGE, VERT, BLEU);
		shader.chargeLumiere(soleil);
		shader.chargeMatriceVision(camera);
		renduGraphique.rendu(entites);
		shader.arret();
		terrainOmbrage.demarre();
		terrainOmbrage.chargeCielCouleur(ROUGE, VERT, BLEU);
		terrainOmbrage.chargeLumiere(soleil);
		terrainOmbrage.chargeMatriceVision(camera);
		terrainRenderer.rendu(terrains);
		terrainOmbrage.arret();
		terrains.clear();
		entites.clear();
	}
	
	public void procederTerrain(Terrain terrain) {
		terrains.add(terrain);
	}
	
	public void procederEntite(Entite entite) {
		TextureAvecModele entiteModele = entite.getModele();
		List<Entite> groupe = entites.get(entiteModele);
		if(groupe!=null) {
			groupe.add(entite);
		}else {
			List<Entite> nouveauGroupe = new ArrayList<Entite>();
			nouveauGroupe.add(entite);
			entites.put(entiteModele, nouveauGroupe);
		}
		
	}
	
	public void Nettoyage() {
		shader.nettoyage();
		terrainOmbrage.nettoyage();
	}
	
	public void prepare() {
		GL11.glEnable(GL11.GL_DEPTH_TEST);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
		GL11.glClearColor(ROUGE, VERT, BLEU, 1);
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

package ombrage;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import boite_a_outils.Mathematique;
import entitees.Camera;
import entitees.Lumiere;

public class TerrainOmbrage extends OmbrageClass{
	
	private static final String FICHIER_VERTEX = "src/ombrage/terrainVertexShader.txt";
	private static final String FICHIER_FRAGMENT = "src/ombrage/terrainFragmentShader.txt";
	
	private int location_transformationMatrice;
	private int location_matriceProjection;
	private int location_matriceVision;
	private int location_positionLumiere;
	private int location_couleurLumiere;
	private int location_coucheBrillance;
	private int location_reflectivite;
	private int location_couleurCiel;
	private int location_backgroundTexture;
	private int location_rTexture;
	private int location_gTexture;
	private int location_bTexture;
	private int location_blendMap;
	
	//Constructeur
	public TerrainOmbrage() {
		super(FICHIER_VERTEX, FICHIER_FRAGMENT);
	
	}

	@Override
	protected void lieAttributs() {
		super.lieAttributs(0, "position");
		super.lieAttributs(1, "coordTexture");
		super.lieAttributs(2, "normal");
		
	}

	@Override
	protected void getToutUniformLocations() {
		location_transformationMatrice = super.getUniformLocation("transformationMatrice");
		location_matriceProjection = super.getUniformLocation("matriceProjection");
		location_matriceVision = super.getUniformLocation("matriceVision");
		location_positionLumiere = super.getUniformLocation("positionLumiere");
		location_couleurLumiere = super.getUniformLocation("couleurLumiere");
		location_coucheBrillance = super.getUniformLocation("coucheBrillance");
		location_reflectivite = super.getUniformLocation("reflectivite");
		location_couleurCiel = super.getUniformLocation("couleurCiel");
		location_backgroundTexture = super.getUniformLocation("backgroundTexture");
		location_rTexture = super.getUniformLocation("rTexture");
		location_gTexture = super.getUniformLocation("gTexture");
		location_bTexture = super.getUniformLocation("bTexture");
		location_blendMap = super.getUniformLocation("blendMap");
	}
	
	public void unitesTextureConnexe() {
		super.chargeInt(location_backgroundTexture, 0);
		super.chargeInt(location_rTexture,1);
		super.chargeInt(location_gTexture,2);
		super.chargeInt(location_bTexture,3);
		super.chargeInt(location_blendMap,4);
	}
	
	public void chargeCielCouleur(float r,float g,float b) {
		super.chargeVecteur(location_couleurCiel, new Vector3f(r,g,b));
	}
	
	public void chargeVariablesBrillance(float coucheBrillance, float reflectivite ) {
		super.chargeFloat(location_coucheBrillance, coucheBrillance);
		super.chargeFloat(location_reflectivite, reflectivite);
	}
	
	public void chargeTransformationMatrice(Matrix4f matrice) {
		super.chargeMatrice(location_transformationMatrice, matrice);
	}
	
	public void chargeLumiere(Lumiere lumiere) {
		super.chargeVecteur(location_positionLumiere, lumiere.getPosition());
		super.chargeVecteur(location_couleurLumiere, lumiere.getCouleur());

	}
	
	public void chargeMatriceVision(Camera camera) {
		Matrix4f matriceVision = Mathematique.createMatriceVision(camera);
		super.chargeMatrice(location_matriceVision, matriceVision);
	}
	
	public void chargeMatriceProjection(Matrix4f projection) {
		super.chargeMatrice(location_matriceProjection, projection);
	}	

}

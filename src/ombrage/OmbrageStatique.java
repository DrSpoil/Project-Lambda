package ombrage;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import boite_a_outils.Mathematique;
import entitees.Camera;
import entitees.Lumiere;
//Classe qui se charge des ombrages statiques.
public class OmbrageStatique extends OmbrageClass{
	
	private static final String FICHIER_VERTEX = "src/ombrage/vertexShader.txt";
	private static final String FICHIER_FRAGMENT = "src/ombrage/fragmentShader.txt";
	
	private int location_transformationMatrice;
	private int location_matriceProjection;
	private int location_matriceVision;
	private int location_positionLumiere;
	private int location_couleurLumiere;
	private int location_coucheBrillance;
	private int location_reflectivite;
	private int location_utiliseFausseLumiere;
	private int location_couleurCiel;
	
	//Constructeur
	public OmbrageStatique() {
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
		location_utiliseFausseLumiere = super.getUniformLocation("utiliseFausseLumiere");
		location_couleurCiel = super.getUniformLocation("couleurCiel");
	}
	
	public void chargeCielCouleur(float r,float g,float b) {
		super.chargeVecteur(location_couleurCiel, new Vector3f(r,g,b));
	}
	
	public void chargeFausseLumiereVariable(boolean useFake) {
		super.chargeBooleen(location_utiliseFausseLumiere, useFake);
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

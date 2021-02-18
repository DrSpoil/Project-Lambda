package ombrage;

import org.lwjgl.util.vector.Matrix4f;

import boite_a_outils.Mathematique;
import entitees.Camera;

public class OmbrageStatique extends OmbrageClass{
	
	private static final String FICHIER_VERTEX = "/home/hdd1/Documents/Coding/Java/Project-Lambda/src/ombrage/vertexShader.txt";
	private static final String FICHIER_FRAGMENT = "/home/hdd1/Documents/Coding/Java/Project-Lambda/src/ombrage/fragmentShader.txt";
	
	private int location_transformationMatrice;
	private int location_matriceProjection;
	private int location_matriceVision;

	
	public OmbrageStatique() {
		super(FICHIER_VERTEX, FICHIER_FRAGMENT);
	
	}

	@Override
	protected void lieAttributs() {
		super.lieAttributs(0, "position");
		super.lieAttributs(1, "coordTexture");
		
	}

	@Override
	protected void getToutUniformLocations() {
		location_transformationMatrice = super.getUniformLocation("transformationMatrice");
		location_matriceProjection = super.getUniformLocation("matriceProjection");
		location_matriceVision = super.getUniformLocation("matriceVision");
	}
	
	public void chargeTransformationMatrice(Matrix4f matrice) {
		super.chargeMatrice(location_transformationMatrice, matrice);
	}
	
	public void chargeMatriceVision(Camera camera) {
		Matrix4f matriceVision = Mathematique.createMatriceVision(camera);
		super.chargeMatrice(location_matriceVision, matriceVision);
	}
	
	public void chargeMatriceProjection(Matrix4f projection) {
		super.chargeMatrice(location_matriceProjection, projection);
	}
	

	
}

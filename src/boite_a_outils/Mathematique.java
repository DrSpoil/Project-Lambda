package boite_a_outils;

import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

import entitees.Camera;

public class Mathematique {
	
	public static Matrix4f creeTransformationMatrice(Vector3f translation, float rx, float ry, float rz, float scale) {
		
		Matrix4f matrice = new Matrix4f();
		matrice.setIdentity();
		Matrix4f.translate(translation, matrice, matrice);
		Matrix4f.rotate((float) Math.toRadians(rx), new Vector3f(1,0,0), matrice, matrice);
		Matrix4f.rotate((float) Math.toRadians(ry), new Vector3f(0,1,0), matrice, matrice);
		Matrix4f.rotate((float) Math.toRadians(rz), new Vector3f(0,0,1), matrice, matrice);
		Matrix4f.scale(new Vector3f(scale,scale,scale), matrice, matrice);
		
		return matrice;
		
	}
	
	public static Matrix4f createMatriceVision(Camera camera) {
		
		Matrix4f matriceVision = new Matrix4f();
		matriceVision.setIdentity();
		Matrix4f.rotate((float) Math.toRadians(camera.getPitch()), new Vector3f(1, 0, 0), matriceVision,
				matriceVision);
		Matrix4f.rotate((float) Math.toRadians(camera.getYaw()), new Vector3f(0, 1, 0), matriceVision, matriceVision);
		Vector3f cameraPos = camera.getPosition();
		Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
		Matrix4f.translate(negativeCameraPos, matriceVision, matriceVision);
		return matriceVision;
	}
}
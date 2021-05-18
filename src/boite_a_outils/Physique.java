package boite_a_outils;

import org.lwjgl.util.vector.Vector3f;

public class Physique {
	
	private Vector3f vitesse;
	private Vector3f acceleration;
	
	public Physique() {
		
	}
	
	public Physique(Vector3f vitesse, Vector3f acceleration) {
		this.vitesse = vitesse;
		this.acceleration = acceleration;
	}

	
	public Vector3f getVitesse() {
		return vitesse;
	}

	public Vector3f getAcceleration() {
		return acceleration;
	}
	
}

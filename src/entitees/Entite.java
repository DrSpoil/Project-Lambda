package entitees;

import org.lwjgl.util.vector.Vector3f;

import modeles.TextureAvecModele;

/* La classe qui se charge de transformer
   les formes 3D en entite pour pouvoir
   les deplacer ou les tourner sur
   les axe x,y et z.
*/
public class Entite {
	
	private TextureAvecModele modele;
	private Vector3f position;
	private float rotX, rotY, rotZ;
	private float scale;
	
	private Vector3f vitesse = new Vector3f(0,0,0);
	private Vector3f acceleration = new Vector3f(0,0,0);
	private Vector3f force = new Vector3f(0,0,0);

	private static final float TERRAIN_HAUTEUR = 1;
	private static final float GRAVITE = -0.00075f;
	private float masse;

	private boolean estDansAir = false;
	
	//Constructeur
	public Entite(TextureAvecModele modele, Vector3f position, float masse,float rotX, float rotY, float rotZ, float scale) {
		super();
		this.modele = modele;
		this.masse = masse;
		this.position = position;
		this.rotX = rotX;
		this.rotY = rotY;
		this.rotZ = rotZ;
		this.scale = scale;
	}
	
	public void deplacement() {
		this.vitesse.x += acceleration.x;
		this.vitesse.y += acceleration.y;
		this.vitesse.z += acceleration.z;
		this.position.x += vitesse.x;
		this.position.y += vitesse.y;
		this.position.z += vitesse.z;
		
		forceGravitationnelle();
		
		if(position.y<=TERRAIN_HAUTEUR) {
			position.y = TERRAIN_HAUTEUR;
			vitesse.y = 0;
			acceleration.y = 0;
			estDansAir = false;
		}

	}
	
	public void forceGravitationnelle() {
		if(estDansAir) {
			acceleration.y += GRAVITE; 
			force.y = masse*GRAVITE;

		}
	}

	public void augmenteRotation(float dx, float dy, float dz) {
		this.rotX += dx;
		this.rotY += dy;
		this.rotZ += dz;

	}
	
	public float getMasse() {
		return masse;
	}

	public void setMasse(float masse) {
		this.masse = masse;
	}
	
	
	public Vector3f getForce() {
		this.force.x = masse*acceleration.x;
		this.force.y = masse*acceleration.y;
		this.force.z = masse*acceleration.z;
		return force;
	}

	public void setForce(Vector3f force) {
		this.force = force;
	}
	
	public Vector3f getAcceleration() {
		return acceleration;
	}
	
	public void setAcceleration(Vector3f acceleration) {
		this.acceleration = acceleration;
	}
	
	public Vector3f getVitesse() {
		return vitesse;
	}
	
	public void setVitesse(Vector3f vitesse) {
		this.vitesse = vitesse;
	}
	
	public TextureAvecModele getModele() {
		return modele;
	}

	public void setModele(TextureAvecModele modele) {
		this.modele = modele;
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}

	public float getRotX() {
		return rotX;
	}

	public void setRotX(float rotX) {
		this.rotX = rotX;
	}

	public float getRotY() {
		return rotY;
	}

	public void setRotY(float rotY) {
		this.rotY = rotY;
	}

	public float getRotZ() {
		return rotZ;
	}

	public void setRotZ(float rotZ) {
		this.rotZ = rotZ;
	}

	public float getScale() {
		return scale;
	}

	public void setScale(float scale) {
		this.scale = scale;
	}

	
	
}

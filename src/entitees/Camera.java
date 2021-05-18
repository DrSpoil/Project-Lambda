package entitees;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;
import boite_a_outils.Physique;

// Classe qui se charge de simuler un effet de camera.

public class Camera {
	
	
	private Vector3f position = new Vector3f(25,5,25);
	Physique moteurPhysique; //<--- TODO Implementer le monteur physique avec la classe Physique
	private Vector3f vitesse = new Vector3f(0,0,0);
	private Vector3f acceleration = new Vector3f(0,0,0);
	private Vector3f force = new Vector3f(0,0,0);
	private float limiteVitesse = 0.2f;
	//private Terrain terrain = new Terrain();
	
	private static final float MASSE = 2;
	private static final float MOVEMENT_SPEED = 0.002f;
	private static final float GRAVITE = -0.00075f;
	private static final float SAUT = 0.02f;
	private static final float TERRAIN_HAUTEUR = 1;
	
	public boolean estDansAir = false;

	private float pitch;
	private float yaw;
	private float roll;
	
	public Camera() {
		
	}
	
	/*
	   En informatique, les environnement 3D n'ont pas de
	   camera en tant que tel, mais plutot un semblant en
	   fesant bouger tout les objets autours donnent
	   l'illusion de camera.
	*/
	
	public void deplacementCamera() {
		
		this.vitesse.x += acceleration.x;
		this.vitesse.y += acceleration.y;
		this.vitesse.z += acceleration.z;
		
		this.position.x += vitesse.x;
		this.position.y += vitesse.y;
		this.position.z += vitesse.z;
	}
	
	public void mouvement() {
		checkInput();
		forceGravitationnelle();
		deplacementCamera();
		limiteVitesse();
		
		if(position.y>TERRAIN_HAUTEUR) {
			estDansAir = true;
		}
		/*
		if(position.y<=TERRAIN_HAUTEUR) {
			position.y = TERRAIN_HAUTEUR;
			vitesse.y = 0;
			acceleration.y = 0;
			estDansAir = false;
		}*/
		
	}
	
	public void checkInput(){
		
		if(Keyboard.isKeyDown(Keyboard.KEY_W) || Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			acceleration.z = -MOVEMENT_SPEED * (float)Math.cos(Math.toRadians(yaw));
			acceleration.x = MOVEMENT_SPEED * (float)Math.sin(Math.toRadians(yaw));
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_D) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			acceleration.x = -MOVEMENT_SPEED * (float)Math.sin(Math.toRadians(yaw-90));
			acceleration.z = MOVEMENT_SPEED * (float)Math.cos(Math.toRadians(yaw-90));
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_A) || Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			acceleration.x = MOVEMENT_SPEED * (float)Math.sin(Math.toRadians(yaw-90));
			acceleration.z = -MOVEMENT_SPEED * (float)Math.cos(Math.toRadians(yaw-90));
		}
		else if(Keyboard.isKeyDown(Keyboard.KEY_S) || Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
			acceleration.z = MOVEMENT_SPEED * (float)Math.cos(Math.toRadians(yaw));
			acceleration.x = -MOVEMENT_SPEED * (float)Math.sin(Math.toRadians(yaw));
		}else if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)) {
			acceleration.x = 0;
			acceleration.y = 0;
			acceleration.z = 0;
			vitesse.x = 0;
			vitesse.y = 0;
			vitesse.z = 0;
		}else if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)) {
			acceleration.x = 0;
			acceleration.y = 0;
			acceleration.z = 0;
			vitesse.x = 0;
			vitesse.y = 0;
			vitesse.z = 0;
			this.position.x = 25;
			this.position.y = 5;
			this.position.z = 25;
			pitch = 0;
			yaw = 0;
		}else{
			if(!estDansAir) {
			acceleration.x = 0;
			acceleration.z = 0;
			vitesse.x -= 0.07 *vitesse.x;
			vitesse.z -= 0.07 *vitesse.z;
			limiteVitesse = 0.2f;
			}
		}
		if(Mouse.isButtonDown(0)) {
		pitch -= 0.1f*Mouse.getDY();
		yaw += 0.1f*Mouse.getDX();

		}

		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			System.exit(0);
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			saut();
		}
	}
	
	
	public void saut() {
		if(!estDansAir) {
			acceleration.y += SAUT;
			estDansAir = true;
		}
	}
	
	public Vector3f getForce() {
		this.force.x = MASSE*acceleration.x;
		this.force.y = MASSE*acceleration.y;
		this.force.z = MASSE*acceleration.z;
		
		return force;
	}

	public void forceGravitationnelle() {
		if(estDansAir) {
			acceleration.y += GRAVITE; 
			force.y = MASSE*GRAVITE;

		}
	}
	public void limiteVitesse() {
		if(vitesse.x>=limiteVitesse) {
			vitesse.x =limiteVitesse;
		}
		if(vitesse.z>=limiteVitesse) {
			vitesse.z =limiteVitesse;
		}
		if(vitesse.x<=-limiteVitesse) {
			vitesse.x = -limiteVitesse;
		}
		if(vitesse.z<=-limiteVitesse) {
			vitesse.z = -limiteVitesse;
		}
		if(vitesse.y>=0.1f) {
			vitesse.y = 0.1f;
		}
	}
	
	public Vector3f getPosition() {
		return position;
	}
	
	public Vector3f getVitesse() {
		return vitesse;
	}

	public Vector3f getAcceleration() {
		return acceleration;
	}
	
	public float getPitch() {
		return pitch;
	}

	public float getYaw() {
		return yaw;
	}

	public float getRoll() {
		return roll;
	}
	
	
}

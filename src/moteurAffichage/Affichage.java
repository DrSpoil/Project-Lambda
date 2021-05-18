package moteurAffichage;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;
/* Classe qui se charge de l'affichage comme la resolution
   de la fenetre et le rafraichissement des images par secondes (FPS).
*/
public class Affichage {
	
	private static final int LARGEUR = 1280;
	private static final int HAUTEUR = 720;
	private static final int rafraichissementFPS = 60;
	private static final String TITRE = "Project-Lambda";
	
	
	//Temps
	
	private static long tempsDerniereFrame;
	private static float delta;

	//Initialise la fenetre
	
	public static void creerAffichage() {
		
		ContextAttribs attribut = new ContextAttribs(3,2)
		.withForwardCompatible(true)
		.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(LARGEUR, HAUTEUR));
			Display.create(new PixelFormat(), attribut);
			Display.setTitle(TITRE);
		} catch (LWJGLException e) {

			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, LARGEUR, HAUTEUR);
		tempsDerniereFrame = getTempsActuel();
	};
	
	//Les images (60 images par seconde dans notre cas) sont rafraichit, c'est la base de l'animation.
	
	public static void majAffichage() {
		
		Display.sync(rafraichissementFPS);
		Display.update();
		long tempsActuelFrame = getTempsActuel();
		delta = (tempsActuelFrame - tempsDerniereFrame)/1000f;
		tempsDerniereFrame = tempsActuelFrame;
	};
	
	public static float getTempsFrameSeconds() {
		return delta;
	}
	
	/* Dans la programmation graphique, il est toujours important
	   de fermer le processus a la fin de l'utilisation du programme,
	   sinon celle-ci sera toujours active meme si la fenetre est fermee.
	*/
	
	public static void fermerAffichage() {
		
		Display.destroy();
		
	};
	//Affiche le temps en miliseconde
	private static long getTempsActuel(){
		return Sys.getTime()*1000/Sys.getTimerResolution();
	}
	
	
}

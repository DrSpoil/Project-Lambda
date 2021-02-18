package moteurAffichage;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

public class Affichage {
	
	private static final int LARGEUR = 1280;
	private static final int HAUTEUR = 720;
	private static final int rafraichissementFPS = 60;
	private static final String TITRE = "Project-Lambda";
	
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
		
	};
	
	public static void majAffichage() {
		
		Display.sync(rafraichissementFPS);
		Display.update();
		
	};
	
	public static void fermerAffichage() {
		
		Display.destroy();
		
	};
	
}

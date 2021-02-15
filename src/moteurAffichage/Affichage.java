//Auteur: Rayan Ibrahim

package moteurAffichage;


import org.lwjgl.*;
import org.lwjgl.glfw.*;
import org.lwjgl.opengl.*;
import org.lwjgl.system.*;
import org.lwjgl.glfw.GLFWVidMode;

import java.nio.*;

import static org.lwjgl.glfw.Callbacks.*;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryStack.*;
import static org.lwjgl.system.MemoryUtil.*;

public class Affichage {
	
	/*
	 * Initialise les constantes pour la hauteur et la largeur de l'ecran
	 * Ces attribut ne doivent pas changer
	*/
	private static final int LARGEUR = 1280;
	private static final int HAUTEUR = 720;
	
	//Initialise la fenetre, on ajoutera les attribut plus tard
	private long fenetre;
	
	public void lancement() {
		System.out.println("Ceci est un test, LWJGL version:" + Version.getVersion());
		
		//Initialise le programme et lance le loop
		init();
		loop();
		
		
		glfwFreeCallbacks(fenetre);
		glfwDestroyWindow(fenetre);
		
		//Ferme la fenetre et le programme
		glfwTerminate();
		glfwSetErrorCallback(null).free();
		
	}
	
	private void init() {
		
		//Affiche l'erreur sur la console
		GLFWErrorCallback.createPrint(System.err).set();
		
		//Lance un message d'erreur si l'initialisation de glfw n'a pu se declencher
		if(!glfwInit()) {
			throw new IllegalStateException("Erreure: L'initialisation de GLFW n'a pas pu se lancer!");
			}
		

		// La fenetre ne sera visible qu'apres sa creation
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
		
		// La taille de la fenetre ne peut pas etre modifier
		glfwWindowHint(GLFW_RESIZABLE, GLFW_FALSE); 
		
		/*
		 *Creer une fenetre de type long, largeur, hauteur, titre, moniteur (permet de mettre la fenetre en plein ecran)
		 *et "share" permet d'utiliser OpenGl (la librairie pour faire des graphiques texture etc.)
		 *sur plusieure fenetre sans utiliser trop de memoire
		 *Si vous voulez le mettre en plein ecran, remplacer le quatrieme attribut par glfwGetPrimaryMonitor() 
		 *e.g. : glfwCreateWindow(1280, 720, "Project-Lambda", glfwGetPrimaryMonitor(), 0);
		*/
		fenetre = glfwCreateWindow(LARGEUR, HAUTEUR, "Project-Lambda", NULL, NULL);
		
		//Lance un message d'erreur si la fenetre n'a pas pu se creer
		if(fenetre == NULL) {
			throw new RuntimeException("Erreure: La fenetre n'a pas pu se lancer!");
		}
		
		//Affiche les touches du clavier appuye, en boucle ou relacher
		glfwSetKeyCallback(fenetre, (window, key, scancode, action, mods) -> {
			if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
				glfwSetWindowShouldClose(fenetre, true); // Sera affiche dans le loop
		});
		
		// Gere la memoire et les frames
		try ( MemoryStack stack = stackPush() ) {
			
			/*En programmation, lorsqu'on a un "p" au debut d'une variable c'est pour signifier 
			 *qu'il s'agit d'un "pointer". Pour faire simple, un pointer est un attribut qui 
			 *contient l'addresse de la memoires. Paniquez pas, vous allez voir cela plus tard et 
			 *ce n'est pas aussi compliquer que ca en a l'air.
			*/
			IntBuffer pLargeur = stack.mallocInt(1);
			IntBuffer pHauteur = stack.mallocInt(1);

			// Getter pour la taille de la fenetre qui est ajouter au glfwCreateWindow
			glfwGetWindowSize(fenetre, pLargeur, pHauteur);

			// Getter pour la resolution de l'ecran principal
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

			// Center the window
			glfwSetWindowPos(
				fenetre,
				(vidmode.width() - pLargeur.get(0)) / 2,
				(vidmode.height() - pHauteur.get(0)) / 2
			);
		} // La frame est remplacee automatiquement
		
		// Utilise le OpenGL
		glfwMakeContextCurrent(fenetre);
		// Active le v-sync
		glfwSwapInterval(1);

		// Affiche la fenetre
		glfwShowWindow(fenetre);
		
	}
	
	private void loop() {
				
		/* Loop principal de la fenetre, met a jour chaque frame,
		 * creer les GLCapabilities et permet d'utiliser OpenGL
		*/
			
		GL.createCapabilities();

		// Setter pour la couleur de la fenetre
		glClearColor(0.0f, 0.0f, 0.0f, 0.0f);//--> On va mettre noir pour ce test

		// Lance le loop jusqu'a ce que l'utilisateur quitte en fermant la fenetre 
		// ou appuie sur la touche ESC.
		while ( !glfwWindowShouldClose(fenetre) ) {
				
				// Vide le framebuffer
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				
				// Change la couleur des buffers
				glfwSwapBuffers(fenetre);

				//Met a jour la fenetre
				glfwPollEvents();
				}
		
	}
	
}
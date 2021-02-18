package lambda_src;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import entitees.Camera;
import entitees.Entite;
import modeles.Modele;
import textures.ModeleTexture;
import modeles.TextureAvecModele;
import moteurAffichage.Affichage;
import moteurAffichage.ChargeModele3d;
import moteurAffichage.RenduGraphique;
import ombrage.OmbrageStatique;

public class Main {

	public static void main(String[] args) {
		
		Affichage.creerAffichage();
		
		ChargeModele3d chargeur = new ChargeModele3d();
		OmbrageStatique ombrage = new OmbrageStatique();
		RenduGraphique rendu = new RenduGraphique(ombrage);
		
		  float[] sommets = {			
					
				  	-0.5f,0.5f,-0.5f,	
					-0.5f,-0.5f,-0.5f,	
					0.5f,-0.5f,-0.5f,	
					0.5f,0.5f,-0.5f,		
					
					-0.5f,0.5f,0.5f,	
					-0.5f,-0.5f,0.5f,	
					0.5f,-0.5f,0.5f,	
					0.5f,0.5f,0.5f,
					
					0.5f,0.5f,-0.5f,	
					0.5f,-0.5f,-0.5f,	
					0.5f,-0.5f,0.5f,	
					0.5f,0.5f,0.5f,
					
					-0.5f,0.5f,-0.5f,	
					-0.5f,-0.5f,-0.5f,	
					-0.5f,-0.5f,0.5f,	
					-0.5f,0.5f,0.5f,
					
					-0.5f,0.5f,0.5f,
					-0.5f,0.5f,-0.5f,
					0.5f,0.5f,-0.5f,
					0.5f,0.5f,0.5f,
					
					-0.5f,-0.5f,0.5f,
					-0.5f,-0.5f,-0.5f,
					0.5f,-0.5f,-0.5f,
					0.5f,-0.5f,0.5f
					
			};
		  
		  int[] indices = {
					0,1,3,	
					3,1,2,	
					4,5,7,
					7,5,6,
					8,9,11,
					11,9,10,
					12,13,15,
					15,13,14,	
					16,17,19,
					19,17,18,
					20,21,23,
					23,21,22

			};
		  
		  float[] coordTexture = {
					
					0,0,
					0,1,
					1,1,
					1,0,			
					0,0,
					0,1,
					1,1,
					1,0,			
					0,0,
					0,1,
					1,1,
					1,0,
					0,0,
					0,1,
					1,1,
					1,0,
					0,0,
					0,1,
					1,1,
					1,0,
					0,0,
					0,1,
					1,1,
					1,0

					
			};
		
		  Modele modele = chargeur.chargeVAO(sommets,coordTexture,indices);
		  
		  TextureAvecModele modeleStatique = new TextureAvecModele(modele, new ModeleTexture(chargeur.chargeTexture("pierre")));
		  
		  Entite entite = new Entite(modeleStatique, new Vector3f(0,0,-3),0,0,0,1);
		  
		  Camera camera = new Camera();
		  
		while(!Display.isCloseRequested()) {
			entite.augmenteRotation(1, -1, 0);
			camera.Mouvement();
			entite.augmenteRotation(0, 0, 0);
			rendu.prepare();
			ombrage.demarre();
			ombrage.chargeMatriceVision(camera);
			rendu.rendu(entite,ombrage);
			ombrage.arret();
			Affichage.majAffichage();
			
		}
		ombrage.nettoyage();
		chargeur.Nettoyage();
		Affichage.fermerAffichage();

	}

}

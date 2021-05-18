package lambda_src;

import java.util.ArrayList;
import java.util.List;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import convertisseurOBJ.ModelData;
import convertisseurOBJ.ChargeFichierObj;
import entitees.Camera;
import entitees.Entite;
import entitees.Lumiere;
import frameGUI.Frame;
import modeles.Modele;
import textures.ModeleTexture;
import textures.TextureTerrain;
import textures.TextureTerrainPack;
import modeles.TextureAvecModele;
import moteurAffichage.Affichage;
//import moteurAffichage.ChargeFichierObj;
import moteurAffichage.ChargeModele3d;
import moteurAffichage.MasterRenduGraphique;
import terrains.Terrain;
/*Le main, la ou tout est mis en place pour afficher
  et deplacer des objets en trois dimensions.
*/
public class Main {
	
	public static void main(String[] args) {
		
		
		
		Frame frame = new Frame();
		
		while(!frame.isInit()){
		System.out.println(Affichage.getTempsFrameSeconds());
	    }
			
		Affichage.creerAffichage();
			 
		ChargeModele3d chargeur = new ChargeModele3d();
		
	//Texture du terrain
		
		TextureTerrain textureBackground = new TextureTerrain(chargeur.chargeTexture("gris"));
		TextureTerrain rTexture = new TextureTerrain(chargeur.chargeTexture("gris"));
		TextureTerrain gTexture = new TextureTerrain(chargeur.chargeTexture("gris"));
		TextureTerrain bTexture = new TextureTerrain(chargeur.chargeTexture("gris"));
		
		TextureTerrainPack texturePack = new TextureTerrainPack(textureBackground,rTexture,gTexture,bTexture);
		TextureTerrain blendMap = new TextureTerrain(chargeur.chargeTexture("blendMap2"));
	
	//Terrain
		Terrain terrain = new Terrain(0,0,chargeur,texturePack,blendMap);
		Terrain terrain2 = new Terrain(-1,0,chargeur,texturePack,blendMap);
		Terrain terrain3 = new Terrain(0,-1,chargeur,texturePack,blendMap);
		Terrain terrain4 = new Terrain(-1,-1,chargeur,texturePack,blendMap);
		
	//Objets en 3D
		
		List<Entite> toutesEntites = new ArrayList<Entite>();
		
		//Charge les donnees du fichier obj pour les cubes
		ModelData donneeCube = ChargeFichierObj.loadOBJ("cube");
		
		Modele modeleCube = chargeur.chargeVAO(donneeCube.getVertices(), donneeCube.getTextureCoords(), donneeCube.getNormals(), donneeCube.getIndices());
		
		//cube 1
		
		TextureAvecModele cubeModeleStatique0 = new TextureAvecModele(modeleCube, new ModeleTexture(chargeur.chargeTexture("metal")));
		  
		Entite entiteCube0 = new Entite(cubeModeleStatique0, new Vector3f(25,1,-40),3,0,0,0,1);
		
		//cube 2
		  
		  TextureAvecModele cubeModeleStatique1 = new TextureAvecModele(modeleCube, new ModeleTexture(chargeur.chargeTexture("glace")));
		  
		  Entite entiteCube1 = new Entite(cubeModeleStatique1, new Vector3f(20,4,-40),1,0,0,0,1);
		  
		//cube 3
		  
		  TextureAvecModele cubeModeleStatique2 = new TextureAvecModele(modeleCube, new ModeleTexture(chargeur.chargeTexture("pierre")));
		  
		  Entite entiteCube2 = new Entite(cubeModeleStatique2, new Vector3f(30,4,-40),2,0,0,0,1);
		
		
		
		//Charge les donnees du fichier obj pour les spheres
		
		ModelData donneeSphere = ChargeFichierObj.loadOBJ("sphere");
		
		Modele modeleSphere = chargeur.chargeVAO(donneeSphere.getVertices(), donneeSphere.getTextureCoords(), donneeSphere.getNormals(), donneeSphere.getIndices());
		
		//Jupiter
		  
		  TextureAvecModele sphereModeleStatiqueJupiter = new TextureAvecModele(modeleSphere, new ModeleTexture(chargeur.chargeTexture("jupiter")));
		  
		  Entite entiteSphereJupiter = new Entite(sphereModeleStatiqueJupiter, new Vector3f(40,40,-120),10,0,0,0,40);
		
		//sphere 1
		  
		  TextureAvecModele sphereModeleStatique0 = new TextureAvecModele(modeleSphere, new ModeleTexture(chargeur.chargeTexture("terre")));
		  
		  Entite entiteSphere0 = new Entite(sphereModeleStatique0, new Vector3f(15,1,-40),10,0,0,0,1);
		  
		//sphere 2
		  
		  TextureAvecModele sphereModeleStatique1 = new TextureAvecModele(modeleSphere, new ModeleTexture(chargeur.chargeTexture("bois")));
		  
		  Entite entiteSphere1 = new Entite(sphereModeleStatique1, new Vector3f(35,1,-40),0.5f,0,0,0,1);
		  
		  entiteSphere1.setVitesse(new Vector3f(-0.001f,0,0));
		  entiteSphere1.setAcceleration(new Vector3f(-0.00000000000000001f,0,0));
		  
		//sphere 3
		  
		  TextureAvecModele sphereModeleStatique2 = new TextureAvecModele(modeleSphere, new ModeleTexture(chargeur.chargeTexture("glace")));
		  
		  Entite entiteSphere2 = new Entite(sphereModeleStatique2, new Vector3f(-40,15,25),0.5f,0,0,0,1);		  

		  
		  Entite entiteSphere3 = new Entite(sphereModeleStatique2, new Vector3f(-40,2,15),0.5f,0,0,0,1);
		  

		  Entite entiteSphere4 = new Entite(sphereModeleStatique2, new Vector3f(-40,15,35),0.5f,0,0,0,1);
		  entiteSphere4.setVitesse(new Vector3f(0,-0.07f,0));
		  
		  entiteSphere4.setAcceleration(new Vector3f(0,-0.001f,0));
		  
		  Entite entiteSphere5 = new Entite(sphereModeleStatique2, new Vector3f(80,15,35),0.5f,0,0,0,4);
		  entiteSphere5.setVitesse(new Vector3f(0,0,-0.2f));

		  
		//plateforme
		  
		  ModelData donneePlateforme = ChargeFichierObj.loadOBJ("platforme");
			
		  Modele modelePlateforme = chargeur.chargeVAO(donneePlateforme.getVertices(), donneePlateforme.getTextureCoords(), donneePlateforme.getNormals(), donneePlateforme.getIndices());
		  
		  TextureAvecModele plateformeModeleStatique = new TextureAvecModele(modelePlateforme, new ModeleTexture(chargeur.chargeTexture("gris")));
		  
		  Entite entitePlateforme = new Entite(plateformeModeleStatique, new Vector3f(-40,2,25),0,0,0,0,1);
		  
		  Entite entitePlateforme2 = new Entite(plateformeModeleStatique, new Vector3f(-40,16,25),0,0,0,0,1);
		  
		  Entite entitePlateforme3 = new Entite(plateformeModeleStatique, new Vector3f(-40,2,15),0,0,0,0,1.5f);
		  
		  Entite entitePlateforme4 = new Entite(plateformeModeleStatique, new Vector3f(-40,16,15),0,0,0,0,1.5f);
		  
		  Entite entitePlateforme5 = new Entite(plateformeModeleStatique, new Vector3f(-40,2,35),0,0,0,0,1.5f);
		  
		  Entite entitePlateforme6 = new Entite(plateformeModeleStatique, new Vector3f(-40,16,35),0,0,0,0,1.5f);
		  
		  Entite entitePlateforme7 = new Entite(plateformeModeleStatique, new Vector3f(80,15,0),0,0,0,0,2.5f);
		  entitePlateforme7.augmenteRotation(90, 0, 0);
		  
		  Entite entitePlateforme8 = new Entite(plateformeModeleStatique, new Vector3f(80,15,70),0,0,0,0,2.5f);
		  entitePlateforme8.augmenteRotation(90, 0, 0);
		  
		  Entite entitePlateforme9 = new Entite(plateformeModeleStatique, new Vector3f(25,15,100),0,0,0,0,2.5f);
		 
		  
		  sphereModeleStatique1.getTexture().setEstTransparent(true);
		  
		//Lumiere et camera
		  
		  ModeleTexture texture = sphereModeleStatique1.getTexture();
		  texture.setCoucheBrillance(50);
		  texture.setReflectivite(1);
		  
		  ModeleTexture texture2 = sphereModeleStatique2.getTexture();
		  texture2.setCoucheBrillance(50);
		  texture2.setReflectivite(1);
		  
		  Lumiere lumiere = new Lumiere(new Vector3f(25,50,25), new Vector3f(1,1,1));
		  
		  //Player
		  		  
		  Camera camera = new Camera();
		  
		//crosshair
		 
		 
		 // TextureAvecModele crosshairModeleStatique = new TextureAvecModele(crosshair, new ModeleTexture(chargeur.chargeTexture("crosshair3")));
		  
		 // Entite entiteCrosshair = new Entite(crosshairModeleStatique, new Vector3f(camera.getPosition().x,camera.getPosition().y,camera.getPosition().z-10),0,0,0,1);
		
		  toutesEntites.add(entiteCube0);
		  toutesEntites.add(entiteCube1);
		  toutesEntites.add(entiteCube2);
		  toutesEntites.add(entiteSphere0);
		  toutesEntites.add(entiteSphere1);
		  toutesEntites.add(entitePlateforme);
		  toutesEntites.add(entitePlateforme2);
		  toutesEntites.add(entiteSphere2);
		  toutesEntites.add(entitePlateforme3);
		  toutesEntites.add(entitePlateforme4);
		  toutesEntites.add(entiteSphere3);
		  toutesEntites.add(entiteSphere4);
		  toutesEntites.add(entitePlateforme5);
		  toutesEntites.add(entitePlateforme6);
		  toutesEntites.add(entiteSphere5);
		  toutesEntites.add(entitePlateforme7);
		  toutesEntites.add(entitePlateforme8);
		  toutesEntites.add(entitePlateforme9);
		  toutesEntites.add(entiteSphereJupiter);
		  
		/*While loop, necessaire dans tout ce qui est animation, aussi nomme game loop.
		  Tout ce qui est texturage, rendu graphique, ombrage ,lumiere, couleur et mouvement
		  se situent en dessous de ce loop.
		  */
		  MasterRenduGraphique renduG = new MasterRenduGraphique();

		while(!Display.isCloseRequested()) {

			entiteCube0.augmenteRotation(0, 1, 0);
			entiteCube1.augmenteRotation(0, -2, 2);
			entiteCube2.augmenteRotation(-1, 3, 0);
			entiteSphere0.augmenteRotation(0, -2, 0);
			entiteSphere1.augmenteRotation(0,0,0);
			entiteSphere2.deplacement();
			entiteSphere3.deplacement();
			entiteSphere4.deplacement();
			entiteSphere5.deplacement();
			entitePlateforme9.augmenteRotation(0, 0, 4);
			entiteSphereJupiter.augmenteRotation(0, -0.25f, 0);
			
			for(Entite entites:toutesEntites ) {
				renduG.procederEntite(entites);
			}
			
			if(entiteSphere2.getPosition().y<=entitePlateforme.getPosition().y+1) {
				entiteSphere2.setVitesse(new Vector3f(0,0.07f,0));
			}
			if(entiteSphere2.getPosition().y>=entitePlateforme2.getPosition().y-1) {
				entiteSphere2.setVitesse(new Vector3f(0,-0.07f,0));
			}
			if(entiteSphere3.getPosition().y<=entitePlateforme.getPosition().y+1) {
				entiteSphere3.setVitesse(new Vector3f(0,0.1f,0));
			}
			if(entiteSphere3.getPosition().y>=entitePlateforme2.getPosition().y-1) {
				entiteSphere3.setVitesse(new Vector3f(0,-0.1f,0));
			}
			if(entiteSphere4.getPosition().y<=entitePlateforme.getPosition().y+1) {
				entiteSphere4.setVitesse(new Vector3f(entiteSphere4.getVitesse().x,-1*entiteSphere4.getVitesse().y,entiteSphere4.getVitesse().z));
				entiteSphere4.setAcceleration(new Vector3f(entiteSphere4.getAcceleration().x,-1*entiteSphere4.getAcceleration().y,entiteSphere4.getAcceleration().z));
			}
			if(entiteSphere4.getPosition().y>=entitePlateforme2.getPosition().y-1) {
				entiteSphere4.setVitesse(new Vector3f(entiteSphere4.getVitesse().x,-1*entiteSphere4.getVitesse().y,entiteSphere4.getVitesse().z));
				entiteSphere4.setAcceleration(new Vector3f(entiteSphere4.getAcceleration().x,-1*entiteSphere4.getAcceleration().y,entiteSphere4.getAcceleration().z));
			}
			if(entiteSphere5.getPosition().z<=entitePlateforme7.getPosition().z+4) {
				entiteSphere5.setVitesse(new Vector3f(entiteSphere5.getVitesse().x,entiteSphere5.getVitesse().y,-1*entiteSphere5.getVitesse().z));
			}
			if(entiteSphere5.getPosition().z>=entitePlateforme8.getPosition().z-4) {
				entiteSphere5.setVitesse(new Vector3f(entiteSphere5.getVitesse().x,entiteSphere5.getVitesse().y,-1*entiteSphere5.getVitesse().z));
			}
			
			
			if(camera.getPosition().y<=1) {
				camera.getPosition().y = 1;
				camera.getVitesse().y = 0;
				camera.getAcceleration().y = 0;
				camera.estDansAir = false;
			}else if(camera.getPosition().y<=1) {
				
			}
			
			camera.mouvement();
			renduG.procederTerrain(terrain);
			renduG.procederTerrain(terrain2);
			renduG.procederTerrain(terrain3);
			renduG.procederTerrain(terrain4);
			renduG.renduGraphique(lumiere, camera);
			Affichage.majAffichage();
			System.out.println(camera.getPosition());

			
		}
		renduG.Nettoyage();
		chargeur.Nettoyage();
		Affichage.fermerAffichage();
		}
	}

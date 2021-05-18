package moteurAffichage;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import modeles.Modele;

public class ChargeModele3d {
	/*Initialise les listes  vao (vertex array object),
	  vbo (vertex buffer object) et textures.
	  Pour faire simple, un vao et un vbo est une liste de
	  matrice, chaque matrice contient des information sur
	  les objets en trois dimensions.
	  Pour plus de detail sur ce qu'est un vao et un vbo
	  voici un lien: https://www.khronos.org/opengl/wiki/Vertex_Array_Objects
	*/
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();

	//Methode qui initialise un vao
	public Modele chargeVAO(float[] positions,float[] coordTexture ,float[] normals,int[] indices) {
		int vaoID = creerVAO();
		liaisonIndicesBuffer(indices);
		enregistreDonneeListeAttribut(0,3,positions);
		enregistreDonneeListeAttribut(1,2,coordTexture);
		enregistreDonneeListeAttribut(2,3,normals);
		delieVAO();
		
		return new Modele(vaoID, indices.length);
	}
	
	//Methode qui recupere les textures dans le dossier /res  
	public int chargeTexture(String nomFichier) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+nomFichier+".png"));
			GL30.glGenerateMipmap(GL11.GL_TEXTURE_2D);
			GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR_MIPMAP_LINEAR);
			GL11.glTexParameterf(GL11.GL_TEXTURE_2D, GL14.GL_TEXTURE_LOD_BIAS, -0.3f);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int idTexture = texture.getTextureID();
		textures.add(idTexture);
		return idTexture;
	}
	
	//Methode qui vide les tableaux vao et vbo
	public void Nettoyage() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int texture:textures) {
			GL11.glDeleteTextures(texture);
		}
	}
	
	//Creer un vao
	private int creerVAO() {
		
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
		
	}
	
	private void enregistreDonneeListeAttribut(int nombreAttribut, int tailleCoordonnee ,float[] donnee) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,vboID);
		FloatBuffer buffer = enregistreDonneeFLoatBuffer(donnee);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(nombreAttribut, tailleCoordonnee, GL11.GL_FLOAT, false,0,0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER,0);
	}
	
	private void delieVAO() {
		GL30.glBindVertexArray(0);
	}
	
	private void liaisonIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = enregistreDoneeDansIntBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}
	
	private IntBuffer enregistreDoneeDansIntBuffer(int[] donnee) {
		IntBuffer buffer = BufferUtils.createIntBuffer(donnee.length);
		buffer.put(donnee);
		buffer.flip();
		return buffer;
	}
	
	private FloatBuffer enregistreDonneeFLoatBuffer(float[] donnee) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(donnee.length);
		buffer.put(donnee);
		buffer.flip();
		return buffer;
	}
	
}

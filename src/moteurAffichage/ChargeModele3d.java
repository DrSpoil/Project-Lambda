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
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import modeles.Modele;

public class ChargeModele3d {
	
	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();

	
	public Modele chargeVAO(float[] positions,float[] coordTexture ,int[] indices) {
		int vaoID = creerVAO();
		liaisonIndicesBuffer(indices);
		enregistreDonneeListeAttribut(0,3,positions);
		enregistreDonneeListeAttribut(1,2,coordTexture);
		delieVAO();
		
		return new Modele(vaoID, indices.length);
	}
	
	public int chargeTexture(String nomFichier) {
		Texture texture = null;
		try {
			texture = TextureLoader.getTexture("PNG", new FileInputStream("res/"+nomFichier+".png"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		int idTexture = texture.getTextureID();
		textures.add(idTexture);
		return idTexture;
	}
	
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

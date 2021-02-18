package ombrage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;

public abstract class OmbrageClass {
	
	private int idProgramme;
	private int idVertexShader;
	private int	idFragmentShader;
	
	private static FloatBuffer matriceBuffer = BufferUtils.createFloatBuffer(16);
	
	public OmbrageClass(String fichieVertex, String fichierFragment) {
		this.idVertexShader = chargeOmbrage(fichieVertex, GL20.GL_VERTEX_SHADER);
		this.idFragmentShader = chargeOmbrage(fichierFragment, GL20.GL_FRAGMENT_SHADER);
		this.idProgramme = GL20.glCreateProgram();
		GL20.glAttachShader(idProgramme, idVertexShader);
		GL20.glAttachShader(idProgramme, idFragmentShader);
		lieAttributs();
		GL20.glLinkProgram(idProgramme);
		GL20.glValidateProgram(idProgramme);
		getToutUniformLocations();
	}
	
	protected abstract void getToutUniformLocations();
	
	protected int getUniformLocation(String uniformNom) {
		return GL20.glGetUniformLocation(idProgramme, uniformNom);
	}
	
	public void demarre() {
		GL20.glUseProgram(idProgramme);
	}
	
	public void arret() {
		GL20.glUseProgram(0);
	}
	
	public void nettoyage() {
		arret();
		GL20.glDetachShader(idProgramme, idVertexShader);
		GL20.glDetachShader(idProgramme, idFragmentShader);
		GL20.glDeleteShader(idVertexShader);
		GL20.glDeleteShader(idFragmentShader);
		GL20.glDeleteProgram(idProgramme);
	}
	
	protected abstract void lieAttributs();
	
	protected void lieAttributs(int attribut, String nomVariable) {
		GL20.glBindAttribLocation(idProgramme, attribut, nomVariable);
		
	}
	
	protected void chargeFloat(int location, int valeur) {
		GL20.glUniform1f(location, valeur);
	}
	
	protected void chargeVecteur(int location, Vector3f vecteur) {
		GL20.glUniform3f(location, vecteur.x,vecteur.y,vecteur.z);
	}
	
	protected void chargeBooleen(int location, boolean valeur) {
		float aCharge = 0;
		if(valeur) {
			aCharge = 1;
		}
		GL20.glUniform1f(location, aCharge);
	}
	
	protected void chargeMatrice(int location, Matrix4f matrice) {
		matrice.store(matriceBuffer);
		matriceBuffer.flip();
		GL20.glUniformMatrix4(location, false, matriceBuffer);
	}
	
	private static int chargeOmbrage(String fichier, int type) {
		StringBuilder shaderSource = new StringBuilder();
		  try{
		   BufferedReader reader = new BufferedReader(new FileReader(fichier));
		   String line;
		   while((line = reader.readLine())!=null){
		    shaderSource.append(line).append("//\n");
		   }
		   reader.close();
		  }catch(IOException e){
		   e.printStackTrace();
		   System.exit(-1);
		  }
		  int shaderID = GL20.glCreateShader(type);
		  GL20.glShaderSource(shaderID, shaderSource);
		  GL20.glCompileShader(shaderID);
		  if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS )== GL11.GL_FALSE){
		   System.out.println(GL20.glGetShaderInfoLog(shaderID, 500));
		   System.err.println("Le shader n'a pas pu etre compile!");
		   System.exit(-1);
		  }
		  return shaderID;	
		
	}
}

package frameGUI;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

//Auteur Gbeti Kenneth Edwin, Huaracha Mario, Huynh Thanh-Phong

public class Controle{
		
		public Controle(){
			
			UIManager.put("OptionPane.background", new Color(120,120,120));
			UIManager.put("Panel.background", new Color(120,120,120));
			
			JOptionPane.showMessageDialog(null,"Utiliser les touches directionnelles ou WASD pour se deplacer en avant, en arriere, a gauche et a droite.\n"
					+ "Pour sauter, utiliser la barre d'espace.\n"
					+ "Pour faire tourner la camera, appuyer sur le clic droit de la souris sans le relacher.\n"
					 ,"Controles",JOptionPane.PLAIN_MESSAGE);
		}

}

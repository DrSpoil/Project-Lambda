package frameGUI;

import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

//Auteur Gbeti Kenneth Edwin, Huaracha Mario, Huynh Thanh-Phong

public class APropos{
	

	public APropos(){
		
		UIManager.put("OptionPane.background", new Color(120,120,120));
		UIManager.put("Panel.background", new Color(120,120,120));
		
		JOptionPane.showMessageDialog(null,"Notre application Project-Lambda effectue une simulation de physique qui se base sur.\n"
				+ "les concepts théoriques vu en mécanique classique.\n"
				+ "\nDe plus, notre application permettra de simuler le déplacement d'un sujet avec\n"
				+ "une caméra a la premiere personne contrôlée par l'utilisateur.\n"
				+ "\nProjet crée par : Huynh Thanh-Phong, Ibrahim Rayan, Kenneth Edwin Gbeti et Huaracha Mario.\n"
				+ "Cours: Projet d'intégration en Sciences informatiques et mathématiques (420-204-RE)\n"
				+ "Remis à : Mme Wafaa Niar Dinedane.","A propos",JOptionPane.PLAIN_MESSAGE);

	}

}

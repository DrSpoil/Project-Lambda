package frameGUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JButton;

//Auteur Gbeti Kenneth Edwin, Huaracha Mario, Huynh Thanh-Phong

public class Frame extends JFrame implements ActionListener{
	

	private static final long serialVersionUID = 1L;

	private boolean init = false;
	
	public boolean isInit() {
		return init;
	}

	public void setInit(boolean init) {
		this.init = init;
	}

	private ImageIcon icon;
	
	private ImageIcon logo;
	
	private JLabel label;
	
	private JButton demarrer;
	
	private JButton quitter;
	
	private JMenuBar menuBar;
	
	private JMenu aide;
	
	private JMenuItem aPropos;
	
	private JMenuItem controle;
	
	
	public Frame() {
		
		icon = new ImageIcon("res/Project-LambdaIcon.png");
		
		logo = new ImageIcon("res/Lambda2.png");
		
		label = new JLabel();
		
		demarrer = new JButton("Demarrer");
		
		quitter = new JButton("Quitter");
		
		menuBar = new JMenuBar();
		
		aide = new JMenu("?");
		
		aPropos = new JMenuItem("A propos");
		
		controle = new JMenuItem("Controles");
		
		
		aPropos.addActionListener(this);
		
		controle.addActionListener(this);
		
		demarrer.addActionListener(this);
		
		aide.add(controle);
		aide.add(aPropos);
		
		menuBar.add(aide);
		
		demarrer.setBounds(60, 800, 125, 30);
		demarrer.setFocusable(false);
		
		quitter.setBounds(460, 800, 125, 30);
		quitter.addActionListener(e -> System.exit(0));
		quitter.setFocusable(false);
		
		label.setIcon(logo);
		label.setHorizontalAlignment(JLabel.CENTER);
		
		this.setSize(650,900);
		
		this.setLocationRelativeTo(null);
		
		this.setTitle("Project-Lambda");
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.setResizable(false);
		
		this.setVisible(true);
		
		this.setIconImage(icon.getImage());
		
		this.getContentPane().setBackground(new Color(120,120,120));
		
		this.setJMenuBar(menuBar);
		
		this.add(demarrer);
		this.add(quitter);
		this.add(label);
	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == demarrer) {
			this.dispose();
			setInit(true);
			System.out.println("System Init: "+isInit());

		}
		
		if(e.getSource() == aPropos) {
			new APropos();

		}
		
		if(e.getSource() == controle) {
			new Controle();

		}
		
	}

}

package Time;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

import control.Controller;
import robot.Bala;
import robot.IConstants;
import robot.IRobot;
import robot.MyRobot;
import robot.MyWeapon;
import robot.Weapon;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class CicloTiempo {

	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\eclipse-workspace\\Caso4-2\\src\\Imagen\\";
	public int dir = 0;
	public boolean dirIzq = false;
	public boolean dirDer = false;
	public boolean dirArriba = false;
	public boolean dirAbajo = false;
	public ArrayList<JLabel> ListaImagenRobot;
	public ArrayList<JLabel> ListaImagenDano;
	public ArrayList<JLabel> ListaImagenTrampas;
	private JPanel panelPrincipal;
	
	public JButton button1 = new JButton();
	public JButton button2 = new JButton();
	public JButton button3 = new JButton();
	public JButton button4 = new JButton();
	public JButton button5 = new JButton();
	public JButton button6 = new JButton();
	public JButton button7 = new JButton();
	public JButton button8 = new JButton();
	public JButton button9 = new JButton();
	public JButton button10 = new JButton();
	public JButton button11 = new JButton();
	
	private Controller controlador;
	
	public CicloTiempo(Controller pControlador) {
		this.ListaImagenRobot = new ArrayList<JLabel>();
		this.ListaImagenDano = new ArrayList<JLabel>();
		this.ListaImagenTrampas = new ArrayList<JLabel>();
		this.controlador = pControlador;
		
	}
	
	public void CrearInterfaz(ArrayList<IRobot> ListaRobots) {
		JFrame frame = new JFrame("Caso 4 POO");  
		panelPrincipal = new JPanel();
	    //JPanel panel1 = new JPanel();
		panelPrincipal.setSize(1300, 800);

	    frame.addKeyListener(controlador);
	    
		Interfaz(frame, panelPrincipal);
		//mostrarTrampas(panelPrincipal);
		Background(panelPrincipal);
		
	    for(int i = 1; i<ListaRobots.size(); i++) {
	    	MyRobot RobotAct = (MyRobot) ListaRobots.get(i);
	    	mostrarImagen(RobotAct);	
	    }
	    MyRobot RobotAct = (MyRobot) ListaRobots.get(0);
    	mostrarImagen(RobotAct);
    	MostrarInfoRobot(RobotAct);
	}
	
	
	public void RemoverRobots(ArrayList<IRobot> ListaRobots) {
		for(int i = 0; i<ListaRobots.size(); i++) {
			MyRobot RobotAct = (MyRobot) ListaRobots.get(i);
			panelPrincipal.remove(RobotAct.imagenRobot);
			
	    }
	}
	public void RepaintRobots() {
		panelPrincipal.revalidate();
		panelPrincipal.repaint();
	}
	
	
	public void MostrarInfoRobot(MyRobot RobotAct) {
		
		this.button1.setText("Energy: " + RobotAct.getEnergy()); 
        Dimension size1 = this.button1.getPreferredSize();
        this.button1.setBounds(10, 5, size1.width, size1.height);
        panelPrincipal.add(this.button1, 0);
        
        this.button2.setText("Right dir: " + RobotAct.dirDerechaHealth); 
        Dimension size2 = this.button2.getPreferredSize();
        this.button2.setBounds(155, 5, size2.width, size2.height);
        panelPrincipal.add(this.button2, 0);
        
        this.button3.setText("Left dir: " + RobotAct.dirIzquierdaHealth); 
        Dimension size3 = this.button3.getPreferredSize();
        this.button3.setBounds(305, 5, size3.width, size3.height);
        panelPrincipal.add(this.button3, 0);
        
        this.button4.setText("Up dir: " + RobotAct.dirArribaHealth); 
        Dimension size4 = this.button4.getPreferredSize();
        this.button4.setBounds(455, 5, size4.width, size4.height);
        panelPrincipal.add(this.button4, 0);
        
        this.button5.setText("Down dir: " + RobotAct.dirAbajoHealth); 
        Dimension size5 = this.button5.getPreferredSize();
        this.button5.setBounds(605, 5, size5.width, size5.height);
        panelPrincipal.add(this.button5, 0);
        
        this.button6.setText("Strike1: " + RobotAct.golpe1Health); 
        Dimension size6 = this.button6.getPreferredSize();
        this.button6.setBounds(755, 5, size6.width, size6.height);
        panelPrincipal.add(this.button6, 0);
        
        this.button7.setText("Strike2: " + RobotAct.golpe2Health); 
        Dimension size7 = this.button7.getPreferredSize();
        this.button7.setBounds(905, 5, size7.width, size7.height);
        panelPrincipal.add(this.button7, 0);
        
        this.button8.setText("Weapon: " + RobotAct.disparoHealth); 
        Dimension size8 = this.button8.getPreferredSize();
        this.button8.setBounds(1055, 5, size8.width, size8.height);
        panelPrincipal.add(this.button8, 0);	
        
        this.button9.setText("Orientation: " + RobotAct.getOrientacion()); 
        Dimension size9 = this.button9.getPreferredSize();
        this.button9.setBounds(10, 50, size9.width, size9.height);
        panelPrincipal.add(this.button9, 0);
        
        this.button10.setText("Lado: " + controlador.ladoAct); 
        Dimension size10 = this.button10.getPreferredSize();
        this.button10.setBounds(150, 50, size10.width, size10.height);
        panelPrincipal.add(this.button10, 0);
        
        this.button11.setText("XR: " + RobotAct.getPosX()); 
        Dimension size11 = this.button11.getPreferredSize();
        this.button11.setBounds(310, 50, size11.width, size11.height);
        panelPrincipal.add(this.button11, 0);
	}
	
	public void RevalidateInfoRobot(MyRobot RobotAct) {
		this.button1.setText("Energy: " + RobotAct.getEnergy()); 
		this.button2.setText("Right dir: " + RobotAct.dirDerechaHealth); 
		this.button3.setText("Left dir: " + RobotAct.dirIzquierdaHealth); 
		this.button4.setText("Up dir: " + RobotAct.dirArribaHealth); 
		this.button5.setText("Down dir: " + RobotAct.dirAbajoHealth); 
		this.button6.setText("Strike1: " + RobotAct.golpe1Health); 
		this.button7.setText("Strike2: " + RobotAct.golpe2Health); 
		this.button8.setText("Weapon: " + RobotAct.disparoHealth);
		this.button9.setText("Orientation: " + RobotAct.getOrientacion());
		this.button10.setText("Lado: " + controlador.ladoAct); 
		this.button11.setText("XR: " + RobotAct.getPosX()); 
	}
	


	public void Interfaz(JFrame frame, JPanel panel1) {
		  
	    			
		//JPanel mainPanel = new JPanel();
		//BorderLayout mainLayout = new BorderLayout();
		//mainPanel.setLayout(mainLayout);	
		
		panel1.setLayout(null);
		panel1.setBackground(Color.GRAY);
		frame.getContentPane();
		frame.add(panel1);
		
		
		//FlowLayout f1 = new FlowLayout();
	    //panel1.setLayout(f1);
        
				    		
	    frame.setSize(1300, 800);  
	    frame.setLocationRelativeTo(null);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true); 
	    
	}
	

	void Background(JPanel panel) {
			
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Piso5.jpg"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(-25, -50, size.width, size.height);
	        //panel.setComponentZOrder(picLabel, 3);
		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }
	
	public void cambiarLado() {
		try {
			for(JLabel trampa : this.ListaImagenTrampas) {
				panelPrincipal.remove(trampa);
			}
			this.ListaImagenTrampas.clear();
			if(controlador.ladoAct == IConstants.LADO_IZQUIERDO) {
				BufferedImage myPicture = null;
				myPicture = ImageIO.read(new File(rutaImagenes + "Trampa1.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panelPrincipal.add(picLabel, 10);
				Dimension size = picLabel.getPreferredSize();
		        picLabel.setBounds(1000, 0, size.width, size.height);
		        this.ListaImagenTrampas.add(picLabel);
		        
		        BufferedImage myPicture3 = null;
				myPicture3 = ImageIO.read(new File(rutaImagenes + "Trampa3.png"));
				JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
				panelPrincipal.add(picLabel3, 10);
				Dimension size3 = picLabel3.getPreferredSize();
		        picLabel3.setBounds(-400, 115, size3.width, size3.height);
		        this.ListaImagenTrampas.add(picLabel3);
			}
			else {
		        BufferedImage myPicture2 = null;
				myPicture2 = ImageIO.read(new File(rutaImagenes + "Trampa2.png"));
				JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
				panelPrincipal.add(picLabel2, 10);
				Dimension size2 = picLabel2.getPreferredSize();
		        picLabel2.setBounds(350, 565, size2.width, size2.height);	
		        this.ListaImagenTrampas.add(picLabel2);
		        
		        BufferedImage myPicture4 = null;
				myPicture4 = ImageIO.read(new File(rutaImagenes + "Trampa3.png"));
				JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
				panelPrincipal.add(picLabel4, 10);
				Dimension size4 = picLabel4.getPreferredSize();
		        picLabel4.setBounds(1170, 115, size4.width, size4.height);
		        this.ListaImagenTrampas.add(picLabel4);
			}
			
		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
	}
	
	
	public void GameOver() {
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "GameOver.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			panelPrincipal.add(picLabel, 0);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(250, 0, size.width, size.height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void Winner() {
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Winner.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			panelPrincipal.add(picLabel, 0);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(200, 130, size.width, size.height);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*private void mostrarTrampas(JPanel panel) {
		
		try {
			if(controlador.ladoAct == IConstants.LADO_IZQUIERDO) {
				BufferedImage myPicture = null;
				myPicture = ImageIO.read(new File(rutaImagenes + "Trampa1.png"));
				JLabel picLabel = new JLabel(new ImageIcon(myPicture));
				panel.add(picLabel);
				Dimension size = picLabel.getPreferredSize();
		        picLabel.setBounds(1000, 0, size.width, size.height);
		        this.ListaImagenTrampas.add(picLabel);
		        
		        BufferedImage myPicture3 = null;
				myPicture3 = ImageIO.read(new File(rutaImagenes + "Trampa3.png"));
				JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
				panel.add(picLabel3, 2);
				Dimension size3 = picLabel3.getPreferredSize();
		        picLabel3.setBounds(-400, 115, size3.width, size3.height);
		        this.ListaImagenTrampas.add(picLabel3);
			}
			else {
		        BufferedImage myPicture2 = null;
				myPicture2 = ImageIO.read(new File(rutaImagenes + "Trampa2.png"));
				JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
				panel.add(picLabel2);
				Dimension size2 = picLabel2.getPreferredSize();
		        picLabel2.setBounds(350, 565, size2.width, size2.height);	
		        this.ListaImagenTrampas.add(picLabel2);
		        
		        BufferedImage myPicture4 = null;
				myPicture4 = ImageIO.read(new File(rutaImagenes + "Trampa3.png"));
				JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
				panel.add(picLabel4, 2);
				Dimension size4 = picLabel4.getPreferredSize();
		        picLabel4.setBounds(1170, 115, size4.width, size4.height);
		        this.ListaImagenTrampas.add(picLabel4);
			}
			
		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }*/
	
	
	public void mostrarImagen(MyRobot RobotAct) {	
		MyWeapon weaponAct = ((MyWeapon)RobotAct.getWeapon(0));
		MyWeapon strike1Act = ((MyWeapon)RobotAct.getStrike(0));
		MyWeapon strike2Act = ((MyWeapon)RobotAct.getStrike(1));
		if(!RobotAct.agregado) {
			panelPrincipal.add(RobotAct.imagenRobot, 0);
			panelPrincipal.add(weaponAct.ImagenWeapon, 0);
			panelPrincipal.add(strike1Act.ImagenWeapon, 0);
			panelPrincipal.add(strike2Act.ImagenWeapon, 0);
			RobotAct.agregado = true;
			
		}
		panelPrincipal.remove(RobotAct.imagenRobot);
		panelPrincipal.remove(weaponAct.ImagenWeapon);
		panelPrincipal.remove(strike1Act.ImagenWeapon);
		panelPrincipal.remove(strike2Act.ImagenWeapon);
		if(RobotAct.getSide() == controlador.ladoAct) {
			panelPrincipal.add(RobotAct.imagenRobot, 0);
			panelPrincipal.add(weaponAct.ImagenWeapon, 0);
			panelPrincipal.add(strike1Act.ImagenWeapon, 0);
			panelPrincipal.add(strike2Act.ImagenWeapon, 0);
				Dimension size = RobotAct.imagenRobot.getPreferredSize();
				RobotAct.imagenRobot.setBounds(RobotAct.getPosXReal(), RobotAct.getPosY(), size.width, size.height);   
				panelPrincipal.remove(RobotAct.imagenDano);
				
				Dimension size2 = weaponAct.ImagenWeapon.getPreferredSize();
				weaponAct.ImagenWeapon.setBounds(RobotAct.getPosXReal() + weaponAct.getPosX(), RobotAct.getPosY() + weaponAct.getPosY(), size2.width, size2.height);   
				Dimension size3 = strike1Act.ImagenWeapon.getPreferredSize();
				strike1Act.ImagenWeapon.setBounds(RobotAct.getPosXReal() + strike1Act.getPosX(), RobotAct.getPosY() + strike1Act.getPosY(), size3.width, size3.height);  
				Dimension size4 = strike2Act.ImagenWeapon.getPreferredSize();
				strike2Act.ImagenWeapon.setBounds(RobotAct.getPosXReal() + strike2Act.getPosX(), RobotAct.getPosY() + strike2Act.getPosY(), size4.width, size4.height);  
				removeDisparos(RobotAct);
				mostrarDisparos(RobotAct);
			
			if(RobotAct.getSide() == IConstants.LADO_IZQUIERDO) {
				if((RobotAct.getPosXReal()>=950)&&(RobotAct.getPosXReal()<=1170)&&(RobotAct.getPosY()<=90)&&(RobotAct.getPosY()>-10)) {
					mostrarDano(RobotAct);
				}
				else if((RobotAct.getPosY()>=100)&&(RobotAct.getPosY()<=500)&&(RobotAct.getPosXReal()<=50)) {
					mostrarDano(RobotAct);
				}
				else if(RobotAct.IsGolpeado > 0) {
					mostrarDano(RobotAct);
				}
			}
			else {
				if((RobotAct.getPosY()>=585)&&(RobotAct.getPosXReal()>=350)&&(RobotAct.getPosXReal()<=1000)) {
					mostrarDano(RobotAct);
				}
				else if((RobotAct.getPosY()>=100)&&(RobotAct.getPosY()<=500)&&(RobotAct.getPosXReal()>=1100)) {
					mostrarDano(RobotAct);
				}
				else if(RobotAct.IsGolpeado > 0) {
					mostrarDano(RobotAct);
				}
			}	
		}
    }
	
	
	public void mostrarDano(MyRobot RobotAct) {	
			panelPrincipal.add(RobotAct.imagenDano, 0);
			Dimension size = RobotAct.imagenDano.getPreferredSize();
			RobotAct.imagenDano.setBounds(RobotAct.getPosXReal(), RobotAct.getPosY(), size.width, size.height);    
    }
	
	
	private void mostrarDisparos(MyRobot RobotAct) {
		MyWeapon arma = null;
		for(int i = 0; i<RobotAct.getWeaponsSize(); i++) {
			arma = (MyWeapon) RobotAct.getWeapon(i);
			for(int j = 0; j<arma.disparos.size(); j++) {
				Bala disparoAct = arma.disparos.get(j);
				if(disparoAct.existeInterfaz == false) {
					disparoAct.existeInterfaz = true;
					panelPrincipal.add(disparoAct.imagenBala, 0);
				}
				else {
					Dimension size = disparoAct.imagenBala.getPreferredSize();
					disparoAct.imagenBala.setBounds(disparoAct.posX, disparoAct.posY, size.width, size.height);   
				}
			}
		}
	}
	
	public void removeDisparos(MyRobot RobotAct) {
		MyWeapon arma = null;
		for(int i = 0; i<RobotAct.getWeaponsSize(); i++) {
			arma = (MyWeapon) RobotAct.getWeapon(i);
			for(int j = arma.disparos.size()-1; j>=0; j--) {
				Bala disparoAct = arma.disparos.get(j);
				if(disparoAct.distancia >= arma.getAttackDistance()) {
					panelPrincipal.remove(disparoAct.imagenBala);
				}
				else if(disparoAct.posX <= -50) {
					panelPrincipal.remove(disparoAct.imagenBala);			
				}
				else if(disparoAct.posX >= 1200) {
					panelPrincipal.remove(disparoAct.imagenBala);
				}
				else if(disparoAct.posY <= -50) {
					panelPrincipal.remove(disparoAct.imagenBala);
				}
				else if(disparoAct.posY >= 700) {
					panelPrincipal.remove(disparoAct.imagenBala);
				}
			}
			
			arma.removeDisparos();
		}		
	}
	
	
}

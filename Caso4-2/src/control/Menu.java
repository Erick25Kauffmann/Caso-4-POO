package control;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Menu {
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\eclipse-workspace\\Caso4-2\\src\\Imagen\\";
	public int dir = 0;
	public int num1 = 0;
	public int num2 = 0;
	public int num3 = 0;
	public int RobotNum = 0;
	private Controller mControlador;
	
	public Menu(Controller controlador) {
		this.mControlador = controlador;
	}
	
	public void MostrarMenu() {
		boolean temp = true;
		boolean temp2 = true;
		
		JFrame frame = new JFrame("Menu");  
		//frame.getContentPane();
	    JPanel panel1 = new JPanel();
	    panel1.setSize(1300, 800);
	    JButton button1 = new JButton();
	    JButton button2 = new JButton();
	    JButton button3 = new JButton();
	    JButton button4 = new JButton();
	    JButton button5 = new JButton();
	    JButton button6 = new JButton();
	    
	    JButton button7 = new JButton();
	    JButton button8 = new JButton();
	    JButton button9 = new JButton();
	    JButton button10 = new JButton();
	    JButton button11 = new JButton();
	    JButton button12 = new JButton();
	    JButton button13 = new JButton();
	    JButton button14 = new JButton();
	    
	    String nombreRobot;
	    
	    Interfaz(frame, panel1);
		mostrarButtons(panel1, button1, button2, button3, button4, button5, button6);
		mostrarRobots(panel1);
		Background(panel1);
		
		while(temp == true) {
			int opcion = chooseRobot(panel1, button1, button2, button3, button4, button5, button6, dir);
			if(opcion == 1) {
				setRobotNum(1);
				nombreRobot = "Bicho4.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
			else if(opcion == 2) {
				setRobotNum(2);
				nombreRobot = "Bicho5.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
			else if(opcion == 3) {
				setRobotNum(3);
				nombreRobot = "Bicho6.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
			else if(opcion == 4) {
				setRobotNum(4);
				nombreRobot = "Bicho8.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
			else if(opcion == 5) {
				setRobotNum(5);
				nombreRobot = "Bicho9.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
			else if(opcion == 6) {
				setRobotNum(6);
				nombreRobot = "Bicho10.png";
				panel1.removeAll();
				Background(panel1);
				mostrarRobot(panel1, nombreRobot);
				break;
			}
		}
		mostrarArmas1(panel1);
		mostrarWeaponsButtons(panel1, button7, button8, button9, button10, button11, button12, button13, button14);
		
		while(temp2 == true) {
			chooseWeapon(panel1, button7, button8, button9, button10, button11, button12, button13, button14);
			if((num1!=0)&&(num2!=0)&&(num3!=0)) {
				break;
			}
		}
		System.out.println("Robot: " + RobotNum);
		System.out.println("Arma1: " + num1);
		System.out.println("Arma2: " + num2);
		System.out.println("Arma3: " + num3);
		
		System.out.println("Menu lado: " + mControlador.ladoInicial);
		mControlador.addRobotPos(0, RobotNum, num1, num2, num3, mControlador.ladoInicial);
		//mControlador.addRobot(2, 1, 3, 4);
		//mControlador.addRobot(6, 1, 3, 4);
		//mControlador.addRobot(5, 1, 3, 4);
		
	}
	
	
	
	public void Interfaz(JFrame frame, JPanel panel1) {

		panel1.setLayout(null);
		panel1.setBackground(Color.GRAY);
		frame.getContentPane();
		frame.add(panel1);
		
	    frame.setSize(1300, 800);  
	    frame.setLocationRelativeTo(null);  
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
	    frame.setVisible(true); 
	    
	}
	
	
	void mostrarRobots(JPanel panel) {
		
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Bicho4.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(150, 200, size.width, size.height);
	        
	        BufferedImage myPicture2 = null;
			myPicture2 = ImageIO.read(new File(rutaImagenes + "Bicho5.png"));
			JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel2);
			Dimension size2 = picLabel2.getPreferredSize();
	        picLabel2.setBounds(300, 200, size2.width, size2.height);
	        
	        BufferedImage myPicture3 = null;
			myPicture3 = ImageIO.read(new File(rutaImagenes + "Bicho6.png"));
			JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel3);
			Dimension size3 = picLabel3.getPreferredSize();
	        picLabel3.setBounds(400, 200, size3.width, size3.height);
	        	        
	        BufferedImage myPicture4 = null;
			myPicture4 = ImageIO.read(new File(rutaImagenes + "Bicho8.png"));
			JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel4);
			Dimension size4 = picLabel4.getPreferredSize();
	        picLabel4.setBounds(650, 200, size4.width, size4.height);
	        
	        BufferedImage myPicture5 = null;
			myPicture5 = ImageIO.read(new File(rutaImagenes + "Bicho9.png"));
			JLabel picLabel5 = new JLabel(new ImageIcon(myPicture5));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel5);
			Dimension size5 = picLabel5.getPreferredSize();
	        picLabel5.setBounds(800, 200, size5.width, size5.height);
	        
	        BufferedImage myPicture6 = null;
			myPicture6 = ImageIO.read(new File(rutaImagenes + "Bicho10.png"));
			JLabel picLabel6 = new JLabel(new ImageIcon(myPicture6));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel6);
			Dimension size6 = picLabel6.getPreferredSize();
	        picLabel6.setBounds(1000, 200, size6.width, size6.height);
		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }
	
	
	void mostrarArmas1(JPanel panel) {
		
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Disparo1.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel, 0);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(340, 350, size.width, size.height);
	        
	        BufferedImage myPicture2 = null;
			myPicture2 = ImageIO.read(new File(rutaImagenes + "Disparo2.png"));
			JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel2, 0);
			Dimension size2 = picLabel2.getPreferredSize();
	        picLabel2.setBounds(465, 350, size2.width, size2.height);
	        
	        BufferedImage myPicture3 = null;
			myPicture3 = ImageIO.read(new File(rutaImagenes + "Golpe1.png"));
			JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel3, 0);
			Dimension size3 = picLabel3.getPreferredSize();
	        picLabel3.setBounds(630, 350, size3.width, size3.height);
	        	        
	        BufferedImage myPicture4 = null;
			myPicture4 = ImageIO.read(new File(rutaImagenes + "Golpe2.png"));
			JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel4, 0);
			Dimension size4 = picLabel4.getPreferredSize();
	        picLabel4.setBounds(800, 350, size4.width, size4.height);
	        
	        BufferedImage myPicture5 = null;
			myPicture5 = ImageIO.read(new File(rutaImagenes + "Golpe3.png"));
			JLabel picLabel5 = new JLabel(new ImageIcon(myPicture5));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel5, 0);
			Dimension size5 = picLabel5.getPreferredSize();
	        picLabel5.setBounds(950, 350, size5.width, size5.height);

		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }
	
	/*void mostrarArmas2(JPanel panel) {
		
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Disparo3.png"));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(150, 550, size.width, size.height);
	        
	        BufferedImage myPicture2 = null;
			myPicture2 = ImageIO.read(new File(rutaImagenes + "Disparo4.png"));
			JLabel picLabel2 = new JLabel(new ImageIcon(myPicture2));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel2);
			Dimension size2 = picLabel2.getPreferredSize();
	        picLabel2.setBounds(300, 550, size2.width, size2.height);
	        
	        BufferedImage myPicture3 = null;
			myPicture3 = ImageIO.read(new File(rutaImagenes + "Golpe4.png"));
			JLabel picLabel3 = new JLabel(new ImageIcon(myPicture3));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel3);
			Dimension size3 = picLabel3.getPreferredSize();
	        picLabel3.setBounds(400, 550, size3.width, size3.height);
	        	        
	        BufferedImage myPicture4 = null;
			myPicture4 = ImageIO.read(new File(rutaImagenes + "Golpe5.png"));
			JLabel picLabel4 = new JLabel(new ImageIcon(myPicture4));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel4);
			Dimension size4 = picLabel4.getPreferredSize();
	        picLabel4.setBounds(600, 550, size4.width, size4.height);
	        
	        BufferedImage myPicture5 = null;
			myPicture5 = ImageIO.read(new File(rutaImagenes + "Golpe6.png"));
			JLabel picLabel5 = new JLabel(new ImageIcon(myPicture5));
			//picLabel.setLocation(0, 600);
			panel.add(picLabel5);
			Dimension size5 = picLabel5.getPreferredSize();
	        picLabel5.setBounds(800, 550, size5.width, size5.height);

		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }*/
	
	
	
	void mostrarButtons(JPanel panel1, JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6) {
		button1.setText("choose"); 
        Dimension size2 = button1.getPreferredSize();
		button1.setBounds(150, 350, size2.width, size2.height);
        panel1.add(button1);
        
        button2.setText("choose"); 
        Dimension size3 = button2.getPreferredSize();
		button2.setBounds(305, 350, size3.width, size3.height);
        panel1.add(button2);
        
        button3.setText("choose"); 
        Dimension size4 = button3.getPreferredSize();
		button3.setBounds(475, 350, size4.width, size4.height);
        panel1.add(button3);
        
        button4.setText("choose"); 
        Dimension size5 = button4.getPreferredSize();
		button4.setBounds(660, 350, size5.width, size5.height);
        panel1.add(button4);
        
        button5.setText("choose"); 
        Dimension size6 = button5.getPreferredSize();
		button5.setBounds(850, 350, size6.width, size6.height);
        panel1.add(button5);
        
        button6.setText("choose"); 
        Dimension size7 = button6.getPreferredSize();
		button6.setBounds(1010, 350, size7.width, size7.height);
        panel1.add(button6);
	}
	
	
	void Background(JPanel panel) {
		
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + "Piso4.jpg"));
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
	
	
	int chooseRobot(JPanel panel1, JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6, int dir) {
				
        button1.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(1);
        	}  
   	    });
        
        button2.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(2);
        	}  
   	    });
        
        button3.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(3);
        	}  
   	    });
        
        button4.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(4);
        	}  
   	    });
        
        button5.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(5);
        	}  
   	    });
        
        button6.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		setDir(6);
        	}  
   	    });
        
        return dir;
	}
	
	void setDir(int num) {
		this.dir = num;
	}
	void setNum1(int num) {
		this.num1 = num;
	}
	void setNum2(int num) {
		this.num2 = num;
	}
	void setNum3(int num) {
		this.num3 = num;
	}
	
	void setRobotNum(int num) {
		this.RobotNum = num;
	}
	
	void mostrarRobot(JPanel panel, String nombreRobot) {
		
		try {
			BufferedImage myPicture = null;
			myPicture = ImageIO.read(new File(rutaImagenes + nombreRobot));
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
			//picLabel.setLocation(100, 500);
			panel.add(picLabel, 0);
			Dimension size = picLabel.getPreferredSize();
	        picLabel.setBounds(550, 200, size.width, size.height);
	        
		} catch (IOException e) {
			System.out.println("Error cargando imagen ");
			e.printStackTrace();
		}
        
    }

	void chooseWeapon(JPanel panel1, JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6, JButton button7, JButton button8) {
		
        button1.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num1 == 0) {
        			setNum1(1);
        		}
        	}  
   	    });
        
        button2.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num1 == 0) {
        			setNum1(2);
        		}
        	}  
   	    });
        
        button3.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num2 == 0) {
        			setNum2(3);
        		}
        	}  
   	    });
        
        button4.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num2 == 0) {
        			setNum2(4);
        		}
        	}  
   	    });
        
        button5.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num2 == 0) {
        			setNum2(5);
        		}
        	}  
   	    });
        
        button6.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num3 == 0) {
        			setNum3(3);
        		}
        	}  
   	    });
        
        button7.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num3 == 0) {
        			setNum3(4);
        		}
        	}  
   	    });
        
        button8.addActionListener(new ActionListener(){  
        	public void actionPerformed(ActionEvent e){
        		if(num3 == 0) {
        			setNum3(5);
        		}
        	}  
   	    });
                
	}
	
	void mostrarWeaponsButtons(JPanel panel1, JButton button1, JButton button2, JButton button3, JButton button4, JButton button5, JButton button6, JButton button7, JButton button8) {
		button1.setText("choose for shoot"); 
        Dimension size2 = button1.getPreferredSize();
		button1.setBounds(300, 400, size2.width, size2.height);
        panel1.add(button1, 0);
        
        button2.setText("choose for shoot"); 
        Dimension size3 = button2.getPreferredSize();
		button2.setBounds(450, 400, size3.width, size3.height);
        panel1.add(button2, 0);
        
        button3.setText("choose for strike 1"); 
        Dimension size4 = button3.getPreferredSize();
		button3.setBounds(600, 400, size4.width, size4.height);
        panel1.add(button3, 0);
        
        button4.setText("choose for strike 1"); 
        Dimension size5 = button4.getPreferredSize();
		button4.setBounds(750, 400, size5.width, size5.height);
        panel1.add(button4, 0);
        
        button5.setText("choose for strike 1"); 
        Dimension size6 = button5.getPreferredSize();
		button5.setBounds(900, 400, size6.width, size6.height);
        panel1.add(button5, 0);
        
        button6.setText("choose for strike 2"); 
        Dimension size7 = button6.getPreferredSize();
		button6.setBounds(600, 440, size7.width, size7.height);
        panel1.add(button6, 0);
        
        button7.setText("choose for strike 2"); 
        Dimension size8 = button7.getPreferredSize();
		button7.setBounds(750, 440, size8.width, size8.height);
        panel1.add(button7, 0);
        
        button8.setText("choose for strike 2"); 
        Dimension size9 = button8.getPreferredSize();
		button8.setBounds(900, 440, size9.width, size9.height);
        panel1.add(button8, 0);
        
	}
	
}



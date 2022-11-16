package robot;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class MyRobot extends IRobot{
	public static String rutaImagenes = "C:\\Users\\Erick Kauffmann\\eclipse-workspace\\Caso4-2\\src\\Imagen\\";
	public int RobotType;
	public String RobotImage;
	public boolean isAlive = true;
	public boolean dirArriba = false;
	public boolean dirAbajo = false;
	public boolean dirIzquierda = false;
	public boolean dirDerecha = false;
	public boolean disparar = false;
	public boolean dirUpEnable = true;
	public boolean dirDownEnable = true;
	public boolean dirRightEnable = true;
	public boolean dirLeftEnable = true;
	public boolean golpe1Enable = true;
	public boolean golpe2Enable = true;
	public boolean disparoEnable = true;
	public float dirArribaHealth = 100;
	public float dirAbajoHealth = 100;
	public float dirIzquierdaHealth = 100;
	public float dirDerechaHealth = 100;
	public float golpe1Health = 100;
	public float golpe2Health = 100;
	public float disparoHealth = 100;
	public JLabel imagenRobot;
	public JLabel imagenDano;
	public int AnchoRobot;
	public int AltoRobot;
	public int IsGolpeado = 0;
	public int contDistanciaRecorrida = 0;
	public int contBalasDisparadas = 0;
	public boolean agregado = false;


	
	
	public MyRobot(int NomRobot, int weapon, int strike1, int strike2, int ladoInicial) {
		super();
		if(ladoInicial == IConstants.LADO_IZQUIERDO) {
			System.out.println("lado en creacion de robot: " + ladoInicial);
			this.posX = IConstants.INITIALPOSXI;
			this.posY = IConstants.INITIALPOSYI;
		}
		else {
			System.out.println("lado en creacion de robot: " + ladoInicial);
			this.posX = IConstants.INITIALPOSXD;
			this.posY = IConstants.INITIALPOSYD;
		}
		this.energy = 100;
		this.RobotType = NomRobot;
		if(RobotType == 1) {
			RobotImage = "Bicho4.png";
			AnchoRobot = 125;
			AltoRobot = 125;
		}
		else if(RobotType == 2) {
			RobotImage = "Bicho5.png";
			AnchoRobot = 165;
			AltoRobot = 125;
		}
		else if(RobotType == 3) {
			RobotImage = "Bicho6.png";
			AnchoRobot = 222;
			AltoRobot = 125;
		}
		else if(RobotType == 4) {
			RobotImage = "Bicho8.png";
			AnchoRobot = 105;
			AltoRobot = 125;
		}
		else if(RobotType == 5) {
			RobotImage = "Bicho9.png";
			AnchoRobot = 219;
			AltoRobot = 125;
		}
		else if(RobotType == 6) {
			RobotImage = "Bicho10.png";
			AnchoRobot = 89;
			AltoRobot = 125;
		}
		Weapon golpe1 = null;
		Weapon golpe2 = null;
		Weapon disparo = null;
		if(strike1 == 3) {
			golpe1 = new MyWeapon(3, 3, 6, 20);
			this.addStrike(golpe1);
		}
		else if(strike1 == 4) {
			golpe1 = new MyWeapon(4, 4, 3, 20);
			this.addStrike(golpe1);
		}
		else if(strike1 == 5) {
			golpe1 = new MyWeapon(5, 5, 1, 20);
			this.addStrike(golpe1);
		}
		golpe1.setPosX(0);
		golpe1.setPosY(0);
		if(strike2 == 3) {
			golpe2 = new MyWeapon(3, 3, 6, 20);
			this.addStrike(golpe2);
		}
		else if(strike2 == 4) {
			golpe2 = new MyWeapon(4, 4, 3, 20);
			this.addStrike(golpe2);
		}
		else if(strike2 == 5) {
			golpe2 = new MyWeapon(5, 5, 1, 20);
			this.addStrike(golpe2);
		}
		golpe2.setPosY(60);
		if(RobotType == 1) {
			golpe2.setPosX(60);			
		}
		else if(RobotType == 2) {
			golpe2.setPosX(80);			
		}
		else if(RobotType == 3) {
			golpe2.setPosX(111);	
		}
		else if(RobotType == 4) {
			golpe2.setPosX(60);	
		}
		else if(RobotType == 5) {
			golpe2.setPosX(110);	
		}
		else if(RobotType == 6) {
			golpe2.setPosX(45);	
		}
		if(weapon == 1) {
			disparo = new MyWeapon(1, 10, 8, 300);
			this.addWeapon(disparo);
		}
		else if(weapon == 2) {
			disparo = new MyWeapon(2, 15, 10, 600);
			this.addWeapon(disparo);
		}	
		disparo.setPosY(110);
		if(RobotType == 1) {
			disparo.setPosX(60);			
		}
		else if(RobotType == 2) {
			disparo.setPosX(80);			
		}
		else if(RobotType == 3) {
			disparo.setPosX(111);	
		}
		else if(RobotType == 4) {
			disparo.setPosX(60);	
		}
		else if(RobotType == 5) {
			disparo.setPosX(110);	
		}
		else if(RobotType == 6) {
			disparo.setPosX(45);	
		}
		
		BufferedImage myPicture = null;
	    BufferedImage myPicture2 = null;
		try {
			
			myPicture = ImageIO.read(new File(rutaImagenes + this.RobotImage));
			myPicture2 = ImageIO.read(new File(rutaImagenes + "Dano1.png"));
			
			imagenRobot = new JLabel(new ImageIcon(myPicture));
			imagenDano = new JLabel(new ImageIcon(myPicture2));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	public MyWeapon RobotCercaGolpe(MyRobot RobotNear) {
		MyWeapon golpeAct = null;
		int distanciaCerca;
		if(currentOrientation == ORIENTATION.EAST) {
			if (
				 (
					((RobotNear.getPosY() + RobotNear.AltoRobot) >= this.getPosY()) &&
					((RobotNear.getPosY() + RobotNear.AltoRobot) <= (this.AltoRobot + this.getPosY()))
				 ) ||
				 (
					(RobotNear.getPosY() >= this.getPosY()) &&
					(RobotNear.getPosY() <= (this.AltoRobot + this.getPosY()))
				 )
			   ) 
			{
				
				distanciaCerca = (RobotNear.getPosX() - (this.getPosX()));
				if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(0).attackDistance+this.AnchoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(0);
				}
				else if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(1).attackDistance+this.AnchoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(1);
				}
				
			}
			
			
		}
		else if(currentOrientation == ORIENTATION.WEST) {
			
			if (
					 (
						((RobotNear.getPosY() + RobotNear.AltoRobot) >= this.getPosY()) &&
						((RobotNear.getPosY() + RobotNear.AltoRobot) <= (this.AltoRobot + this.getPosY()))
					 ) ||
					 (
						(RobotNear.getPosY() >= this.getPosY()) &&
						(RobotNear.getPosY() <= (this.AltoRobot + this.getPosY()))
					 )
				   ) 
			{
				
				distanciaCerca = (this.getPosX() - (RobotNear.getPosX()));
				if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(0).attackDistance+RobotNear.AnchoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(0);
				}
				else if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(1).attackDistance+RobotNear.AnchoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(1);
				}
					
			}
			
			
		}
		else if(currentOrientation == ORIENTATION.NORTH) {
			
			if (
					 (
						((RobotNear.getPosX() + RobotNear.AnchoRobot) >= this.getPosX()) &&
						((RobotNear.getPosX() + RobotNear.AnchoRobot) <= (this.AnchoRobot + this.getPosX()))
					 ) ||
					 (
						(RobotNear.getPosX() >= this.getPosX()) &&
						(RobotNear.getPosX() <= (this.AnchoRobot + this.getPosX()))
					 )
				   ) 
			{
				
				distanciaCerca = (this.getPosY() - (RobotNear.getPosY()));
				if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(0).attackDistance+RobotNear.AltoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(0);
				}
				else if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(1).attackDistance+RobotNear.AltoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(1);
				}
				
			}
			
			
		}
		else if(currentOrientation == ORIENTATION.SOUTH) {
			
			if (
					 (
						((RobotNear.getPosX() + RobotNear.AnchoRobot) >= this.getPosX()) &&
						((RobotNear.getPosX() + RobotNear.AnchoRobot) <= (this.AnchoRobot + this.getPosX()))
					 ) ||
					 (
						(RobotNear.getPosX() >= this.getPosX()) &&
						(RobotNear.getPosX() <= (this.AnchoRobot + this.getPosX()))
					 )
				   ) 
			{
				
				distanciaCerca = (RobotNear.getPosY() - (this.getPosY()));
				if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(0).attackDistance+this.AltoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(0);
				}
				else if((distanciaCerca >= 0) && (distanciaCerca < this.getStrike(1).attackDistance+this.AltoRobot)) {
					golpeAct = (MyWeapon) this.getStrike(1);
				}
				
			}
			
			
		}
		
		return golpeAct;
		
	}
	
	
	
	
	
	public MyWeapon RobotCercaDisparo(MyRobot RobotNear) {
		MyWeapon golpeAct = null;
		for(int i = 0; i<this.weapons.length; i++) {
			MyWeapon armaAct = (MyWeapon) this.weapons[i];
			for(int j = 0; j<armaAct.disparos.size(); j++) {
				Bala disparoAct = armaAct.disparos.get(j);
				
				if (
						 (
							(disparoAct.posY+10 <= (RobotNear.getPosY() + RobotNear.AltoRobot)) &&
							(disparoAct.posY+10 >= RobotNear.getPosY())
						 ) &&
						 (
							(disparoAct.posX+75 >=  RobotNear.getPosXReal()) &&
							(disparoAct.posX+75 <=  (RobotNear.getPosXReal() + RobotNear.AnchoRobot))
						 )
					   ) 
					{
						golpeAct = (MyWeapon) this.getWeapon(0);						
					}
			}
		}	
		return golpeAct;	
	}
	
	
	
	
	
	public void RecibeGolpe(Weapon golpe) {
		if(golpe.isEnabled()) {
			this.IsGolpeado = 5;
			//Logica rebajar vida elemento x
			int numElementoGolpeado = 0;
			float porcentajeDano = 0;
			
			if((golpe.level == 1) || (golpe.level == 2)) {
				if(this.getEnergy() > 0) {
					this.setEnergy(this.getEnergy() - golpe.level);
					if(this.getEnergy() == 0) {
						this.isAlive = false;
					}
				}			
			}
			
			else if((golpe.level >= 3) && (golpe.level <= 5)) {
				numElementoGolpeado = (int)(Math.random()*7+1);
				if(numElementoGolpeado == 1) {
					porcentajeDano = ((20*(this.dirArribaHealth))/100);
					if(this.dirArribaHealth > 0) {
						this.dirArribaHealth -= porcentajeDano;
						if(this.dirArribaHealth == 0) {
							this.dirUpEnable = false;
						}
						if(this.dirArribaHealth < 2) {
							this.dirArribaHealth = 0;
							this.dirUpEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 2) {
					porcentajeDano = ((20*(this.dirAbajoHealth))/100);
					if(this.dirAbajoHealth > 0) {
						this.dirAbajoHealth -= porcentajeDano;
						if(this.dirAbajoHealth == 0) {
							this.dirDownEnable = false;
						}
						if(this.dirAbajoHealth < 2) {
							this.dirAbajoHealth = 0;
							this.dirDownEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 3) {
					porcentajeDano = ((20*(this.dirDerechaHealth))/100);
					if(this.dirDerechaHealth > 0) {
						this.dirDerechaHealth -= porcentajeDano;
						if(this.dirDerechaHealth == 0) {
							this.dirRightEnable = false;
						}
						if(this.dirDerechaHealth < 2) {
							this.dirDerechaHealth = 0;
							this.dirRightEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 4) {
					porcentajeDano = ((20*(this.dirIzquierdaHealth))/100);
					if(this.dirIzquierdaHealth > 0) {
						this.dirIzquierdaHealth -= porcentajeDano;
						if(this.dirIzquierdaHealth == 0) {
							this.dirLeftEnable = false;
						}
						if(this.dirIzquierdaHealth < 2) {
							this.dirIzquierdaHealth = 0;
							this.dirLeftEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 5) {
					MyWeapon golpeRandom = (MyWeapon) this.getStrike(0);			
					porcentajeDano = ((20*(golpeRandom.weaponHealth))/100);
					if(golpeRandom.weaponHealth > 0) {
						golpeRandom.weaponHealth -= porcentajeDano;
						this.golpe1Health = golpeRandom.weaponHealth;
						if(golpeRandom.weaponHealth == 0) {
							golpeRandom.weaponEnable = false;
						}
						if(golpeRandom.weaponHealth < 2) {
							golpeRandom.weaponHealth = 0;
							golpeRandom.weaponEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 6) {
					MyWeapon golpeRandom2 = (MyWeapon) this.getStrike(1);
					porcentajeDano = ((20*(golpeRandom2.weaponHealth))/100);
					if(golpeRandom2.weaponHealth > 0) {
						golpeRandom2.weaponHealth -= porcentajeDano;
						this.golpe2Health = golpeRandom2.weaponHealth;
						if(golpeRandom2.weaponHealth == 0) {
							golpeRandom2.weaponEnable = false;
						}
						if(golpeRandom2.weaponHealth < 2) {
							golpeRandom2.weaponHealth = 0;
							golpeRandom2.weaponEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 7) {
					MyWeapon disparoRandom = (MyWeapon) this.getWeapon(0);
					porcentajeDano = ((20*(disparoRandom.weaponHealth))/100);
					if(disparoRandom.weaponHealth > 0) {
						disparoRandom.weaponHealth -= porcentajeDano;
						this.disparoHealth = disparoRandom.weaponHealth;
						if(disparoRandom.weaponHealth == 0) {
							disparoRandom.weaponEnable = false;
						}
						if(disparoRandom.weaponHealth < 2) {
							disparoRandom.weaponHealth = 0;
							disparoRandom.weaponEnable = false;
						}
					}
				}	
			}
			
			else if((golpe.level >= 6) && (golpe.level <= 8)) {
				numElementoGolpeado = (int)(Math.random()*7+1);
				if(numElementoGolpeado == 1) {
					porcentajeDano = ((30*(this.dirArribaHealth))/100);
					if(this.dirArribaHealth > 0) {
						this.dirArribaHealth -= porcentajeDano;
						if(this.dirArribaHealth == 0) {
							this.dirUpEnable = false;
						}
						if(this.dirArribaHealth < 2) {
							this.dirArribaHealth = 0;
							this.dirUpEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 2) {
					porcentajeDano = ((30*(this.dirAbajoHealth))/100);
					if(this.dirAbajoHealth > 0) {
						this.dirAbajoHealth -= porcentajeDano;
						if(this.dirAbajoHealth == 0) {
							this.dirDownEnable = false;
						}
						if(this.dirAbajoHealth < 2) {
							this.dirAbajoHealth = 0;
							this.dirDownEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 3) {
					porcentajeDano = ((30*(this.dirDerechaHealth))/100);
					if(this.dirDerechaHealth > 0) {
						this.dirDerechaHealth -= porcentajeDano;
						if(this.dirDerechaHealth == 0) {
							this.dirRightEnable = false;
						}
						if(this.dirDerechaHealth < 2) {
							this.dirDerechaHealth = 0;
							this.dirRightEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 4) {
					porcentajeDano = ((30*(this.dirIzquierdaHealth))/100);
					if(this.dirIzquierdaHealth > 0) {
						this.dirIzquierdaHealth -= porcentajeDano;
						if(this.dirIzquierdaHealth == 0) {
							this.dirLeftEnable = false;
						}
						if(this.dirIzquierdaHealth < 2) {
							this.dirIzquierdaHealth = 0;
							this.dirLeftEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 5) {
					MyWeapon golpeRandom = (MyWeapon) this.getStrike(0);			
					porcentajeDano = ((30*(golpeRandom.weaponHealth))/100);
					if(golpeRandom.weaponHealth > 0) {
						golpeRandom.weaponHealth -= porcentajeDano;
						this.golpe1Health = golpeRandom.weaponHealth;
						if(golpeRandom.weaponHealth == 0) {
							golpeRandom.weaponEnable = false;
						}
						if(golpeRandom.weaponHealth < 2) {
							golpeRandom.weaponHealth = 0;
							golpeRandom.weaponEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 6) {
					MyWeapon golpeRandom2 = (MyWeapon) this.getStrike(1);
					porcentajeDano = ((30*(golpeRandom2.weaponHealth))/100);
					if(golpeRandom2.weaponHealth > 0) {
						golpeRandom2.weaponHealth -= porcentajeDano;
						this.golpe2Health = golpeRandom2.weaponHealth;
						if(golpeRandom2.weaponHealth == 0) {
							golpeRandom2.weaponEnable = false;
						}
						if(golpeRandom2.weaponHealth < 2) {
							golpeRandom2.weaponHealth = 0;
							golpeRandom2.weaponEnable = false;
						}
					}
				}
				else if(numElementoGolpeado == 7) {
					MyWeapon disparoRandom = (MyWeapon) this.getWeapon(0);
					porcentajeDano = ((30*(disparoRandom.weaponHealth))/100);
					if(disparoRandom.weaponHealth > 0) {
						disparoRandom.weaponHealth -= porcentajeDano;
						this.disparoHealth = disparoRandom.weaponHealth;
						if(disparoRandom.weaponHealth == 0) {
							disparoRandom.weaponEnable = false;
						}
						if(disparoRandom.weaponHealth < 2) {
							disparoRandom.weaponHealth = 0;
							disparoRandom.weaponEnable = false;
						}
					}
				}			
			}
			
			else if((golpe.level == 9) || (golpe.level == 10)) {
				numElementoGolpeado = (int)(Math.random()*7+1);
				if(numElementoGolpeado == 1) {
					porcentajeDano = this.dirArribaHealth;
					this.dirArribaHealth -= porcentajeDano;
					this.dirUpEnable = false;
				}
				else if(numElementoGolpeado == 2) {
					porcentajeDano = this.dirAbajoHealth;
					this.dirAbajoHealth -= porcentajeDano;
					this.dirDownEnable = false;
				}
				else if(numElementoGolpeado == 3) {
					porcentajeDano = this.dirDerechaHealth;
					this.dirDerechaHealth -= porcentajeDano;
					this.dirRightEnable = false;
				}
				else if(numElementoGolpeado == 4) {
					porcentajeDano = this.dirIzquierdaHealth;
					this.dirIzquierdaHealth -= porcentajeDano;
					this.dirLeftEnable = false;
				}
				else if(numElementoGolpeado == 5) {
					MyWeapon golpeRandom = (MyWeapon) this.getStrike(0);			
					porcentajeDano = golpeRandom.weaponHealth;
					golpeRandom.weaponHealth -= porcentajeDano;
					this.golpe1Health = golpeRandom.weaponHealth;
					golpeRandom.weaponEnable = false;
				}
				else if(numElementoGolpeado == 6) {
					MyWeapon golpeRandom2 = (MyWeapon) this.getStrike(1);			
					porcentajeDano = golpeRandom2.weaponHealth;
					golpeRandom2.weaponHealth -= porcentajeDano;
					this.golpe2Health = golpeRandom2.weaponHealth;
					golpeRandom2.weaponEnable = false;
				}
				else if(numElementoGolpeado == 7) {
					MyWeapon disparoRandom = (MyWeapon) this.getWeapon(0);			
					porcentajeDano = disparoRandom.weaponHealth;
					disparoRandom.weaponHealth -= porcentajeDano;
					this.disparoHealth = disparoRandom.weaponHealth;
					disparoRandom.weaponEnable = false;
				}			
			}
		}
		
	}
	
	
	public void moverRobotIzquierda() {
		if(this.getPosX()<=-50) {
			
		}
		else {
			//RobotPrueba.setPosX(RobotPrueba.getPosX() + 20);
			//RobotPrueba.contDistanciaRecorrida += 20;
			//RobotPrueba2.setPosY(RobotPrueba2.getPosY() + 20);
			//RobotPrueba3.setPosY(RobotPrueba3.getPosY() - 20);
			this.setPosX(this.getPosX() - 20);
			this.contDistanciaRecorrida += 20;
			System.out.println("funciona mover izquierda");
			System.out.println("Robot tipo: " + this.RobotType);
			if(this.contDistanciaRecorrida >= 1000) {
				if(this.getEnergy() > 0) {
					this.setEnergy(this.getEnergy() - 1);
					this.contDistanciaRecorrida = 0;
				}
			}
			if(this.getEnergy() == 0) {
				this.isAlive = false;
			}
			
			if((this.getPosX()>=950)&&(this.getPosX()<=1170)&&(this.getPosY()<=90)&&(this.getPosY()>-10)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=585)&&(this.getPosX()>=1550)&&(this.getPosX()<=2200)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()<=50)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()>=2270)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			
		}
	}
	
	
	public void moverRobotDerecha() {
		if(this.getPosX()>=2400/*1200*/) {
			
		}
		else {
			//RobotPrueba.setPosX(RobotPrueba.getPosX() - 20);
			//RobotPrueba.contDistanciaRecorrida += 20;
			//RobotPrueba2.setPosY(RobotPrueba2.getPosY() - 20);
			//RobotPrueba3.setPosY(RobotPrueba3.getPosY() + 20);
			this.setPosX(this.getPosX() + 20);
			this.contDistanciaRecorrida += 20;
			if(this.contDistanciaRecorrida >= 1000) {
				if(this.getEnergy() > 0) {
					this.setEnergy(this.getEnergy() - 1);
					this.contDistanciaRecorrida = 0;
				}
			}
			if(this.getEnergy() == 0) {
				this.isAlive = false;
			}							
			
			if((this.getPosX()>=950)&&(this.getPosX()<=1170)&&(this.getPosY()<=90)&&(this.getPosY()>-10)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=585)&&(this.getPosX()>=1550)&&(this.getPosX()<=2200)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()<=50)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()>=2270)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			
		}
	}
	
	
	public void moverRobotArriba() {
		if(this.getPosY()<=-50) {
			
		}
		else {
			//RobotPrueba.setPosY(RobotPrueba.getPosY() + 20);
			//RobotPrueba.contDistanciaRecorrida += 20;
			//RobotPrueba2.setPosX(RobotPrueba2.getPosX() + 20);
			//RobotPrueba3.setPosX(RobotPrueba3.getPosX() - 20);
			this.setPosY(this.getPosY() - 20);
			this.contDistanciaRecorrida += 20;
			if(this.contDistanciaRecorrida >= 1000) {
				if(this.getEnergy() > 0) {
					this.setEnergy(this.getEnergy() - 1);
					this.contDistanciaRecorrida = 0;
				}
			}
			if(this.getEnergy() == 0) {
				this.isAlive = false;
			}
			
			if((this.getPosX()>=950)&&(this.getPosX()<=1170)&&(this.getPosY()<=90)&&(this.getPosY()>-10)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=585)&&(this.getPosX()>=1550)&&(this.getPosX()<=2200)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()<=50)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()>=2270)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			
		}
	}
	
	
	public void moverRobotAbajo() {
		if(this.getPosY()>=700) {
			
		}
		else {
			//RobotPrueba.setPosY(RobotPrueba.getPosY() - 20);
			//RobotPrueba.contDistanciaRecorrida += 20;
			//RobotPrueba2.setPosX(RobotPrueba2.getPosX() - 20);
			//RobotPrueba3.setPosX(RobotPrueba3.getPosX() + 20);
			this.setPosY(this.getPosY() + 20);
			this.contDistanciaRecorrida += 20;
			if(this.contDistanciaRecorrida >= 1000) {
				if(this.getEnergy() > 0) {
					this.setEnergy(this.getEnergy() - 1);
					this.contDistanciaRecorrida = 0;
				}
			}
			if(this.getEnergy() == 0) {
				this.isAlive = false;
			}
			
			if((this.getPosX()>=950)&&(this.getPosX()<=1170)&&(this.getPosY()<=90)&&(this.getPosY()>-10)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=585)&&(this.getPosX()>=1550)&&(this.getPosX()<=2200)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()<=50)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			else if((this.getPosY()>=100)&&(this.getPosY()<=500)&&(this.getPosX()>=2270)) {
				if(this.getEnergy() >= 5) {
					this.setEnergy(this.getEnergy() - 5);
				}
				else {
					this.setEnergy(this.getEnergy() - this.getEnergy());
				}
			}
			
		}
	}
	
	
	public void disparar() {
		MyWeapon Arma = ((MyWeapon) this.getWeapon(0));
		Bala bala = new Bala(Arma.WeaponType, this.getPosXReal()+Arma.getPosX(), this.getPosY()+Arma.getPosY(), this.getOrientacion());
		Arma.disparos.add(bala);
		this.contBalasDisparadas += 1;
		if(this.contBalasDisparadas >= 50) {
			if(this.getEnergy() > 0) {
				this.setEnergy(this.getEnergy() - 1);
				this.contBalasDisparadas = 0;
			}
		}
	}
	
	
	public void revisarIsAlive() {
		if(
				(this.dirLeftEnable == false)&&
				(this.dirRightEnable == false)&&
				(this.dirUpEnable == false)&&
				(this.dirDownEnable == false)&&
				(((MyWeapon)this.getWeapon(0)).weaponEnable == false)&&
				(((MyWeapon)this.getStrike(0)).weaponEnable == false)&&
				(((MyWeapon)this.getStrike(1)).weaponEnable == false)
			) 
		{
			this.isAlive = false;
		}
	}
	
	
	
	public void RebajarIsGolpeado() {
		if(this.IsGolpeado > 0) {
			this.IsGolpeado -= 1;
		}		
	}
	
	
	public void moverDisparos() {
		for(int i = 0; i<this.weapons.length; i++) {
			((MyWeapon) this.weapons[i]).moverDisparos();			
		}
	}
	
	public int getSide() {
		int lado = 1;
		if(this.getPosX()>=1200) {
			lado = 2;
		}
		return lado;		
	}
	
	public int getPosXReal() {
		int posXReal = this.getPosX();
		if(this.getSide() == 2) {
			posXReal -= 1200;
		}
		return posXReal;
	}
	
	
	public ORIENTATION getOrientacion() {
		return this.currentOrientation;
	}
	public void setOrientacion(ORIENTATION pOrientation) {
		 this.currentOrientation = pOrientation;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	public void setEnergy(int num) {
		this.energy = num;
	}
	
	public int getPosX() {
		return this.posX;
	}
	public void setPosX(int num) {
		this.posX = num;
	}
	
	public int getPosY() {
		return this.posY;
	}
	public void setPosY(int num) {
		this.posY = num;
	}
	
	public Weapon getWeapon(int i) {
		return this.weapons[i];
	}
	public Weapon getStrike(int i) {
		return this.strikes[i];
	}
	
	public int getWeaponsSize() {
		return this.weapons.length;
	}
	
}

package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.UnknownHostException;
import java.util.ArrayList;

import Sockets.ClientComm;
import Sockets.ServerComm;
import Time.CicloTiempo;
import robot.Bala;
import robot.IConstants;
import robot.IRobot;
import robot.MyRobot;
import robot.MyWeapon;
import robot.ORIENTATION;
import robot.Weapon;

public class Controller implements KeyListener {
	public ArrayList<IRobot> ListaRobots;
	public MyRobot CurrentRobot;
	public MyRobot RobotPrueba;
	public MyRobot RobotPrueba2;
	public MyRobot RobotPrueba3;
	public CicloTiempo interfaz;
	public int ladoAct;
	public int ladoInicial;
	public ClientComm client;
	private String ip = "";
    private String puertoServer = "";
    private String puertoClient = "";
	 
	
	public Controller(String pIP, String pPuertoServer, String pPuertoClient, int pPantallaSide) {
		this.ListaRobots = new ArrayList<IRobot>();
		this.interfaz = new CicloTiempo(this);
		this.ladoAct = pPantallaSide;
		this.ladoInicial = pPantallaSide;
		this.ip = pIP;
		this.puertoServer = pPuertoServer;
		this.puertoClient = pPuertoClient;
	}
	
	public void conectarRival() {
		boolean conectado = false;
		client = new ClientComm();
		while(true) {	
			conectado = client.startConnection(this.ip, Integer.parseInt(this.puertoClient));
			if(!conectado) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			else {
				break;
			}
		}
		if(conectado) {
			RunGame();
		}
	}
	
	public void RunGame() {
		//new ServerComm(Integer.parseInt(puertoServer), this).start();
		//client = new ClientComm();
		//client.startConnection(this.ip, Integer.parseInt(this.puertoClient));
		
		
		interfaz.CrearInterfaz(ListaRobots);
		controlarRobot();
		
		
	}
	
	
	
	public void addRobot(int NumRobot, int NumWeapon, int NumStrike1, int NumStrike2, int PosX, int PosY, int ladoInicial) {
		IRobot myRobot = new MyRobot(NumRobot, NumWeapon, NumStrike1, NumStrike2, ladoInicial);
		//((MyRobot) myRobot).setPosX(PosX);
		//((MyRobot) myRobot).setPosY(PosY);
		ListaRobots.add(myRobot);
		
	}
	
	public void addRobotPos(int Pos, int NumRobot, int NumWeapon, int NumStrike1, int NumStrike2, int ladoInicial) {
		IRobot myRobot = new MyRobot(NumRobot, NumWeapon, NumStrike1, NumStrike2, ladoInicial);
		ListaRobots.add(Pos, myRobot);
		
	}
	
	public String mensajeCrearRobot() {
		String result = "";	
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT).concat("{");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_TYPE).concat(Integer.toString(CurrentRobot.RobotType)).concat(",");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_A1).concat(Integer.toString(((MyWeapon)CurrentRobot.getWeapon(0)).WeaponType)).concat(",");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_A2).concat(Integer.toString(((MyWeapon)CurrentRobot.getStrike(0)).WeaponType)).concat(",");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_A3).concat(Integer.toString(((MyWeapon)CurrentRobot.getStrike(1)).WeaponType)).concat(",");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSX).concat(Integer.toString(CurrentRobot.getPosX())).concat(",");
		result = result.concat(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSY).concat(Integer.toString(CurrentRobot.getPosY()));
		result = result.concat("}");
		return result;
	}
	
	
	public void controlarRobot() {
		CurrentRobot = (MyRobot) ListaRobots.get(0);
		if(ListaRobots.size()>1) {
			RobotPrueba = (MyRobot) ListaRobots.get(1);
		}
		
		if(client!=null) {
			System.out.println("Mensaje Enviado");
			client.sendMessage(mensajeCrearRobot());
		}
		
		//RobotPrueba2 = (MyRobot) ListaRobots.get(2);
		//RobotPrueba3 = (MyRobot) ListaRobots.get(3);
		
		boolean simulacion = true;
		
		while(simulacion == true) {
			//interfaz.RemoverRobots(this.ListaRobots);
			if(CurrentRobot != null) {
				
				if(CurrentRobot.isAlive) {
			
					if(CurrentRobot.dirIzquierda) {	
						if(client!=null) {
							client.sendMessage("izquierdo");
						}
						System.out.println("funciona izquierda");
						if(CurrentRobot.getPosX()<=-50) {
							
						}
						else {
							//RobotPrueba.setPosX(RobotPrueba.getPosX() + 20);
							//RobotPrueba.contDistanciaRecorrida += 20;
							//RobotPrueba2.setPosY(RobotPrueba2.getPosY() + 20);
							//RobotPrueba3.setPosY(RobotPrueba3.getPosY() - 20);
							CurrentRobot.setPosX(CurrentRobot.getPosX() - 20);
							CurrentRobot.contDistanciaRecorrida += 20;
							System.out.println("funciona mover izquierda");
							System.out.println("Robot tipo: " + CurrentRobot.RobotType);
							if(CurrentRobot.contDistanciaRecorrida >= 1000) {
								if(CurrentRobot.getEnergy() > 0) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 1);
									CurrentRobot.contDistanciaRecorrida = 0;
								}
							}
							if(CurrentRobot.getEnergy() == 0) {
								CurrentRobot.isAlive = false;
							}
							
							if((CurrentRobot.getPosX()>=950)&&(CurrentRobot.getPosX()<=1170)&&(CurrentRobot.getPosY()<=90)&&(CurrentRobot.getPosY()>-10)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=585)&&(CurrentRobot.getPosX()>=1550)&&(CurrentRobot.getPosX()<=2200)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()<=50)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()>=2270)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							
						}
					}
					else if(CurrentRobot.dirDerecha) {
						if(client!=null) {
							client.sendMessage("derecho");
						}
						if(CurrentRobot.getPosX()>=2400/*1200*/) {
		
						}
						else {
							//RobotPrueba.setPosX(RobotPrueba.getPosX() - 20);
							//RobotPrueba.contDistanciaRecorrida += 20;
							//RobotPrueba2.setPosY(RobotPrueba2.getPosY() - 20);
							//RobotPrueba3.setPosY(RobotPrueba3.getPosY() + 20);
							CurrentRobot.setPosX(CurrentRobot.getPosX() + 20);
							CurrentRobot.contDistanciaRecorrida += 20;
							if(CurrentRobot.contDistanciaRecorrida >= 1000) {
								if(CurrentRobot.getEnergy() > 0) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 1);
									CurrentRobot.contDistanciaRecorrida = 0;
								}
							}
							if(CurrentRobot.getEnergy() == 0) {
								CurrentRobot.isAlive = false;
							}							
							
							if((CurrentRobot.getPosX()>=950)&&(CurrentRobot.getPosX()<=1170)&&(CurrentRobot.getPosY()<=90)&&(CurrentRobot.getPosY()>-10)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=585)&&(CurrentRobot.getPosX()>=1550)&&(CurrentRobot.getPosX()<=2200)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()<=50)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()>=2270)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							
						}
					}
					else if(CurrentRobot.dirArriba) {
						if(client!=null) {
							client.sendMessage("arriba");
						}
						if(CurrentRobot.getPosY()<=-50) {
		
						}
						else {
							//RobotPrueba.setPosY(RobotPrueba.getPosY() + 20);
							//RobotPrueba.contDistanciaRecorrida += 20;
							//RobotPrueba2.setPosX(RobotPrueba2.getPosX() + 20);
							//RobotPrueba3.setPosX(RobotPrueba3.getPosX() - 20);
							CurrentRobot.setPosY(CurrentRobot.getPosY() - 20);
							CurrentRobot.contDistanciaRecorrida += 20;
							if(CurrentRobot.contDistanciaRecorrida >= 1000) {
								if(CurrentRobot.getEnergy() > 0) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 1);
									CurrentRobot.contDistanciaRecorrida = 0;
								}
							}
							if(CurrentRobot.getEnergy() == 0) {
								CurrentRobot.isAlive = false;
							}
							
							if((CurrentRobot.getPosX()>=950)&&(CurrentRobot.getPosX()<=1170)&&(CurrentRobot.getPosY()<=90)&&(CurrentRobot.getPosY()>-10)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=585)&&(CurrentRobot.getPosX()>=1550)&&(CurrentRobot.getPosX()<=2200)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()<=50)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()>=2270)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							
						}
					}
					else if(CurrentRobot.dirAbajo) {
						if(client!=null) {
							client.sendMessage("abajo");
						}
						if(CurrentRobot.getPosY()>=700) {
		
						}
						else {
							//RobotPrueba.setPosY(RobotPrueba.getPosY() - 20);
							//RobotPrueba.contDistanciaRecorrida += 20;
							//RobotPrueba2.setPosX(RobotPrueba2.getPosX() - 20);
							//RobotPrueba3.setPosX(RobotPrueba3.getPosX() + 20);
							CurrentRobot.setPosY(CurrentRobot.getPosY() + 20);
							CurrentRobot.contDistanciaRecorrida += 20;
							if(CurrentRobot.contDistanciaRecorrida >= 1000) {
								if(CurrentRobot.getEnergy() > 0) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 1);
									CurrentRobot.contDistanciaRecorrida = 0;
								}
							}
							if(CurrentRobot.getEnergy() == 0) {
								CurrentRobot.isAlive = false;
							}
							
							if((CurrentRobot.getPosX()>=950)&&(CurrentRobot.getPosX()<=1170)&&(CurrentRobot.getPosY()<=90)&&(CurrentRobot.getPosY()>-10)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=585)&&(CurrentRobot.getPosX()>=1550)&&(CurrentRobot.getPosX()<=2200)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()<=50)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							else if((CurrentRobot.getPosY()>=100)&&(CurrentRobot.getPosY()<=500)&&(CurrentRobot.getPosX()>=2270)) {
								if(CurrentRobot.getEnergy() >= 5) {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 5);
								}
								else {
									CurrentRobot.setEnergy(CurrentRobot.getEnergy() - CurrentRobot.getEnergy());
								}
							}
							
						}
					}
				}
				moverDisparos();
				if (CurrentRobot.disparar) {		
					MyWeapon Arma = ((MyWeapon) CurrentRobot.getWeapon(0));
					Bala bala = new Bala(Arma.WeaponType, CurrentRobot.getPosXReal()+Arma.getPosX(), CurrentRobot.getPosY()+Arma.getPosY(), CurrentRobot.getOrientacion());
					Arma.disparos.add(bala);
					CurrentRobot.contBalasDisparadas += 1;
					if(CurrentRobot.contBalasDisparadas >= 50) {
						if(CurrentRobot.getEnergy() > 0) {
							CurrentRobot.setEnergy(CurrentRobot.getEnergy() - 1);
							CurrentRobot.contBalasDisparadas = 0;
						}
					}
				}
				
				RevisarGolpe();
				RevisarDisparo();
				
	
				try {
					int newSide = CurrentRobot.getSide();
					if(ladoAct != newSide) {
						ladoAct = newSide;
						interfaz.cambiarLado();
					}
					for(int i = 0; i<ListaRobots.size(); i++) {
						interfaz.mostrarImagen((MyRobot) ListaRobots.get(i));
					}
					//interfaz.mostrarImagen(CurrentRobot, false);
					//interfaz.mostrarImagen(RobotPrueba, false);
					
					Thread.sleep(50);
				}
				catch (Exception ex) {
					ex.printStackTrace();
				}
				for(int i = 0; i<ListaRobots.size(); i++) {
					((MyRobot) ListaRobots.get(i)).RebajarIsGolpeado();
					//interfaz.MostrarInfoRobot(((MyRobot) ListaRobots.get(i)));
				}
				interfaz.RepaintRobots();
				interfaz.RevalidateInfoRobot(CurrentRobot);
			}
		}
		
	}
	
	
	private void moverDisparos() {
		for(int i = 0; i<ListaRobots.size(); i++) {
			((MyRobot) ListaRobots.get(i)).moverDisparos();			
		}
	}
	
	
	private void RevisarGolpe(){
		MyWeapon golpeAct = null;
		MyRobot RobotAct = null;
		MyRobot RobotNear = null;
		for(int i = 0; i<ListaRobots.size(); i++) {
			RobotAct = (MyRobot) ListaRobots.get(i);
			for(int j = 0; j<ListaRobots.size(); j++) {
				if(i!=j) {
					RobotNear = (MyRobot) ListaRobots.get(j);
					golpeAct = (MyWeapon) RobotAct.RobotCercaGolpe(RobotNear);
					if(golpeAct != null) {
						if(golpeAct.weaponEnable) {
							RobotNear.RecibeGolpe(golpeAct);
						}
					}
					
				}
			}
		}
	}
	
	private void RevisarDisparo(){
		MyWeapon golpeAct = null;
		MyRobot RobotAct = null;
		MyRobot RobotNear = null;
		for(int i = 0; i<ListaRobots.size(); i++) {
			RobotAct = (MyRobot) ListaRobots.get(i);
			for(int j = 0; j<ListaRobots.size(); j++) {
				if(i!=j) {
					RobotNear = (MyRobot) ListaRobots.get(j);
					golpeAct = (MyWeapon) RobotAct.RobotCercaDisparo(RobotNear);					
					if(golpeAct != null) {
						if(golpeAct.weaponEnable) {
							RobotNear.RecibeGolpe(golpeAct);
						}
					}
					
				}
			}
		}
	}
	
	
	public void setRobotRival() {
		if(ListaRobots.size()>1) {
			this.RobotPrueba = (MyRobot) ListaRobots.get(1);
		}
		else {
			this.RobotPrueba = null;
		}
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}



	@Override
	public void keyPressed(KeyEvent e) {
		if(CurrentRobot != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	if(CurrentRobot.isAlive) {
			    	if(CurrentRobot.dirLeftEnable) {
			    		CurrentRobot.dirIzquierda = true;
			    	}
			    	CurrentRobot.setOrientacion(ORIENTATION.WEST);
		    	}
		    }
	
		    else if (key == KeyEvent.VK_RIGHT) {
		    	if(CurrentRobot.isAlive) {
			    	if(CurrentRobot.dirRightEnable) {
			    		CurrentRobot.dirDerecha = true;
			    	}
			    	CurrentRobot.setOrientacion(ORIENTATION.EAST);
		    	}
		    }
	
		    else if (key == KeyEvent.VK_UP) {
		    	if(CurrentRobot.isAlive) {
			    	if(CurrentRobot.dirUpEnable) {
			    		CurrentRobot.dirArriba = true;
			    	}
			    	CurrentRobot.setOrientacion(ORIENTATION.NORTH);
		    	}
		    }
	
		    else if (key == KeyEvent.VK_DOWN) {
		    	if(CurrentRobot.isAlive) {
		    		if(CurrentRobot.dirDownEnable) {
			    		CurrentRobot.dirAbajo = true;
			    	}
			    	CurrentRobot.setOrientacion(ORIENTATION.SOUTH);
		    	}    	
		    }
		    
		    else if (key == KeyEvent.VK_SPACE) {
		    	if(CurrentRobot.isAlive) {
		    		if(CurrentRobot.disparoEnable) {
			    		CurrentRobot.disparar = true;
			    	}
		    	}    	
		    }
		}
	}



	@Override
	public void keyReleased(KeyEvent e) {
		if(CurrentRobot != null) {
			int key = e.getKeyCode();
		    if (key == KeyEvent.VK_LEFT) {
		    	CurrentRobot.dirIzquierda = false;
		    }
	
		    else if (key == KeyEvent.VK_RIGHT) {
		    	CurrentRobot.dirDerecha = false;
		    }
	
		    else if (key == KeyEvent.VK_UP) {
		    	CurrentRobot.dirArriba = false;
		    }
	
		    else if (key == KeyEvent.VK_DOWN) {
		    	CurrentRobot.dirAbajo = false;
		    }
		    else if (key == KeyEvent.VK_SPACE) {
			    CurrentRobot.disparar = false;   	
		    }
		}
	}
	
}

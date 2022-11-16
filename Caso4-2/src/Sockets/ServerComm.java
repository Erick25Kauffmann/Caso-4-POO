package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import control.Controller;
import robot.IConstants;
import robot.MyRobot;
import robot.ORIENTATION;

public class ServerComm extends Thread{
	private ServerSocket serverSocket;
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;
	private int port;
	private Controller controlador;
	
	public ServerComm(int pPort, Controller pControlador)
	{
		port = pPort;
		controlador = pControlador;
	}
	    public void run() {
	    
	        try {
				serverSocket = new ServerSocket(port);
				System.out.println("El ip es " + serverSocket.getInetAddress().getHostAddress());
				while (true) {
		            new ServerClientHandler(serverSocket.accept(), controlador).start();
				}
		        /*clientSocket = serverSocket.accept();
		        out = new PrintWriter(clientSocket.getOutputStream(), true);
		        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		        
		        String inputLine;
		        while ((inputLine = in.readLine()) != null) {
		        	System.out.println(inputLine);
		        	if(inputLine.equals("izquierdo")) {
		        		System.out.println("izquierda: "+controlador.CurrentRobot.getPosX());
		        		controlador.RobotPrueba.setPosX(controlador.RobotPrueba.getPosX() + 20);
						controlador.RobotPrueba.contDistanciaRecorrida += 20;
		        	}
		        	if(inputLine.equals("derecho")) {
		        		System.out.println("derecha: "+controlador.CurrentRobot.getPosX());
		        		controlador.RobotPrueba.setPosX(controlador.RobotPrueba.getPosX() - 20);
						controlador.RobotPrueba.contDistanciaRecorrida += 20;
		        	}
		        	if(inputLine.equals("arriba")) {
		        		System.out.println("arriba: "+controlador.CurrentRobot.getPosY());
		        		controlador.RobotPrueba.setPosY(controlador.RobotPrueba.getPosY() + 20);
						controlador.RobotPrueba.contDistanciaRecorrida += 20;
		        	}
		        	if(inputLine.equals("abajo")) {
		        		System.out.println("abajo: "+controlador.CurrentRobot.getPosY());
		        		controlador.RobotPrueba.setPosY(controlador.RobotPrueba.getPosY() - 20);
						controlador.RobotPrueba.contDistanciaRecorrida += 20;
		        	}
		        	
		        	
			        if (".".equals(inputLine)) {
			            out.println("good bye");
			            break;
			         }
		         out.println(inputLine);
		        }*/
	        } catch (IOException e) {
				e.printStackTrace();
			}
	    }
	    
	    /*@Override
	    public void stop() {
	        in.close();
	        out.close();
	        clientSocket.close();
	        serverSocket.close();
	    }*/
	    
	    private static class ServerClientHandler extends Thread {
	        private Socket clientSocket;
	        private PrintWriter out;
	        private BufferedReader in;
	        private Controller controlador;

	        public ServerClientHandler(Socket socket, Controller pControlador) {
	            this.clientSocket = socket;
	            controlador = pControlador;
	        }

	        public void run() {
	        	try {
			        out = new PrintWriter(clientSocket.getOutputStream(), true);
			        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			        
			        String inputLine;
			        while ((inputLine = in.readLine()) != null) {
			        	System.out.println("Recibe msg: " + inputLine);
			        	
			        	if(esMensajeCrearRobot(inputLine)) {
			        		leerInfoRobot(inputLine);
			        		System.out.println("Robot Rival Creado y Agregado");
			        	}
			        	
			        	else if(esMensajeCambiarPos(inputLine)) {
			        		leerInfoCambiarPos(inputLine);
			        		System.out.println("Posicion Robot Rival Actualizada");
			        	}
			        	
			        	if(inputLine.equals("izquierdo")) {
			        		if(controlador.CurrentRobot != null) {
			        		//System.out.println("izquierda: "+controlador.CurrentRobot.getPosX());
			        		controlador.RobotPrueba.moverRobotIzquierda();
			        		}
			        	}
			        	if(inputLine.equals("derecho")) {
			        		if(controlador.CurrentRobot != null) {
			        		//System.out.println("derecha: "+controlador.CurrentRobot.getPosX());
			        		controlador.RobotPrueba.moverRobotDerecha();
			        		}
			        	}
			        	if(inputLine.equals("arriba")) {
			        		if(controlador.CurrentRobot != null) {
			        		//System.out.println("arriba: "+controlador.CurrentRobot.getPosY());
			        		controlador.RobotPrueba.moverRobotArriba();
			        		}
			        	}
			        	if(inputLine.equals("abajo")) {
			        		if(controlador.CurrentRobot != null) {
			        		//System.out.println("abajo: "+controlador.CurrentRobot.getPosY());
			        		controlador.RobotPrueba.moverRobotAbajo();
			        		}
			        	}
			        	if(inputLine.equals("disparo")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.RobotPrueba.disparar();
			        		}
			        	}
			        	if(inputLine.equals("OrientacionWest")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.RobotPrueba.setOrientacion(ORIENTATION.WEST);
			        		}
			        	}
			        	if(inputLine.equals("OrientacionEast")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.RobotPrueba.setOrientacion(ORIENTATION.EAST);
			        		}
			        	}
			        	if(inputLine.equals("OrientacionNorth")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.RobotPrueba.setOrientacion(ORIENTATION.NORTH);
			        		}
			        	}
			        	if(inputLine.equals("OrientacionSouth")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.RobotPrueba.setOrientacion(ORIENTATION.SOUTH);
			        		}
			        	}
			        	if(inputLine.equals("YouWin")) {
			        		if(controlador.CurrentRobot != null) {
			        			controlador.interfaz.Winner();
			        			controlador.interfaz.RepaintRobots();
			        		}
			        	}
			        	
			        	
				        if (".".equals(inputLine)) {
				            out.println("good bye");
				            break;
				         }
			         out.println(inputLine);
			        }
		        } catch (IOException e) {
					e.printStackTrace();
				}
	        }
	        
	        private boolean esMensajeCrearRobot(String inputLine) {
	        	System.out.println("LLegó a esMensajeCrearRobot");
	        	boolean result = false;
	        	if(inputLine.startsWith(IConstants.COMANDO_CREAR_ROBOT)) {
	        		result = true;	        		
	        	}
	        	return result;
	        }
	        
	        private void leerInfoRobot(String inputLine) {
	        	System.out.println("LLegó a leerInfoRobot");
	        	String tempLine = inputLine.replace(IConstants.COMANDO_CREAR_ROBOT, "").replace("{", "").replace("}", "");
	        	System.out.println("Pasó tempLine");
	        	String []params = tempLine.split(",");
	        	params[0] = params[0].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_TYPE, "");
	        	params[1] = params[1].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_A1, "");
	        	params[2] = params[2].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_A2, "");
	        	params[3] = params[3].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_A3, "");
	        	params[4] = params[4].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSX, "");
	        	params[5] = params[5].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSY, "");
	        	controlador.addRobot(Integer.parseInt(params[0]), Integer.parseInt(params[1]), Integer.parseInt(params[2]), Integer.parseInt(params[3]), 
	        						 Integer.parseInt(params[4]), Integer.parseInt(params[5]), controlador.ladoInicial);
	        	controlador.setRobotRival();
	        	controlador.reenviarPosRobot();
	        }
	        
	        private boolean esMensajeCambiarPos(String inputLine) {
	        	System.out.println("LLegó a esMensajeCambiarPos");
	        	boolean result = false;
	        	if(inputLine.startsWith(IConstants.COMANDO_CAMBIAR_POS)) {
	        		result = true;	        		
	        	}
	        	return result;
	        }
	        
	        private void leerInfoCambiarPos(String inputLine) {
	        	System.out.println("LLegó a leerInfoRobot");
	        	String tempLine = inputLine.replace(IConstants.COMANDO_CAMBIAR_POS, "").replace("{", "").replace("}", "");
	        	System.out.println("Pasó tempLine");
	        	String []params = tempLine.split(",");
	        	params[0] = params[0].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSX, "");
	        	params[1] = params[1].replace(IConstants.COMANDO_CREAR_ROBOT_PARAM_POSY, "");
	        	if(controlador.RobotPrueba != null) {
		        	controlador.RobotPrueba.setPosX(Integer.parseInt(params[0]));
		        	controlador.RobotPrueba.setPosY(Integer.parseInt(params[1]));
	        	}
	        }
	        
	        
	    }
	    
	    
	
}

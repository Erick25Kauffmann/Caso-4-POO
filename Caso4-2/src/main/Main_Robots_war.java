package main;

import Sockets.ServerComm;
import control.Controller;
import control.Menu;
import robot.IConstants;

public class Main_Robots_war {
	public static void main(String[] args) {	
		String ip = "";
        String puertoServer = "";
        String puertoClient = "";
        int pantallaSide = IConstants.LADO_IZQUIERDO;
		if(args.length > 0) {
			ip = args[0];
		}
		if(args.length > 1) {
			puertoServer = args[1];
		}
		if(args.length > 2) {
			puertoClient = args[2];
		}
		if(args.length > 3) {
			if(args[3].compareTo("D") == 0) {
				System.out.println("Parametro lado: derecho");
				pantallaSide = IConstants.LADO_DERECHO;
			}
			else {
				System.out.println("Parametro lado: izqierdo");
			}
		}
        
        //System.out.println("ip: " + ip + " ---" + " puerto: " + puertoServer);
        // Display the arguments from the command line
        for(int counter = 0; counter < args.length; counter++){
            System.out.println("argument index " + counter + ": " + args[counter]);  
        }
		
		Controller controlador = new Controller(ip, puertoServer, puertoClient, pantallaSide);
		new ServerComm(Integer.parseInt(puertoServer), controlador).start();
		
		Menu menu = new Menu(controlador);
		menu.MostrarMenu();
		
		//controlador.RunGame();
		controlador.conectarRival();
		
	}
	
}

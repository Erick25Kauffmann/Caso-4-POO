package Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientComm {
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;

    public boolean startConnection(String ip, int port) {
    	boolean result = false;
		System.out.println("start Connection");
        try {
        	
			clientSocket = new Socket();
			clientSocket.connect(new InetSocketAddress(ip, port), 0);
			System.out.println("creando socket");
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			System.out.println("Socket conectado");
			result = true;
        } catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return result;
    }

    public String sendMessage(String msg) {
    	System.out.println("enviando msg: " + msg);
        out.println(msg);
        String resp = null;
		try {
			resp = in.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
        return resp;
    }

    public void stopConnection() {
        try {
			in.close();
			out.close();
			clientSocket.close();
        } catch (IOException e) {
			e.printStackTrace();
		}
    }
}

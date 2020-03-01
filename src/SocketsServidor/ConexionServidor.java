package SocketsServidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionServidor {
	
	//Creacion de variables
	private Socket socket;
	private ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	final int puerto = 1234;
	
	public ConexionServidor() {
		
		try {
			
			serverSocket = new ServerSocket(puerto);
			socket = serverSocket.accept();	
			
			//Creacion de entrada de datos para lectura de datos
			entradaSocket = new InputStreamReader(socket.getInputStream());
			entrada = new BufferedReader(entradaSocket);
			
			//Creacion de la salida de datos para la lectura de mensajes
			salida = new DataOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			
		}
		
	}
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			salida.writeUTF(mensaje);
			
		} catch (IOException e) {
			
		}
				
	}
	
	public String leerMensaje() {
		
		try {
			
			return entrada.readLine();
			
		} catch (IOException e) {
			
		}
		return null;
		
	}
	
	public void desconectar() {
		
		try {
			
			socket.close();
			
		} catch (Exception e) {
			
		}
		
		try {
			
			serverSocket.close();
			
		} catch (Exception e) {
			
		}
		
	}

}

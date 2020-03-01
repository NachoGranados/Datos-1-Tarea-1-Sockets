package SocketsCliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionCliente extends Thread{
	
	//Creacion de variables
	private Socket socket;
	private ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	final int puerto = 1234;
	 
	public ConexionCliente(String ip) {
		
		try {
			
			this.socket = new Socket(ip, puerto);
			
			//Creacion de entrada de datos para lectura de datos
			this.entradaSocket = new InputStreamReader(socket.getInputStream());
			this.entrada = new BufferedReader(entradaSocket);
			
			//Creacion de la salida de datos para la lectura de mensajes
			this.salida = new DataOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			
		}
		
	}
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			this.salida = new DataOutputStream(socket.getOutputStream());
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	public void run() {
		
		String texto;
		
		while(true) {
			
			try {
				
				texto = this.entrada.readLine();
				ChatCliente.areaMensajes.setText(ChatCliente.areaMensajes.getText() + "Servidor: " + texto + "\n");
								
			} catch (IOException e) {
				
			}			
			
		}					
		
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

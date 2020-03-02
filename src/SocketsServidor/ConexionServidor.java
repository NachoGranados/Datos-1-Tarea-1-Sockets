package SocketsServidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import SocketsServidor.ChatServidor;

public class ConexionServidor extends Thread{
	
	//Creacion de variables
	static Socket socket;
	static ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	public static int puerto;
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	public void run() {
		
		String texto;
		
		try {
				
			ConexionServidor.serverSocket = new ServerSocket(puerto);
			ConexionServidor.socket = serverSocket.accept();	
			
			//Creacion de entrada de datos para lectura de datos
			this.entradaSocket = new InputStreamReader(socket.getInputStream());
			this.entrada = new BufferedReader(entradaSocket);
			
			//Creacion de la salida de datos para la lectura de mensajes
			this.salida = new DataOutputStream(socket.getOutputStream());
				
			while(true) {
				
				texto = this.entrada.readLine();
				ChatServidor.areaMensajes.setText(ChatServidor.areaMensajes.getText() + "Cliente: " + texto + "\n");
						
			}
			
		} catch (IOException e) {
		
		}
		
	}
	
	public void desconectar() {
		
		try {
			
			socket.close();
			serverSocket.close();
			
		} catch (IOException e) {
			
		}
		
	}

}

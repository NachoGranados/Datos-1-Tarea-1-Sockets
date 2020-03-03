package SocketsServidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import SocketsServidor.ChatServidor;

public class ConexionServidor extends Thread{
	
	//Creacion de variables.
	static Socket socket;
	static ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	public static int puerto;
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			//Envio de mensaje.
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	public void run() {
		
		String texto;
		
		try {
			
			//Inicializacion del servidor.
			ConexionServidor.serverSocket = new ServerSocket(puerto);
			ConexionServidor.socket = serverSocket.accept();	
			
			//Creacion de variables para la entrada de datos.
			this.entradaSocket = new InputStreamReader(socket.getInputStream());
			this.entrada = new BufferedReader(entradaSocket);
			
			//Creacion de variables la salida de mensajes.
			this.salida = new DataOutputStream(socket.getOutputStream());
				
			while(true) {
				
				//Ciclo para que el socket siempre reciba mensajes nuevos.
				texto = this.entrada.readLine();
				ChatServidor.areaMensajes.setText(ChatServidor.areaMensajes.getText() + "Cliente: " + texto + "\n");
						
			}
			
		} catch (IOException e) {
		
		}
		
	}

}

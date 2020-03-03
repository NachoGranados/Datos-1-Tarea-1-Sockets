package SocketsCliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class ConexionCliente extends Thread{
	
	//Creacion de variables.
	static Socket socket;
	static ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	public static int puerto;
	 
	public ConexionCliente(String ip) {
		
		try {
			
			//Inicializacion del cliente.
			ConexionCliente.socket = new Socket(ip, puerto);
			
			//Creacion de variables para la entrada de datos.
			this.entradaSocket = new InputStreamReader(socket.getInputStream());
			this.entrada = new BufferedReader(entradaSocket);
			
			//Creacion de variables para la salida de mensajes.
			this.salida = new DataOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			
		}
		
	}
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			//Envio de mensaje.
			this.salida = new DataOutputStream(socket.getOutputStream());
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	public void run() {
		
		String texto;
		
		while(true) {
			
			try {
				
				//Agregar mensaje recibido.
				texto = this.entrada.readLine();
				ChatCliente.areaMensajes.setText(ChatCliente.areaMensajes.getText() + "Servidor: " + texto + "\n");
								
			} catch (IOException e) {
				
			}			
			
		}					
		
	}

}

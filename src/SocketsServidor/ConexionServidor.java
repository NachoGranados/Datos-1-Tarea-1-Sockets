package SocketsServidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import SocketsServidor.ChatServidor;

/**
 * Esta clase permite manejar la creacion del socket  del servidor junto
 * con sus repectivas funcionalidades de envio y recibo de mensajes.
 * 
 * @author Ignacio Granados *
 */
public class ConexionServidor extends Thread{
	
	//Creacion de variables.
	static Socket socket;
	static ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	public static int puerto;
	
	/**
	 * Este metodo permite enviarle un mensaje al respectivo cliente
	 * con el cual se encuentre conectado.
	 * 
	 * @param mensaje - Mensaje a enviar.
	 */
	public void enviarMensaje(String mensaje) {
		
		try {
			
			//Envio de mensaje.
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	/**
	 * Este metodo se encarga de inicializar o correr el hilo del servidor.
	 */
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

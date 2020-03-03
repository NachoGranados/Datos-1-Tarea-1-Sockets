package SocketsCliente;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Esta clase permite manejar la creacion del socket  del cliente junto
 * con sus repectivas funcionalidades de envio y recibo de mensajes.
 * 
 * @author Ignacio Granados *
 */
public class ConexionCliente extends Thread{
	
	//Creacion de variables.
	static Socket socket;
	static ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	public static int puerto;
	 
	
	/**
	 * Este metodo permite el envio y recibido de mensajes provenientes del
	 * servidor al cual se encuentre conectado dicho cliente.
	 * 
	 * @param ip - Ip para indicarle a la computadora que se abriran conexiones
	 * dentro de ella misma.
	 */
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
	
	/**
	 * Este metodo permite enviarle un mensaje al respectivo servidor
	 * con el cual se encuentre conectado.
	 * 
	 * @param mensaje - Mensaje a enviar.
	 */
	public void enviarMensaje(String mensaje) {
		
		try {
			
			//Envio de mensaje.
			this.salida = new DataOutputStream(socket.getOutputStream());
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	/**
	 * Este metodo se encarga de inicializar o correr el hilo del servidor.
	 */
	public void run() {
		
		//Creacion de variable.
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

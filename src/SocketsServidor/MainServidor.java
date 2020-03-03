package SocketsServidor;

/**
 * Esta clase permite manejar la creacion y establecimiento del socket
 * del servidor para futuros chats.
 * 
 * @author Ignacio Granados *
 */
public class MainServidor {

	public static ConexionServidor servidor;

	/**
	 * Este metodo corresponde al metodo principal de esta clase el cual se
	 * encarga de crear tanto el socket como la interfaz grafica del
	 * respectivo chat.	 * 
	 */
	public static void main() {
		
		//Creacion e inicializacion de la ventana del servidor.
		ChatServidor servidor = new ChatServidor();
		servidor.main();

	}
	
	/**
	 * Este metodo permite inicializar el socket del servidor junto con su
	 * respectivo hilo.
	 */
	public static void iniciarServidor() {
		
		//Inicializacion del socket junto con su respectivo hilo.
		servidor = new ConexionServidor();
		servidor.start();
				
	}

}

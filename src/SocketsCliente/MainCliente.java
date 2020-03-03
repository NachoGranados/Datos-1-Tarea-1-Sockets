package SocketsCliente;

/**
 * Esta clase permite manejar la creacion y establecimiento del socket
 * del cliente para futuros chats.
 * 
 * @author Ignacio Granados
 */
public class MainCliente {
	
	public static ConexionCliente cliente;

	/**
	 * Este metodo corresponde al metodo principal de esta clase el cual se
	 * encarga de crear tanto el socket como la interfaz grafica del
	 * respectivo chat.	 * 
	 */
	public static void main() {
		
		//Creacion e inicializacion de la ventana del cliente.
		ChatCliente cliente = new ChatCliente();
		cliente.main();

	}
		
	/**
	 * Este metodo permite inicializar el socket del cliente junto con su
	 * respectivo hilo.
	 * 
	 * @param ip - Ip para indicarle a la computadora que se abriran conexiones
	 * dentro de ella misma.
	 */
	public static void iniciarCliente(String ip) {
		
		//Inicializacion del socket junto con su respectivo hilo.
		cliente = new ConexionCliente(ip);
		cliente.start();
		
	}
}

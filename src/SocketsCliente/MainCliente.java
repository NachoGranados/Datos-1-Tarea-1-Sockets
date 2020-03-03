package SocketsCliente;

public class MainCliente {
	
	public static ConexionCliente cliente;

	public static void main() {
		
		//Creacion e inicializacion de la ventana del cliente.
		ChatCliente cliente = new ChatCliente();
		cliente.main();

	}
	
	public static void iniciarCliente(String ip) {
		
		//Inicializacion del socket junto con su respectivo hilo.
		cliente = new ConexionCliente(ip);
		cliente.start();
		
	}
}

package SocketsServidor;

public class MainServidor {

	public static ConexionServidor servidor;

	public static void main() {
		
		//Creacion e inicializacion de la ventana del servidor.
		ChatServidor servidor = new ChatServidor();
		servidor.main();

	}
	
	public static void iniciarServidor() {
		
		//Inicializacion del socket junto con su respectivo hilo.
		servidor = new ConexionServidor();
		servidor.start();
				
	}

}

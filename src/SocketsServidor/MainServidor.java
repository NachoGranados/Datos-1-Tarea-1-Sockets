package SocketsServidor;

public class MainServidor {

	public static ConexionServidor servidor;

	public static void main() {
		
		ChatServidor servidor = new ChatServidor();
		servidor.main();

	}
	
	public static void iniciarServidor() {
		
		servidor = new ConexionServidor();
		servidor.start();
				
	}

}

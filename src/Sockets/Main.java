package Sockets;

public class Main {
	
	public static Conexion servidor, cliente;

	public static void main(String[] args) {
		
		ChatServidor servidor = new ChatServidor();
		servidor.main();

	}
	
	public static void iniciarServidor() {
		
		servidor = new Conexion();		
				
	}
	
	public static void iniciarCliente(String ip) {
		
		cliente = new Conexion(ip);
		
	}

}

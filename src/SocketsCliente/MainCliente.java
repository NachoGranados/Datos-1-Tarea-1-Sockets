package SocketsCliente;

import Sockets.ChatServidor;
import Sockets.Conexion;

public class MainCliente {
	
	public static ConexionCliente cliente;

	public static void main(String[] args) {
		
		ChatServidor servidor = new ChatServidor();
		servidor.main();

	}
	
	public static void iniciarCliente(String ip) {
		
		cliente = new ConexionCliente(ip);
		
	}
}

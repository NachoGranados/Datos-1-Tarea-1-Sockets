package SocketsServidor;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import SocketsServidor.ChatServidor;

public class ConexionServidor extends Thread{
	
	//Creacion de variables
	private Socket socket;
	private ServerSocket serverSocket;
	private InputStreamReader entradaSocket;
	private DataOutputStream salida;
	private BufferedReader entrada;
	final int puerto = 1234;
	
	/*
	public ConexionServidor() {
		
		try {
			
			serverSocket = new ServerSocket(puerto);
			socket = serverSocket.accept();	
			
			//Creacion de entrada de datos para lectura de datos
			entradaSocket = new InputStreamReader(socket.getInputStream());
			entrada = new BufferedReader(entradaSocket);
			
			//Creacion de la salida de datos para la lectura de mensajes
			salida = new DataOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			
		}
		
	}
	
	/*/
	
	public void enviarMensaje(String mensaje) {
		
		try {
			
			this.salida.writeUTF(mensaje + "\n");
			
		} catch (IOException e) {
			
		}
				
	}
	
	public void run() {
		
		String texto;
		
		try {
			System.out.print("Estoy pasando por aqui");
				
			this.serverSocket = new ServerSocket(puerto);
			this.socket = serverSocket.accept();	
			
			//Creacion de entrada de datos para lectura de datos
			this.entradaSocket = new InputStreamReader(socket.getInputStream());
			this.entrada = new BufferedReader(entradaSocket);
			
			//Creacion de la salida de datos para la lectura de mensajes
			this.salida = new DataOutputStream(socket.getOutputStream());
				
			while(true) {
			
				texto = this.entrada.readLine();
				System.out.print(texto);
				ChatServidor.areaMensajes.setText(ChatServidor.areaMensajes.getText() + "\n" + texto);		
						
			}
			
		} catch (IOException e) {
		
		}
		
	}
	
	public String leerMensaje() {
		
		try {
			
			return entrada.readLine();
			
		} catch(IOException e) {
			
		}
		return null;
		
	}
	
	public void desconectar() {
		
		try {
			
			socket.close();
			
		} catch (Exception e) {
			
		}
		
		try {
			
			serverSocket.close();
			
		} catch (Exception e) {
			
		}
		
	}

}

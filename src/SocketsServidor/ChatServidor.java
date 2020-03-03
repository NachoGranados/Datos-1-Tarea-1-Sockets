package SocketsServidor;

import java.awt.EventQueue;
import SocketsServidor.MainServidor;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import SocketsVentanaInicio.VentanaInicio;

/**
 * Esta clase permite manejar la creacion y establecimiento de la 
 * interfaz grafica del chat del servidor junto con sus respectivas
 * funcionalidades de los elementos graficos que se encuentra en ella.
 * 
 * @author Ignacio Granados
 */
public class ChatServidor {

	//Creacion de variables.
	private JFrame frame;
	public static JTextField areaTexto;
	public static JTextArea areaMensajes;
	private JScrollPane scrollPane;	
	public static ArrayList<String> listaConversaciones = new ArrayList<String>();
	private JButton botonCerrarChat;	
	public int indice;

	/**
	 * Este metodo corresponde al metodo principal de esta clase el cual se
	 * encarga de crear la interfaz grafica.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatServidor window = new ChatServidor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Este metodo corresponde al metodo que se encarga de inicializar la
	 * interfaz grafica.
	 */
	public ChatServidor() {
		initialize();
	}

	/**
	 * Este metodo se encarga de agregar todos los objetos graficos de la interfaz
	 * grafica junto con su respectiva funcionalidad dentro del sistema.
	 */	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton botonEnviar = new JButton("Enviar");
		botonEnviar.addActionListener(new ActionListener() {

			/**
			 *Este metodo corresponde a la funcionalidad que realizara el boton
			 *Enviar cada vez que sea presionado.
			 */	
			public void actionPerformed(ActionEvent e) {
								
				//Asignacion de datos a algunas variables creadas.
				String texto = areaTexto.getText();
				int longitud = VentanaInicio.listaPuertos.size() - 1;
				
				if(longitud < 0) {
					
					longitud = 0;
					
				}		
				
				//Conexion con el socket para enviar mensajes.
				MainServidor.servidor.enviarMensaje(texto);
				areaMensajes.setText(areaMensajes.getText() + "Servidor: " + texto + "\n");
				areaTexto.setText("");
				
				//Condicion que verifica si se debe cargar una conversacion anterior.
				if(VentanaInicio.cargar == true) {	
					
					listaConversaciones.set(indice, areaMensajes.getText());
												
				} else {
					
					listaConversaciones.set(longitud, areaMensajes.getText());
					
				}
						
			}
		});
		
		botonEnviar.setBounds(335, 227, 89, 23);
		frame.getContentPane().add(botonEnviar);
		
		JButton botonIniciarServidor = new JButton("Iniciar Servidor");
		botonIniciarServidor.addActionListener(new ActionListener() {
			
			/**
			 *Este metodo corresponde a la funcionalidad que realizara el boton
			 *Iniciar Servidor cada vez que sea presionado.
			 */	
			public void actionPerformed(ActionEvent e) {
				
				//Creacion de esta variable.
				indice = VentanaInicio.listaPuertos.indexOf(VentanaInicio.puerto);
				
				//Inicio de la conxion socket.
				MainServidor.iniciarServidor();				
				
				//Condicion que verifica si se debe cargar una conversacion anterior.
				if(VentanaInicio.cargar == true) {
					
					String conversacion = listaConversaciones.get(indice);
					
					areaMensajes.setText(conversacion);
					
				}
					
			}
		});
		
		botonIniciarServidor.setBounds(174, 11, 120, 23);
		frame.getContentPane().add(botonIniciarServidor);
		
		areaTexto = new JTextField();
		areaTexto.setBounds(10, 228, 315, 20);
		frame.getContentPane().add(areaTexto);
		areaTexto.setColumns(10);
		
		JLabel labelServidor = new JLabel("Servidor");
		labelServidor.setBounds(10, 11, 60, 23);
		frame.getContentPane().add(labelServidor);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 414, 171);
		frame.getContentPane().add(scrollPane);
		
		areaMensajes = new JTextArea();
		scrollPane.setViewportView(areaMensajes);
		
		botonCerrarChat = new JButton("Cerrar Chat");
		botonCerrarChat.addActionListener(new ActionListener() {
			
			/**
			 *Este metodo corresponde a la funcionalidad que realizara el boton
			 *Cerrar Chat cada vez que sea presionado.
			 */	
			public void actionPerformed(ActionEvent evento) {
				
				try {
					
					//Se cierra el puerto.
					VentanaInicio.cargar = false;
					ConexionServidor.serverSocket.close();
					
				} catch (IOException e){
					
				}
				
			}
		});
		
		botonCerrarChat.setBounds(304, 11, 120, 23);
		frame.getContentPane().add(botonCerrarChat);
	}
}

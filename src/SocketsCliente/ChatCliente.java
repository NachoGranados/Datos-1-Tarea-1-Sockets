package SocketsCliente;

import java.awt.EventQueue;
import SocketsCliente.MainCliente;
import SocketsServidor.ChatServidor;
import SocketsVentanaInicio.VentanaInicio;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Esta clase permite manejar la creacion y establecimiento de la 
 * interfaz grafica del chat del cliente junto con sus respectivas
 * funcionalidades de los elementos graficos que se encuentra en ella.
 * 
 * @author Ignacio Granados
 */
public class ChatCliente {

	//Creacion de variables.
	private JFrame frame;
	public static JTextField areaTexto;
	public static JTextArea areaMensajes;	
	int indice;	
	
	/**
	 * Este metodo corresponde al metodo principal de esta clase el cual se
	 * encarga de crear la interfaz grafica.
	 */
	public void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ChatCliente window = new ChatCliente();
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
	public ChatCliente() {
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
		
		JButton botonEnviar = new JButton("Enviar");
		botonEnviar.setBounds(335, 227, 89, 23);
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
				MainCliente.cliente.enviarMensaje(texto);
				areaMensajes.setText(areaMensajes.getText() + "Cliente: " + texto + "\n");
				areaTexto.setText("");
				
				//Condicion que verifica si se debe cargar una conversacion anterior.
				if(VentanaInicio.cargar == true) {	
					
					ChatServidor.listaConversaciones.set(indice, areaMensajes.getText());	
												
				} else {
					
					ChatServidor.listaConversaciones.set(longitud, areaMensajes.getText());
					
				}
				
			}
		});
		
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(botonEnviar);
		
		JButton botonConectarse = new JButton("Conectarse");
		botonConectarse.setBounds(294, 11, 130, 23);
		botonConectarse.addActionListener(new ActionListener() {
			
			/**
			 *Este metodo corresponde a la funcionalidad que realizara el boton
			 *Conectarse cada vez que sea presionado.
			 */	
			public void actionPerformed(ActionEvent e) {
				
				//Asignacion del ip de la computadora
				MainCliente.iniciarCliente("127.0.0.1");
				
				//Creacion de esta variable.
				indice = VentanaInicio.listaPuertos.indexOf(VentanaInicio.puerto);
				
				//Condicion que verifica si se debe cargar una conversacion anterior.
				if(VentanaInicio.cargar == true) {					
				
					String conversacion = ChatServidor.listaConversaciones.get(indice);
					
					areaMensajes.setText(conversacion);
					
				}
				
			}
		});
		
		frame.getContentPane().add(botonConectarse);
		
		JLabel labelCliente = new JLabel("Cliente");
		labelCliente.setBounds(10, 11, 60, 23);
		frame.getContentPane().add(labelCliente);
		
		areaTexto = new JTextField();
		areaTexto.setBounds(10, 230, 315, 20);
		frame.getContentPane().add(areaTexto);
		areaTexto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 45, 414, 171);
		frame.getContentPane().add(scrollPane);
		
		areaMensajes = new JTextArea();
		scrollPane.setViewportView(areaMensajes);
				
	}
}

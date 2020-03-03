package SocketsVentanaInicio;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import SocketsServidor.MainServidor;
import SocketsServidor.ChatServidor;
import SocketsServidor.ConexionServidor;
import SocketsCliente.ConexionCliente;
import SocketsCliente.MainCliente;
import javax.swing.JScrollPane;
import java.awt.Font;

/**
 * Esta clase permite manejar la creacion y establecimiento de sockets
 * mediante una unica ventana capaz de ligar todas las demás clases
 * del programa. 
 * 
 * @author Ignacio Granados
 */
public class VentanaInicio {

	private JFrame frame;
	private JTextField areaPuerto;
	private JTextArea areaPuertosConsultados;
	public static ArrayList<String> listaPuertos = new ArrayList<String>();
	public static boolean cargar;
	public static String puerto;	
	
	/**
	 * Este metodo corresponde al metodo principal de esta clase el cual se
	 * encarga de crear la interfaz grafica.
	 * 
	 * @param args - Respectivo parametro del metodo main.
	 */	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio window = new VentanaInicio();
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
	public VentanaInicio() {
		initialize();
	}

	/**
	 * Este metodo se encarga de agregar todos los objetos graficos de la interfaz
	 * grafica junto con su respectiva funcionalidad dentro del sistema.
	 */	
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelPuertosConsultados = new JLabel("Puertos Consultados");
		labelPuertosConsultados.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPuertosConsultados.setBounds(10, 11, 165, 14);
		frame.getContentPane().add(labelPuertosConsultados);
		
		JButton botonNuevoChat = new JButton("Nuevo Chat");
		botonNuevoChat.setFont(new Font("Tahoma", Font.PLAIN, 15));
		botonNuevoChat.addActionListener(new ActionListener() {
			
			/**
			 *Este metodo corresponde a la funcionalidad que realizara el boton
			 *NuevoChat cada vez que sea presionado.
			 */			
			public void actionPerformed(ActionEvent evento) {
				
				//Creacion de variables
				puerto = areaPuerto.getText();
				cargar = false;
				String antes = "";
				
				//Manejo de errores para que el usuario siempre digite un puerto numerico
				try {					
								
					//Primera condicion que verifica si se desea cargar una conversacion anterior
					if(listaPuertos.contains(puerto) == false) {
						
						listaPuertos.add(puerto);
						antes = areaPuertosConsultados.getText();
						areaPuertosConsultados.setText(areaPuertosConsultados.getText() + "\n" + puerto);
						areaPuerto.setText("");
						
					} else {
						
						cargar = true;
						
					}
					
					//Conexion con el socket que el usuario solicito
					ConexionServidor.puerto = Integer.parseInt(puerto);
					ConexionCliente.puerto = Integer.parseInt(puerto);
					MainServidor.main();
					MainCliente.main();					
					
					//Segunda condicion que verifica si se desea cargar una conversacion anterior
					if(cargar != true) {
						
						ChatServidor.listaConversaciones.add(puerto);
						
					}				
					
				  //No se abre la conexion socket hasta que el usuario digite correctamente el puerto.
				} catch (NumberFormatException e) {
					
					areaPuertosConsultados.setText(antes);
					JOptionPane.showMessageDialog(null, "Puero incorrecto. Por favor digite un puerto numérico.");
					listaPuertos.remove(puerto);
					
				}
				
			}
				
		});
		
		botonNuevoChat.setBounds(155, 203, 269, 47);
		frame.getContentPane().add(botonNuevoChat);
		
		JLabel labelPuerto = new JLabel("Digite el puerto al cual quiere conectarse:");
		labelPuerto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		labelPuerto.setBounds(155, 127, 295, 23);
		frame.getContentPane().add(labelPuerto);
		
		areaPuerto = new JTextField();
		areaPuerto.setFont(new Font("Tahoma", Font.PLAIN, 15));
		areaPuerto.setBounds(155, 161, 269, 31);
		frame.getContentPane().add(areaPuerto);
		areaPuerto.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 36, 135, 214);
		frame.getContentPane().add(scrollPane);
		
		areaPuertosConsultados = new JTextArea();
		scrollPane.setViewportView(areaPuertosConsultados);
		
		JLabel labelTarea1 = new JLabel("Tarea 1");
		labelTarea1.setFont(new Font("Tahoma", Font.PLAIN, 35));
		labelTarea1.setBounds(226, 11, 135, 47);
		frame.getContentPane().add(labelTarea1);
		
		JLabel labelSockets = new JLabel("Sockets");
		labelSockets.setFont(new Font("Tahoma", Font.PLAIN, 35));
		labelSockets.setBounds(226, 69, 135, 47);
		frame.getContentPane().add(labelSockets);
		
	}
}

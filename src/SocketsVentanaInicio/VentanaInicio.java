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

public class VentanaInicio {

	private JFrame frame;
	private JTextField areaPuerto;
	private JTextArea areaPuertosConsultados;
	public static ArrayList<String> listaPuertos = new ArrayList<String>();
	public static boolean cargar;
	public static String puerto;
	
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
	
	public VentanaInicio() {
		initialize();
	}

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
			public void actionPerformed(ActionEvent evento) {

				puerto = areaPuerto.getText();
				cargar = false;
				String antes = "";
				
				try {					
											
					if(listaPuertos.contains(puerto) == false) {
						
						listaPuertos.add(puerto);
						antes = areaPuertosConsultados.getText();
						areaPuertosConsultados.setText(areaPuertosConsultados.getText() + "\n" + puerto);
						areaPuerto.setText("");
						
					} else {
						
						cargar = true;
						
					}
												
					ConexionServidor.puerto = Integer.parseInt(puerto);
					ConexionCliente.puerto = Integer.parseInt(puerto);
					MainServidor.main();
					MainCliente.main();					

					if(cargar != true) {
						
						ChatServidor.listaConversaciones.add(puerto);
						
					}				
																								
				} catch (NumberFormatException e) {
					
					areaPuertosConsultados.setText(antes);
					JOptionPane.showMessageDialog(null, "Puero incorrecto. Por favor digite un puerto num�rico.");
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

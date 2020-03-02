package SocketsVentanaInicio;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class VentanaInicio {

	private JFrame frame;
	private JTextField areaNombre;
	private JTextField areaPuerto;
	private JTextArea areaPuertosConsultados;
	public static ArrayList<String> listaPuertos = new ArrayList<String>();
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

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
		labelPuertosConsultados.setBounds(10, 11, 125, 14);
		frame.getContentPane().add(labelPuertosConsultados);
		
		JButton botonNuevoChat = new JButton("Nuevo Chat");
		botonNuevoChat.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String nombre = areaNombre.getText();
				String puerto = areaPuerto.getText();
				
				if(nombre == "" || puerto == "") {
					
					System.out.print("No se puede ejecutar el chat");
					
				} else {
					
					if(listaPuertos.contains(puerto) == false) {
						
						listaPuertos.add(puerto);
						
					ConexionServidor.puerto = Integer.parseInt(puerto);
					ConexionCliente.puerto = Integer.parseInt(puerto);
					MainServidor.main();
					MainCliente.main();					
					areaPuertosConsultados.setText(areaPuertosConsultados.getText() + "\n" + puerto);
					ChatServidor.listaConversaciones.add("");
					}
				
			} 
		}
				
		});
		
		botonNuevoChat.setBounds(145, 125, 125, 23);
		frame.getContentPane().add(botonNuevoChat);
		
		areaPuertosConsultados = new JTextArea();
		areaPuertosConsultados.setBounds(10, 36, 119, 214);
		frame.getContentPane().add(areaPuertosConsultados);
		
		areaNombre = new JTextField();
		areaNombre.setBounds(145, 38, 279, 20);
		frame.getContentPane().add(areaNombre);
		areaNombre.setColumns(10);
		
		JLabel labelNombre = new JLabel("Digite su nombre:");
		labelNombre.setBounds(145, 11, 279, 14);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelPuerto = new JLabel("Digite el puerto al cual quiere conectarse:");
		labelPuerto.setBounds(145, 69, 279, 14);
		frame.getContentPane().add(labelPuerto);
		
		areaPuerto = new JTextField();
		areaPuerto.setBounds(145, 94, 279, 20);
		frame.getContentPane().add(areaPuerto);
		areaPuerto.setColumns(10);
	}
	
}

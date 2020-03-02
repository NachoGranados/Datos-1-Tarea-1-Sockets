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

public class ChatServidor {

	private JFrame frame;
	public static JTextField areaTexto;
	public static JTextArea areaMensajes;
	private JScrollPane scrollPane;	
	public static ArrayList<String> listaConversaciones = new ArrayList<String>();
	private JButton botonCerrarChat;	
	public int indice;

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

	public ChatServidor() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton botonEnviar = new JButton("Enviar");
		botonEnviar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
								
				String texto = areaTexto.getText();
				int longitud = VentanaInicio.listaPuertos.size() - 1;
				
				if(longitud < 0) {
					
					longitud = 0;
					
				}		
					
				MainServidor.servidor.enviarMensaje(texto);
				areaMensajes.setText(areaMensajes.getText() + "Servidor: " + texto + "\n");
				areaTexto.setText("");
				
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
			public void actionPerformed(ActionEvent e) {
				
				indice = VentanaInicio.listaPuertos.indexOf(VentanaInicio.puerto);
				
				MainServidor.iniciarServidor();				
				
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
			public void actionPerformed(ActionEvent evento) {
				
				try {
					
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

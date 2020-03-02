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

public class ChatCliente {

	private JFrame frame;
	public static JTextField areaTexto;
	public static JTextArea areaMensajes;	
	int indice;	
	
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
 
	public ChatCliente() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JButton botonEnviar = new JButton("Enviar");
		botonEnviar.setBounds(335, 227, 89, 23);
		botonEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String texto = areaTexto.getText();
				int longitud = VentanaInicio.listaPuertos.size() - 1;
								
				if(longitud < 0) {
					
					longitud = 0;
					
				}
				
				MainCliente.cliente.enviarMensaje(texto);
				areaMensajes.setText(areaMensajes.getText() + "Cliente: " + texto + "\n");
				areaTexto.setText("");
				
				
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
			public void actionPerformed(ActionEvent e) {
				
				MainCliente.iniciarCliente("127.0.0.1");
				
				indice = VentanaInicio.listaPuertos.indexOf(VentanaInicio.puerto);
				
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

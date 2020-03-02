import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class VentanaInicio {

	private JFrame frame;
	private JTextField areaNombre;
	private JTextField areaPuerto;

	/**
	 * Launch the application.
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
	 * Create the application.
	 */
	public VentanaInicio() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel labelPuertosConsultados = new JLabel("Puertos Consultados");
		labelPuertosConsultados.setBounds(10, 11, 125, 14);
		frame.getContentPane().add(labelPuertosConsultados);
		
		JButton botonNuevoChat = new JButton("Nuevo Chat");
		botonNuevoChat.setBounds(145, 125, 89, 23);
		frame.getContentPane().add(botonNuevoChat);
		
		JTextArea areaPuertosConsultados = new JTextArea();
		areaPuertosConsultados.setBounds(10, 36, 99, 214);
		frame.getContentPane().add(areaPuertosConsultados);
		
		areaNombre = new JTextField();
		areaNombre.setBounds(145, 38, 279, 20);
		frame.getContentPane().add(areaNombre);
		areaNombre.setColumns(10);
		
		JLabel labelNombre = new JLabel("Digite su nombre:");
		labelNombre.setBounds(145, 11, 89, 14);
		frame.getContentPane().add(labelNombre);
		
		JLabel labelPuerto = new JLabel("Digite el puerto al cual quiere conectarse:");
		labelPuerto.setBounds(145, 69, 199, 14);
		frame.getContentPane().add(labelPuerto);
		
		areaPuerto = new JTextField();
		areaPuerto.setBounds(145, 94, 279, 20);
		frame.getContentPane().add(areaPuerto);
		areaPuerto.setColumns(10);
	}
}

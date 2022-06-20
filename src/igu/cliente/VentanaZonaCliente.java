package igu.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.UsuarioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaZonaCliente extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblZonaCliente;
	private JButton btnHacerEnvio;
	private JButton btnSalir;
	private VentanaDatosCrearEnvio crearEnvio;


	/**
	 * Create the frame.
	 */
	public VentanaZonaCliente() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaZonaCliente.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblZonaCliente());
		contentPane.add(getBtnHacerEnvio());
		contentPane.add(getBtnSalir());
	}
	
	
	
	private JLabel getLblZonaCliente() {
		if (lblZonaCliente == null) {
			lblZonaCliente = new JLabel("Zona de cliente");
			lblZonaCliente.setHorizontalAlignment(SwingConstants.CENTER);
			lblZonaCliente.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblZonaCliente.setBounds(92, 26, 375, 33);
		}
		return lblZonaCliente;
	}
	
	private JButton getBtnHacerEnvio() {
		if (btnHacerEnvio == null) {
			btnHacerEnvio = new JButton("Hacer env\u00EDos");
			btnHacerEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showVentanaDatosCrearEnvio();
				}
			});
			btnHacerEnvio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnHacerEnvio.setBounds(163, 116, 229, 76);
		}
		return btnHacerEnvio;
	}
	private JButton getBtnSalir() {
		if (btnSalir == null) {
			btnSalir = new JButton("Salir");
			btnSalir.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnSalir.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnSalir.setBounds(183, 256, 190, 53);
		}
		return btnSalir;
	}
	
	private void showVentanaDatosCrearEnvio() {
		crearEnvio = new VentanaDatosCrearEnvio();
		crearEnvio.setLocationRelativeTo(this);
		//crearEnvio.setModalityType(true);
		crearEnvio.setVisible(true);
		crearEnvio.initialize(this.loggedUser);
	}


	private UsuarioDto loggedUser;

	public void initialize(UsuarioDto dto) {
		this.loggedUser = dto;		
	}
}

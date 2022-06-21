package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.EnviosService;
import igu.destinatario.VentanaCodigoEnvio;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class VentanaInicio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblWelcome;
	private JButton btnRegistro;
	private JButton btnIniciarSesion;
	private JButton btnConsultarEnvio;
	private JLabel lblSeleccione;

	private VentanaRegistro ventanaRegistro;
	private VentanaLogin ventanaLogin;
	private VentanaCodigoEnvio codigoEnvio;
	

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (checkExit()) {
					System.exit(0);
				}
			}
		});
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 668, 388);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblWelcome());
		contentPane.add(getBtnRegistro());
		contentPane.add(getBtnIniciarSesion());
		contentPane.add(getBtnConsultarEnvio());
		contentPane.add(getLblSeleccione());
	}
	
	
	
	private JLabel getLblWelcome() {
		if (lblWelcome == null) {
			lblWelcome = new JLabel("Bienvenido al sistema de transporte de paquetes");
			lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
			lblWelcome.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblWelcome.setBounds(83, 58, 516, 33);
		}
		return lblWelcome;
	}
	
	private JButton getBtnRegistro() {
		if (btnRegistro == null) {
			btnRegistro = new JButton("Registrarse");
			btnRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showVentanaRegistro();
				}
			});
			btnRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnRegistro.setBounds(36, 264, 190, 53);
		}
		return btnRegistro;
	}
	private JButton getBtnIniciarSesion() {
		if (btnIniciarSesion == null) {
			btnIniciarSesion = new JButton("Iniciar sesi\u00F3n");
			btnIniciarSesion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showVentanaLogin();
				}
			});
			btnIniciarSesion.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnIniciarSesion.setBounds(236, 264, 190, 53);
		}
		return btnIniciarSesion;
	}
	private JButton getBtnConsultarEnvio() {
		if (btnConsultarEnvio == null) {
			btnConsultarEnvio = new JButton("Seguimiento env\u00EDo");
			btnConsultarEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showVentanaCodigoEnvio();
				}
			});
			btnConsultarEnvio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnConsultarEnvio.setBounds(436, 264, 190, 53);
		}
		return btnConsultarEnvio;
	}
	private JLabel getLblSeleccione() {
		if (lblSeleccione == null) {
			lblSeleccione = new JLabel("Seleccione:");
			lblSeleccione.setHorizontalAlignment(SwingConstants.CENTER);
			lblSeleccione.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSeleccione.setBounds(69, 158, 516, 33);
		}
		return lblSeleccione;
	}
	
	public boolean checkExit() {		
		if (JOptionPane.showConfirmDialog(this, "Estás seguro de que quieres cerrar la aplicación?") == JOptionPane.YES_OPTION) {
			return true;
		}
		return false;
	}
	
	private void showVentanaRegistro() {
		ventanaRegistro = new VentanaRegistro();
		ventanaRegistro.setLocationRelativeTo(this);
		//ventanaRegistro.setModalityType(true);
		ventanaRegistro.setVisible(true);	
	}
	
	private void showVentanaLogin() {
		ventanaLogin = new VentanaLogin();
		ventanaLogin.setLocationRelativeTo(this);
		//ventanaLogin.setModalityType(true);
		ventanaLogin.setVisible(true);		
	}
	
	private void showVentanaCodigoEnvio() {
		codigoEnvio = new VentanaCodigoEnvio();
		codigoEnvio.setLocationRelativeTo(this);
		//codigoEnvio.setModalityType(true);
		codigoEnvio.setVisible(true);
	}
}

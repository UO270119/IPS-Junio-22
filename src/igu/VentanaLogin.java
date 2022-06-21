package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.UsuariosService;
import business.exception.BusinessException;
import dto.UsuarioDto;
import igu.admin.VentanaZonaAdministrador;
import igu.cliente.VentanaZonaCliente;
import igu.repartidor.VentanaZonaRepartidor;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblInicioSesion;
	private JButton btnEntrar;
	private JButton btnSalir;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JLabel lblContrasena;
	private JPasswordField passwordField;
	
	private VentanaZonaCliente zonaCliente;
	private VentanaZonaRepartidor zonaRepartidor;
	private VentanaZonaAdministrador zonaAdministrador;


	/**
	 * Create the frame.
	 */
	public VentanaLogin() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaLogin.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblInicioSesion());
		contentPane.add(getBtnEntrar());
		contentPane.add(getBtnSalir());
		contentPane.add(getLblDNI());
		contentPane.add(getTxtDNI());
		contentPane.add(getLblContrasena());
		contentPane.add(getPasswordField());
		
	}
	
	
	
	private JLabel getLblInicioSesion() {
		if (lblInicioSesion == null) {
			lblInicioSesion = new JLabel("Inicio de sesi\u00F3n");
			lblInicioSesion.setHorizontalAlignment(SwingConstants.CENTER);
			lblInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblInicioSesion.setBounds(115, 27, 375, 33);
		}
		return lblInicioSesion;
	}
	
	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					String dni = getTxtDNI().getText();
					String contrasena = getPasswordField().getText();
					
					UsuariosService us = BusinessFactory.getUsuariosService();
					try {
						UsuarioDto usuario = us.login(dni, contrasena);
						
						if (UsuarioDto.TIPO_ADMIN.equals(usuario.tipo))
							showVentanaZonaAdministrador(usuario);
						else if (UsuarioDto.TIPO_REPARTIDOR.equals(usuario.tipo))
							showVentanaZonaRepartidor(usuario);
						else showVentanaZonaCliente(usuario);
						
					} catch(BusinessException ex) {
						JOptionPane.showMessageDialog(rootPane, "DNI o contraseña incorrectos");
					}
				}
			});
			btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnEntrar.setBounds(83, 239, 190, 53);
		}
		return btnEntrar;
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
			btnSalir.setBounds(311, 239, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
			lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDNI.setBounds(23, 82, 168, 33);
		}
		return lblDNI;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setColumns(10);
			txtDNI.setBounds(185, 86, 336, 33);
		}
		return txtDNI;
	}
	private JLabel getLblContrasena() {
		if (lblContrasena == null) {
			lblContrasena = new JLabel("Contrase\u00F1a:");
			lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
			lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblContrasena.setBounds(23, 154, 168, 33);
		}
		return lblContrasena;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(185, 158, 336, 33);
		}
		return passwordField;
	}

	
	
	private void showVentanaZonaCliente(UsuarioDto dto) {
		zonaCliente = new VentanaZonaCliente();
		zonaCliente.setLocationRelativeTo(this);
		//zonaCliente.setModalityType(true);
		zonaCliente.setVisible(true);
		
		zonaCliente.initialize(dto);
	}
	
	private void showVentanaZonaRepartidor(UsuarioDto dto) {
		zonaRepartidor = new VentanaZonaRepartidor();
		zonaRepartidor.setLocationRelativeTo(this);
		//zonaRepartidor.setModalityType(true);
		zonaRepartidor.setVisible(true);
		
		zonaRepartidor.initialize(dto);
	}
	
	private void showVentanaZonaAdministrador(UsuarioDto dto) {
		zonaAdministrador = new VentanaZonaAdministrador();
		zonaAdministrador.setLocationRelativeTo(this);
		//zonaAdministrador.setModalityType(true);
		zonaAdministrador.setVisible(true);
		
		zonaAdministrador.initialize(dto);
	}
}

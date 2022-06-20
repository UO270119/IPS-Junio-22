package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.UsuariosService;
import business.exception.BusinessException;
import dto.DtoFactory;
import dto.UsuarioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblRegistro;
	private JButton btnConfirmarRegistro;
	private JButton btnSalir;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellido;
	private JLabel lblDNI;
	private JTextField txtDNI;
	private JTextField txtCorreo;
	private JLabel lblCorreo;
	private JLabel lblContrasena;
	private JRadioButton rdbtnParticular;
	private JRadioButton rdbtnEmpresa;
	private JPasswordField passwordField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JLabel lblSoy;
	
	private VentanaConfirmacionRegistro confirmacionRegistro;


	/**
	 * Create the frame.
	 */
	public VentanaRegistro() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaRegistro.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 589, 514);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblRegistro());
		contentPane.add(getBtnConfirmarRegistro());
		contentPane.add(getBtnSalir());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblApellidos());
		contentPane.add(getTxtApellido());
		contentPane.add(getLblDNI());
		contentPane.add(getTxtDNI());
		contentPane.add(getTxtCorreo());
		contentPane.add(getLblCorreo());
		contentPane.add(getLblContrasena());
		contentPane.add(getRdbtnParticular());
		contentPane.add(getRdbtnEmpresa());
		contentPane.add(getPasswordField());
		contentPane.add(getLblSoy());
	}
	
	
	
	private JLabel getLblRegistro() {
		if (lblRegistro == null) {
			lblRegistro = new JLabel("Registro de nuevo cliente");
			lblRegistro.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistro.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblRegistro.setBounds(83, 21, 516, 33);
		}
		return lblRegistro;
	}
	
	private JButton getBtnConfirmarRegistro() {
		if (btnConfirmarRegistro == null) {
			btnConfirmarRegistro = new JButton("Confirmar registro");
			btnConfirmarRegistro.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {				
					if(!getTxtNombre().getText().isEmpty() && !getTxtApellido().getText().isEmpty() && !getTxtDNI().getText().isEmpty() && !getTxtCorreo().getText().isEmpty() && !getPasswordField().getText().isEmpty() && (getRdbtnEmpresa().isSelected() || getRdbtnParticular().isSelected())) {
						// correcto haz cosas
						
						UsuarioDto dto = DtoFactory.newUsuarioCliente();
						dto.nombre = getTxtNombre().getText();
						dto.apellido = getTxtApellido().getText();
						dto.dni = getTxtDNI().getText();
						dto.email = getTxtCorreo().getText();
						dto.contrasena = getPasswordField().getText();
						
						UsuariosService us = BusinessFactory.getUsuariosService();
						try {
							us.registrarUsuario(dto);	
							showVentanaConfirmacionRegistro();
						} catch(BusinessException ex) {
							JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al registrar el usuario");
						}
						
					} else {
						JOptionPane.showMessageDialog(rootPane, "Alguno de los campos es incorrecto"); //hacer uno por cada
					}
				}
			});
			btnConfirmarRegistro.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnConfirmarRegistro.setBounds(100, 393, 190, 53);
		}
		return btnConfirmarRegistro;
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
			btnSalir.setBounds(320, 393, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombre.setBounds(23, 82, 168, 33);
		}
		return lblNombre;
	}
	private JTextField getTxtNombre() {
		if (txtNombre == null) {
			txtNombre = new JTextField();
			txtNombre.setBounds(185, 82, 336, 33);
			txtNombre.setColumns(10);
		}
		return txtNombre;
	}
	private JLabel getLblApellidos() {
		if (lblApellidos == null) {
			lblApellidos = new JLabel("Apellidos:");
			lblApellidos.setHorizontalAlignment(SwingConstants.CENTER);
			lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblApellidos.setBounds(23, 125, 168, 33);
		}
		return lblApellidos;
	}
	private JTextField getTxtApellido() {
		if (txtApellido == null) {
			txtApellido = new JTextField();
			txtApellido.setColumns(10);
			txtApellido.setBounds(185, 125, 336, 33);
		}
		return txtApellido;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
			lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDNI.setBounds(23, 168, 168, 33);
		}
		return lblDNI;
	}
	private JTextField getTxtDNI() {
		if (txtDNI == null) {
			txtDNI = new JTextField();
			txtDNI.setColumns(10);
			txtDNI.setBounds(185, 168, 336, 33);
		}
		return txtDNI;
	}
	private JTextField getTxtCorreo() {
		if (txtCorreo == null) {
			txtCorreo = new JTextField();
			txtCorreo.setColumns(10);
			txtCorreo.setBounds(185, 211, 336, 33);
		}
		return txtCorreo;
	}
	private JLabel getLblCorreo() {
		if (lblCorreo == null) {
			lblCorreo = new JLabel("Correo:");
			lblCorreo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCorreo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCorreo.setBounds(23, 211, 168, 33);
		}
		return lblCorreo;
	}
	private JLabel getLblContrasena() {
		if (lblContrasena == null) {
			lblContrasena = new JLabel("Contrase\u00F1a:");
			lblContrasena.setHorizontalAlignment(SwingConstants.CENTER);
			lblContrasena.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblContrasena.setBounds(23, 254, 168, 33);
		}
		return lblContrasena;
	}
	private JRadioButton getRdbtnParticular() {
		if (rdbtnParticular == null) {
			rdbtnParticular = new JRadioButton("Particular");
			rdbtnParticular.setFont(new Font("Tahoma", Font.PLAIN, 18));
			buttonGroup.add(rdbtnParticular);
			rdbtnParticular.setBounds(236, 330, 150, 45);
		}
		return rdbtnParticular;
	}
	private JRadioButton getRdbtnEmpresa() {
		if (rdbtnEmpresa == null) {
			rdbtnEmpresa = new JRadioButton("Empresa");
			rdbtnEmpresa.setFont(new Font("Tahoma", Font.PLAIN, 18));
			buttonGroup.add(rdbtnEmpresa);
			rdbtnEmpresa.setBounds(388, 330, 150, 45);
		}
		return rdbtnEmpresa;
	}
	private JPasswordField getPasswordField() {
		if (passwordField == null) {
			passwordField = new JPasswordField();
			passwordField.setBounds(185, 258, 336, 33);
		}
		return passwordField;
	}
	private JLabel getLblSoy() {
		if (lblSoy == null) {
			lblSoy = new JLabel("Soy...");
			lblSoy.setHorizontalAlignment(SwingConstants.CENTER);
			lblSoy.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblSoy.setBounds(33, 335, 168, 33);
		}
		return lblSoy;
	}
	
	private void showVentanaConfirmacionRegistro() {
		confirmacionRegistro = new VentanaConfirmacionRegistro();
		confirmacionRegistro.setLocationRelativeTo(this);
		//confirmacionRegistro.setModalityType(true);
		confirmacionRegistro.setVisible(true);			
	}
}

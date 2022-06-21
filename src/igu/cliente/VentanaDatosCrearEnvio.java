package igu.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.EnviosService;
import business.UsuariosService;
import business.exception.BusinessException;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.UsuarioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaDatosCrearEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblDatosEnvio;
	private JButton btnConfirmarDatosEnvio;
	private JButton btnSalir;
	private JLabel lblNombre;
	private JTextField txtNombre;
	private JLabel lblApellidos;
	private JTextField txtApellidos;
	private JLabel lblDireccion;
	private JTextField txtDireccion;
	private JTextField txtPeso;
	private JLabel lblPeso;
	private VentanaConfirmacionEnvio confirmacionEnvio;


	/**
	 * Create the frame.
	 */
	public VentanaDatosCrearEnvio() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDatosCrearEnvio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 590, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblDatosEnvio());
		contentPane.add(getBtnConfirmarDatosEnvio());
		contentPane.add(getBtnSalir());
		contentPane.add(getLblNombre());
		contentPane.add(getTxtNombre());
		contentPane.add(getLblApellidos());
		contentPane.add(getTxtApellidos());
		contentPane.add(getLblDireccion());
		contentPane.add(getTxtDireccion());
		contentPane.add(getTxtPeso());
		contentPane.add(getLblPeso());
	}
	
	
	
	private JLabel getLblDatosEnvio() {
		if (lblDatosEnvio == null) {
			lblDatosEnvio = new JLabel("Introducir datos del env\u00EDo");
			lblDatosEnvio.setHorizontalAlignment(SwingConstants.CENTER);
			lblDatosEnvio.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblDatosEnvio.setBounds(31, 28, 516, 33);
		}
		return lblDatosEnvio;
	}
	
	private JButton getBtnConfirmarDatosEnvio() {
		if (btnConfirmarDatosEnvio == null) {
			btnConfirmarDatosEnvio = new JButton("Confirmar env\u00EDo");
			btnConfirmarDatosEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!getTxtNombre().getText().isEmpty() && !getTxtApellidos().getText().isEmpty() && !getTxtDireccion().getText().isEmpty() && !getTxtPeso().getText().isEmpty()) {
						//correcto haz cosas			
								
						EnviosService es = BusinessFactory.getEnviosService();
						try {
							
							EnvioDto dto = DtoFactory.newEnvio(loggedUser.id);
							dto.nombreDestinatario = getTxtNombre().getText();
							dto.apellidoDestinatario = getTxtApellidos().getText();
							dto.direccion = getTxtDireccion().getText();
							dto.peso = Integer.parseInt(getTxtPeso().getText());
							
							es.add(dto);
							showVentanaConfirmacionEnvio(dto);						
						} catch (BusinessException ex) {
							ex.printStackTrace();
							JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al crear el envío");
						}
						
					} else {
						JOptionPane.showMessageDialog(rootPane, "Alguno de los campos es incorrecto"); // hacer uno por cada
					}
					
				}
			});
			btnConfirmarDatosEnvio.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnConfirmarDatosEnvio.setBounds(97, 288, 190, 53);
		}
		return btnConfirmarDatosEnvio;
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
			btnSalir.setBounds(317, 288, 190, 53);
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
	private JTextField getTxtApellidos() {
		if (txtApellidos == null) {
			txtApellidos = new JTextField();
			txtApellidos.setColumns(10);
			txtApellidos.setBounds(185, 125, 336, 33);
		}
		return txtApellidos;
	}
	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDireccion.setBounds(23, 168, 168, 33);
		}
		return lblDireccion;
	}
	private JTextField getTxtDireccion() {
		if (txtDireccion == null) {
			txtDireccion = new JTextField();
			txtDireccion.setColumns(10);
			txtDireccion.setBounds(185, 168, 336, 33);
		}
		return txtDireccion;
	}
	private JTextField getTxtPeso() {
		if (txtPeso == null) {
			txtPeso = new JTextField();
			txtPeso.setColumns(10);
			txtPeso.setBounds(185, 211, 336, 33);
		}
		return txtPeso;
	}
	private JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso:");
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPeso.setBounds(23, 211, 168, 33);
		}
		return lblPeso;
	}
	
	private void showVentanaConfirmacionEnvio(EnvioDto dto) {
		confirmacionEnvio = new VentanaConfirmacionEnvio();
		confirmacionEnvio.setLocationRelativeTo(this);
		//confirmacionRegistro.setModalityType(true);
		confirmacionEnvio.setVisible(true);
		confirmacionEnvio.initialize(dto);
	}

	private UsuarioDto loggedUser;
	
	public void initialize(UsuarioDto loggedUser) {
		this.loggedUser = loggedUser;
		
	}
}

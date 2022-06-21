package igu.cliente;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dto.EnvioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmacionEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JLabel lblEnvio;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDireccion;
	private JLabel lblPeso;
	private JLabel lblPrecio;
	private JLabel lblNombreInput;
	private JLabel lblApellidoInput;
	private JLabel lblDireccionInput;
	private JLabel lblPesoInput;
	private JLabel lblPrecioInput;

	/**
	 * Create the frame.
	 */
	public VentanaConfirmacionEnvio() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacionEnvio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 465);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSalir());
		contentPane.add(getLblEnvio());
		contentPane.add(getLblNombre());
		contentPane.add(getLblApellido());
		contentPane.add(getLblDireccion());
		contentPane.add(getLblPeso());
		contentPane.add(getLblPrecio());
		contentPane.add(getLblNombreInput());
		contentPane.add(getLblApellidoInput());
		contentPane.add(getLblDireccionInput());
		contentPane.add(getLblPesoInput());
		contentPane.add(getLblPrecioInput());
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
			btnSalir.setBounds(189, 338, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblEnvio() {
		if (lblEnvio == null) {
			lblEnvio = new JLabel("\u00A1Su env\u00EDo se ha registrado correctamente!");
			lblEnvio.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnvio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEnvio.setBounds(38, 27, 487, 33);
		}
		return lblEnvio;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombre.setBounds(35, 89, 168, 33);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido:");
			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblApellido.setBounds(35, 132, 168, 33);
		}
		return lblApellido;
	}
	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDireccion.setBounds(35, 175, 168, 33);
		}
		return lblDireccion;
	}
	private JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso:");
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPeso.setBounds(35, 218, 168, 33);
		}
		return lblPeso;
	}
	private JLabel getLblPrecio() {
		if (lblPrecio == null) {
			lblPrecio = new JLabel("Precio:");
			lblPrecio.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPrecio.setBounds(35, 261, 168, 33);
		}
		return lblPrecio;
	}
	
	private JLabel getLblNombreInput() {
		if (lblNombreInput == null) {
			lblNombreInput = new JLabel("");
			lblNombreInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombreInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblNombreInput.setBounds(213, 89, 267, 33);
		}
		return lblNombreInput;
	}
	private JLabel getLblApellidoInput() {
		if (lblApellidoInput == null) {
			lblApellidoInput = new JLabel("");
			lblApellidoInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblApellidoInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblApellidoInput.setBounds(213, 132, 267, 33);
		}
		return lblApellidoInput;
	}
	private JLabel getLblDireccionInput() {
		if (lblDireccionInput == null) {
			lblDireccionInput = new JLabel("");
			lblDireccionInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccionInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDireccionInput.setBounds(213, 175, 267, 33);
		}
		return lblDireccionInput;
	}
	private JLabel getLblPesoInput() {
		if (lblPesoInput == null) {
			lblPesoInput = new JLabel("");
			lblPesoInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblPesoInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPesoInput.setBounds(213, 218, 267, 33);
		}
		return lblPesoInput;
	}
	private JLabel getLblPrecioInput() {
		if (lblPrecioInput == null) {
			lblPrecioInput = new JLabel("");
			lblPrecioInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblPrecioInput.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPrecioInput.setBounds(213, 261, 267, 33);
		}
		return lblPrecioInput;
	}
	
	public void initialize(EnvioDto dto) {
		this.getLblNombreInput().setText(dto.nombreDestinatario);
		this.getLblApellidoInput().setText(dto.apellidoDestinatario);
		this.getLblDireccionInput().setText(dto.direccion);
		this.getLblPesoInput().setText(String.valueOf(dto.peso));
		this.getLblPrecioInput().setText(String.valueOf(dto.precio));
		
	}
}

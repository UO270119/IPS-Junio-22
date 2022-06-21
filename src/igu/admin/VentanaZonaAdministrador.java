package igu.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.EnviosService;
import business.exception.BusinessException;
import dto.EnvioDto;
import dto.UsuarioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class VentanaZonaAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblZonaAdministrador;
	private JButton btnListaEnvios;
	private JButton btnSalir;
	private JButton btnAsignarRutas;
	
	private VentanaListaEnviosAdministrador listaAdministrador;
	private VentanaAsignarRutas asignarRutas;


	/**
	 * Create the frame.
	 */
	public VentanaZonaAdministrador() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaZonaAdministrador.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblZonaAdministrador());
		contentPane.add(getBtnListaEnvios());
		contentPane.add(getBtnSalir());
		contentPane.add(getBtnAsignarRutas());
	}
	
	
	
	private JLabel getLblZonaAdministrador() {
		if (lblZonaAdministrador == null) {
			lblZonaAdministrador = new JLabel("Zona de administrador");
			lblZonaAdministrador.setHorizontalAlignment(SwingConstants.CENTER);
			lblZonaAdministrador.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblZonaAdministrador.setBounds(92, 26, 375, 33);
		}
		return lblZonaAdministrador;
	}
	
	private JButton getBtnListaEnvios() {
		if (btnListaEnvios == null) {
			btnListaEnvios = new JButton("Ver lista de env\u00EDos");
			btnListaEnvios.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						EnviosService es = BusinessFactory.getEnviosService();
						List<EnvioDto> envios = es.getAll();
						showVentanaListaEnviosAdministrador(envios);
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnListaEnvios.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnListaEnvios.setBounds(160, 69, 229, 76);
		}
		return btnListaEnvios;
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
	
	
	private JButton getBtnAsignarRutas() { // CUANDO LE DEMOS A ASIGNAR RUTAS, HAREMOS QUE AUTOMÁTICAMENTE TODOS LOS PEDIDOS QUE TENÍAN EL ESTADO "PREPARANDO" PASEN A "CENTRO DE DISTRIBUCIÓN"
		if (btnAsignarRutas == null) {
			btnAsignarRutas = new JButton("Asignar Rutas");
			btnAsignarRutas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						EnviosService es = BusinessFactory.getEnviosService();
						List<EnvioDto> envios = es.getEnviosEnCentroDistribucion();
						showVentanaAsignarRutas(envios);
					} catch (BusinessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnAsignarRutas.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnAsignarRutas.setBounds(160, 155, 229, 76);
		}
		return btnAsignarRutas;
	}
	
	private void showVentanaListaEnviosAdministrador(List<EnvioDto> envios) {
		listaAdministrador = new VentanaListaEnviosAdministrador();
		listaAdministrador.setLocationRelativeTo(this);
		//listaAdministrador.setModalityType(true);
		listaAdministrador.setVisible(true);
		listaAdministrador.initialize(envios);	
	}
	
	private void showVentanaAsignarRutas(List<EnvioDto> envios) {
		asignarRutas = new VentanaAsignarRutas();
		asignarRutas.setLocationRelativeTo(this);
		//asignarRutas.setModalityType(true);
		asignarRutas.setVisible(true);	
		asignarRutas.initialize(envios);
	}



	public void initialize(UsuarioDto dto) {
		// TODO Auto-generated method stub
		
	}
}

package igu.repartidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

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

public class VentanaZonaRepartidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblZonaRepartidor;
	private JButton btnVerEnviosReparto;
	private JButton btnSalir;
	private VentanaListaEnviosRepartidor listaEnviosRepartidor;


	/**
	 * Create the frame.
	 */
	public VentanaZonaRepartidor() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaZonaRepartidor.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblZonaRepartidor());
		contentPane.add(getBtnVerEnviosReparto());
		contentPane.add(getBtnSalir());
	}
	
	
	
	private JLabel getLblZonaRepartidor() {
		if (lblZonaRepartidor == null) {
			lblZonaRepartidor = new JLabel("Zona de repartidor");
			lblZonaRepartidor.setHorizontalAlignment(SwingConstants.CENTER);
			lblZonaRepartidor.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblZonaRepartidor.setBounds(92, 26, 375, 33);
		}
		return lblZonaRepartidor;
	}
	
	private JButton getBtnVerEnviosReparto() {
		if (btnVerEnviosReparto == null) {
			btnVerEnviosReparto = new JButton("Ver env\u00EDos a repartir");
			btnVerEnviosReparto.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showVentanaListaEnviosRepartidor();
				}
			});
			btnVerEnviosReparto.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnVerEnviosReparto.setBounds(163, 116, 229, 76);
		}
		return btnVerEnviosReparto;
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
	
	private void showVentanaListaEnviosRepartidor() {
			listaEnviosRepartidor = new VentanaListaEnviosRepartidor();
			listaEnviosRepartidor.setLocationRelativeTo(this);
			//listaEnviosRepartidor.setModalityType(true);
			listaEnviosRepartidor.setVisible(true);
			listaEnviosRepartidor.initialize(this.usuarioDto);
	}


	
	private UsuarioDto usuarioDto;

	public void initialize(UsuarioDto dto) {
		this.usuarioDto = dto;
	}
}

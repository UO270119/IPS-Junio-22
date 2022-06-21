package igu.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import business.BusinessFactory;
import business.UsuariosService;
import business.exception.BusinessException;
import dto.EnvioDto;
import dto.UsuarioDto;
import igu.util.ReadonlyTableModel;

public class VentanaAsignarRutas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAsignarRutas;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JButton btnAsignarRuta;
	
	private VentanaFormularioRutas formularioRutas;
	private JLabel lblPedidosEnCentro;
	private JTable tableEnviosCentro;
	
	private DefaultTableModel dtm;
	private List<EnvioDto> envios;


	/**
	 * Create the frame.
	 */
	public VentanaAsignarRutas() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAsignarRutas.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 592, 415);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAsignarRutas());
		contentPane.add(getBtnSalir());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnAsignarRuta());
		contentPane.add(getLblPedidosEnCentro());
	}
	
	
	
	private JLabel getLblAsignarRutas() {
		if (lblAsignarRutas == null) {
			lblAsignarRutas = new JLabel("Asignar rutas");
			lblAsignarRutas.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsignarRutas.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblAsignarRutas.setBounds(92, 26, 375, 33);
		}
		return lblAsignarRutas;
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
			btnSalir.setBounds(184, 315, 190, 53);
		}
		return btnSalir;
	}
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(53, 144, 483, 147);
			scrollPane.setViewportView(getTableEnviosCentro());
		}
		return scrollPane;
	}
	private JButton getBtnAsignarRuta() {
		if (btnAsignarRuta == null) {
			btnAsignarRuta = new JButton("Asignar ruta");
			btnAsignarRuta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = getTableEnviosCentro().getSelectedRow();
					if (selectedRow != -1) {
						EnvioDto envio = envios.get(selectedRow);
						showVentanaFormularioRutas(envio);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Debes seleccionar un envío para poder ver sus detalles");
					}
				}
			});
			btnAsignarRuta.setBounds(413, 113, 124, 21);
		}
		return btnAsignarRuta;
	}
	
	private JLabel getLblPedidosEnCentro() {
		if (lblPedidosEnCentro == null) {
			lblPedidosEnCentro = new JLabel("Pedidos en centro de distribuci\u00F3n:");
			lblPedidosEnCentro.setHorizontalAlignment(SwingConstants.CENTER);
			lblPedidosEnCentro.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblPedidosEnCentro.setBounds(20, 69, 516, 33);
		}
		return lblPedidosEnCentro;
	}

	private JTable getTableEnviosCentro() { // hacer una query que recoja todos los envíos con estado "en centro de distribución", ordenar por fecha y hora
		if (tableEnviosCentro == null) {
			tableEnviosCentro = new JTable();
			dtm = new ReadonlyTableModel(
				new Object[][] {
				},
				new String[] {
					"Destinatario", "Fecha y hora"
				}
			);
			tableEnviosCentro.setModel(dtm);
		}
		return tableEnviosCentro;
	}
	

	public void initialize(List<EnvioDto> envios) {
		this.envios = envios;
		
		getTableEnviosCentro();
		
		for (EnvioDto envioDto : envios) {
			String dest = "" + envioDto.apellidoDestinatario + ", " + envioDto.nombreDestinatario;
			
			dtm.addRow(new Object[] {dest, envioDto.fechaEmision});
		}
		
	}
	
	
	
	
	private void showVentanaFormularioRutas(EnvioDto envio) {	
		try {
			UsuariosService us = BusinessFactory.getUsuariosService();
			List<UsuarioDto> repartidores = us.getAllRepartidores();
			
			formularioRutas = new VentanaFormularioRutas();
			formularioRutas.setLocationRelativeTo(this);
			//formularioRutas.setModalityType(true);
			formularioRutas.setVisible(true);
			formularioRutas.initialize(envio, repartidores);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

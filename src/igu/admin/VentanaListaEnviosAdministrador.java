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
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dto.EnvioDto;
import igu.util.ReadonlyTableModel;

public class VentanaListaEnviosAdministrador extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblListaEnvios;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JButton btnDetails;
	
	private VentanaDetallesEnvio detallesEnvio;
	private JTable tableEnvios;
	
	//private DefaultListModel<String> dlm = new DefaultListModel<>();
	private DefaultTableModel dtm;
	private List<EnvioDto> envios;


	/**
	 * Create the frame.
	 */
	public VentanaListaEnviosAdministrador() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaListaEnviosAdministrador.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblListaEnvios());
		contentPane.add(getBtnSalir());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnDetails());
	}
	
	
	
	private JLabel getLblListaEnvios() {
		if (lblListaEnvios == null) {
			lblListaEnvios = new JLabel("Lista de env\u00EDos");
			lblListaEnvios.setHorizontalAlignment(SwingConstants.CENTER);
			lblListaEnvios.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblListaEnvios.setBounds(92, 26, 375, 33);
		}
		return lblListaEnvios;
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
	
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(52, 85, 483, 147);
			scrollPane.setViewportView(getTableEnvios());
		}
		return scrollPane;
	}
	private JButton getBtnDetails() {
		if (btnDetails == null) {
			btnDetails = new JButton("Details");
			
			btnDetails.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = getTableEnvios().getSelectedRow();
					if (selectedRow != -1) {
						EnvioDto envio = envios.get(selectedRow);
						showVentanaDetallesEnvio(envio);
					} else {
						JOptionPane.showMessageDialog(rootPane, "Debes seleccionar un envío para poder ver sus detalles");
					}
				}
			});
			btnDetails.setBounds(447, 54, 89, 21);
		}
		return btnDetails;
	}
	
	private void showVentanaDetallesEnvio(EnvioDto envio) {
		detallesEnvio = new VentanaDetallesEnvio();
		detallesEnvio.setLocationRelativeTo(this);
		//detallesEnvio.setModalityType(true);
		detallesEnvio.setVisible(true);
		detallesEnvio.initialize(envio);
	}
	
	private JTable getTableEnvios() {
		if (tableEnvios == null) {
			tableEnvios = new JTable();
			
			dtm = new ReadonlyTableModel();
			
			tableEnvios.setModel(dtm);
		}
		return tableEnvios;
	}
	

	public void initialize(List<EnvioDto> envios) {
		this.envios = envios;
		
		getTableEnvios();
		
		String[] columnNames = new String[] { "Destinatario", "Fecha y hora", "Estado"};
		dtm.setColumnIdentifiers(columnNames);
		for (EnvioDto envioDto : envios) {
			String dest = "" + envioDto.apellidoDestinatario + ", " + envioDto.nombreDestinatario;
			
			dtm.addRow(new Object[] {dest, envioDto.fechaEmision, envioDto.estado});
		}
		
	}
	

}

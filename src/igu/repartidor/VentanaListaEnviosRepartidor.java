package igu.repartidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dto.EnvioDto;
import dto.EstadoEnvioDto;
import igu.util.JTableButtonRenderer;
import igu.util.ReadonlyTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTable;

public class VentanaListaEnviosRepartidor extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblListaEnvios;
	private JButton btnSalir;
	private JScrollPane scrollPane;
	private JButton btnNewButton;
	
	private DefaultTableModel dtm;
	private JTable tableEnviosReparto;


	/**
	 * Create the frame.
	 */
	public VentanaListaEnviosRepartidor() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaListaEnviosRepartidor.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 367);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblListaEnvios());
		contentPane.add(getBtnSalir());
		contentPane.add(getScrollPane());
		contentPane.add(getBtnNewButton());
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
			scrollPane.setViewportView(getTableEnviosReparto());
		}
		return scrollPane;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("OK");
			btnNewButton.setBounds(479, 54, 57, 21);
		}
		return btnNewButton;
	}
	
	
	private JTable getTableEnviosReparto() { //hacer una query que recoja todos los envíos con estado "EN REPARTO", asociados al DNI del REPARTIDOR ordenar por fecha y hora
		if (tableEnviosReparto == null) {
			tableEnviosReparto = new JTable();
			
			dtm = new ReadonlyTableModel(
				new Object[][] {},
				new String[] {
					"Destinatario", "Fecha y hora", "Marcar Reparto"
				}
			) {
				private static final long serialVersionUID = 1L;
				@Override
				public Class getColumnClass(int column) {
					return getValueAt(0, column).getClass();
				}
			};
			
			JTableButtonRenderer.applyToTable(tableEnviosReparto);
			tableEnviosReparto.setModel(dtm);
		}
		return tableEnviosReparto;
	}

	
	
	public void initialize(List<EnvioDto> envios) {
		
		for (EnvioDto envio : envios) {
			
			String destinatario = "" + envio.apellidoDestinatario + ", " + envio.nombreDestinatario;
			String fecha = envio.fechaEmision.getDate() + "/" + envio.fechaEmision.getMonth() + "/" + envio.fechaEmision.getYear();
			
			JButton btn = new JButton("Marcar");
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JOptionPane.showMessageDialog(rootPane, envio.codigo);
				}
			});
			
			dtm.addRow(new Object[] {destinatario, fecha, btn});
		}
	}
	
}

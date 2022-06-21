package igu.repartidor;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.BusinessFactory;
import business.EnviosService;
import business.RutasService;
import business.exception.BusinessException;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.EstadoEnvioDto;
import dto.RutaDto;
import dto.UsuarioDto;
import igu.util.JTableButtonRenderer;
import igu.util.ReadonlyTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.LinkedList;
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
	private JButton btnMArcarEnvio;
	
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
		contentPane.add(getBtnMArcarEnvio());
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
	private JButton getBtnMArcarEnvio() {
		if (btnMArcarEnvio == null) {
			btnMArcarEnvio = new JButton("Marcar Env\u00EDo");
			btnMArcarEnvio.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int selectedRow = getTableEnviosReparto().getSelectedRow();
					if (selectedRow < 0)
					{
						JOptionPane.showMessageDialog(rootPane, "Seleccione un envío para marcar.");
						return;
					}
					try {
						EnvioDto envio = envios.get(selectedRow);
						int opt = JOptionPane.showConfirmDialog(rootPane, "Envío entregado con éxito?");
						
						if (opt == JOptionPane.OK_OPTION) {
							EnviosService es = BusinessFactory.getEnviosService();
							es.updateEstado(envio, EnvioDto.ESTADO_ENTREGADO);
							
							RutasService rs = BusinessFactory.getRutasService();
							rs.deleteForEnvio(envio.id);
						}
						else if (opt == JOptionPane.NO_OPTION) {
							EnviosService es = BusinessFactory.getEnviosService();
							es.updateEstado(envio, EnvioDto.ESTADO_FALLIDO);
							
							RutasService rs = BusinessFactory.getRutasService();
							rs.deleteForEnvio(envio.id);
						}
						refreshEnvios();	
						
					} catch (BusinessException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al asignar la ruta");
					}
				}
			});
			btnMArcarEnvio.setBounds(445, 54, 91, 21);
		}
		return btnMArcarEnvio;
	}
	
	
	private JTable getTableEnviosReparto() { //hacer una query que recoja todos los envíos con estado "EN REPARTO", asociados al DNI del REPARTIDOR ordenar por fecha y hora
		if (tableEnviosReparto == null) {
			tableEnviosReparto = new JTable();
			
			dtm = new ReadonlyTableModel(new Object[][] {}, new String[] {"Destinatario", "Fecha y hora"}) {
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

	
	private UsuarioDto repartidor;
	private List<EnvioDto> envios;
	
	public void initialize(UsuarioDto repartidor) {
		this.repartidor = repartidor;
		refreshEnvios();
	}
	
	private void refreshEnvios() {
		this.envios = new LinkedList<EnvioDto>();
		
		try {
			EnviosService es = BusinessFactory.getEnviosService();
			this.envios = es.findForRepartidor(this.repartidor.id);
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (int i = dtm.getRowCount()-1; i >= 0; i--)
			dtm.removeRow(i);
		
		for (EnvioDto envio : envios) {
			String destinatario = "" + envio.apellidoDestinatario + ", " + envio.nombreDestinatario;
			String fecha = envio.fechaEmision.getDate() + "/" + envio.fechaEmision.getMonth() + "/" + envio.fechaEmision.getYear();
			dtm.addRow(new Object[] {destinatario, fecha });
		}
	}
	
}

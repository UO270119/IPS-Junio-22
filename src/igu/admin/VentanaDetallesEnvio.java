package igu.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.BusinessFactory;
import business.EnviosService;
import business.EstadosEnvioService;
import business.exception.BusinessException;
import dto.EnvioDto;
import dto.EstadoEnvioDto;
import igu.util.ReadonlyTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;

public class VentanaDetallesEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JLabel lblDetalles;
	private JLabel lblNombre;
	private JLabel lblApellido;
	private JLabel lblDireccion;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JScrollPane scrollPane;
	private JPanel pnEstado;
	private JLabel lblEstados;
	private JCheckBox chckbxCentroDist;
	private JLabel lblPeso;
	private JTable tableEstados;

	// tabla para actualizaciones de estado de envio
	private DefaultTableModel dtm;


	/**
	 * Create the frame.
	 */
	public VentanaDetallesEnvio() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaDetallesEnvio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSalir());
		contentPane.add(getLblDetalles());
		contentPane.add(getLblNombre());
		contentPane.add(getLblApellido());
		contentPane.add(getLblDireccion());
		contentPane.add(getLblFecha());
		contentPane.add(getLblHora());
		contentPane.add(getScrollPane());
		contentPane.add(getLblEstados());
		contentPane.add(getChckbxCentroDist());
		contentPane.add(getLblPeso());
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
			btnSalir.setBounds(80, 528, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblDetalles() {
		if (lblDetalles == null) {
			lblDetalles = new JLabel("Detalles del env\u00EDo");
			lblDetalles.setHorizontalAlignment(SwingConstants.CENTER);
			lblDetalles.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDetalles.setBounds(63, 26, 487, 33);
		}
		return lblDetalles;
	}
	private JLabel getLblNombre() {
		if (lblNombre == null) {
			lblNombre = new JLabel("Nombre:");
			lblNombre.setHorizontalAlignment(SwingConstants.CENTER);
			lblNombre.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblNombre.setBounds(38, 108, 168, 33);
		}
		return lblNombre;
	}
	private JLabel getLblApellido() {
		if (lblApellido == null) {
			lblApellido = new JLabel("Apellido:");
			lblApellido.setHorizontalAlignment(SwingConstants.CENTER);
			lblApellido.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblApellido.setBounds(38, 151, 168, 33);
		}
		return lblApellido;
	}
	private JLabel getLblDireccion() {
		if (lblDireccion == null) {
			lblDireccion = new JLabel("Direcci\u00F3n:");
			lblDireccion.setHorizontalAlignment(SwingConstants.CENTER);
			lblDireccion.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblDireccion.setBounds(38, 194, 168, 33);
		}
		return lblDireccion;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblFecha.setBounds(320, 108, 168, 33);
		}
		return lblFecha;
	}
	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora:");
			lblHora.setHorizontalAlignment(SwingConstants.CENTER);
			lblHora.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblHora.setBounds(320, 151, 168, 33);
		}
		return lblHora;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(80, 360, 483, 147);
			scrollPane.setViewportView(getPnEstado());
		}
		return scrollPane;
	}
	private JPanel getPnEstado() {
		if (pnEstado == null) {
			pnEstado = new JPanel();
			pnEstado.setBackground(Color.WHITE);
			pnEstado.setLayout(new BorderLayout(0, 0));
			pnEstado.add(getTableEstados());
		}
		return pnEstado;
	}
	
	private JTable getTableEstados() {
		if (tableEstados == null) {
			tableEstados = new JTable();
			
			dtm = new ReadonlyTableModel();
			
			tableEstados.setModel(dtm);
		}
		return tableEstados;
	}
	
	private JLabel getLblEstados() {
		if (lblEstados == null) {
			lblEstados = new JLabel("Estados");
			lblEstados.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstados.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEstados.setBounds(38, 317, 168, 33);
		}
		return lblEstados;
	}
	private JCheckBox getChckbxCentroDist() {
		if (chckbxCentroDist == null) {
			chckbxCentroDist = new JCheckBox("Recibido en centro de distribuci\u00F3n");
			chckbxCentroDist.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String newEstado;
					if (chckbxCentroDist.isSelected()) newEstado = EnvioDto.ESTADO_DISTRIBUCION;
					else newEstado = EnvioDto.ESTADO_PREPARANDO;
					
					try {
						EnviosService es = BusinessFactory.getEnviosService();
						es.updateEstado(envio, newEstado);
						refreshEstados();
						chckbxCentroDist.setEnabled(false);
					} catch (BusinessException e1) {
						JOptionPane.showMessageDialog(rootPane, "No se ha podido actualizar el estado del envío.");
					}
				}
			});
			chckbxCentroDist.setFont(new Font("Tahoma", Font.PLAIN, 14));
			chckbxCentroDist.setBounds(319, 528, 244, 39);
		}
		return chckbxCentroDist;
	}
	private JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso:");
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPeso.setBounds(320, 194, 168, 33);
		}
		return lblPeso;
	}

	
	
	private EnvioDto envio;
	
	public void initialize(EnvioDto envio) {
		
		this.envio = envio;
		
		this.getLblNombre().setText("Nombre: " + envio.nombreDestinatario);
		this.getLblApellido().setText("Apellidos: " + envio.apellidoDestinatario);
		this.getLblFecha().setText("Día: " + envio.fechaEmision.getDate() + "/" + envio.fechaEmision.getMonth() + "/" + envio.fechaEmision.getYear()); //asignar una nueva date a dos días después, luego veremos si domingo
		this.getLblHora().setText("Hora: " + envio.fechaEmision.getHours() + ":" + envio.fechaEmision.getMinutes());
		this.getLblPeso().setText("Peso: " + String.valueOf(envio.peso));
		this.getLblDireccion().setText("Dirección: " + envio.direccion);
		//this.getLblPrecio_1().setText("Previo: " + String.valueOf(envio.precio));

		// Sólo se pueden marcar envíos recién recibidos
		this.getChckbxCentroDist().setEnabled(EnvioDto.ESTADO_PREPARANDO.equals(envio.estado));
		this.getChckbxCentroDist().setSelected(EnvioDto.ESTADO_DISTRIBUCION.equals(envio.estado));
		
		refreshEstados();
	}
	
	private void refreshEstados() {

		try {
			EstadosEnvioService es = BusinessFactory.getEstadosEnvioService();
			List<EstadoEnvioDto> estados = es.getForEnvio(envio.id);

			dtm = (DefaultTableModel) getTableEstados().getModel();
			tableEstados.setModel(dtm);
			
			for (int i = dtm.getRowCount()-1; i >= 0; i--)
				dtm.removeRow(i);
			
			String[] columnNames = new String[] { "Fecha", "Antiguo Estado", "Nuevo Estado"};
			dtm.setColumnIdentifiers(columnNames);
			for (EstadoEnvioDto estado : estados) {
				String fecha = String.valueOf(estado.fecha);
				
				dtm.addRow(new Object[] {fecha, estado.oldEstado, estado.newEstado});
			}
			
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

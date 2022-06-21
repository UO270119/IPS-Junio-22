package igu.destinatario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import business.BusinessFactory;
import business.EstadosEnvioService;
import business.exception.BusinessException;
import dto.EnvioDto;
import dto.EstadoEnvioDto;
import igu.util.ReadonlyTableModel;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JList;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import java.awt.BorderLayout;

public class VentanaSeguimientoEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JLabel lblEnvio;
	private JLabel lblDestino;
	private JLabel lblFecha;
	private JLabel lblHora;
	private JScrollPane scrollPane;
	private JPanel pnEstado;
	private JLabel lblEstados;
	private JLabel lblPeso;
	private JLabel lblCodigo;
	private JLabel lblFechaInput;
	private JLabel lblHoraInput;
	private JLabel lblPesoInput;
	private JLabel lblDestinoInput;

	private JTable tableEstados;

	// tabla para actualizaciones de estado de envio
	private DefaultTableModel dtm;

	/**
	 * Create the frame.
	 */
	public VentanaSeguimientoEnvio() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaSeguimientoEnvio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 665, 628);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSalir());
		contentPane.add(getLblEnvio());
		contentPane.add(getLblDestino());
		contentPane.add(getLblFecha());
		contentPane.add(getLblHora());
		contentPane.add(getScrollPane());
		contentPane.add(getLblEstados());
		contentPane.add(getLblPeso());
		contentPane.add(getLblCodigo());
		contentPane.add(getLblFechaInput());
		contentPane.add(getLblHoraInput());
		contentPane.add(getLblPesoInput());
		contentPane.add(getLblDestinoInput());
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
			btnSalir.setBounds(222, 528, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblEnvio() {
		if (lblEnvio == null) {
			lblEnvio = new JLabel("Su env\u00EDo:");
			lblEnvio.setHorizontalAlignment(SwingConstants.CENTER);
			lblEnvio.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEnvio.setBounds(125, 25, 123, 33);
		}
		return lblEnvio;
	}
	private JLabel getLblDestino() {
		if (lblDestino == null) {
			lblDestino = new JLabel("Destino:");
			lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblDestino.setBounds(90, 213, 168, 33);
		}
		return lblDestino;
	}
	private JLabel getLblFecha() {
		if (lblFecha == null) {
			lblFecha = new JLabel("Fecha:");
			lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
			lblFecha.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblFecha.setBounds(80, 84, 168, 33);
		}
		return lblFecha;
	}
	private JLabel getLblHora() {
		if (lblHora == null) {
			lblHora = new JLabel("Hora:");
			lblHora.setHorizontalAlignment(SwingConstants.CENTER);
			lblHora.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblHora.setBounds(80, 127, 168, 33);
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
	
	private JLabel getLblEstados() {
		if (lblEstados == null) {
			lblEstados = new JLabel("Estados");
			lblEstados.setHorizontalAlignment(SwingConstants.CENTER);
			lblEstados.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblEstados.setBounds(38, 317, 168, 33);
		}
		return lblEstados;
	}
	private JLabel getLblPeso() {
		if (lblPeso == null) {
			lblPeso = new JLabel("Peso:");
			lblPeso.setHorizontalAlignment(SwingConstants.CENTER);
			lblPeso.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPeso.setBounds(80, 170, 168, 33);
		}
		return lblPeso;
	}
	
	private JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("");
			lblCodigo.setBackground(Color.WHITE);
			lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCodigo.setBounds(267, 25, 250, 33);
		}
		return lblCodigo;
	}
	private JLabel getLblFechaInput() {
		if (lblFechaInput == null) {
			lblFechaInput = new JLabel("");
			lblFechaInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblFechaInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblFechaInput.setBounds(267, 84, 250, 33);
		}
		return lblFechaInput;
	}
	private JLabel getLblHoraInput() {
		if (lblHoraInput == null) {
			lblHoraInput = new JLabel("");
			lblHoraInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblHoraInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblHoraInput.setBounds(267, 127, 250, 33);
		}
		return lblHoraInput;
	}
	private JLabel getLblPesoInput() {
		if (lblPesoInput == null) {
			lblPesoInput = new JLabel("");
			lblPesoInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblPesoInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblPesoInput.setBounds(267, 170, 250, 33);
		}
		return lblPesoInput;
	}
	private JLabel getLblDestinoInput() {
		if (lblDestinoInput == null) {
			lblDestinoInput = new JLabel("");
			lblDestinoInput.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestinoInput.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblDestinoInput.setBounds(320, 213, 152, 33);
		}
		return lblDestinoInput;
	}

	
	private JTable getTableEstados() {
		if (tableEstados == null) {
			tableEstados = new JTable();
			
			dtm = new ReadonlyTableModel();
			
			tableEstados.setModel(dtm);
		}
		return tableEstados;
	}
	
	
	public void initialize(EnvioDto envio) {
		
		this.getLblCodigo().setText(envio.codigo);
		this.getLblFechaInput().setText(envio.fechaEmision.getDate() + "/" + envio.fechaEmision.getMonth() + "/" + envio.fechaEmision.getYear()); //asignar una nueva date a dos días después, luego veremos si domingo
		this.getLblHoraInput().setText(envio.fechaEmision.getHours() + ":" + envio.fechaEmision.getMinutes());
		this.getLblPesoInput().setText(String.valueOf(envio.peso));
		//this.getLblOrigenInput().setText(envio.direccion);
		this.getLblDestinoInput().setText(envio.direccion);
		//this.getLblPrecioInput().setText(String.valueOf(envio.precio));
		//this.getLblEstado().setText("Estado: " + envio.estado);
		
		try {
			EstadosEnvioService es = BusinessFactory.getEstadosEnvioService();
			List<EstadoEnvioDto> estados = es.getForEnvio(envio.id);

			getTableEstados();
			
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

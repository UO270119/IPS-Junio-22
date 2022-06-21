package igu.destinatario;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.EnviosService;
import business.exception.BusinessException;
import dto.EnvioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaCodigoEnvio extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblCodigoEnvio;
	private JButton btnEntrar;
	private JButton btnSalir;
	private JLabel lblCodigo;
	private JTextField txtCodigo;
	private VentanaSeguimientoEnvio seguimientoEnvio;


	/**
	 * Create the frame.
	 */
	public VentanaCodigoEnvio() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaCodigoEnvio.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 594, 291);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblCodigoEnvio());
		contentPane.add(getBtnEntrar());
		contentPane.add(getBtnSalir());
		contentPane.add(getLblCodigo());
		contentPane.add(getTxtCodigo());
	}
	
	
	
	private JLabel getLblCodigoEnvio() {
		if (lblCodigoEnvio == null) {
			lblCodigoEnvio = new JLabel("Introduzca c\u00F3digo de env\u00EDo");
			lblCodigoEnvio.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodigoEnvio.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblCodigoEnvio.setBounds(115, 27, 375, 33);
		}
		return lblCodigoEnvio;
	}
	
	private JButton getBtnEntrar() {
		if (btnEntrar == null) {
			btnEntrar = new JButton("Entrar");
			btnEntrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						String codigo = getTxtCodigo().getText();
						EnviosService es = BusinessFactory.getEnviosService();
						EnvioDto envio = es.findByCodigo(codigo);
						
						VentanaSeguimientoEnvio ventana = showVentanaSeguimientoEnvio();
						ventana.initialize(envio); // creo que habría que meterlo en el show para que funcionase correctamente
					}
					catch (BusinessException ex) {
						System.err.println(ex);
						JOptionPane.showMessageDialog(rootPane, "El código de envío no existe");
					}
				}
			});
			btnEntrar.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnEntrar.setBounds(84, 184, 190, 53);
		}
		return btnEntrar;
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
			btnSalir.setBounds(312, 184, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblCodigo() {
		if (lblCodigo == null) {
			lblCodigo = new JLabel("C\u00F3digo:");
			lblCodigo.setHorizontalAlignment(SwingConstants.CENTER);
			lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblCodigo.setBounds(22, 105, 168, 33);
		}
		return lblCodigo;
	}
	private JTextField getTxtCodigo() {
		if (txtCodigo == null) {
			txtCodigo = new JTextField();
			txtCodigo.setColumns(10);
			txtCodigo.setBounds(184, 109, 336, 33);
		}
		return txtCodigo;
	}
	
	private VentanaSeguimientoEnvio showVentanaSeguimientoEnvio() {
		seguimientoEnvio = new VentanaSeguimientoEnvio();
		seguimientoEnvio.setLocationRelativeTo(this);
		//seguimientoEnvio.setModalityType(true);
		seguimientoEnvio.setVisible(true);		
		return seguimientoEnvio;
	}
}

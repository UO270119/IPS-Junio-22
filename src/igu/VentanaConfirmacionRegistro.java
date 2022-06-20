package igu;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaConfirmacionRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton btnSalir;
	private JLabel lblRegistroCorrecto;
	/**
	 * Create the frame.
	 */
	public VentanaConfirmacionRegistro() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaConfirmacionRegistro.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 215);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getBtnSalir());
		contentPane.add(getLblRegistroCorrecto());
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
			btnSalir.setBounds(184, 101, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblRegistroCorrecto() {
		if (lblRegistroCorrecto == null) {
			lblRegistroCorrecto = new JLabel("\u00A1Su registro se ha realizado correctamente!");
			lblRegistroCorrecto.setHorizontalAlignment(SwingConstants.CENTER);
			lblRegistroCorrecto.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblRegistroCorrecto.setBounds(38, 38, 487, 33);
		}
		return lblRegistroCorrecto;
	}
}

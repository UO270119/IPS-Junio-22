package igu.admin;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import business.BusinessFactory;
import business.EnviosService;
import business.RutasService;
import business.exception.BusinessException;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.RutaDto;
import dto.UsuarioDto;

import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class VentanaFormularioRutas extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel lblAsignarRuta;
	private JButton btnConfirmarRuta;
	private JButton btnSalir;
	private JLabel lblDNI;
	private JComboBox<String> cbDNIRepartidor;
	private JLabel lblDestino;
	private JLabel lblOficina;
	private VentanaConfirmaciónRuta confirmacionRuta;
	private JComboBox<String> cbDestino;
	private JLabel lblAlmacen;
	private JComboBox<String> cbOficinaAlmacen;


	/**
	 * Create the frame.
	 */
	public VentanaFormularioRutas() {
		setTitle("Sistema de transporte de paquetes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaFormularioRutas.class.getResource("/res/709806.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 591, 457);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.add(getLblAsignarRuta());
		contentPane.add(getBtnConfirmarRuta());
		contentPane.add(getBtnSalir());
		contentPane.add(getLblDNI());
		contentPane.add(getCbDNIRepartidor());
		contentPane.add(getLblDestino());
		contentPane.add(getLblOficina());
		contentPane.add(getCbDestino());
		contentPane.add(getLblAlmacen());
		contentPane.add(getCbOficinaAlmacen());
	}
	
	
	
	private JLabel getLblAsignarRuta() {
		if (lblAsignarRuta == null) {
			lblAsignarRuta = new JLabel("Asignar rutas");
			lblAsignarRuta.setHorizontalAlignment(SwingConstants.CENTER);
			lblAsignarRuta.setFont(new Font("Tahoma", Font.BOLD, 20));
			lblAsignarRuta.setBounds(31, 28, 516, 33);
		}
		return lblAsignarRuta;
	}
	
	private JButton getBtnConfirmarRuta() {
		if (btnConfirmarRuta == null) {
			btnConfirmarRuta = new JButton("Confirmar ruta");
			btnConfirmarRuta.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					int indiceRepartidor = getCbDNIRepartidor().getSelectedIndex();
					if (indiceRepartidor < 0)
					{
						JOptionPane.showMessageDialog(rootPane, "Seleccione un repartidor.");
						return;
					}
					try {
						RutaDto dto = DtoFactory.newRuta();
						dto.idEnvio = envio.id;
						dto.idRepartidor = repartidores.get(indiceRepartidor).id;

						RutasService rs = BusinessFactory.getRutasService();
						rs.add(dto);
						showVentanaConfirmacionRuta();					
					} catch (BusinessException ex) {
						ex.printStackTrace();
						JOptionPane.showMessageDialog(rootPane, "Ha ocurrido un error al asignar la ruta");
					}
				}
			});
			btnConfirmarRuta.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnConfirmarRuta.setBounds(96, 357, 190, 53);
		}
		return btnConfirmarRuta;
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
			btnSalir.setBounds(316, 357, 190, 53);
		}
		return btnSalir;
	}
	private JLabel getLblDNI() {
		if (lblDNI == null) {
			lblDNI = new JLabel("DNI:");
			lblDNI.setHorizontalAlignment(SwingConstants.CENTER);
			lblDNI.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDNI.setBounds(23, 82, 168, 33);
		}
		return lblDNI;
	}
	private JComboBox<String> getCbDNIRepartidor() {
		if (cbDNIRepartidor == null) {
			cbDNIRepartidor = new JComboBox<String>();
			cbDNIRepartidor.setBounds(185, 82, 336, 33);
			cbDNIRepartidor.setEditable(true);
		}
		return cbDNIRepartidor;
	}
	private JLabel getLblDestino() {
		if (lblDestino == null) {
			lblDestino = new JLabel("Destino:");
			lblDestino.setHorizontalAlignment(SwingConstants.CENTER);
			lblDestino.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblDestino.setBounds(23, 135, 168, 33);
		}
		return lblDestino;
	}
	private JLabel getLblOficina() {
		if (lblOficina == null) {
			lblOficina = new JLabel("Seleccionar oficina");
			lblOficina.setHorizontalAlignment(SwingConstants.CENTER);
			lblOficina.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblOficina.setBounds(10, 178, 168, 33);
		}
		return lblOficina;
	}
	
	private void showVentanaConfirmacionRuta() {
		confirmacionRuta = new VentanaConfirmaciónRuta();
		confirmacionRuta.setLocationRelativeTo(this);
		//confirmacionRuta.setModalityType(true);
		confirmacionRuta.setVisible(true);			
	}
	private JComboBox<String> getCbDestino() {
		if (cbDestino == null) {
			cbDestino = new JComboBox<String>();
			cbDestino.setModel(new DefaultComboBoxModel<String>(new String[] {"Domicilio", "Oficina", "Almac\u00E9n"}));
			cbDestino.setEditable(true);
			cbDestino.setBounds(185, 135, 336, 33);
		}
		return cbDestino;
	}
	private JLabel getLblAlmacen() {
		if (lblAlmacen == null) {
			lblAlmacen = new JLabel("o almac\u00E9n");
			lblAlmacen.setHorizontalAlignment(SwingConstants.CENTER);
			lblAlmacen.setFont(new Font("Tahoma", Font.PLAIN, 20));
			lblAlmacen.setBounds(10, 207, 168, 33);
		}
		return lblAlmacen;
	}
	private JComboBox<String> getCbOficinaAlmacen() { //if domicilio se desactiva, 1 almacén por provincia, 1 oficina por capital de provincia y si no 2
		if (cbOficinaAlmacen == null) {
			cbOficinaAlmacen = new JComboBox<String>();
			cbOficinaAlmacen.setModel(new DefaultComboBoxModel<String>(new String[] {
					"Andalucía", "Aragón", 
					"Islas Baleares", "Islas Canarias", 
					"Cantabria", "Castilla-La Mancha", "Castilla y León", "Cataluña", "Ceuta", 
					"Comunidad de Madrid", "Comunidad Foral de Navarra", "Comunidad Valenciana", 
					"Extremadura", "Galicia", "Melilla", 
					"País Vasco", "Principado de Asturias", 
					"Región de Murcia", "La Rioja", 
					"--------",
					"A Coruña", "Albacete", "Alicante", "Almería", "Ávila", 
					"Badajoz", "Barcelona", "Bilbao", "Burgos", 
					"Castellón de la Plana", "Ciudad Real", "Cuenca", "Cáceres", "Cádiz", "Ceuta", "Córdoba", 
					"Girona", "Granada", "Guadalajara", 
					"Huelva", "Huesca", 
					"Jaén", 
					"Las Palmas de Gran Canaria", "León", "Logroño", "Lugo", "Lleida", 
					"Madrid", "Melilla", "Murcia", "Málaga", 
					"Ourense", "Oviedo", 
					"Palencia", "Palma de Mallorca", "Pamplona", "Pontevedra", 
					"Salamanca", "San Sebastián", "Santa Cruz de Tenerife", "Santander", "Segovia", "Sevilla", "Soria", 
					"Tarragona", "Teruel", "Toledo", 
					"Valencia", "Valladolid", "Vitoria", 
					"Zamora", "Zaragoza"}));
			cbOficinaAlmacen.setEditable(true);
			cbOficinaAlmacen.setBounds(185, 188, 336, 33);
		}
		return cbOficinaAlmacen;
	}
	
	
	private EnvioDto envio;
	private List<UsuarioDto> repartidores;
	
	public void initialize(EnvioDto envio, List<UsuarioDto> repartidores) {
		this.envio = envio;
		this.repartidores = repartidores;
		
		String[] dnis = new String[repartidores.size()];
		
		int index = 0;
		for(UsuarioDto repartidor : repartidores) {
			dnis[index++] = repartidor.dni;
		}
		
		getCbDNIRepartidor().setModel(new DefaultComboBoxModel<String>(dnis));
	}
}

package dto;

import java.util.Date;

public class EnvioDto {

	public final static String ESTADO_PREPARANDO = "PREPARANDO";
	
	/*package*/ EnvioDto() {
		
	}
	
	public Long id;

	public String codigo;
	
	public Long idEmisor;
	
	public Date fechaEmision;

	public String estado;
	
	public String nombreDestinatario;
	
	public String apellidoDestinatario;
	
	public String direccion;
	
	public double peso;
	
	public double precio;
}

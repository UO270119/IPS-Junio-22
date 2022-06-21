package dto;

import java.util.Date;

public class EnvioDto {

	public final static String ESTADO_PREPARANDO = "PREPARANDO";
	public final static String ESTADO_DISTRIBUCION = "CENTRO_DISTRIBUCION";
	public final static String ESTADO_REPARTO = "REPARTO";
	public final static String ESTADO_ENTREGADO = "ENTREGADO";
	public final static String ESTADO_FALLIDO = "FALLIDO";
	
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

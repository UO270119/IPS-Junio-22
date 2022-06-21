package dto;

import java.util.Date;

public class EstadoEnvioDto {

	/*package*/ EstadoEnvioDto() {
		
	}
	
	public Long id;
	
	public Long idEnvio;
	
	public Date fecha;

	public String oldEstado;
	
	public String newEstado;
}

package dto;

public class UsuarioDto {
	
	public final static String TIPO_ADMIN = "ADMINISTRADOR";
	public final static String TIPO_REPARTIDOR = "REPARTIDOR";
	public final static String TIPO_CLIENTE = "CLIENTE";
	
	/*package*/ UsuarioDto() {
		
	}
	
	public Long id;

	public String nombre;
	
	public String apellido;
	
	public String dni;

	public String email;
	
	public String contrasena;
	
	public String tipo;

}

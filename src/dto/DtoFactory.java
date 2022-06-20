package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import business.BusinessFactory;
import business.EnviosService;
import business.exception.BusinessException;
import dto.util.PrimaryKeyGenerator;

public class DtoFactory {

	public static EnvioDto newEnvio(Long idEmisor) throws BusinessException {
		EnvioDto envio = new EnvioDto();
		envio.id = PrimaryKeyGenerator.newEnvioPK();
		envio.fechaEmision = new Date(); // TODO this very might be wrong
		envio.estado = "PROCESADO";
		envio.idEmisor = idEmisor;
		return envio;
	}

	public static EnvioDto getEnvio(Long id) throws BusinessException {
		EnviosService ss = BusinessFactory.getEnviosService();
		EnvioDto envio = ss.find(id);
		return envio;
	}

	public static EnvioDto getEnvio(ResultSet rs) throws SQLException {
		EnvioDto dto = new EnvioDto();
		dto.id = rs.getLong("ID");
		dto.idEmisor = rs.getLong("ID_EMISOR");
		dto.estado = rs.getString("ESTADO");
		
		Timestamp ts = rs.getTimestamp("FECHA_EMISION");
		dto.fechaEmision = Date.from(ts.toInstant());
		
		return dto;
	}
	
	
	
	
	
	
	

	public static UsuarioDto newUsuarioCliente() {
		UsuarioDto usuario = new UsuarioDto();
		usuario.id = PrimaryKeyGenerator.newUsuarioPK();
		usuario.tipo = UsuarioDto.TIPO_CLIENTE;
		return usuario;
	}

	public static UsuarioDto getUsuario(ResultSet rs) throws SQLException {
		UsuarioDto dto = new UsuarioDto();
		dto.id = rs.getLong("ID");
		dto.nombre = rs.getString("NOMBRE");
		dto.apellido = rs.getString("APELLIDO");
		dto.email = rs.getString("EMAIL");
		dto.dni = rs.getString("DNI");
		dto.contrasena = rs.getString("CONTRASENA");
		dto.tipo = rs.getString("TIPO");
		return dto;
	}
}

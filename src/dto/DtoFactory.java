package dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

import business.exception.BusinessException;
import dto.util.PrimaryKeyGenerator;

public class DtoFactory {

	public static EnvioDto newEnvio(Long idEmisor) throws BusinessException {
		EnvioDto envio = new EnvioDto();
		envio.id = PrimaryKeyGenerator.newEnvioPK();
		envio.fechaEmision = new Date();
		envio.estado = EnvioDto.ESTADO_PREPARANDO;
		envio.idEmisor = idEmisor;
		UUID uuid = UUID.randomUUID();
		envio.codigo = uuid.toString().split("-")[0];
		return envio;
	}
	


	public static EnvioDto getEnvio(ResultSet rs) throws SQLException {
		EnvioDto dto = new EnvioDto();
		dto.id = rs.getLong("ID");
		dto.codigo = rs.getString("CODIGO");
		dto.idEmisor = rs.getLong("ID_EMISOR");
		dto.estado = rs.getString("ESTADO");
		dto.nombreDestinatario = rs.getString("NOMBRE_DESTINATARIO");
		dto.apellidoDestinatario = rs.getString("APELLIDOS_DESTINATARIO");
		dto.peso = rs.getDouble("PESO");
		dto.precio = rs.getDouble("PRECIO");
		
		Timestamp ts = rs.getTimestamp("FECHA_EMISION");
		if (ts == null) {
			System.err.println("No se ha encontrado fecha para envío con id " + dto.id);
			dto.fechaEmision = new Date();
		}
		else dto.fechaEmision = Date.from(ts.toInstant());
		
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

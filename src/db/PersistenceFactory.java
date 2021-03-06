package db;

import java.sql.Connection;

import db.impl.EnviosGatewayImpl;
import db.impl.EstadosEnvioGatewayImpl;
import db.impl.RutasGatewayImpl;
import db.impl.UsuariosGatewayImpl;

public class PersistenceFactory {
	
	public static EnviosGateway getEnviosGateway(Connection con) {
		EnviosGateway eg = new EnviosGatewayImpl();
		eg.setConnection(con);
		return eg;
	}
	
	public static EstadosEnvioGateway getEstadosEnvioGateway(Connection con) {
		EstadosEnvioGateway eg = new EstadosEnvioGatewayImpl();
		eg.setConnection(con);
		return eg;
	}

	public static UsuariosGateway getUsuariosGateway(Connection con) {
		UsuariosGateway eg = new UsuariosGatewayImpl();
		eg.setConnection(con);
		return eg;
	}

	public static RutasGateway getRutasGateway(Connection con) {
		RutasGateway rg = new RutasGatewayImpl();
		rg.setConnection(con);
		return rg;
	}

}

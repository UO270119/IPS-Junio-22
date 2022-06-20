package db;

import java.sql.Connection;

import db.impl.EnviosGatewayImpl;

public class PersistenceFactory {
	
	public static EnviosGateway getEnviosGateway(Connection con) {
		EnviosGateway eg = new EnviosGatewayImpl();
		eg.setConnection(con);
		return eg;
	}

}

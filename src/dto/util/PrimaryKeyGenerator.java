package dto.util;

import business.BusinessFactory;
import business.EnviosService;

public class PrimaryKeyGenerator {

	public static long newEnvioPK() {
		EnviosService es = BusinessFactory.getEnviosService();
		return es.count() + 1;
	}
}

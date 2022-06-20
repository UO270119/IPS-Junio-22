package business;

import business.impl.EnviosServiceImpl;

public class BusinessFactory {

	public static EnviosService getEnviosService() {
		return new EnviosServiceImpl();
	}

}

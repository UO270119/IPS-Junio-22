package business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import business.EnviosService;
import business.exception.BusinessException;
import db.EnviosGateway;
import db.PersistenceFactory;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.EnvioDto;

public class EnviosServiceImpl implements EnviosService {

	@Override
	public void add(EnvioDto envio) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.save(envio);
			System.out.println("Env�o: " + envio.id + "a�adido correctamente");
		} catch (SQLException | PersistenceException e) {
			throw new BusinessException(e);
		}
	}

	@Override
	public EnvioDto findByCodigo(String codigoEnvio) throws BusinessException {
		EnvioDto envio = null;
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			envio = eg.findByCodigo(codigoEnvio);
		} catch (SQLException e) {
			throw new BusinessException("Problems occured creating a connection to the DB. Check everything is well connected.");
		} catch (PersistenceException e) {
			throw new BusinessException(e);
		}
		return envio;
	}

	@Override
	public void update(EnvioDto envio) {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.save(envio);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(EnvioDto idEnvio) {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.delete(idEnvio);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<EnvioDto> getAll() throws BusinessException {
		List<EnvioDto> result = new LinkedList<EnvioDto>();
		try (Connection con = Jdbc.getConnection()) {
			for (EnvioDto dto : PersistenceFactory.getEnviosGateway(con).listAll())
				result.add(dto);
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public int count() {
		int count = 0;
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			count = eg.count();
		} catch (SQLException | PersistenceException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 *  -	Seg�n destino:
			- Misma provincia: tarifa fija de 4 euros.
			- En diferentes provincias: tarifa fija de 6 euros.
			
		-	Seg�n peso:
			- Paquete peque�o (menor 1kg): tarifa fija de dos euros
			- Paquete mediano (1-5): tarifa fija de cuatro euros
			- Paquete grande (> 5kg): tarifa variable, tendr� un incremento de 1 euro por cada kilo (5kg = +1 euro, 6kg = +2 euros...)
			
		-	Seg�n tipo de recogida:
			
			-	Recogida en oficina o almac�n: +1 euro
			-	Recogida en domicilio: +2,50
			
			El c�mputo total ser� de tarifa por destino + tarifa por peso + recargo seg�n recogida. 
	 */
	@Override
	public double calculatePrice(String provinciaOrigen, String provinciaDestino, double peso, String recogida) {
		
		int basePrice = 0;
		
		if(provinciaOrigen.equals(provinciaDestino)) {
			basePrice += 4;
		} else {
			basePrice += 6;
		}
		
		if(peso < 1) {
			basePrice += 2;
		} else if (peso < 5) {
			basePrice += 4;
		} else { // mayor que 5
			 double kilosExtra = peso - 5;
			 int eurosPorKiloExtra = (int) kilosExtra; // trunca al int m�s cercano
			 basePrice += 4; // Cuota base
			 basePrice += eurosPorKiloExtra; // Penalizaci�n por kilos de m�s
		}
		
		if (recogida.equals("Oficina") || recogida.equals("Almac�n")) {
			basePrice += 1;
		} else {
			basePrice += 2.50;
		}
		
		return basePrice;
	}

}

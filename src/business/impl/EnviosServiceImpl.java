package business.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import business.BusinessFactory;
import business.EnviosService;
import business.EstadosEnvioService;
import business.exception.BusinessException;
import db.EnviosGateway;
import db.PersistenceFactory;
import db.exception.PersistenceException;
import db.util.Jdbc;
import dto.DtoFactory;
import dto.EnvioDto;
import dto.EstadoEnvioDto;

public class EnviosServiceImpl implements EnviosService {

	@Override
	public void add(EnvioDto envio) throws BusinessException {
		try (Connection con = Jdbc.getConnection()) {
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.save(envio);
			System.out.println("Envío: " + envio.id + " añadido correctamente");
			
			EstadoEnvioDto estado = DtoFactory.newEstadoEnvio();
			estado.idEnvio = envio.id;
			estado.oldEstado = "-";
			estado.newEstado = envio.estado;
			EstadosEnvioService es = BusinessFactory.getEstadosEnvioService();
			es.add(estado);
			
		} catch (SQLException | PersistenceException | BusinessException e) {
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
	public void updateEstado(EnvioDto envio, String newEstado) {
		try (Connection con = Jdbc.getConnection()) {
			
			String oldEstado = envio.estado;
			envio.estado = newEstado;
			
			EnviosGateway eg = PersistenceFactory.getEnviosGateway(con);
			eg.updateEstado(envio);
			
			EstadoEnvioDto estado = DtoFactory.newEstadoEnvio();
			estado.idEnvio = envio.id;
			estado.oldEstado = oldEstado;
			estado.newEstado = newEstado;
			EstadosEnvioService es = BusinessFactory.getEstadosEnvioService();
			es.add(estado);
			
		} catch (SQLException | PersistenceException | BusinessException e) {
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
			List<EnvioDto> envios = PersistenceFactory.getEnviosGateway(con).listAll();
			for (EnvioDto dto : envios)
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
	 *  -	Según destino:
			- Misma provincia: tarifa fija de 4 euros.
			- En diferentes provincias: tarifa fija de 6 euros.
			
		-	Según peso:
			- Paquete pequeño (menor 1kg): tarifa fija de dos euros
			- Paquete mediano (1-5): tarifa fija de cuatro euros
			- Paquete grande (> 5kg): tarifa variable, tendrá un incremento de 1 euro por cada kilo (5kg = +1 euro, 6kg = +2 euros...)
			
		-	Según tipo de recogida:
			
			-	Recogida en oficina o almacén: +1 euro
			-	Recogida en domicilio: +2,50
			
			El cómputo total será de tarifa por destino + tarifa por peso + recargo según recogida. 
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
			 int eurosPorKiloExtra = (int) kilosExtra; // trunca al int más cercano
			 basePrice += 4; // Cuota base
			 basePrice += eurosPorKiloExtra; // Penalización por kilos de más
		}
		
		if (recogida.equals("Oficina") || recogida.equals("Almacén")) {
			basePrice += 1;
		} else {
			basePrice += 2.50;
		}
		
		return basePrice;
	}

}

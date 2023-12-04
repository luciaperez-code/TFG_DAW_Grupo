package com.edix.apirest.cinema.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.Projection;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.ProjectionRepository;
import com.edix.apirest.cinema.repository.ScreenRepository;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository prepo;
	
	@Autowired
	private ScreenRepository srepo;
	
	// Lista de todos los Projections
	@Override
	public List<Projection> findAllProjections() {
		return prepo.findAll();
	}

	// Buscar un Projection por su ID
	@Override
	public Projection findProjectionById(int idProjection) {
		return prepo.findById(idProjection).orElse(null);
	}
	
	// Ordenar de Projections
	@Override
	public List<Projection> OrderByPriceAsc() {
		return prepo.orderByPriceAsc();
	}

	// Ordenación de Projections
	@Override
	public List<Projection> OrderByPriceDesc() {
		return prepo.orderByPriceDesc();
	}

	// Borrar un Projection
	@Override
	public int deleteProjection(int idProjection) {
		int filasBorradas = 0;
		try {
			prepo.deleteById(idProjection);
			filasBorradas=1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasBorradas;
	}

	// Insertar un Projection
	@Override
	public int insertarProjection(Projection projection) {
		int filasInsertadas = 0;
		
		if (projection.getScreen() != null) {
			
			Screen screen = srepo.findById(projection.getScreen().getIdScreen()).orElse(null);
			
			int numberNormalSeats = Integer.parseInt(screen.getNormalSeats());
			String[] normalOccupiedSeatsArray = new String[numberNormalSeats];
			Arrays.fill(normalOccupiedSeatsArray, "0");
			projection.setOccupiedNormalSeats(Arrays.toString(normalOccupiedSeatsArray));
			System.out.println(Arrays.toString(normalOccupiedSeatsArray));
			
			int numberSpecialSeats = Integer.parseInt(screen.getSpecialSeats());
			String[] specialOccupiedSeatsArray = new String[numberSpecialSeats];
			Arrays.fill(specialOccupiedSeatsArray, "0");
			projection.setOccupiedSpecialSeats(Arrays.toString(specialOccupiedSeatsArray));
			System.out.println(Arrays.toString(specialOccupiedSeatsArray));
		}
		
		try {
			prepo.save(projection);
			filasInsertadas = 1;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return filasInsertadas;
	}
	
	// Modificar un Projection
	@Override
	public int modifyProjection(Projection projection) {
			
		int filasModificadas = 0;
		Projection prod = null;
		try {
			prod = prepo.findById(projection.getIdProjection()).orElse(null);
			prod = projection;
			prepo.save(prod);
			filasModificadas = 1;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return filasModificadas;
	}

	@Override
	public double findPrecioByNombre(String nombre) {
		return prepo.findPriceByName(nombre);
	}

	@Override
	public List<Projection> buscador(String nombre) {
		return prepo.buscadorNombre(nombre);
	}


	@Override
	public int bookProjection(int idProjection, String[] normalSeats, String[] specialSeats) {

        Projection projection = prepo.findById(idProjection).orElse(null);
        if (projection == null) 
        	return 0;

        String updatedNormalSeats = updateReservedSeats(projection.getOccupiedNormalSeats(), normalSeats);
        if (updatedNormalSeats != null) {
        	projection.setOccupiedNormalSeats(updatedNormalSeats);
        }
        
        String updatedSpecialSeats = updateReservedSeats(projection.getOccupiedSpecialSeats(), specialSeats);
        if (updatedSpecialSeats != null) {
        	projection.setOccupiedSpecialSeats(updatedSpecialSeats);
        }

        prepo.save(projection);
        return 1;
	}
	
    private String updateReservedSeats(String currentOccupiedSeats, String[] newReservedSeats) {
 
    	// Veo si newReservedSeats es nulo o vacío
        if (newReservedSeats == null || newReservedSeats.length == 0) {
            return currentOccupiedSeats;
        }

        if (newReservedSeats[0] == null || newReservedSeats[0].isEmpty()) {
            return currentOccupiedSeats;
        }

        String[] reservedSeatsArray = newReservedSeats[0].replaceAll("[\\[\\],\\s]", "").split("");
        if (reservedSeatsArray.length == 0) {
            return currentOccupiedSeats;
        }

     // Convierto currentOccupiedSeats a un array de Strings
        String[] currentSeatsArray = currentOccupiedSeats.replaceAll("[\\[\\],\\s]", "").split("");

        // Aseguro que reservedSeatsArray.length == currentSeatsArray.length
        if (reservedSeatsArray.length != currentSeatsArray.length) {
            return currentOccupiedSeats;
        }

        // Añado los asientos reservados a la cadena de asientos ocupados
        for (int i = 0; i < reservedSeatsArray.length && i < currentSeatsArray.length; i++) {
            if ("1".equals(reservedSeatsArray[i])) {
                currentSeatsArray[i] = "1";
            }
        }
        
        System.out.println("reservedSeatsArray, nueva reserva -> " + Arrays.toString(reservedSeatsArray));
        System.out.println("currentSeatsArray, previa reserva -> " + Arrays.toString(currentSeatsArray));
        
        return Arrays.toString(currentSeatsArray);
    }
    
}

package com.edix.apirest.cinema.service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edix.apirest.cinema.entities.JSONResponse;
import com.edix.apirest.cinema.entities.Projection;
import com.edix.apirest.cinema.entities.Screen;
import com.edix.apirest.cinema.repository.ProjectionRepository;
import com.edix.apirest.cinema.repository.ScreenRepository;
import com.edix.apirest.cinema.utils.Utils;

@Service
public class ProjectionServiceImpl implements ProjectionService{

	@Autowired
	private ProjectionRepository prepo;
	
	@Autowired
	private ScreenRepository srepo;
	
	@Override
	public JSONResponse findAllProjections() {
		JSONResponse response = new JSONResponse();
		List<Projection> allProjections = prepo.findAll();
		if (allProjections != null) {
			Utils.createJSONResponseOk(response, allProjections);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay proyecciones :(");
		}
		return response;
	}

	@Override
	public JSONResponse findProjectionById(int idProjection) {
		JSONResponse response = new JSONResponse();
		Projection projectionById = prepo.findById(idProjection).orElse(null);
		if (projectionById != null) {
			Utils.createJSONResponseOk(response, projectionById);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay proyecciones con este ID :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse findProjectionByFilm(int idFilm) {
		JSONResponse response = new JSONResponse();
		List<Projection> allProjectionsByFilm = prepo.findProjectionByFilm(idFilm);
		if (allProjectionsByFilm != null) {
			Utils.createJSONResponseOk(response, allProjectionsByFilm);
		}else {
			Utils.createJSONResponseFailed(response, 404, "Esta película no existe o no tiene proyecciones :(");
		}
		return response;
	}

	@Override
	public JSONResponse getProjectionByDate(Date date) {
		JSONResponse response = new JSONResponse();
		List<Projection> allProjectionsByDate = prepo.findProjectionByDate(date);
		if (allProjectionsByDate != null) {
			Utils.createJSONResponseOk(response, allProjectionsByDate);
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay proyecciones para esta fecha :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse insertarProjection(Projection projection) {		
		JSONResponse response = new JSONResponse();
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
		}else {
			Utils.createJSONResponseFailed(response, 404, "Screen no encontrada");
		}
		
		Utils.createJSONResponseOk(response, prepo.save(projection));

		return response;
	}
	
	@Override
	public JSONResponse deleteProjection(int idProjection) {
		JSONResponse response = new JSONResponse();
		Projection projectionToDelete = prepo.findById(idProjection).orElse(null);
		if (projectionToDelete != null) {
			prepo.delete(projectionToDelete);
			Utils.createJSONResponseOk(response, "Proyección borrada con éxito");
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay proyecciones con este ID :(");
		}
		return response;
	}
	
	@Override
	public JSONResponse modifyProjection(Projection projection, int idProjection) {
		JSONResponse response = new JSONResponse();
		if (prepo.findById(idProjection) != null) {
			projection.setIdProjection(idProjection);
			Utils.createJSONResponseOk(response, prepo.save(projection));
		}else {
			Utils.createJSONResponseFailed(response, 404, "No hay proyecciones con ese id :(");
		}
		return response;
	}

	@Override
	public JSONResponse bookProjection(int idProjection, String normalSeats, String specialSeats) {
		JSONResponse response = new JSONResponse();

        Projection projection = prepo.findById(idProjection).orElse(null);
        if (projection == null) {
        	Utils.createJSONResponseFailed(response, 404, "Projection no encontrada con ese ID");
        	return response;
        }

        String updatedNormalSeats = updateReservedSeats(projection.getOccupiedNormalSeats(), normalSeats);
        if (updatedNormalSeats != null) {
        	projection.setOccupiedNormalSeats(updatedNormalSeats);
        }
        
        String updatedSpecialSeats = updateReservedSeats(projection.getOccupiedSpecialSeats(), specialSeats);
        if (updatedSpecialSeats != null) {
        	projection.setOccupiedSpecialSeats(updatedSpecialSeats);
        }
        prepo.save(projection);
        Utils.createJSONResponseOk(response, prepo.save(projection));
        return response;
	}
	
    private String updateReservedSeats(String currentOccupiedSeats, String newReservedSeats) {
 
        if (newReservedSeats == null || newReservedSeats.isEmpty()) {
            return currentOccupiedSeats;
        }
        if (newReservedSeats == null || newReservedSeats.isEmpty()) {
            return currentOccupiedSeats;
        }

        String[] reservedSeatsArray = newReservedSeats.replaceAll("[\\[\\],\\s]", "").split("");
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

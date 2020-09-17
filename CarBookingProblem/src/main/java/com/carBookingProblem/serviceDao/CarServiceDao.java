package com.carBookingProblem.serviceDao;

import java.util.List;

import com.carBookingProblem.DTO.LocationDTO;
import com.carBookingProblem.DTO.ResistrationDto;
import com.carBookingProblem.baseResponce.BaseResponce;
import com.carBookingProblem.models.Rides;

public interface CarServiceDao {

	BaseResponce registration(ResistrationDto resistrationDto);

	BaseResponce updateLocation(LocationDTO locationDTO);

	BaseResponce updateAvailablity(String email, boolean isAvailable);

	List<ResistrationDto> getAllAvailableDrivers(int lat, int lon);

	BaseResponce bookCab(String emailId, ResistrationDto resistrationDto);

	List<Rides> getAllRides(String emailId);

	BaseResponce endRide(String emailId);

}

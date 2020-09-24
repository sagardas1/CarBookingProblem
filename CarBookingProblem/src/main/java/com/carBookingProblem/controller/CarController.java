package com.carBookingProblem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.carBookingProblem.DTO.LocationDTO;
import com.carBookingProblem.DTO.ResistrationDto;
import com.carBookingProblem.baseResponce.BaseResponce;
import com.carBookingProblem.models.Rides;
import com.carBookingProblem.serviceDao.CarServiceDao;

@RestController
@RequestMapping("/carcontroller")
public class CarController {

	@Autowired
	private CarServiceDao carServiceDao;

	@PostMapping(value="/registration",headers = "Accept=application/json")
	public BaseResponce registration(@RequestBody ResistrationDto resistrationDto) {
		BaseResponce baseResponce = null;
		try {
			baseResponce = carServiceDao.registration(resistrationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}
	
	@PutMapping(value="/updatelocation",headers = "Accept=application/json")
	public BaseResponce updateLocation(@RequestBody LocationDTO locationDTO) {
		BaseResponce baseResponce = null;
		try {
			baseResponce = carServiceDao.updateLocation(locationDTO);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}
	
	@PutMapping(value="/updateavailablity",headers = "Accept=application/json")
	public BaseResponce updateAvailablity(@RequestParam String email,@RequestParam boolean isAvailable) {
		BaseResponce baseResponce = null;
		try {
			baseResponce = carServiceDao.updateAvailablity(email,isAvailable);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}
	
	
	@GetMapping(value="/getallavailabledrivers",headers = "Accept=application/json")
	public List<ResistrationDto> getAllAvailableDrivers(@RequestParam int lat,@RequestParam int lon) {
		List<ResistrationDto> resistrationDtosList = null;
		try {
			resistrationDtosList = carServiceDao.getAllAvailableDrivers(lat,lon);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resistrationDtosList;
	}
	
	
	@GetMapping(value="/bookcab/{emailId}",headers = "Accept=application/json")
	public BaseResponce bookCab(@PathVariable ("emailId") String emailId,@RequestBody ResistrationDto resistrationDto) {
		BaseResponce baseResponce=null;
		
		try {
			baseResponce = carServiceDao.bookCab(emailId,resistrationDto);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}
	
	
	@GetMapping(value="/getAllRides/{emailId}",headers = "Accept=application/json")
	public List<Rides> getAllRides(@PathVariable ("emailId") String emailId) {
		List<Rides> list=null;
		
		try {
			list = carServiceDao.getAllRides(emailId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
	@GetMapping(value="/endRide/{emailId}",headers = "Accept=application/json")
	public BaseResponce endRide(@PathVariable ("emailId") String emailId) {
		BaseResponce baseResponce=null;
		
		try {
			baseResponce = carServiceDao.endRide(emailId);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

}

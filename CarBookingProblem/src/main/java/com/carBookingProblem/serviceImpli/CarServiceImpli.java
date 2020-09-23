package com.carBookingProblem.serviceImpli;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carBookingProblem.DTO.LocationDTO;
import com.carBookingProblem.DTO.ResistrationDto;
import com.carBookingProblem.baseResponce.BaseResponce;
import com.carBookingProblem.carRepo.CarRepository;
import com.carBookingProblem.carRepo.LocationRepository;
import com.carBookingProblem.carRepo.RidesRepos;
import com.carBookingProblem.constants.Constants;
import com.carBookingProblem.models.LocationDetails;
import com.carBookingProblem.models.Registration;
import com.carBookingProblem.models.Rides;
import com.carBookingProblem.responceConstant.ResponceConstant;
import com.carBookingProblem.serviceDao.CarServiceDao;
import com.carBookingProblem.utils.Utills;

@Service
public class CarServiceImpli implements CarServiceDao {

	@Autowired
	private CarRepository carRepository;
	@Autowired
	private LocationRepository locationRepository;
	@Autowired
	private RidesRepos ridesRepos;

	@Override
	public BaseResponce registration(ResistrationDto resistrationDto) {
		BaseResponce baseResponce = null;
		Registration entity = null;
		try {
			Registration registration = carRepository.getRegistration(resistrationDto.getEmailId(),
					resistrationDto.getContactNum());
			if (registration == null) {
if(resistrationDto.getUserType()==Constants.USERTYPE_DRIVERS) {
	resistrationDto.setSeatfillUp(0);
}
				entity = new Registration();
				entity.setContactNum(resistrationDto.getContactNum());
				entity.setEmailId(resistrationDto.getEmailId());
				entity.setfName(resistrationDto.getfName());
				entity.setlName(resistrationDto.getlName());
				entity.setUserType(resistrationDto.getUserType());
				entity.setSeatfillUp(0);
				carRepository.save(entity);
				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.SUCCESS_CREATED);
				baseResponce.setStatusMessage(ResponceConstant.SUCESS_MESSAGE);
			} else {
				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.STATUS404);
				baseResponce.setStatusMessage(ResponceConstant.FAIL_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

	@Override
	public BaseResponce updateLocation(LocationDTO locationDTO) {
		BaseResponce baseResponce = null;
		try {
			LocationDetails entity = new LocationDetails();
			entity.setEmail(locationDTO.getEmail());
			entity.setLat(locationDTO.getX());
			entity.setLon(locationDTO.getY());
			locationRepository.save(entity);
			// locationRepository.updateLocation(locationDTO.getX(), locationDTO.getEmail(),
			// locationDTO.getY());
			// if (i > 0) {

			baseResponce = new BaseResponce();
			baseResponce.setStatusCode(ResponceConstant.SUCCESS_CREATED);
			baseResponce.setStatusMessage(ResponceConstant.SUCESS_MESSAGE);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

	@Override
	public BaseResponce updateAvailablity(String email, boolean isAvailable) {
		BaseResponce baseResponce = null;
		try {

			int update = carRepository.updateAvailablity(email, isAvailable);
			if (update > 0) {

				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.SUCCESS_CREATED);
				baseResponce.setStatusMessage(ResponceConstant.SUCESS_MESSAGE);
			} else {
				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.STATUS404);
				baseResponce.setStatusMessage(ResponceConstant.FAIL_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

	@Override
	public List<ResistrationDto> getAllAvailableDrivers(int lat, int lon) {
		List<Registration> resistrationDtos = null;

		List<ResistrationDto> finalList = null;
		ResistrationDto obj = null;
		try {
			List<LocationDetails> detailsList = new ArrayList<LocationDetails>();
//int size=5;
			resistrationDtos = carRepository.getAllAvailableDrivers();
			for (Registration r : resistrationDtos) {
				LocationDetails detail = locationRepository.getDirverLocation(r.getEmailId());
				detailsList.add(detail);
			}

//	List<String> emails= detailsList.stream()
//			.filter(p -> Utills.getDistanceInkm(p.getLat(),p.getLon(),lat,lon)<size)
//			.map(str -> str.getEmail())
//			.collect(Collectors.toList());
//			

			if (resistrationDtos != null && !resistrationDtos.isEmpty()) {
				finalList = new ArrayList<ResistrationDto>();
				for (Registration r : resistrationDtos) {
					LocationDetails details = locationRepository.getDirverLocation(r.getEmailId());
					if (Utills.getDistanceInkm(details.getLat(), details.getLon(), lat, lon) <= 5) {
						obj = new ResistrationDto();
						obj.setContactNum(r.getContactNum());
						obj.setAvailable(true);
						obj.setEmailId(r.getEmailId());
						obj.setfName(r.getfName());
						obj.setlName(r.getlName());
						finalList.add(obj);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return finalList;
	}

	@Override
	public BaseResponce bookCab(String emailId, ResistrationDto resistrationDto) {
		BaseResponce baseResponce = null;
		try {
			Registration registration = carRepository.getRegistration(emailId);
			if (registration != null) {
				Rides ride = new Rides();
				ride.setDriverEmail(resistrationDto.getEmailId());
				ride.setUserEmail(emailId);
				ride.setDate(System.currentTimeMillis());
				ridesRepos.save(ride);

				int update = carRepository.updateAvailablity(resistrationDto.getEmailId(), false);
				if (update > 0) {

					baseResponce = new BaseResponce();
					baseResponce.setStatusCode(ResponceConstant.SUCCESS_CREATED);
					baseResponce.setStatusMessage(ResponceConstant.SUCESS_MESSAGE);
				} else {
					baseResponce = new BaseResponce();
					baseResponce.setStatusCode(ResponceConstant.STATUS404);
					baseResponce.setStatusMessage(ResponceConstant.FAIL_MESSAGE);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

	@Override
	public List<Rides> getAllRides(String emailId) {
		List<Rides> list = ridesRepos.getAllRides(emailId);

		return list;
	}

	@Override
	public BaseResponce endRide(String emailId) {
		BaseResponce baseResponce = null;
		try {
			int update = carRepository.updateAvailablity(emailId, true);
			if (update > 0) {

				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.SUCCESS_CREATED);
				baseResponce.setStatusMessage(ResponceConstant.SUCESS_MESSAGE);
			} else {
				baseResponce = new BaseResponce();
				baseResponce.setStatusCode(ResponceConstant.STATUS404);
				baseResponce.setStatusMessage(ResponceConstant.FAIL_MESSAGE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return baseResponce;
	}

}

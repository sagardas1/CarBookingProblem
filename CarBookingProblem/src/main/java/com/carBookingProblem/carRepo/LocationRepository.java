package com.carBookingProblem.carRepo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carBookingProblem.models.LocationDetails;

@Repository
@Transactional
public interface LocationRepository extends CrudRepository<LocationDetails, Long> {

	@Transactional
	@Query(value = "UPDATE LocationDetails set x= :x,y=:y where email = :email", nativeQuery = true)
	int updateLocation(double x, String email, double y);

	@Transactional
	@Query(value = "select * from LocationDetails where email=:email", nativeQuery = true)
	LocationDetails getDirverLocation(String email);

}

package com.carBookingProblem.carRepo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carBookingProblem.models.Registration;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Registration, Long> {
	

	@Transactional
	@Query(value = "select * from Registration where emailId = :emailId AND contactNum = :contactNum", nativeQuery = true)
	Registration getRegistration(String emailId, String contactNum);

	@Transactional
	@Query(value = "UPDATE Registration set isAvailable=:isAvailable where email = :email AND userType=2", nativeQuery = true)
	int updateAvailablity(String email, boolean isAvailable);

	@Transactional
	@Query(value = "select * from Registration where userType = :2 AND isAvailable; = :true", nativeQuery = true)
	List<Registration> getAllAvailableDrivers();

	@Transactional
	@Query(value = "select * from Registration where emailId = :emailId", nativeQuery = true)
	Registration getRegistration(String emailId);

}

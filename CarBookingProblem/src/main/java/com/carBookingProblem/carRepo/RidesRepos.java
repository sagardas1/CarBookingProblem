package com.carBookingProblem.carRepo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.carBookingProblem.models.Rides;

@Repository
@Transactional
public interface RidesRepos extends CrudRepository<Rides, Long> {

	@Transactional
	@Query(value = "select * from Rides where userEmail = :userEmail", nativeQuery = true)
	List<Rides> getAllRides(String userEmail);

}

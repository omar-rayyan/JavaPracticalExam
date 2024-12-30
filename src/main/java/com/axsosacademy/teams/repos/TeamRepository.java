package com.axsosacademy.teams.repos;

import com.axsosacademy.teams.models.Team;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeamRepository extends CrudRepository<Team, Long> {
	Optional<Team> findByName(String name);
	List<Team> findAll();
}
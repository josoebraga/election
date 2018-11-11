package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ElectionRepository extends CrudRepository<Election, Long> {
    List<ElectionOutput> findByYear(Integer year);
}

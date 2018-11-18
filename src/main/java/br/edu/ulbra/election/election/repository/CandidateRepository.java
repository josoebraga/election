package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Candidate;
import org.springframework.data.repository.CrudRepository;

public interface CandidateRepository extends CrudRepository<Candidate, Long> {
    Candidate findFirstById(Long Id);
    Long findFirstByNumberElection(Long numberElection);
}

package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.VoteOutput;
import org.springframework.data.repository.CrudRepository;

public interface ResultRepository extends CrudRepository<Vote, Long> {

    Long countAllByCandidateId(Long candidateId);
    Long countAllByElection_Id(Long electionId);

}

package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    Vote findFirstByElectionId(Long electionId);
    Long countAllByElection_Id(Long electionId);
    Long countAllByVoterId (Long voterId);
    Long countAllByVoterIdAndElection_Id (Long voterId, Long electionId);
}

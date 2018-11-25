package br.edu.ulbra.election.election.repository;

import br.edu.ulbra.election.election.model.Vote;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepository extends CrudRepository<Vote, Long> {
    Vote findFirstByElectionId(Long electionId);
    Long countAllByElection_Id(Long electionId);
    Long countAllByVoterId (Long voterId);
    Long countAllByVoterIdAndElection_Id (Long voterId, Long electionId);
    Long countAllByCandidateIdAndElection_Id (Long candidateId, Long electionId);
    Long countAllByBlankVoteAndElection_Id(Boolean blanck, Long electionId);
    Long countAllByNullVoteAndElection_Id(Boolean nulls, Long electionId);
}

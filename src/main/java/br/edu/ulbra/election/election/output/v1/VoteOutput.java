package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Vote Output Information")
public class VoteOutput {

    @ApiModelProperty(example = "1", notes = "Candidate Unique Identification")
    private Long candidateId;
    @ApiModelProperty(example = "1", notes = "Election Unique Identification")
    private Long electionId;
    @ApiModelProperty(example = "1", notes = "Voter Unique Identification")
    private Long voterId;

    public Long getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Long getElectionId() {
        return electionId;
    }

    public void setElectionId(Long electionId) {
        this.electionId = electionId;
    }

    public Long getVoterId() {
        return voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }


}

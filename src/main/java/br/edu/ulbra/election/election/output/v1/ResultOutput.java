package br.edu.ulbra.election.election.output.v1;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

@ApiModel(description = "Result Output Information")
public class ResultOutput {

    @ApiModelProperty(notes = "Election Information")
    private ElectionOutput election;
    @ApiModelProperty(notes = "Candidate Vote Information")
    private List<ElectionCandidateResultOutput> candidates;
    @ApiModelProperty(example = "10000", notes = "Total Votes")
    private Long totalVotes;
    @ApiModelProperty(example = "12", notes = "Total of Blank Votes")
    private Long blankVotes;
    @ApiModelProperty(example = "11", notes = "Total of Null Votes")
    private Long nullVotes;

    public ElectionOutput getElection() {
        return this.election;
    }

    public void setElection(ElectionOutput election) {
        this.election = election;
    }

    public List<ElectionCandidateResultOutput> getCandidates() {
        return this.candidates;
    }

    public void setCandidates(List<ElectionCandidateResultOutput> candidates) {
        this.candidates = candidates;
    }

    public Long getTotalVotes() {
        return this.totalVotes;
    }

    public void setTotalVotes(Long totalVotes) {
        this.totalVotes = totalVotes;
    }

    public Long getBlankVotes() {
        return this.blankVotes;
    }

    public void setBlankVotes(Long blankVotes) {
        this.blankVotes = blankVotes;
    }

    public Long getNullVotes() {
        return this.nullVotes;
    }

    public void setNullVotes(Long nullVotes) {
        this.nullVotes = nullVotes;
    }
}

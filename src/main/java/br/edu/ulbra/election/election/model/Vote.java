package br.edu.ulbra.election.election.model;

import javax.persistence.*;

@Entity
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private Long voterId;

    @Column (nullable = true)
    private Long candidateId;

    @Column (nullable = false)
    private Boolean blankVote;

    @Column (nullable = false)
    private Boolean nullVote;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Election election;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVoterId() {
        return this.voterId;
    }

    public void setVoterId(Long voterId) {
        this.voterId = voterId;
    }

    public Long getCandidateId() {
        return this.candidateId;
    }

    public void setCandidateId(Long candidateId) {
        this.candidateId = candidateId;
    }

    public Boolean getBlankVote() {
        return this.blankVote;
    }

    public void setBlankVote(Boolean blankVote) {
        this.blankVote = blankVote;
    }

    public Boolean getNullVote() {
        return this.nullVote;
    }

    public void setNullVote(Boolean nullVote) {
        this.nullVote = nullVote;
    }

    public Election getElection() {
        return this.election;
    }

    public void setElection(Election election) {
        this.election = election;
    }
}

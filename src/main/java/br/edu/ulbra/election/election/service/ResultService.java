package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.client.CandidateClientService2;
import br.edu.ulbra.election.election.client.CandidateClientService3;
import br.edu.ulbra.election.election.client.ElectionClientService;
import br.edu.ulbra.election.election.client.VoterClientService;
import br.edu.ulbra.election.election.client.PartyClientService;
import br.edu.ulbra.election.election.enums.StateCodes;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.ElectionInput;
import br.edu.ulbra.election.election.model.Candidate;
import br.edu.ulbra.election.election.output.v1.GenericOutput;
import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.output.v1.VoteOutput;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.*;
import br.edu.ulbra.election.election.repository.CandidateRepository;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.ResultRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import org.apache.commons.lang.StringUtils;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultService {

    private final ElectionRepository electionRepository;
    private final VoteRepository voteRepository;
    private final ResultRepository resultRepository;
    private final CandidateRepository candidateRepository;
    private final CandidateClientService candidateClientService;
    private final CandidateClientService3 candidateClientService3;
    private final ElectionClientService electionClientService;
    private final PartyClientService partyClientService;

    private final ModelMapper modelMapper;

    private static final String MESSAGE_INVALID_ID = "Invalid id";
    private static final String MESSAGE_ELECTION_NOT_FOUND = "Election not found";
    private static final String MESSAGE_CANDIDATE_NOT_FOUND = "Candidate not found";


    @Autowired
    public ResultService(ModelMapper modelMapper, ElectionRepository electionRepository, VoteRepository voteRepository, ResultRepository resultRepository, CandidateRepository candidateRepository, CandidateClientService candidateClientService, ElectionClientService electionClientService, PartyClientService partyClientService, CandidateClientService3 candidateClientService3){
        this.modelMapper = modelMapper;
        this.electionRepository = electionRepository;
        this.voteRepository = voteRepository;
        this.resultRepository = resultRepository;
        this.candidateRepository = candidateRepository;
        this.candidateClientService = candidateClientService;
        this.electionClientService = electionClientService;
        this.partyClientService = partyClientService;
        this.candidateClientService3 = candidateClientService3;
    }


    public ElectionOutput getElectionById(Long electionId){
        if (electionId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        return modelMapper.map(election, ElectionOutput.class);
    }



    public ResultOutput ResultOutput(Long electionId) {

        if (electionId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException(MESSAGE_ELECTION_NOT_FOUND);
        }

        ElectionOutput electionOutput;
        electionOutput = this.getElectionById(electionId);
        ResultOutput resultOutput =  modelMapper.map(electionOutput, ResultOutput.class);
        resultOutput.setElection(electionOutput);

        List<ElectionCandidateResultOutput> electionCandidateResultOutput = candidateClientService3.getAll();
        resultOutput.setTotalVotes(voteRepository.countAllByElection_Id(electionId));
        resultOutput.setBlankVotes(voteRepository.countAllByBlankVoteAndElection_Id(true, electionId));
        resultOutput.setNullVotes(voteRepository.countAllByNullVoteAndElection_Id(true, electionId));
        resultOutput.setCandidates(electionCandidateResultOutput);

        return modelMapper.map(resultOutput, ResultOutput.class);

    }

    public ElectionCandidateResultOutput getResultByCandidate(Long candidateId) {

        if (candidateId == null){
            throw new GenericOutputException(MESSAGE_INVALID_ID);
        }

        CandidateOutput candidateOutput;
        candidateOutput = candidateClientService.getById(candidateId);
        if (candidateOutput.getId() == null){
            throw new GenericOutputException(MESSAGE_CANDIDATE_NOT_FOUND);
        }

        Long totalVotes = voteRepository.countAllByCandidateIdAndElection_Id(candidateId, candidateOutput.getElectionOutputId());
        ElectionCandidateResultOutput electionCandidateResultOutput =  modelMapper.map(candidateOutput, ElectionCandidateResultOutput.class);

        electionCandidateResultOutput.setCandidate(candidateOutput);
        electionCandidateResultOutput.setTotalVotes(totalVotes);

        return electionCandidateResultOutput;

    }


///v1/result/candidate/{candidateId}
///v1/result/election/{electionId}

}

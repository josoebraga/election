package br.edu.ulbra.election.election.service;

import br.edu.ulbra.election.election.client.CandidateClientService;
import br.edu.ulbra.election.election.exception.GenericOutputException;
import br.edu.ulbra.election.election.input.v1.VoteInput;
import br.edu.ulbra.election.election.model.Election;
import br.edu.ulbra.election.election.model.Vote;
import br.edu.ulbra.election.election.output.v1.*;
import br.edu.ulbra.election.election.repository.CandidateRepository;
import br.edu.ulbra.election.election.repository.ElectionRepository;
import br.edu.ulbra.election.election.repository.VoteRepository;
import br.edu.ulbra.election.election.client.VoterClientService;
import br.edu.ulbra.election.election.client.ElectionClientService;
import feign.FeignException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteService {

    private final VoteRepository voteRepository;

    private final ElectionRepository electionRepository;

    private final VoterClientService voterClientService;
    private final ElectionClientService electionClientService;
    private final CandidateClientService candidateClientService;

    private final CandidateRepository candidateRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public VoteService(VoteRepository voteRepository, ElectionRepository electionRepository, VoterClientService voterClientService, ElectionClientService electionClientService, CandidateClientService candidateClientService, CandidateRepository candidateRepository, ModelMapper modelMapper){
        this.voteRepository = voteRepository;
        this.electionRepository = electionRepository;
        this.voterClientService = voterClientService;
        this.electionClientService = electionClientService;
        this.candidateClientService = candidateClientService;

        this.candidateRepository = candidateRepository;

        this.modelMapper = modelMapper;
    }

    public GenericOutput electionVote(VoteInput voteInput){

        Election election = validateInput(voteInput.getElectionId(), voteInput);
        Vote vote = new Vote();
        vote.setElection(election);
        vote.setVoterId(voteInput.getVoterId());

        if (voteInput.getCandidateNumber() == null){
            vote.setBlankVote(true);
        } else {
            vote.setBlankVote(false);
        }

        // TODO: Validate null candidate
        ////////////
        try{

            CandidateOutput candidateOutput;

                candidateOutput = candidateClientService.getById(voteInput.getCandidateId());

            /* Se o eleitor enviar um número de candidato inexistente, o voto deve ser considerado nulo.  */
            if (candidateOutput.getNumberElection() == null){
                vote.setNullVote(true);
                //throw new GenericOutputException("Null vote");
            } else {
                vote.setNullVote(false);
            }

            /* Se ele não enviar o número, o voto deve ser considerado branco. */
            if (voteInput.getCandidateNumber() == null){
                vote.setBlankVote(true);
                //throw new GenericOutputException("Blank vote");
            } else {
                vote.setBlankVote(false);
            }

        } catch (FeignException e){
            if (e.status() == 500) {
                throw new GenericOutputException("Invalid Candidate");
            }
        }
        ////////////

/***************************************/

        voteRepository.save(vote);

        return new GenericOutput("OK");



        //return modelMapper.map(electionVote., GenericOutput.class);

    }

    public GenericOutput multiple(List<VoteInput> voteInputList){
        for (VoteInput voteInput : voteInputList){
            this.electionVote(voteInput);
        }
        return new GenericOutput("OK");
    }

    public Election validateInput(Long electionId, VoteInput voteInput){
        Election election = electionRepository.findById(electionId).orElse(null);
        if (election == null){
            throw new GenericOutputException("Invalid Election");
        }
        if (voteInput.getVoterId() == null){
            throw new GenericOutputException("Invalid Voter");
        }
        // TODO: Validate voter

/***************************************/

//Um voto deve estar vinculado a um eleitor eleição válido.
        try{
            VoterOutput voterOutput = voterClientService.getById(voteInput.getVoterId());
            if (voterOutput.getId() == null){
                throw new GenericOutputException("Voter doesn't exists");
            }
        } catch (FeignException e){
            if (e.status() == 500) {
                throw new GenericOutputException("Invalid Voter");
            }
        }

//        Um voto deve estar vinculado a uma eleição válida.
        try{
            ElectionOutput electionOutput = electionClientService.getById(voteInput.getElectionId());
            if (electionOutput.getId() == null){
                throw new GenericOutputException("Election doesn't exists");
            }
        } catch (FeignException e){
            if (e.status() == 500) {
                throw new GenericOutputException("Invalid Election");
            }
        }

/***************************************/

        return election;
    }
}

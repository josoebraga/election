package br.edu.ulbra.election.election.api.v1;

import br.edu.ulbra.election.election.output.v1.ElectionOutput;
import br.edu.ulbra.election.election.service.ElectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/v1/result")
public class ResultApi {

    private final ElectionService electionService;

    @Autowired
    public ResultApi(ElectionService electionService) {
        this.electionService = electionService;
    }


    @GetMapping("/election/{electionId}")
    public ElectionOutput getResultByElection(@PathVariable Long electionId){
        //return new ResultOutput();
        return electionService.getById(electionId);
    }

    @GetMapping("/candidate/{candidateId}")
    public ElectionOutput getResultByCandidate(@PathVariable Long candidateId){
        //return new ElectionCandidateResultOutput();
        return electionService.getById(candidateId);
    }

}

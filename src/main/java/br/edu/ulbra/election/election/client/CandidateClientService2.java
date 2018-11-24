package br.edu.ulbra.election.election.client;

import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CandidateClientService2 {

    private final CandidateClientService2.CandidateClient candidateClient;

    @Autowired
    public CandidateClientService2(CandidateClientService2.CandidateClient candidateClient){
        this.candidateClient = candidateClient;
    }

    public List<Long> getById(Long id){
        return (List<Long>) this.candidateClient.getById(id);
    }

    @FeignClient(value="candidate-service", url="${url.candidate-service}")
    private interface CandidateClient {

        @GetMapping("/v1/candidate/getFirstByElectionId/{electionId}")
        List getById(@PathVariable(name = "electionId") Long electionId);
    }
}

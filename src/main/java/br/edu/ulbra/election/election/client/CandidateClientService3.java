package br.edu.ulbra.election.election.client;

import br.edu.ulbra.election.election.output.v1.CandidateOutput;
import br.edu.ulbra.election.election.output.v1.ElectionCandidateResultOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class CandidateClientService3 {

    private final CandidateClientService3.CandidateClient candidateClient;

    @Autowired
    public CandidateClientService3(CandidateClientService3.CandidateClient candidateClient){
        this.candidateClient = candidateClient;
    }

    public List<ElectionCandidateResultOutput> getAll(){
        return (List<ElectionCandidateResultOutput>) this.candidateClient.getAll();
    }

    @FeignClient(value="candidate-service", url="${url.candidate-service}")
    private interface CandidateClient {

        @GetMapping("/v1/candidate/")
        List getAll();
    }
}

package br.edu.ulbra.election.election.client;


import br.edu.ulbra.election.election.output.v1.VoterOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class LoginClientService {

    private final LoginClient loginClient;

    @Autowired
    public LoginClientService(LoginClient loginClient){
        this.loginClient = loginClient;
    }

    public VoterOutput checkTokenByVoterId(Long voterId){
        return this.loginClient.checkTokenByVoterId(voterId);
    }

    @FeignClient(value="voter-service", url="${url.voter-service}")
    private interface LoginClient {

        @GetMapping("/checkByVoterId/{voterId}")
        VoterOutput checkTokenByVoterId(@PathVariable(value = "voterId") Long voterId);
    }
}

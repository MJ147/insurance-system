package com.mja.controller.worker;

import com.mja.model.client.Client;
import com.mja.model.policy.CarInsurancePolicy;
import com.mja.model.policy.HomeInsurancePolicy;
import com.mja.model.policy.InsurancePolicy;
import com.mja.model.policy.LifeInsurancePolicy;
import com.mja.model.worker.InsuranceAgent;
import com.mja.service.client.ClientService;
import com.mja.service.policy.CarInsurancePolicyService;
import com.mja.service.policy.HomeInsurancePolicyService;
import com.mja.service.policy.InsurancePolicyService;
import com.mja.service.policy.LifeInsurancePolicyService;
import com.mja.service.worker.InsuranceAgentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RequestMapping("/agent")
@RestController
public class InsuranceAgentController {

    private ClientService clientService;
    private InsuranceAgentService insuranceAgentService;
    private LifeInsurancePolicyService lifeInsurancePolicyService;
    private CarInsurancePolicyService carInsurancePolicyService;
    private HomeInsurancePolicyService homeInsurancePolicyService;
    private InsurancePolicyService insurancePolicyService;

    public InsuranceAgentController(ClientService clientService, InsuranceAgentService insuranceAgentService, LifeInsurancePolicyService lifeInsurancePolicyService,
                                    CarInsurancePolicyService carInsurancePolicyService, HomeInsurancePolicyService homeInsurancePolicyService, InsurancePolicyService insurancePolicyService) {
        this.clientService = clientService;
        this.insuranceAgentService = insuranceAgentService;
        this.lifeInsurancePolicyService = lifeInsurancePolicyService;
        this.carInsurancePolicyService = carInsurancePolicyService;
        this.homeInsurancePolicyService = homeInsurancePolicyService;
        this.insurancePolicyService = insurancePolicyService;
    }

    //CLIENT
    @PostMapping("/add-client")
    public ResponseEntity<Client> addClient(@RequestBody Client client, @RequestParam Long agentId) {
        InsuranceAgent insuranceAgent = insuranceAgentService.findFirstById(agentId);
        insuranceAgent.setClients(Arrays.asList(client));
        client.setInsuranceAgent(insuranceAgent);
        return ResponseEntity.ok(clientService.save(client));
    }

    @PostMapping("/update-client")
    public ResponseEntity<Client> updateClient(@RequestParam(required = true) Long clientId,
                                               @RequestParam(required = false) String firstName,
                                               @RequestParam(required = false) String secondName,
                                               @RequestParam(required = false) Long insuranceAgentId) {

        return ResponseEntity.ok(clientService.update(clientId, firstName, secondName, insuranceAgentId));
    }

    @GetMapping("/list-all-clients")
    public ResponseEntity<List<Client>> listAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @GetMapping("list-clients-without-agent")
    public ResponseEntity<List<Client>> listClientsWithoutAgent() {
        return ResponseEntity.ok(clientService.findAllByInsuranceAgentIsNull());
    }

    @PostMapping("/flag-client")
    public ResponseEntity<Client> flagClient(@RequestParam Long id) {
        return ResponseEntity.ok(clientService.setFlag(id));
    }

    // LIFE INSURANCE
    @PostMapping("/add-life-policy")
    public ResponseEntity<LifeInsurancePolicy> addLifeInsurancePolicy(@RequestBody LifeInsurancePolicy lifeInsurancePolicy) {
        return ResponseEntity.ok(lifeInsurancePolicyService.save(lifeInsurancePolicy));
    }

    @GetMapping("/list-life-policies")
    public ResponseEntity<List<LifeInsurancePolicy>> listLifeInsurancePolicies() {
        return ResponseEntity.ok(lifeInsurancePolicyService.findAll());
    }

    // CAR POLICY
    @PostMapping("/add-car-policy")
    public ResponseEntity<CarInsurancePolicy> addCarInsurancePolicy(@RequestBody CarInsurancePolicy carInsurancePolicy) {
        return ResponseEntity.ok(carInsurancePolicyService.save(carInsurancePolicy));
    }

    @GetMapping("/list-car-policies")
    public ResponseEntity<List<CarInsurancePolicy>> listCarInsurancePolicies() {
        return ResponseEntity.ok(carInsurancePolicyService.findAll());
    }

    // HOME POLICY
    @PostMapping("/add-home-policy")
    public ResponseEntity<HomeInsurancePolicy> addHomeInsurancePolicy(@RequestBody HomeInsurancePolicy homeInsurancePolicy) {
        return ResponseEntity.ok(homeInsurancePolicyService.save(homeInsurancePolicy));
    }

    @GetMapping("/list-home-policies")
    public ResponseEntity<List<HomeInsurancePolicy>> listHomeInsurancePolicies() {
        return ResponseEntity.ok(homeInsurancePolicyService.findAll());
    }

    // ALL KIND OF POLICY
    @PostMapping("/flag-policy")
    public ResponseEntity<InsurancePolicy> flagInsurancePolicy(@RequestParam Long id) {
        InsurancePolicy insurancePolicy = insurancePolicyService.findFirstById(id);
        insurancePolicy.setToRemove(true);
        return ResponseEntity.ok(insurancePolicyService.save(insurancePolicy));
    }

}

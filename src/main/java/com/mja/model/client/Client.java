package com.mja.model.client;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mja.model.agent.InsuranceAgent;
import com.mja.model.policy.InsurancePolicy;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String secondName;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<InsurancePolicy> insurancePolicies;
    @ManyToOne
    @JoinColumn(name = "insurance_agent")
    @JsonBackReference
    private InsuranceAgent insuranceAgent;
}

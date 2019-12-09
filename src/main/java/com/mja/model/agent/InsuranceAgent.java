package com.mja.model.agent;

import com.mja.model.policy.InsurancePolicy;
import lombok.Data;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

@Entity
@Data
public class InsuranceAgent {

    @Id
    @GeneratedValue
    private Long id;
    private String firstName;
    private String secondName;
    private InsurancePolicy insurancePolicy;
}

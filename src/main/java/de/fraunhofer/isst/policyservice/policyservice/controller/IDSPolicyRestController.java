package de.fraunhofer.isst.policyservice.policyservice.controller;


import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ActionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.rmi.server.UID;
import java.util.UUID;

@RestController
public class IDSPolicyRestController {
    String baseUid = "http://w3id.org/idsa/autogen/contract/";

    @PostMapping("/policy/ComplexPolicyForm")
    public String complexPolicy(@RequestBody RecieverOdrlPolicy recieverOdrlPolicy) {

        JsonIDSConverter converter = new JsonIDSConverter(recieverOdrlPolicy, RuleType.PERMISSION, ActionType.USE);
        //String uid = baseUid + "complex-policy-access";
        String uid = baseUid + UUID.randomUUID();
        converter.addLocationCondition();
        converter.addCounterCondition();
        converter.addPurposeCondition();
        converter.addPaymentCondition();
        converter.addUsagePeriod();
        return converter.createPolicy(uid);


    }

    @GetMapping("/")
    public String getDemo() {
        return "Hallo Welt";
    }


    @PostMapping("/policy/ComplexPolicy")
    public String complexPolicy(@RequestBody RequestInput requestInput) {
        RecieverOdrlPolicy recieverOdrlPolicy = RequestInputConvert.convertToRecieverOdrlPolicy(requestInput);
        JsonIDSConverter converter = new JsonIDSConverter(recieverOdrlPolicy, RuleType.PERMISSION, ActionType.USE);
        //String uid = baseUid + UUID.randomUUID();
        String uid = baseUid + recieverOdrlPolicy.getPerferenceUUID();
        converter.addLocationCondition();
        converter.addConsumerCondition();
        converter.addCounterCondition();
        converter.addPaymentCondition();
        converter.addPurposeCondition();
        converter.addUsagePeriod();
        converter.addPreDuties();
        return converter.createPolicy(uid);
    }

}

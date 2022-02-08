package de.fraunhofer.isst.policyservice.policyservice.controller;


import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.ActionType;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.RuleType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IDSPolicyRestController {
    String baseUid = "http://w3id.org/idsa/autogen/contract/";

    @PostMapping("/policy/ComplexPolicyForm")
    public String complexPolicy(@RequestBody RecieverOdrlPolicy recieverOdrlPolicy) {

        JsonIDSConverter converter = new JsonIDSConverter(recieverOdrlPolicy, RuleType.PERMISSION, ActionType.USE);
        String uid = baseUid + "complex-policy-access";
        converter.addLocationCondition();
        converter.addCounterCondition();
        converter.addPurposeCondition();
        converter.addPaymentCondition();
        return converter.createPolicy(uid);


    }

    @GetMapping("/policy/ComplexPolicyForm")
    public String getDemo() {
        return "Hallo Chung may";
    }


    @PostMapping("/policy/ComplexPolicy")
    public String complexPolicy(@RequestBody RequestInput requestInput) {
        RecieverOdrlPolicy recieverOdrlPolicy = RequestInputConvert.convertToRecieverOdrlPolicy(requestInput);
        JsonIDSConverter converter = new JsonIDSConverter(recieverOdrlPolicy, RuleType.PERMISSION, ActionType.USE);
        String uid = baseUid + "complex-policy-access";
        converter.addLocationCondition();
        converter.addCounterCondition();
        return converter.createPolicy(uid);
    }

}
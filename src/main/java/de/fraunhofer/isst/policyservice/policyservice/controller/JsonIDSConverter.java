package de.fraunhofer.isst.policyservice.policyservice.controller;

import de.fraunhofer.iese.ids.odrl.policy.library.model.*;
import de.fraunhofer.iese.ids.odrl.policy.library.model.enums.*;
import de.fraunhofer.isst.policyservice.policyservice.model.Compensation;
import de.fraunhofer.isst.policyservice.policyservice.model.DataHistory;
import de.fraunhofer.isst.policyservice.policyservice.model.UsagePeriod;
import de.fraunhofer.isst.policyservice.policyservice.util.OdrlCreator;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;


public class JsonIDSConverter {

    private RecieverOdrlPolicy recieverOdrlPolicy;
    private List<Condition> constraints = new ArrayList<>();
    private List<Rule> rules = new ArrayList<>();
    private List<Rule> postDuties = new ArrayList<>();
    private List<Rule> preDuties = new ArrayList<>();

    public JsonIDSConverter(RecieverOdrlPolicy recieverOdrlPolicy, RuleType ruleType, ActionType actionType) {
        this.recieverOdrlPolicy = recieverOdrlPolicy;
        Action  useAction = new Action(actionType);
        Rule rule = new Rule(ruleType,useAction);
        rule.setTarget(URI.create(recieverOdrlPolicy.getTarget()));
        rules.add(rule);
    }


    public boolean addLocationCondition() {

        if (!recieverOdrlPolicy.getLocation().equals("")) {
            RightOperandType rightOperandType = RightOperandType.ANYURI;
            ConditionType conditionType = ConditionType.CONSTRAINT;
            LeftOperand leftOperand = LeftOperand.ABSOLUTE_SPATIAL_POSITION;
            ArrayList<RightOperand> rightOperands = new ArrayList<>();
            RightOperand rightOperand = new RightOperand("https://ontologi.es/place/"+ recieverOdrlPolicy.getLocation(), rightOperandType);
            rightOperands.add(rightOperand);
            Condition constraint = new Condition(conditionType, leftOperand, Operator.SAME_AS , rightOperands, null);
            constraints.add(constraint);
                          return true;
        }
        return false;
    }

    public boolean addConsumerCondition() {
        if(!recieverOdrlPolicy.getConsumer().equals("")) {
            RightOperandType rightOperandType = RightOperandType.ANYURI;
            ConditionType conditionType = ConditionType.CONSTRAINT;
            LeftOperand leftOperand = LeftOperand.CONNECTOR;
            ArrayList<RightOperand> rightOperands = new ArrayList<>();
            RightOperand rightOperand = new RightOperand(recieverOdrlPolicy.getConsumer(), rightOperandType);
            rightOperands.add(rightOperand);
            Condition constraint = new Condition(conditionType, leftOperand, Operator.SAME_AS, rightOperands, null);
            constraints.add(constraint);
            return true;
        }
        return false;
    }

    public boolean addPurposeCondition() {

        if(!recieverOdrlPolicy.getPurpose().equals("")) {
        RightOperandType rightOperandType = RightOperandType.ANYURI;
        ConditionType conditionType = ConditionType.CONSTRAINT;
        LeftOperand leftOperand = LeftOperand.PURPOSE;
        ArrayList<RightOperand> rightOperands = new ArrayList<>();
        RightOperand rightOperand = new RightOperand(recieverOdrlPolicy.getPurpose(), rightOperandType);
        rightOperands.add(rightOperand);
        Condition constraint = new Condition(conditionType, leftOperand, Operator.SAME_AS , rightOperands, null);
        constraints.add(constraint);
        return true;

        }
        return false;
    }
    public boolean addCounterCondition() {
        if (recieverOdrlPolicy.getCounter() != "") {
            RightOperand rightOperand = new RightOperand(recieverOdrlPolicy.getCounter(), RightOperandType.DECIMAL);
            ArrayList<RightOperand> rightOperands = new ArrayList<>();
            rightOperands.add(rightOperand);
            Condition countCondition = new Condition(ConditionType.CONSTRAINT, LeftOperand.COUNT, Operator.LTEQ,
                    rightOperands, null);
            constraints.add(countCondition);
            return true;
        }
        return false;
    }

    public void addPreDuties() {

        if (recieverOdrlPolicy.getPreduties_anomym() != "") {
            anonymizeInRest();
        }
    }

    private void anonymizeInRest() {
        Action anonymizeAction = new Action(ActionType.ANONYMIZE);
        Rule rule = new Rule(RuleType.OBLIGATION, anonymizeAction);
        //rule.setTarget(URI.create());
        preDuties.add(rule);
    }



 /*   public boolean addPaymentCondition() {
        Compensation compensation = recieverOdrlPolicy.getCompensation();
        if (recieverOdrlPolicy.getPayment() != "") {
            String contract = "http://dbpedia.org/page/";
            RightOperand paymentRightOperand = new RightOperand(String.valueOf(recieverOdrlPolicy.getPrice()), RightOperandType.DOUBLE);
            ArrayList<RightOperand> paymentRightOperands = new ArrayList<>();
            paymentRightOperands.add(paymentRightOperand);
            Condition paymentCondition = new Condition(ConditionType.CONSTRAINT, LeftOperand.PAY_AMOUNT, Operator.EQ,
                    paymentRightOperands, null);
            paymentCondition.setUnit("http://dbpedia.org/resource/" + recieverOdrlPolicy.getUnit());
            paymentCondition.setContract(contract + recieverOdrlPolicy.getPayment());
            constraints.add(paymentCondition);
            return true;
        }
        return false;
    }*/
 public boolean addPaymentCondition() {
     Compensation compensation = recieverOdrlPolicy.getCompensation();
     if (compensation.getPayment() != "") {
         String contract = "http://dbpedia.org/page/";
         RightOperand paymentRightOperand = new RightOperand(String.valueOf(compensation.getPrice()), RightOperandType.DOUBLE);
         ArrayList<RightOperand> paymentRightOperands = new ArrayList<>();
         paymentRightOperands.add(paymentRightOperand);
         Condition paymentCondition = new Condition(ConditionType.CONSTRAINT, LeftOperand.PAY_AMOUNT, Operator.EQ,
                 paymentRightOperands, null);
         paymentCondition.setUnit("http://dbpedia.org/resource/" + compensation.getUnit());
         paymentCondition.setContract(contract + compensation.getPayment());
         constraints.add(paymentCondition);
         return true;
     }
     return false;
 }

    private String checkIfEmptyValue(String value, String defaultValue) {
        if (value.length() > 0) {
            return value;
        } else {
            return defaultValue;
        }
    }

    public boolean addUsagePeriod() {
        ArrayList<RightOperandEntity> durationEntities = new ArrayList<>();
        RightOperand elapsedTimeRightOperand = new RightOperand();
        elapsedTimeRightOperand.setType(RightOperandType.DURATIONENTITY);

        /*UsagePeriod usagePeriod = recieverOdrlPolicy.getUsagePeriod();
        String hour = "";
        String day = "";
        String month = "";
        String year = "";
        if (usagePeriod.getDurationHour()!= null && !usagePeriod.getDurationHour().isEmpty()) {
            hour = "T" + usagePeriod.getDurationHour() + TimeUnit.HOURS.getOdrlXsdDuration();
        }
        if (usagePeriod.getDurationDay() != null && !usagePeriod.getDurationDay().isEmpty()) {
            day = usagePeriod.getDurationDay() + TimeUnit.DAYS.getOdrlXsdDuration();
        }
        if (usagePeriod.getDurationMonth() != null && !usagePeriod.getDurationMonth().isEmpty()) {
            month = usagePeriod.getDurationMonth() + TimeUnit.MONTHS.getOdrlXsdDuration();
        }
        if (usagePeriod.getDurationYear() != null && !usagePeriod.getDurationYear().isEmpty()) {
            year = usagePeriod.getDurationYear() + TimeUnit.YEARS.getOdrlXsdDuration();
        }*/
        String duration = recieverOdrlPolicy.getUsagePeriod();

        if (!duration.equals("P")) {
            RightOperandEntity hasDurationEntity = new RightOperandEntity(EntityType.HASDURATION, duration,
                    RightOperandType.DURATION);
            // hasDurationEntity.setTimeUnit(TimeUnit.valueOf(restrictTimeDurationUnit));
            durationEntities.add(hasDurationEntity);
        }

        if (durationEntities.size() > 0) {
            elapsedTimeRightOperand.setEntities(durationEntities);
            ArrayList<RightOperand> elapsedTimeRightOperands = new ArrayList<>();
            elapsedTimeRightOperands.add(elapsedTimeRightOperand);
            Condition elapsedTimeConstraint = new Condition(ConditionType.CONSTRAINT, LeftOperand.ELAPSED_TIME,
                    Operator.SHORTER_EQ, elapsedTimeRightOperands, null);
            constraints.add(elapsedTimeConstraint);
            return true;
        }
        return false;

    }

    public boolean addDataHistory() {
        ArrayList<RightOperandEntity> durationEntities = new ArrayList<>();
        RightOperand elapsedTimeRightOperand = new RightOperand();
        elapsedTimeRightOperand.setType(RightOperandType.DURATIONENTITY);

        //DataHistory dataHistory = recieverOdrlPolicy.getDataHistory();
/*        String hour = "";
        String day = "";
        String month = "";
        String year = "";
        if (dataHistory.getDurationHour()!= null && !dataHistory.getDurationHour().isEmpty()) {
            hour = "T" + dataHistory.getDurationHour() + TimeUnit.HOURS.getOdrlXsdDuration();
        }
        if (dataHistory.getDurationDay() != null && !dataHistory.getDurationDay().isEmpty()) {
            day = dataHistory.getDurationDay() + TimeUnit.DAYS.getOdrlXsdDuration();
        }
        if (dataHistory.getDurationMonth() != null && !dataHistory.getDurationMonth().isEmpty()) {
            month = dataHistory.getDurationMonth() + TimeUnit.MONTHS.getOdrlXsdDuration();
        }
        if (dataHistory.getDurationYear() != null && !dataHistory.getDurationYear().isEmpty()) {
            year = dataHistory.getDurationYear() + TimeUnit.YEARS.getOdrlXsdDuration();
        }*/
        String duration = recieverOdrlPolicy.getDataHistory();

        if (!duration.equals("P")) {
            RightOperandEntity hasDurationEntity = new RightOperandEntity(EntityType.HASDURATION, duration,
                    RightOperandType.DURATION);
            // hasDurationEntity.setTimeUnit(TimeUnit.valueOf(restrictTimeDurationUnit));
            durationEntities.add(hasDurationEntity);
        }

        if (durationEntities.size() > 0) {
            elapsedTimeRightOperand.setEntities(durationEntities);
            ArrayList<RightOperand> elapsedTimeRightOperands = new ArrayList<>();
            elapsedTimeRightOperands.add(elapsedTimeRightOperand);
            Condition elapsedTimeConstraint = new Condition(ConditionType.CONSTRAINT, LeftOperand.DATA_HISTORY,
                    Operator.SHORTER_EQ, elapsedTimeRightOperands, null);
            constraints.add(elapsedTimeConstraint);
            return true;
        }
        return false;

    }

    public String createPolicy(String policyUID) {

        rules.get(0).setConstraints((ArrayList<Condition>) constraints);
        if(postDuties.size() > 0) {
            rules.get(0).setPostduties((ArrayList<Rule>) postDuties);
        }
        if(preDuties.size() > 0) {
            rules.get(0).setPreduties((ArrayList<Rule>) preDuties);
        }
        OdrlPolicy odrlPolicy = new OdrlPolicy();
        odrlPolicy.setProvider(createProvider());
        odrlPolicy.setConsumer(createConsumer());
        odrlPolicy.setCitizenUUID(recieverOdrlPolicy.getCitizenUUID());
        odrlPolicy.setCreationTime(recieverOdrlPolicy.getCreationTime());
        odrlPolicy.setStatus(recieverOdrlPolicy.isActive());
        odrlPolicy.setRules((ArrayList<Rule>) rules);
        odrlPolicy.setPolicyId(URI.create(policyUID));
        odrlPolicy.setType(PolicyType.getFromIdsString("ids:Contract" + recieverOdrlPolicy.getPolicyType()));
        String jsonPolicyString = OdrlCreator.createODRL(odrlPolicy);

        return jsonPolicyString;
    }

    private Party createConsumer() {
        Party consumer = null;
        try {
            consumer = new Party(PartyType.CONSUMER, new URI(recieverOdrlPolicy.getConsumer()));
            consumer.setType(PartyType.CONSUMER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return consumer;
    }

    private Party createProvider() {
        Party provider = null;
        try {
            provider = new Party(PartyType.PROVIDER, new URI(recieverOdrlPolicy.getProvider()));
            provider.setType(PartyType.PROVIDER);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return provider;
    }

}

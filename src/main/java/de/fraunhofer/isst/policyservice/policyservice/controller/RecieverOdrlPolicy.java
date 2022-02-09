package de.fraunhofer.isst.policyservice.policyservice.controller;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Builder
@Getter
@Setter
public class RecieverOdrlPolicy {

    private String perferenceUUID;
    private String creationTime;
    private String policyType;
    private String target;
    private String provider;
    private String consumer;
    private String counter;
    private String location;
    private String purpose;



   /* private List<String> location_input;
    private String location_op;
    private List<String> application_input;
    private String application_op;
    private List<String> connector_input;
    private String connector_op;
    private List<String> role_input;
    private String role_op;
    private List<String> purpose_input;
    private String purpose_op;
    private List<String> event_input;
    private String event_op;
    private List<String> state_input;
    private String state_op;
    private List<String> securityLevel_input;
    private String securityLevel_op;


    private String system;
    private String interval;*/
    private String payment;
    private String price;
    private String unit;

    private UsagePeriod usagePeriod;
    private Compensation compensation;

/*    private String encoding;
    private String policy;
    private String time;
    private String timeUnit;
    private String timeAndDate;
    private String restrictTimeIntervalStart;
    private String restrictTimeIntervalEnd;
    private String restrictEndTime;
    private String specifyBeginTime;
    private String durationHour;
    private String durationDay;
    private String durationMonth;
    private String durationYear;
    private String artifactState;*/

}

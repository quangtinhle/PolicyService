package de.fraunhofer.isst.policyservice.policyservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class RequestInput{
    @JsonProperty("perferenceID")
    private String perferenceID;
    private String creationTime;
    private ArrayList<String> targetData;
    private String dataHistory;
    private ArrayList<String> dataProviders;
    private ArrayList<String> dataConsumers;
    private String location;
    private Compensation compensation;
    private String modification;
    private String usagePeriod;
    private String purpose;
    private int usageCount;
}

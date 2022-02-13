package de.fraunhofer.isst.policyservice.policyservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class RequestInput{
    @JsonProperty("perferenceUUID")
    private String perferenceUUID;
    private String citizenUUID;
    private String creationTime;
    private boolean active;
    private String targetData;
    private String dataHistory;
    private String dataProviders;
    private String dataConsumers;
    private String location;
    private Compensation compensation;
    private String modification;
    private UsagePeriod usagePeriod;
    private String purpose;
    private int usageCount;
}

package de.fraunhofer.isst.policyservice.policyservice.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import de.fraunhofer.isst.policyservice.policyservice.model.Compensation;
import de.fraunhofer.isst.policyservice.policyservice.model.DataHistory;
import de.fraunhofer.isst.policyservice.policyservice.model.UsagePeriod;
import lombok.Getter;

@Getter
public class RequestInput{
    @JsonProperty("perferenceUUID")
    private String perferenceUUID;
    private String citizenUUID;
    private String creationTime;
    private boolean active;
    private String targetData;
    private String dataProviders;
    private String dataConsumers;
    private String location;
    private Compensation compensation;
    private String modification;
    private String usagePeriod;
    private String purpose;
    private String dataHistory;
    private Double minCompensation;
    private int usageCount;
}

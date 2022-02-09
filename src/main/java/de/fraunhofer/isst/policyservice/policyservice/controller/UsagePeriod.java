package de.fraunhofer.isst.policyservice.policyservice.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class UsagePeriod {


    private String durationYear;
    private String durationMonth;
    private String durationDay;
    private String durationHour;
}

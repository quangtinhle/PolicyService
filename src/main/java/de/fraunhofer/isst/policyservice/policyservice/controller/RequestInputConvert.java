package de.fraunhofer.isst.policyservice.policyservice.controller;


import com.fasterxml.jackson.databind.util.ISO8601DateFormat;

import java.sql.Date;
import java.text.ParseException;
import java.util.UUID;
import java.util.stream.Collectors;

public class RequestInputConvert {
    
    private RequestInputConvert() {

    }

    public static RecieverOdrlPolicy convertToRecieverOdrlPolicy(RequestInput requestInput) throws ParseException {

        ISO8601DateFormat df = new ISO8601DateFormat();
        return RecieverOdrlPolicy.builder()
                .perferenceUUID(requestInput.getPerferenceUUID())
                .citizenUUID(UUID.fromString(requestInput.getCitizenUUID()))
                .active(requestInput.isActive())
                .creationTime(df.parse(requestInput.getCreationTime()))
                .policyType("Offer")
                .target(requestInput.getTargetData())
                .provider(requestInput.getDataProviders())
                .consumer(requestInput.getDataConsumers())
                .location(requestInput.getLocation())
                .counter(String.valueOf(requestInput.getUsageCount()))
                .compensation(requestInput.getCompensation())
                .usagePeriod(requestInput.getUsagePeriod())
                .purpose(requestInput.getPurpose())
                .preduties_anomym(requestInput.getModification())
                .dataHistory(requestInput.getDataHistory())
                .build();

    }
}

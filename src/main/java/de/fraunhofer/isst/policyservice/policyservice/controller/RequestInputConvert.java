package de.fraunhofer.isst.policyservice.policyservice.controller;


import java.util.stream.Collectors;

public class RequestInputConvert {
    
    private RequestInputConvert() {

    }

    public static RecieverOdrlPolicy convertToRecieverOdrlPolicy(RequestInput requestInput) {

        return RecieverOdrlPolicy.builder()
                .perferenceUUID(requestInput.getPerferenceUUID())
                .citizenUUID(requestInput.getCitizenUUID())
                .active(requestInput.isActive())
                .creationTime(requestInput.getCreationTime())
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
                .build();

    }
}

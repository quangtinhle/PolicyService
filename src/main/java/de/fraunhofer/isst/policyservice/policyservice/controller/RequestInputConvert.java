package de.fraunhofer.isst.policyservice.policyservice.controller;


import java.util.stream.Collectors;

public class RequestInputConvert {
    
    private RequestInputConvert() {

    }

    public static RecieverOdrlPolicy convertToRecieverOdrlPolicy(RequestInput requestInput) {

        return RecieverOdrlPolicy.builder()
                .perferenceID(requestInput.getPerferenceID())
                .policyType("Offer")
                .target(requestInput.getTargetData().get(0))
                .provider(requestInput.getDataProviders().get(0))
                .consumer(requestInput.getDataConsumers().get(0))
                .location(requestInput.getLocation())
                .counter(String.valueOf(requestInput.getUsageCount()))
                .build();

    }
}

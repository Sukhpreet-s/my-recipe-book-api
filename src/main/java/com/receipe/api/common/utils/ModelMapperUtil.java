package com.receipe.api.common.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Provide single ModelMapper instance.
 */
public class ModelMapperUtil {
    private ModelMapper modelMapper;
    private static ModelMapperUtil instance;

    private ModelMapperUtil() {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private ModelMapper getModelMapper() { return modelMapper; }

    public static ModelMapper getInstance() {
        if (instance == null) {
            instance = new ModelMapperUtil();
        }
        return instance.getModelMapper();
    }
}

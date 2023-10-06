package com.macmil.framework.config;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Browser configuration class
 */
@Getter
@Setter
@Builder
public class Config {
    private String browserType;
    private String browserViewport;
    private String remoteUri;
    private String testUrl;
}

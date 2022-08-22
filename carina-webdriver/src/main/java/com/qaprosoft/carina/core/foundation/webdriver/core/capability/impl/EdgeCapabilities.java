package com.qaprosoft.carina.core.foundation.webdriver.core.capability.impl;

import static org.openqa.selenium.remote.Browser.EDGE;

import java.lang.invoke.MethodHandles;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.qaprosoft.carina.core.foundation.utils.Configuration;
import com.qaprosoft.carina.core.foundation.webdriver.core.capability.AbstractCapabilities;

public class EdgeCapabilities extends AbstractCapabilities<ChromiumOptions<?>> {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Override
    public ChromiumOptions<?> getCapabilities(Capabilities capabilities) {
        ChromiumOptions<?> options = new ChromiumOptions<>(CapabilityType.BROWSER_NAME, EDGE.browserName(), "ms:edgeOptions");

        setCapabilities(options, capabilities);
        setCapabilities(options, getBrowserConfigurationCapabilities());

        if (Configuration.getBoolean(Configuration.Parameter.HEADLESS)) {
            options.setHeadless(true);
            LOGGER.info("Browser will be started in headless mode. VNC and Video will be disabled.");
            options.setCapability("enableVNC", false);
            options.setCapability("enableVideo", false);
        }
        options.addArguments("--start-maximized", "--ignore-ssl-errors");
        options.setAcceptInsecureCerts(true);
        return options;
    }
}

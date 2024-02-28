package com.diegobarrioh.api.akdmiaapi.security.xss;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.safety.Safelist;

@Log4j2
public class RequestWrapper extends HttpServletRequestWrapper {

    public RequestWrapper(HttpServletRequest request) {
        super(request);
    }

    @Override
    public String getHeader(String name) {
        log.trace("Saniting header {}", name);
        return cleanXSS(name, super.getHeader(name));
    }

    public String getParameter(String parameter) {
        log.trace("Saniting parameter {}",parameter);
        return cleanXSS(parameter, super.getParameter(parameter));
    }
    public String[] getParameterValues(String parameter){
        log.trace("Saniting parameters values");
        String[] values = super.getParameterValues(parameter);
        if( values == null) {
            return new String[0];
        }
        int count = values.length;
        String[] encodedValues = new String[count];
        for (int i = 0; i < encodedValues.length; i++) {
             encodedValues[i] = cleanXSS(parameter,values[i]);
        }
        return encodedValues;
    }

    private String cleanXSS(String key, String value) {
        if (!StringUtils.isEmpty(value)) {
            log.trace("key {} with value [{}]", key, value);
            String safeValue = Jsoup.parse(Jsoup.clean(value, Safelist.basic())).text();
            log.trace("key {} with value sanitized [{}]", key, safeValue);
            if (StringUtils.isEmpty(safeValue)) {
                throw new IllegalArgumentException("Parameter {" + key + "} contains an illegal value [" + value + "]");
            }
            return safeValue;
        }
        return value;
    }

}

package com.zup.bootcamp.Utils;

import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;

@Log4j2
@UtilityClass
public class ResourceUriHelper {

    public static void addUriInResponseHeader(Object resourceId) {

        try {

            URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                    .path("/{id}")
                    .buildAndExpand(resourceId).toUri();

            HttpServletResponse response = ((ServletRequestAttributes)
                    RequestContextHolder.getRequestAttributes()).getResponse();

            response.setHeader(HttpHeaders.LOCATION, uri.toString());
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }

}

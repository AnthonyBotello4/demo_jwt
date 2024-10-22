package com.example.demo_jwt.trips.infrastructure.acl;

import com.example.demo_jwt.shared.utils.EnvironmentConstants;
import com.example.demo_jwt.trips.domain.model.DriverDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Component
public class DriverAcl {

    private final RestTemplate restTemplate;
    private final String BASE_URL = EnvironmentConstants.CURRENT_ENV_URL + "/api/drivers/";

    private static final Logger logger = LoggerFactory.getLogger(DriverAcl.class);

    public DriverAcl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Optional<DriverDto> findDriverById(Long id) {
        String url = BASE_URL + id;

        logger.info("DriverAcl: findDriverById: url: {}", url);

        try {
            DriverDto driverDto = restTemplate.getForObject(url, DriverDto.class);
            if (driverDto != null) {
                logger.info("Driver encontrado: {}", driverDto);
                return Optional.of(driverDto);
            } else {
                logger.warn("No se encontró ningún conductor con id: {}", id);
                return Optional.empty();
            }
        } catch (Exception e) {
            logger.error("Error llamando al servicio de drivers: {}", e.getMessage(), e);
            return Optional.empty();
        }
    }


    public List<DriverDto> findAllDriversByIdIn(List<Long> ids){
        String url = BASE_URL + "batch";
        ResponseEntity<List<DriverDto>> response = restTemplate.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(ids),
                new ParameterizedTypeReference<List<DriverDto>>() {}
        );
        return response.getBody();
    }
}

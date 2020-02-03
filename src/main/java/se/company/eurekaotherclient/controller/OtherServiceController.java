package se.company.eurekaotherclient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class OtherServiceController {

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/getUService/{itemId}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String itemId)
    {
        String response = restTemplate.exchange("http://eureka/getUServiceForItemId/{itemId}",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, itemId).getBody();

        return "ItemId -  " + itemId + " \n URL " + response;
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}

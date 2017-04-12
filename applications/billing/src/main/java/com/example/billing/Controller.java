package com.example.billing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by kshitizkriskrishna on 2/16/17.
 */
@RestController
public class Controller {
    @Autowired
    private com.example.payments.Gateway paymentGateway;

    @RequestMapping(value = "/reocurringPayment", method = RequestMethod.POST)
    public ResponseEntity<String> createReocurringPayment(@RequestBody int amount){
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add("content-type", MediaType.APPLICATION_JSON.toString());

        ResponseEntity<String> response;
        if (paymentGateway.createReocurringPayment(amount)) {
            response = new ResponseEntity<>("{errors: []}", responseHeaders, HttpStatus.CREATED);
        } else {
            response = new ResponseEntity<>("{errors: [\"error1\", \"error2\"]}", responseHeaders, HttpStatus.BAD_REQUEST);
        }

        return response;
    }
}

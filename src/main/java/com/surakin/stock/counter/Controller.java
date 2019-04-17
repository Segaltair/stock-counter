package com.surakin.stock.counter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@org.springframework.stereotype.Controller
public class Controller {
    @Autowired
    MyService myService;


    @RequestMapping(value = "/test", method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<Stock[]> baseUrlRedirect(@RequestBody ApiRequest apiRequest) {
        Stock[] request = apiRequest.getStocks();
        Stock[] response = new Stock[request.length];
        for (int i = 0; i < request.length; i++) {
            response[i] = myService.getStockBySymbol(request[i].getSymbol());
        }

        return ResponseEntity.ok(response);
    }

}

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
    public ResponseEntity<Stock> baseUrlRedirect(@RequestBody Stock stock) {
        Stock response = myService.getStockBySymbol(stock.getSymbol());

        return ResponseEntity.ok(response);
    }

}

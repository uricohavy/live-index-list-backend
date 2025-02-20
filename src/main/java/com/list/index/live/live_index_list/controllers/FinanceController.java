package com.list.index.live.live_index_list.controllers;


import com.list.index.live.live_index_list.services.FinanceService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/index")
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class FinanceController {

    private final FinanceService financeService;

    public FinanceController(FinanceService financeService) {
        this.financeService = financeService;
    }

    @GetMapping("/{symbol}")
    public String getIndex(@PathVariable String symbol) {
        System.out.println("This is working");
        System.out.println("Fetching data for symbol: " + symbol);
        return financeService.getIndexData(symbol);
    }
}

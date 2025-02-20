package com.list.index.live.live_index_list.services;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
@Service
public class FinanceService {
    public String getIndexData(String symbol) {
        try {
            // Modify this line to activate the virtual environment
            ProcessBuilder processBuilder = new ProcessBuilder(
                    "/bin/bash", "-c",
                    "source myenv/bin/activate && python3 scripts/fetch_index_data.py " + symbol);

            System.out.println("Successfully processbuilder"); // Debugging print
            Process process = processBuilder.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder result = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
            process.waitFor();
            return result.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return "{\"error\":\"Failed to fetch data\"}";
        }
    }
}

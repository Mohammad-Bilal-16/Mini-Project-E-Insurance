package com.example.mini_project_02.RestController;

import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;
import com.example.mini_project_02.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ReportRestController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/plannames")
    public ResponseEntity<List<String>> getPlanNames(){
        List<String> planNames = reportService.getPlanNames();
        return new ResponseEntity<>(planNames, HttpStatus.OK);
    }
    @GetMapping("/planstatues")
    public ResponseEntity<List<String>> getPlanStatues(){
        List<String> planNames = reportService.getPlanStatues();
        return new ResponseEntity<>(planNames, HttpStatus.OK);
    }
    @PostMapping("/search")
    public ResponseEntity<List<CitizenPlan>> search(@RequestBody SearchRequest searchRequest){
        List<CitizenPlan> citizenPlans = reportService.getCitizenPlans(searchRequest);
        return new ResponseEntity<>(citizenPlans , HttpStatus.OK);
    }
    @GetMapping("/export")
    public void exportExcel(HttpServletResponse response) throws Exception{
        response.setContentType("application/octet-stream");

        String key = "Content-Disposition";
        String value = "attachment;file=citizens.xls";

        response.setHeader(key , value);

        reportService.exportExcel(response);
        response.flushBuffer();
    }

    @GetMapping("/pdf")
    public void exportPdf(HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");

        String key = "Content-Disposition";
        String value = "attachment;plans.pdf";

        response.setHeader(key , value);

        reportService.exportPdf(response);
        response.flushBuffer(); // after writeing it will send the data
    }
}

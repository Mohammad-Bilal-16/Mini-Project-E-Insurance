package com.example.mini_project_02.service;

import com.example.mini_project_02.Repo.CitizenPlanRepository;
import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Service
public class ReportServiceImpl implements ReportService{

    @Autowired
    private CitizenPlanRepository citizenPlanRepository;

    @Override
    public List<String> getPlanNames() {
        return citizenPlanRepository.getPlanNames();
    }

    @Override
    public List<String> getPlanStatues() {
        return citizenPlanRepository.getPlanStatues();
    }

    /**
     * Dynamic Search
     */
    //TODO: In this if i search with planName than only planName data will filter and apper
    // same planStatus if i select both name && status both filter & if nothing is selected
    // in search all the data appears
    @Override
    public List<CitizenPlan> getCitizenPlans(SearchRequest searchRequest) {

        CitizenPlan plan = new CitizenPlan();

        if(searchRequest.getPlanName() != null && !searchRequest.getPlanName().equals(""))
            plan.setPlanName(searchRequest.getPlanName());

        if(searchRequest.getPlanStatus() != null && !searchRequest.getPlanStatus().equals(""))
            plan.setPlanStatus(searchRequest.getPlanStatus());

        if(searchRequest.getGender() != null && !searchRequest.getGender().equals(""))
            plan.setGender(searchRequest.getGender());

        Example<CitizenPlan> example = Example.of(plan);

        List<CitizenPlan> records = citizenPlanRepository.findAll(example);

        return records;
    }

    @Override
    public void exportExcel(HttpServletResponse response) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();

        XSSFSheet sheet = workbook.createSheet("Citizen Info");

        XSSFRow headerRow = sheet.createRow(0);

        headerRow.createCell(0).setCellValue("Id");
        headerRow.createCell(1).setCellValue("Name");
        headerRow.createCell(2).setCellValue("SSN");
        headerRow.createCell(3).setCellValue("Gender");
        headerRow.createCell(3).setCellValue("Plan Name");
        headerRow.createCell(3).setCellValue("Plan Status");

        List<CitizenPlan> records = citizenPlanRepository.findAll();

        int dataRowIndex = 1;
        for(CitizenPlan record : records){

            XSSFRow dataRow = sheet.createRow(dataRowIndex);

            dataRow.createCell(0).setCellValue(record.getCid());
            dataRow.createCell(1).setCellValue(record.getCName());
            dataRow.createCell(2).setCellValue(record.getSsn());
            dataRow.createCell(3).setCellValue(record.getGender());
            dataRow.createCell(4).setCellValue(record.getPlanName());
            dataRow.createCell(5).setCellValue(record.getPlanStatus());

            dataRowIndex++;

        }
        ServletOutputStream ops = response.getOutputStream();
        workbook.write(ops);
        workbook.close();
        ops.close();
    }

    @Override
    public void exportPdf(HttpServletRequest response) {

        List<CitizenPlan> records = citizenPlanRepository.findAll();

    }
}

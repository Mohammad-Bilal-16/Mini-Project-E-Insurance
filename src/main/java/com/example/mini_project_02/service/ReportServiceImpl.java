package com.example.mini_project_02.service;

import com.example.mini_project_02.Repo.CitizenPlanRepository;
import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;
import com.lowagie.text.Font;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
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
     *
     * @TODO:
     * In this if i search with planName than only planName data will filter and apper
     * same planStatus if i select both name && status both filter & if nothing is selected
     * in search all the data appears
     */
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

    /**
     * @exportExcel to export or download data into excel format
     */
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
    /**
     * @exportPdf to export or download data into pdf format
     */
    public void exportPdf(HttpServletResponse response) throws Exception{

        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of Users", font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f, 1.5f});
        table.setSpacingBefore(10);

        //TODO: Set Table Header data
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.BLUE);
        cell.setPadding(5);

        Font f = FontFactory.getFont(FontFactory.HELVETICA);
        f.setColor(Color.WHITE);

        cell.setPhrase(new Phrase("User ID", f));

        table.addCell(cell);

        cell.setPhrase(new Phrase("Name", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SSN", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Gender", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Name", f));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Plan Status", f));
        table.addCell(cell);

        //TODO: Set Table Data
        List<CitizenPlan> records = citizenPlanRepository.findAll();
        for(CitizenPlan record : records){
            table.addCell(String.valueOf(record.getCid()));
            table.addCell(record.getCName());
            table.addCell(String.valueOf(record.getSsn()));
            table.addCell(record.getGender());
            table.addCell(record.getPlanName());
            table.addCell(record.getPlanStatus());
        }

        document.add(table);
        document.close();

    }
}

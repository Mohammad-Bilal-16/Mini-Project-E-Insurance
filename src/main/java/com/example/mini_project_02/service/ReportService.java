package com.example.mini_project_02.service;

import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface ReportService {
    public List<String> getPlanNames();
    public List<String> getPlanStatues();
    public List<CitizenPlan> getCitizenPlans(SearchRequest searchRequest);
    public void exportExcel(HttpServletResponse response) throws IOException;
    public void exportPdf(HttpServletRequest response);

}

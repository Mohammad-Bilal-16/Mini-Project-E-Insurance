package com.example.mini_project_02.service;

import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;

import java.util.List;

public interface ReportService {
    public List<String> getPlanNames();
    public List<String> getPlanStatues();
    public List<CitizenPlan> getCitizenPlans(SearchRequest searchRequest);
}

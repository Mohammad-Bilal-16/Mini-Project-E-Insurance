package com.example.mini_project_02.service;

import com.example.mini_project_02.Repo.CitizenPlanRepository;
import com.example.mini_project_02.model.CitizenPlan;
import com.example.mini_project_02.model.SearchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    public void exportExcel(HttpServletResponse response) {

    }

    @Override
    public void exportPdf(HttpServletRequest response) {

    }
}

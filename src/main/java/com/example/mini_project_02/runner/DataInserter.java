package com.example.mini_project_02.runner;

import com.example.mini_project_02.Repo.CitizenPlanRepository;
import com.example.mini_project_02.model.CitizenPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class DataInserter implements ApplicationRunner {

    @Autowired
    private CitizenPlanRepository repository;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        CitizenPlan cp1 = new CitizenPlan();
        cp1.setCName("Jhon");
        cp1.setGender("Male");
        cp1.setPhoneNo(897867564L);
        cp1.setPlanName("SNAP");
        cp1.setPlanStatus("Approved");
        cp1.setSsn(7676867677l);

        CitizenPlan cp2 = new CitizenPlan();
        cp2.setCName("Smith");
        cp2.setGender("Male");
        cp2.setPhoneNo(9898797678L);
        cp2.setPlanName("SNAP");
        cp2.setPlanStatus("Denied");
        cp2.setSsn(667674279l);

        CitizenPlan cp3 = new CitizenPlan();
        cp3.setCName("Emily");
        cp3.setGender("Female");
        cp3.setPhoneNo(234567899l);
        cp3.setPlanName("CCAP");
        cp3.setPlanStatus("Approved");
        cp3.setSsn(987657677l);

        CitizenPlan cp4 = new CitizenPlan();
        cp4.setCName("Rose");
        cp4.setGender("Female");
        cp4.setPhoneNo(45667564L);
        cp4.setPlanName("CCAP");
        cp4.setPlanStatus("Denied");
        cp4.setSsn(13234665l);

        List<CitizenPlan> list = Arrays.asList(cp1, cp2, cp3, cp4);
        repository.saveAll(list);


    }
}

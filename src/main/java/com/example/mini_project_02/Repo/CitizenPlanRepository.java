package com.example.mini_project_02.Repo;

import com.example.mini_project_02.model.CitizenPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface CitizenPlanRepository extends JpaRepository<CitizenPlan , Serializable> {

    @Query("select distinct(planName) from CitizenPlan")
    public List<String> getPlanNames();

    @Query("select distinct(planStatus) from CitizenPlan")
    public List<String> getPlanStatues();
}

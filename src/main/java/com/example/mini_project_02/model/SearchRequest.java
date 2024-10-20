package com.example.mini_project_02.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequest {

    private String planName;
    private String planStatus;
    private String gender;
//    private LocalDate startDate;
//    private LocalDate endDate;

}

package com.example.mini_project_02.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "CITIZENS_PLANS_INFO")
public class CitizenPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cid;
    private String planName;
    private String planStatus;
    private String cName;
    private String cEmail;
    private String gender;
    private Long phoneNo;
    private Long ssn;

    @CreationTimestamp
    private Date createdOn;
    @UpdateTimestamp
    private Date updatedOn;
}

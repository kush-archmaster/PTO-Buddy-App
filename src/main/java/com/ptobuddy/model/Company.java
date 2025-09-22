package com.ptobuddy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "company_name", nullable = false)
    private String companyName;

    @Column(name = "annual_leave_entitlement", nullable = false)
    private Integer annualLeaveEntitlement;

    @Column(name = "sick_leave_entitlement", nullable = false)
    private Integer sickLeaveEntitlement;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    // Entity Mappings
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> employees = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CorporateHoliday> corporateHolidays = new ArrayList<>();

    // helper methods to add/remove employee
    public void addEmployee(User user) {
        employees.add(user);
        user.setCompany(this);
    }

    public void removeEmployee(User user) {
        employees.remove(user);
        user.setCompany(null);
    }

    // helper methods to add/remove corporate holidays
    public void addCorpHoliday(CorporateHoliday holiday) {
        corporateHolidays.add(holiday);
        holiday.setCompany(this);
    }

    public void removeCorpHoliday(CorporateHoliday holiday) {
        corporateHolidays.remove(holiday);
        holiday.setCompany(null);
    }
}

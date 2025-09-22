package com.ptobuddy.model;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name")
    private String lastName = "LNU";

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "encoded_pwd", nullable = false)
    private String encodedPwd;  // Password Encoded

    @Column(name = "current_year_used_vacation", nullable = false)
    private Integer currentYearUsedVacation = 0;

    @Column(name = "current_year_used_sick", nullable = false)
    private Integer currentYearUsedSick = 0;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    // Entity Mappings
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PTOPlan> ptoPlans = new ArrayList<>();

    // helper methods to add/remove pto plans
    public void addCorpHoliday(PTOPlan ptoPlan) {
        ptoPlans.add(ptoPlan);
        ptoPlan.setUser(this);
    }

    public void removeCorpHoliday(PTOPlan ptoPlan) {
        ptoPlans.remove(ptoPlan);
        ptoPlan.setUser(null);
    }
}

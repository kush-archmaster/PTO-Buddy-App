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
@Table(name = "pto_plans")
@Getter
@Setter
@NoArgsConstructor
public class PTOPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "plan_name", nullable = false)
    private String planName;

    @Column(name = "max_consecutive_days")
    private Integer maxConsecutiveDays = 1;

    @Column(name = "planning_start_date", nullable = false)
    private String planningStartDate;

    @Column(name = "planning_end_date", nullable = false)
    private String planningEndDate;

    @Column(name = "preferred_month")
    private List<String> preferredMonths = new ArrayList<>();

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "ptoPlan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlannedLeave> plannedLeaves = new ArrayList<>();

    // helper methods to add/remove planned leaves
    public void addPlannedLeave(PlannedLeave plannedLeave) {
        plannedLeaves.add(plannedLeave);
        plannedLeave.setPtoPlan(this);
    }

    public void removePlannedLeave(PlannedLeave plannedLeave) {
        plannedLeaves.remove(plannedLeave);
        plannedLeave.setPtoPlan(null);
    }
}

package com.ptobuddy.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Table(
        name = "corporate_holidays",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_company_holiday_date",
                        columnNames = {"company_id", "holiday_date"}
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class CorporateHoliday {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    // The date of the holiday
    @Column(name = "holiday_date", nullable = false)
    private String holidayDate;

    @Column(name = "holiday_name", nullable = false)
    private String holidayName;

    @Column(name = "description")
    private String description;

    @Column(name = "is_optional", nullable = false)
    private Boolean isOptional = false;

    @Column(name = "created_at", nullable = false, updatable = false)
    private Timestamp createdAt;

    @Column(name = "modified_at")
    private Timestamp modifiedAt;

    // Entity Mappings
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;
}

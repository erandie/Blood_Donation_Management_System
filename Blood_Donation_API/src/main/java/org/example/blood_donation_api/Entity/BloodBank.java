package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BloodBank {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bloodBank_id;
    private Date date;
    private Double points;

    @OneToMany(cascade =  CascadeType.ALL, mappedBy = "bloodBank")
    private List<Employee> employees = new ArrayList<>();


}

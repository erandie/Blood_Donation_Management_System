package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Blood {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Blood_id;

    private String blood_group;
    private Double blood_points;
    private Date date;

    @ManyToOne
    @JoinColumn(name = "bloodBank_id")
    private BloodBank bloodBank;

    @ManyToOne
    @JoinColumn(name = "donor_id")
    private Donor donor;

    /*@ManyToOne
    @JoinColumn(name = "patientId")
    private Patient patient;*/

}

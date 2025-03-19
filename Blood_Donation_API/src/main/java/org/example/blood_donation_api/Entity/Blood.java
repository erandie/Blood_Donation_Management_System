package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

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
    @JoinColumn(name = "bloodBank_id_fk")
    private BloodBank bloodBank;

}

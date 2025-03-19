package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Donor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Donor_id;
    private String name;
    private String email;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donor")
    private List<Funds> funds = new ArrayList<>();

}

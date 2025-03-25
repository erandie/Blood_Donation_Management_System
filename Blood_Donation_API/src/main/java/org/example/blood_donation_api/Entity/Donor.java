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
    private int donorId;
    private String name;
    private String email;
    private String address;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "donor")
    private List<Funds> funds = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "bloodBank_id")
    private BloodBank bloodBank;

    @OneToMany(mappedBy = "donor", cascade = CascadeType.ALL)
    private List<Blood> blood = new ArrayList<>();

}

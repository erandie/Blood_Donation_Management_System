package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;
    private String name;
    private String email;
    private String address;

    @ManyToOne
    @JoinColumn(name = "bloodBank_id")
    private BloodBank bloodBank;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Funds> funds = new ArrayList<>();

    /*@OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Blood> blood = new ArrayList<>();*/
}

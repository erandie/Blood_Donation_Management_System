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
    private int bloodBankId;
    @Enumerated(EnumType.STRING)
    private BloodTypes bloodType;
    private Double points;

    @OneToMany(mappedBy = "bloodBank", cascade =  CascadeType.ALL)
    private List<Employee> employees = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "receptionist_id", referencedColumnName = "receptionistId")
    private Receptionist receptionist;

    /*@OneToMany(cascade = CascadeType.ALL, mappedBy = "bloodBank")
    private List<Blood> bloods = new ArrayList<>();
*/


}

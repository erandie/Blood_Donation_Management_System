package org.example.blood_donation_api.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Receptionist {
    @Id
    private int receptionistId;

    private String name;
    private String email;
    private String address;

    /*@OneToOne(mappedBy = "receptionist")
    private BloodBank bloodBank;*/
}

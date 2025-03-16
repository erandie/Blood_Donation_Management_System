package org.example.blood_donation_api.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Blood {
    @Id
    private int Blood_id;
    private String blood_group;
    private Double blood_points;
    private Date date;

}

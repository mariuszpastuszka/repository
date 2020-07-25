package pl.sda.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SdaUser {
    @Id
    private String pesel;
    private String name;
    private String assignedCourse;
    private double coursePrice;
    private boolean payed;
}

package pl.sda.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.persistence.Transient;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
//@Document - for MongoDB
@Table(name = "sda_user")
// SdaUser - sda_user
public class SdaUser {
    @Id
    private String pesel;
    @Column
    private String name;
    private String assignedCourse;
    @Column(name = "price")
    private double coursePrice;
//    @Transient // ignores fields
    private boolean payed;
}

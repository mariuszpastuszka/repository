package pl.sda.repository.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SdaUser {
    private String pesel;
    private String name;
    private String assignedCourse;
    private double coursePrice;
    private boolean payed;
}

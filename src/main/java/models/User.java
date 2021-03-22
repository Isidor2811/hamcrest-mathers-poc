package models;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private Integer id;
    private String firstName;
    private String lastName;
    private Gender gender;
    private BigDecimal salary;
    private double height;


    @Getter
    public enum Gender {
        M,
        F
    }

}

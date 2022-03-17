package shop.style.customer.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.customer.Enums.Sex;

import javax.persistence.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    @Enumerated(EnumType.STRING)
    public Sex sex;

    @Column(unique = true)
    private String CPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Column(unique = true)
    private String email;

    private String password;

    private boolean active = true;
}

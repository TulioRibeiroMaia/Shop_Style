package shop.style.customer.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.style.customer.Enums.Sex;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {

    private Long id;

    private String firstName;

    private String lastName;

    public Sex sex;

    private String CPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String email;

    private boolean active = true;


}

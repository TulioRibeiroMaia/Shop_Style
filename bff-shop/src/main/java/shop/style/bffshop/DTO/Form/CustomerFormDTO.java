package shop.style.bffshop.DTO.Form;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFormDTO {

    private Long id;

    private String firstName;

    private String lastName;

    public String sex;

    private String CPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    private String email;

    private String password;

    private Boolean active = true;
}

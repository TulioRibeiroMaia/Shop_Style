package shop.style.customer.DTO.FormDTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import shop.style.customer.Enums.Sex;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerFormDTO {

    @NotBlank
    @Size(min = 3, message = "O primeiro nome precisa conter no mínimo 3 caracteres")
    private String firstName;

    @NotBlank
    @Size(min = 3, message = "O último nome precisa conter no mínimo 3 caracteres")
    private String lastName;

    @NotNull
    public Sex sex;

    @NotBlank
    @CPF
    private String CPF;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate birthDate;

    @Email(message = "O email não é válido")
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 8, message = "A senha precisa conter no mínimo 8 caracteres")
    private String password;

    @NotNull
    private Boolean active = true;
}

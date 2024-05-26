package unipar.aluno.mywebapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @NotBlank(message = "Email deve ser informado")
    @NotNull(message = "Email deve ser informado")
    @Size(min = 3, message = "Deve ser informado no mínimo 3 caracteres")
    @Size(max = 100, message = "Informar no máximo 100 caracteres")
    @Column(nullable = false, unique = true, name = "email", length = 100 )
    private String email;

    @NotBlank(message = "Senha deve ser informada")
    @NotNull(message = "Senha deve ser informada")
    @Size(min = 6, message = "Senha deve ter no mínimo 6 caracteres")
    @Size(max = 1000, message = "Senha deve ter no máximo 1000 caracteres")
    @Column(nullable = false, name = "password", length = 1000)
    private String password;

    @NotBlank(message = "Nome deve ser informado")
    @NotNull(message = "Nome deve ser informado")
    @Size(min = 3, message = "Nome deve ter no mínimo 3 caracteres")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, name = "name", length = 100)
    private String name;

    @NotNull(message = "O campo ativo deve ser informado")
    @Column(name = "ativo")
    private Boolean enabled;


}

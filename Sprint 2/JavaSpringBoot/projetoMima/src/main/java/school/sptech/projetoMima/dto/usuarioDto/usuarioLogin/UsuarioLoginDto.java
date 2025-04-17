package school.sptech.projetoMima.dto.usuarioDto.usuarioLogin;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class UsuarioLoginDto {

    @Email
    @Schema(description = "Email de Usuário", example = "rozin@gmail.com")
    private String email;

    @Size(min = 5, max = 100)
    @Schema(description = "Senha do usuário", example = "******")
    private String senha;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

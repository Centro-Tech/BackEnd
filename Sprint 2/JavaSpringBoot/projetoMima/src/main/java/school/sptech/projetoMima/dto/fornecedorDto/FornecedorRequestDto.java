package school.sptech.projetoMima.dto.fornecedorDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class FornecedorRequestDto {
    @NotBlank
    @NotNull
    @Size(max = 100, min = 1)
    private String nome;

    @NotBlank
    @NotNull
    @Size(max = 11, min = 11)
    private String telefone;

    @NotBlank
    @NotNull
    @Size(max = 100, min = 1)
    @Email
    private String email;

    public @NotBlank @NotNull @Size(max = 100, min = 1) String getNome() {
        return nome;
    }

    public void setNome(@NotBlank @NotNull @Size(max = 100, min = 1) String nome) {
        this.nome = nome;
    }

    public @NotBlank @NotNull @Size(max = 11, min = 11) String getTelefone() {
        return telefone;
    }

    public void setTelefone(@NotBlank @NotNull @Size(max = 11, min = 11) String telefone) {
        this.telefone = telefone;
    }

    public @NotBlank @NotNull @Size(max = 100, min = 1) @Email String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank @NotNull @Size(max = 100, min = 1) @Email String email) {
        this.email = email;
    }
}

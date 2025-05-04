package school.sptech.projetoMima.dto.fornecedorDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FornecedorRequestDto {

    @NotBlank
    @Size(max = 100, min = 1)
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11)
    private String telefone;

    @NotBlank
    @Size(max = 100, min = 1)
    @Email
    private String email;

    public FornecedorRequestDto() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

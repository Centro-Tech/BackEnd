package school.sptech.projetoMima.versãoAntiga;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Entity
@Schema(description = "Entidade que representa um proprietário ou sócio da empresa.")
public class ProprietarioSocio extends school.sptech.projetoMima.versãoAntiga.Usuario {

    @Schema(description = "Data em que o proprietário ou sócio foi registrado no sistema", example = "2024-01-15", type = "string", format = "date")
    private LocalDate dataDeRegistro;

    @NotBlank
    @Size(min = 3, max = 50)
    @Schema(description = "Papel ou função do proprietário/sócio na empresa, como por exemplo 'Administrador', 'Financeiro', etc.", example = "Administrador", type = "string", minLength = 3, maxLength = 50, required = true)
    private String papel;

    public ProprietarioSocio() {
    }

    public ProprietarioSocio(String nome, String cpf, String email, String telefone, String endereco, LocalDate dataDeRegistro, String papel) {
        super(nome, cpf, email, telefone, endereco);
        this.dataDeRegistro = dataDeRegistro;
        this.papel = papel;
    }

    public LocalDate getDataDeRegistro() {
        return dataDeRegistro;
    }

    public void setDataDeRegistro(LocalDate dataDeRegistro) {
        this.dataDeRegistro = dataDeRegistro;
    }

    public String getPapel() {
        return papel;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }
}

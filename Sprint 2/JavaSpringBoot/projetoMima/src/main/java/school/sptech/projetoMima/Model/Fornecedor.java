package school.sptech.projetoMima.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Schema(description = "Entidade que representa um fornecedor do sistema. Um fornecedor é responsável por fornecer vestuários à loja. Contém dados de identificação e contato.")
public class Fornecedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único do fornecedor gerado automaticamente pelo sistema", example = "1", type = "integer", format = "int32", required = true)
    private Integer id;

    @Schema(description = "Nome completo ou razão social do fornecedor", example = "Empresa XYZ LTDA", type = "string", maxLength = 100, required = true)
    private String nome;

    @Schema(description = "Número de CNPJ do fornecedor contendo apenas dígitos, sem formatação", example = "12345678000190", type = "string", pattern = "\\d{14}", required = true)
    private String cnpj;

    @Schema(description = "Endereço de e-mail para contato com o fornecedor", example = "contato@empresa.com", type = "string", format = "email", required = true)
    private String email;

    @Schema(description = "Número de telefone do fornecedor com DDD", example = "11987654321", type = "string", required = true)
    private String telefone;

    @Schema(description = "Endereço físico do fornecedor, incluindo rua, número e cidade", example = "Rua das Flores, 123, São Paulo", type = "string", required = true)
    private String endereco;

    @Schema(description = "Data em que o fornecedor foi cadastrado no sistema", example = "2024-04-01", type = "string", format = "date", required = true)
    private LocalDate dataRegistro;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

}

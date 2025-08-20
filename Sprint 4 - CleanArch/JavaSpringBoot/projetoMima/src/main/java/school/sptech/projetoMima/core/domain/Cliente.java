package school.sptech.projetoMima.core.domain;

import school.sptech.projetoMima.core.domain.shared.Email;
import school.sptech.projetoMima.core.domain.shared.Telefone;
import school.sptech.projetoMima.core.domain.shared.Cpf;

public class Cliente {

    private final Integer idCliente;
    private final String nome;
    private final Telefone telefone;
    private final Cpf cpf;
    private final Email email;
    private final String endereco;

    public Cliente(Integer idCliente, String nome, String telefone, String cpf, String email, String endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = new Telefone(telefone);
        this.cpf = new Cpf(cpf);
        this.email = new Email(email);
        this.endereco = endereco;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public String getEndereco() {
        return endereco;
    }

    @Override
    public String toString() {
        return String.format(
                "Cliente{idCliente=%d, nome='%s', telefone='%s', cpf='%s', email='%s', endereco='%s'}",
                idCliente, nome, telefone, cpf, email, endereco
        );
    }
}

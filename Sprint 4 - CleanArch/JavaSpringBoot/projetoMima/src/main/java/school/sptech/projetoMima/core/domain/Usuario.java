package school.sptech.projetoMima.core.domain;

import school.sptech.projetoMima.core.domain.shared.Cpf;
import school.sptech.projetoMima.core.domain.shared.Email;
import school.sptech.projetoMima.core.domain.shared.Telefone;

public class Usuario {

    private final Integer id;
    private final String nome;
    private final Cpf cpf;
    private final Email email;
    private final Telefone telefone;
    private final String endereco;
    private final String senha;
    private final String cargo;

    public Usuario(Integer id, String nome, String cpf, String email, String telefone, String endereco, String senha, String cargo) {
        this.id = id;
        this.nome = nome;
        this.cpf = new Cpf(cpf);
        this.email = new Email(email);
        this.telefone = new Telefone(telefone);
        this.endereco = endereco;
        this.senha = senha;
        this.cargo = cargo;
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Cpf getCpf() {
        return cpf;
    }

    public Email getEmail() {
        return email;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getSenha() {
        return senha;
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public String toString() {
        return String.format(
                "Usuario{id=%d, nome='%s', cpf='%s', email='%s', telefone='%s', endereco='%s', cargo='%s'}",
                id, nome, cpf, email, telefone, endereco, cargo
        );
    }
}

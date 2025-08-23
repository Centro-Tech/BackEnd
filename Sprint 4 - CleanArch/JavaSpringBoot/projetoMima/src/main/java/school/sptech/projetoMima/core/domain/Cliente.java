package school.sptech.projetoMima.core.domain;

import school.sptech.projetoMima.core.domain.shared.Email;
import school.sptech.projetoMima.core.domain.shared.Endereco;
import school.sptech.projetoMima.core.domain.shared.Telefone;
import school.sptech.projetoMima.core.domain.shared.Cpf;

public class Cliente {

    private final Integer idCliente;
    private final String nome;
    private final Telefone telefone;
    private final Cpf cpf;
    private final Email email;
    private final Endereco endereco;

    public Cliente(Integer idCliente, String nome, String telefone, String cpf, String email, Endereco endereco) {
        this(idCliente, nome,
                new Telefone(telefone),
                new Cpf(cpf),
                new Email(email),
                endereco);
    }

    public Cliente(Integer idCliente, String nome, Telefone telefone, Cpf cpf, Email email, Endereco endereco) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.endereco = endereco;
    }

    public Integer getIdCliente() { return idCliente; }
    public String getNome() { return nome; }
    public Telefone getTelefone() { return telefone; }
    public Cpf getCpf() { return cpf; }
    public Email getEmail() { return email; }
    public Endereco getEndereco() { return endereco; }

    public Cliente atualizarDados(String nome, String telefone, String cpf, String email, Endereco endereco) {
        return new Cliente(
                this.idCliente,
                nome != null ? nome : this.nome,
                telefone != null ? new Telefone(telefone) : this.telefone,
                cpf != null ? new Cpf(cpf) : this.cpf,
                email != null ? new Email(email) : this.email,
                endereco != null ? endereco : this.endereco
        );
    }

    @Override
    public String toString() {
        return String.format(
                "Cliente{idCliente=%d, nome='%s', telefone='%s', cpf='%s', email='%s', endereco='%s'}",
                idCliente,
                nome,
                telefone.getValor(),
                cpf.getNumero(),
                email.getValor(),
                endereco
        );
    }
}

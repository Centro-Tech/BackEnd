package school.sptech.projetoMima.core.domain;

import school.sptech.projetoMima.core.domain.shared.Email;
import school.sptech.projetoMima.core.domain.shared.Telefone;

public class Fornecedor {

    private final Integer id;
    private final String nome;
    private final Telefone telefone;
    private final Email email;

    public Fornecedor(Integer id, String nome, String telefone, String email) {
        this.id = id;
        this.nome = nome;
        this.telefone = new Telefone(telefone);
        this.email = new Email(email);
    }

    public Integer getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public Email getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return String.format(
                "Fornecedor{id=%d, nome='%s', telefone='%s', email='%s'}",
                id, nome, telefone, email
        );
    }
}

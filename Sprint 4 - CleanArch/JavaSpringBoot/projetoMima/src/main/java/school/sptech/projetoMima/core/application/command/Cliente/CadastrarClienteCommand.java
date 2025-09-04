package school.sptech.projetoMima.core.application.command.Cliente;

public class CadastrarClienteCommand {
    public final String nome;
    public final String telefone;
    public final String CPF;
    public final String email;
    public final String endereco;

    public CadastrarClienteCommand(String nome, String telefone, String CPF, String email, String endereco) {
        this.nome = nome;
        this.telefone = telefone;
        this.CPF = CPF;
        this.email = email;
        this.endereco = endereco;
    }
}

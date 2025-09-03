package school.sptech.projetoMima.core.application.command.Cliente;

public record CadastrarClienteCommand(
        String nome,
        String email,
        String cpf,
        String telefone
) {
}

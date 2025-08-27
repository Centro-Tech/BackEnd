package school.sptech.projetoMima.core.application.command.Cliente;

public record CriarClienteCommand(
        String nome,
        String telefone,
        String cpf,
        String email,
        String endereco
) { }

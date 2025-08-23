package school.sptech.projetoMima.core.adapter.cliente.command;

public record CadastrarClienteCommand(
        String nome,
        String cpf,
        String telefone,
        String email,
        String endereco
) {}
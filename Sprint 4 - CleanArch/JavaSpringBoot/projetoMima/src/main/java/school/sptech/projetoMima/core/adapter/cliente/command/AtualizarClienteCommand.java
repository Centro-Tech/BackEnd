package school.sptech.projetoMima.core.adapter.cliente.command;

public record AtualizarClienteCommand(
        Integer id,
        String nome,
        String cpf,
        String telefone,
        String email,
        String endereco
) {}
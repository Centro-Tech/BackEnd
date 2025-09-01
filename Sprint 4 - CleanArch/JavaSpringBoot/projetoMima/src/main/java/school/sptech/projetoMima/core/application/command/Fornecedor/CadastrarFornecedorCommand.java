package school.sptech.projetoMima.core.application.command.Fornecedor;

public record CadastrarFornecedorCommand(Integer id,
        String nome,
        String telefone,
        String email) {
}

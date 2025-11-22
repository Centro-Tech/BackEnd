package school.sptech.projetoMima.core.usecase;

import org.springframework.stereotype.Service;
import school.sptech.projetoMima.core.adapter.Fornecedor.FornecedorGateway;
import school.sptech.projetoMima.core.domain.Fornecedor;

@Service
public class AtualizarFornecedorUseCase {

    private final FornecedorGateway fornecedorGateway;

    public AtualizarFornecedorUseCase(FornecedorGateway fornecedorGateway) {
        this.fornecedorGateway = fornecedorGateway;
    }

    public Fornecedor atualizarFornecedor(Integer id, Fornecedor fornecedorAtualizado) {
        Fornecedor fornecedorExistente = fornecedorGateway.findById(id);
        if (fornecedorExistente == null) {
            throw new RuntimeException("Fornecedor não encontrado");
        }

        fornecedorExistente.setNome(fornecedorAtualizado.getNome());
        fornecedorExistente.setTelefone(fornecedorAtualizado.getTelefone());
        fornecedorExistente.setEmail(fornecedorAtualizado.getEmail());

        return fornecedorGateway.update(id, fornecedorExistente);
    }
}

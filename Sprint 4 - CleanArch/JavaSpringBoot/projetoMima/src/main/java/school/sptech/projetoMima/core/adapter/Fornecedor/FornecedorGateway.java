package school.sptech.projetoMima.core.adapter.Fornecedor;

import school.sptech.projetoMima.core.domain.Fornecedor;

public interface FornecedorGateway {

    Fornecedor save(Fornecedor fornecedor);

    boolean existsByNome(String nome);

    boolean isNullOrEmpty(String str);
}

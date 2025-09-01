package school.sptech.projetoMima.core.adapter.Fornecedor;

import school.sptech.projetoMima.core.domain.Fornecedor;

import java.util.List;

public interface FornecedorGateway {

    Fornecedor save(Fornecedor fornecedor);

    boolean existsByNome(String nome);

    boolean isNullOrEmpty(String str);

    boolean existsById(Integer id);

    Fornecedor findById(Integer id);

    List<Fornecedor> findAll();

    void deleteById(Integer id);
}

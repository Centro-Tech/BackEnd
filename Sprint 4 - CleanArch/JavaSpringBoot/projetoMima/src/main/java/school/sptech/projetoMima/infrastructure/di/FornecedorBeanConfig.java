package school.sptech.projetoMima.infrastructure.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.projetoMima.core.application.usecase.Fornecedor.CadastrarFornecedorUseCase;
import school.sptech.projetoMima.infrastructure.persistance.FornecedorPersistance.FornecedorJpaAdapter;

@Configuration
public class FornecedorBeanConfig {

    @Bean public CadastrarFornecedorUseCase criarFornecedorUseCase(FornecedorJpaAdapter adapter) {
        return new CadastrarFornecedorUseCase(adapter);
    }
}

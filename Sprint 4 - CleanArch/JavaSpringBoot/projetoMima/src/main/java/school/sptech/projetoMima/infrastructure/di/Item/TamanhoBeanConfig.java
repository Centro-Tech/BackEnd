package school.sptech.projetoMima.infrastructure.di.Item;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.BuscarTamanhoPorIdUseCase;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.CriarTamanhoUseCase;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.DeletarTamanhoUseCase;
import school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.auxiliares.Tamanho.TamanhoJpaAdapter;
import school.sptech.projetoMima.core.application.usecase.Item.auxiliares.TamanhoUseCase.ListarTamanhosUseCase;

@Configuration
public class TamanhoBeanConfig {

    @Bean public CriarTamanhoUseCase criarTamanhoUseCase(TamanhoJpaAdapter adapter) {
        return new CriarTamanhoUseCase(adapter);
    }

    @Bean public DeletarTamanhoUseCase deletarTamanhoUseCase(TamanhoJpaAdapter adapter) {
        return new DeletarTamanhoUseCase(adapter);
    }

    @Bean public ListarTamanhosUseCase listarTamanhosUseCase(TamanhoJpaAdapter adapter) {
        return new ListarTamanhosUseCase(adapter);
    }

    @Bean public BuscarTamanhoPorIdUseCase buscarTamanhoPorIdUseCase(TamanhoJpaAdapter adapter) {
        return new BuscarTamanhoPorIdUseCase(adapter);
    }

}

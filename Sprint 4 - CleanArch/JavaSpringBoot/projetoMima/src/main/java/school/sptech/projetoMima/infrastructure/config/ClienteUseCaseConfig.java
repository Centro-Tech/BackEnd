package school.sptech.projetoMima.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.sptech.projetoMima.core.adapter.cliente.ClienteMutation;
import school.sptech.projetoMima.core.adapter.cliente.ClienteRead;
import school.sptech.projetoMima.core.application.usecase.cliente.*;

@Configuration
public class ClienteUseCaseConfig {

    @Bean
    public BuscarClienteUseCase buscarClienteUseCase(ClienteRead read) {
        return new BuscarClienteUseCase(read);
    }

    @Bean
    public CadastrarClienteUseCase cadastrarClienteUseCase(ClienteMutation mutation) {
        return new CadastrarClienteUseCase(mutation);
    }

    @Bean
    public AtualizarClienteUseCase atualizarClienteUseCase(ClienteRead read, ClienteMutation mutation) {
        return new AtualizarClienteUseCase(read, mutation);
    }

    @Bean
    public ListarClientesUseCase listarClientesUseCase(ClienteRead read) {
        return new ListarClientesUseCase(read);
    }

    @Bean
    public ExcluirClienteUseCase excluirClienteUseCase(ClienteRead read, ClienteMutation mutation) {
        return new ExcluirClienteUseCase(read, mutation);
    }
}
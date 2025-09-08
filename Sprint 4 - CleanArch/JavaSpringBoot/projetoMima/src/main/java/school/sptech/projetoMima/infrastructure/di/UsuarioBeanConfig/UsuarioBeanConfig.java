package school.sptech.projetoMima.infrastructure.di.UsuarioBeanConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import school.sptech.projetoMima.core.adapter.Usuario.AuthGateway;
import school.sptech.projetoMima.core.adapter.Usuario.CryptoGateway;
import school.sptech.projetoMima.core.adapter.Usuario.TokenGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;
import school.sptech.projetoMima.core.application.usecase.Usuario.*;
import school.sptech.projetoMima.infrastructure.config.GerenciadorTokenJwt;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.JwtTokenAdapter;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.SpringAuthAdapter;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.SpringCryptoAdapter;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.UsuarioJpaAdapter;
import school.sptech.projetoMima.infrastructure.persistance.UsuarioPersistance.UsuarioRepository;

@Configuration
public class UsuarioBeanConfig {

    // Os gateways são definidos via @Component nos adapters, não precisam de @Bean aqui

    @Bean
    public CriarUsuarioUseCase criarUsuarioUseCase(UsuarioGateway gateway, CryptoGateway crypto) {
        return new CriarUsuarioUseCase(gateway, crypto);
    }

    @Bean
    public AtualizarUsuarioUseCase atualizarUsuarioUseCase(UsuarioGateway gateway) {
        return new AtualizarUsuarioUseCase(gateway);
    }

    @Bean
    public ListarUsuariosUseCase listarUsuariosUseCase(UsuarioGateway gateway) {
        return new ListarUsuariosUseCase(gateway);
    }

    @Bean
    public BuscarUsuarioPorIdUseCase buscarUsuarioPorIdUseCase(UsuarioGateway gateway) {
        return new BuscarUsuarioPorIdUseCase(gateway);
    }

    @Bean
    public ExcluirUsuarioUseCase excluirUsuarioUseCase(UsuarioGateway gateway) {
        return new ExcluirUsuarioUseCase(gateway);
    }

    @Bean
    public AutenticarUsuarioUseCase autenticarUsuarioUseCase(AuthGateway auth, TokenGateway token, UsuarioGateway gateway) {
        return new AutenticarUsuarioUseCase(auth, token, gateway);
    }

    @Bean
    public TrocarSenhaUseCase trocarSenhaUseCase(UsuarioGateway gateway, CryptoGateway crypto) {
        return new TrocarSenhaUseCase(gateway, crypto);
    }
}

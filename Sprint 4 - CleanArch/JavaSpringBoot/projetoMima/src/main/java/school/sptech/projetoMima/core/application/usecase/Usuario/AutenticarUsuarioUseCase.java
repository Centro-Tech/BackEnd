package school.sptech.projetoMima.core.application.usecase.Usuario;

import school.sptech.projetoMima.core.adapter.Usuario.AuthGateway;
import school.sptech.projetoMima.core.adapter.Usuario.TokenGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;

public class AutenticarUsuarioUseCase {

    private final AuthGateway authGateway;
    private final TokenGateway tokenGateway;
    private final UsuarioGateway usuarioGateway;

    public AutenticarUsuarioUseCase(AuthGateway authGateway, TokenGateway tokenGateway, UsuarioGateway usuarioGateway) {
        this.authGateway = authGateway;
        this.tokenGateway = tokenGateway;
        this.usuarioGateway = usuarioGateway;
    }

    public record Resultado(Usuario usuario, String token) {}

    public Resultado executar(LoginUsuarioCommand cmd) {
        Object authentication = authGateway.authenticate(cmd.email, cmd.senha);

        Usuario usuario = usuarioGateway.findByEmail(cmd.email)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Email do usuário não cadastrado"));

        String token = tokenGateway.generate(authentication);
        return new Resultado(usuario, token);
    }
}

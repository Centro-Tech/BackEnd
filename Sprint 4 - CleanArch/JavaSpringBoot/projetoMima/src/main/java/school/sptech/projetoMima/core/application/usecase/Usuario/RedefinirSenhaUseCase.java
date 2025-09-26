package school.sptech.projetoMima.core.application.usecase.Usuario;

import school.sptech.projetoMima.core.adapter.Usuario.CryptoGateway;
import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;

public class RedefinirSenhaUseCase {

    private final UsuarioGateway usuarioGateway;
    private final CryptoGateway crypto;

    public RedefinirSenhaUseCase(UsuarioGateway usuarioGateway, CryptoGateway crypto) {
        this.usuarioGateway = usuarioGateway;
        this.crypto = crypto;
    }

    public void executar(String token, String novaSenha) {
        var usuario = usuarioGateway.findByRecoveryToken(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        String senhaCriptografada = crypto.encode(novaSenha);
        usuario.setSenha(senhaCriptografada);
        usuarioGateway.save(usuario);
    }
}

package school.sptech.projetoMima.core.application.usecase.Usuario;

import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;
import school.sptech.projetoMima.core.application.command.Usuario.AtualizarUsuarioCommand;

public class AtualizarUsuarioUseCase {
    private final UsuarioGateway gateway;

    public AtualizarUsuarioUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public Usuario executar(AtualizarUsuarioCommand cmd) {
        Usuario existente = gateway.findById(cmd.id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Funcionário com o ID " + cmd.id + " não encontrado!"));

        existente.setNome(cmd.nome);
        existente.setTelefone(cmd.telefone);
        existente.setEmail(cmd.email);
        existente.setCargo(cmd.cargo);
        existente.setEndereco(cmd.endereco);

        return gateway.save(existente);
    }
}

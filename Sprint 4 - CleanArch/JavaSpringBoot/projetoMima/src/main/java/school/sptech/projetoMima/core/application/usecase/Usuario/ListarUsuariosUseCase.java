package school.sptech.projetoMima.core.application.usecase.Usuario;

import school.sptech.projetoMima.core.adapter.Usuario.UsuarioGateway;

public class ListarUsuariosUseCase {

    private final UsuarioGateway gateway;

    public ListarUsuariosUseCase(UsuarioGateway gateway) {
        this.gateway = gateway;
    }

    public List<Usuario> executar() {
        List<Usuario> lista = gateway.findAll();
        if (lista.isEmpty()) throw new UsuarioListaVaziaException("Lista de funcionários está vazia");
        return lista;
    }
}

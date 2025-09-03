package school.sptech.projetoMima.core.application.command.Venda;

import java.util.List;

public class CreateVendaCommand {
    private Integer clienteId;
    private Integer usuarioId;
    private List<Integer> itensVendaIds;

    public CreateVendaCommand(Integer clienteId, Integer usuarioId, List<Integer> itensVendaIds) {
        this.clienteId = clienteId;
        this.usuarioId = usuarioId;
        this.itensVendaIds = itensVendaIds;
    }

    public Integer getClienteId() { return clienteId; }
    public Integer getUsuarioId() { return usuarioId; }
    public List<Integer> getItensVendaIds() { return itensVendaIds; }
}

package school.sptech.projetoMima.core.application.usecase.Cliente;

import school.sptech.projetoMima.core.adapter.Cliente.ClienteMutation;
import school.sptech.projetoMima.core.application.command.Cliente.CriarClienteCommand;
import school.sptech.projetoMima.core.application.dto.clienteDto.ClienteCadastroDto;
import school.sptech.projetoMima.core.application.dto.clienteDto.ClienteMapper;
import school.sptech.projetoMima.core.domain.Cliente;

public class CadastrarClienteUseCase {

    private final ClienteMutation clienteMutation;

    public CadastrarClienteUseCase(ClienteMutation clienteMutation) {
        this.clienteMutation = clienteMutation;
    }

    public Cliente execute(CriarClienteCommand dto) {
        Cliente cliente = ClienteMapper.toEntity(dto);
        return clienteMutation.save(cliente);
    }
}

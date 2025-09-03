package school.sptech.projetoMima.core.application.dto.clienteDto;

import school.sptech.projetoMima.core.application.command.Cliente.CriarClienteCommand;
import school.sptech.projetoMima.core.domain.Cliente;

public class ClienteMapper {

    public static ClienteCadastroDto toCadastroDto(Cliente cliente) {
        ClienteCadastroDto dto = new ClienteCadastroDto();
        dto.setNome(cliente.getNome());
        dto.setTelefone(cliente.getTelefone());
        dto.setCPF(cliente.getCPF());
        dto.setEmail(cliente.getEmail());
        dto.setEndereco(cliente.getEndereco());
        return dto;
    }

    public static ClienteResumidoDto toResumidoDto(Cliente cliente) {
        ClienteResumidoDto dto = new ClienteResumidoDto();
        dto.setIdCliente(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setTelefone(cliente.getTelefone());
        return dto;
    }

    public static ClienteListagemDto toList(Cliente cliente) {
        ClienteListagemDto dto = new ClienteListagemDto();
        dto.setIdCliente(cliente.getId());
        dto.setNome(cliente.getNome());
        dto.setEmail(cliente.getEmail());
        return dto;
    }

    public static Cliente toEntity(CriarClienteCommand dto) {
        Cliente cliente = new Cliente();
        cliente.setNome(dto.getNome());
        cliente.setTelefone(dto.getTelefone());
        cliente.setCPF(dto.getCPF());
        cliente.setEmail(dto.getEmail());
        cliente.setEndereco(dto.getEndereco());
        return cliente;
    }
}

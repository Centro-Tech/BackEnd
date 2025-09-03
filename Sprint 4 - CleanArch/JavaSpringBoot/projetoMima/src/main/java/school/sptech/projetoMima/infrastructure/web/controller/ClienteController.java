package school.sptech.projetoMima.infrastructure.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.core.application.command.Cliente.AtualizarClienteCommand;
import school.sptech.projetoMima.core.application.command.Cliente.CadastrarClienteCommand;
import school.sptech.projetoMima.core.application.command.Cliente.ExcluirClienteCommand;
import school.sptech.projetoMima.core.application.command.Cliente.BuscarClientePorIdCommand;
import school.sptech.projetoMima.core.application.usecase.Cliente.*;
import school.sptech.projetoMima.core.application.dto.clienteDto.ClienteListagemDto;
import school.sptech.projetoMima.core.application.dto.clienteDto.ClienteMapper;
import school.sptech.projetoMima.core.application.dto.clienteDto.ClienteResumidoDto;
import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarClienteUseCase;
    private final AtualizarClienteUseCase atualizarClienteUseCase;
    private final ExcluirClienteUseCase excluirClienteUseCase;
    private final BuscarClientePorIdUseCase buscarClientePorIdUseCase;
    private final ListarClientesUseCase listarClientesUseCase;

    public ClienteController(CadastrarClienteUseCase cadastrarClienteUseCase,
                             AtualizarClienteUseCase atualizarClienteUseCase,
                             ExcluirClienteUseCase excluirClienteUseCase,
                             BuscarClientePorIdUseCase buscarClientePorIdUseCase,
                             ListarClientesUseCase listarClientesUseCase) {
        this.cadastrarClienteUseCase = cadastrarClienteUseCase;
        this.atualizarClienteUseCase = atualizarClienteUseCase;
        this.excluirClienteUseCase = excluirClienteUseCase;
        this.buscarClientePorIdUseCase = buscarClientePorIdUseCase;
        this.listarClientesUseCase = listarClientesUseCase;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteResumidoDto> buscarPorId(@PathVariable Integer id) {
        Cliente cliente = buscarClientePorIdUseCase.execute(new BuscarClientePorIdCommand(id));
        return ResponseEntity.ok(ClienteMapper.toResumidoDto(cliente));
    }

    //pegar a dto e converter em command depois

    @GetMapping
    public ResponseEntity<List<ClienteListagemDto>> listar() {
        List<Cliente> clientes = listarClientesUseCase.execute();
        List<ClienteListagemDto> response = clientes.stream()
                .map(ClienteMapper::toList)
                .toList();
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ClienteResumidoDto> cadastrar(@RequestBody CadastrarClienteCommand command) {
        Cliente novoCliente = cadastrarClienteUseCase.execute(command);
        return ResponseEntity.status(201).body(ClienteMapper.toResumidoDto(novoCliente));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteResumidoDto> atualizar(@RequestBody AtualizarClienteCommand command, @PathVariable Integer id) {
        AtualizarClienteCommand commandComId = new AtualizarClienteCommand(
                id,
                command.nome(),
                command.email(),
                command.cpf(),
                command.telefone()
        );

        Cliente clienteAtualizado = atualizarClienteUseCase.execute(commandComId);
        return ResponseEntity.ok(ClienteMapper.toResumidoDto(clienteAtualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Integer id) {
        excluirClienteUseCase.execute(new ExcluirClienteCommand(id));
        return ResponseEntity.noContent().build();
    }
}

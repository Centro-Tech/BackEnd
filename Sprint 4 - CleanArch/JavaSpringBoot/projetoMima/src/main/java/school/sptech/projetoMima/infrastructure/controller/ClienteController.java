package school.sptech.projetoMima.infrastructure.controller;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.sptech.projetoMima.core.adapter.cliente.command.AtualizarClienteCommand;
import school.sptech.projetoMima.core.adapter.cliente.command.CadastrarClienteCommand;
import school.sptech.projetoMima.core.application.usecase.cliente.*;
import school.sptech.projetoMima.core.domain.Cliente;

import java.util.List;


@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final CadastrarClienteUseCase cadastrarCliente;
    private final BuscarClienteUseCase buscarCliente;
    private final AtualizarClienteUseCase atualizarCliente;
    private final ExcluirClienteUseCase excluirCliente;
    private final ListarClientesUseCase listarClientes;

    public ClienteController(CadastrarClienteUseCase cadastrarCliente,
                             BuscarClienteUseCase buscarCliente,
                             AtualizarClienteUseCase atualizarCliente,
                             ExcluirClienteUseCase excluirCliente,
                             ListarClientesUseCase listarClientes) {
        this.cadastrarCliente = cadastrarCliente;
        this.buscarCliente = buscarCliente;
        this.atualizarCliente = atualizarCliente;
        this.excluirCliente = excluirCliente;
        this.listarClientes = listarClientes;
    }

    @PostMapping
    public ResponseEntity<Cliente> cadastrar(@RequestBody CadastrarClienteCommand cmd) {
        Cliente cliente = cadastrarCliente.executar(cmd);
        return ResponseEntity.ok(cliente);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Integer id) {
        try {
            Cliente cliente = buscarCliente.executar(id);
            return ResponseEntity.ok(cliente);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@PathVariable Integer id,
                                             @RequestBody AtualizarClienteCommand cmd) {
        AtualizarClienteCommand comandoCorrigido = new AtualizarClienteCommand(
                id, cmd.nome(), cmd.cpf(), cmd.telefone(), cmd.email(), cmd.endereco()
        );
        Cliente cliente = atualizarCliente.executar(comandoCorrigido);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        excluirCliente.executar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(listarClientes.executar());
    }
}

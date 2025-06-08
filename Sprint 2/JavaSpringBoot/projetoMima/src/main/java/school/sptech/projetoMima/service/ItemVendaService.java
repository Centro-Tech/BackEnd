package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.itemDto.ItemVendaRequestDto;
import school.sptech.projetoMima.entity.Cliente;
import school.sptech.projetoMima.entity.ItemVenda;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.entity.item.Item;
import school.sptech.projetoMima.repository.ClienteRepository;
import school.sptech.projetoMima.repository.ItemRepository;
import school.sptech.projetoMima.repository.ItemVendaRepository;
import school.sptech.projetoMima.repository.UsuarioRepository;

import java.util.List;

@Service
public class ItemVendaService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ItemVendaRepository itemVendaRepository;

    public ItemVenda adicionarAoCarrinho(ItemVendaRequestDto dto) {
        Item item = itemRepository.findById(dto.getItemId())
                .orElseThrow(() -> new RuntimeException("Item não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Usuario funcionario = usuarioRepository.findById(dto.getFuncionarioId())
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

        ItemVenda itemVenda = new ItemVenda();
        itemVenda.setItem(item);
        itemVenda.setQtdParaVender(dto.getQtdParaVender());
        itemVenda.setCliente(cliente);
        itemVenda.setFuncionario(funcionario);
        itemVenda.setFornecedor(item.getFornecedor());
        itemVenda.setVenda(null);

        return itemVendaRepository.save(itemVenda);
    }

    public List<ItemVenda> listarCarrinho(Integer clienteId) {
        return itemVendaRepository.findByClienteIdAndVendaIsNull(clienteId);
    }
}

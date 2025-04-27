package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.itemDto.ItemListDto;
import school.sptech.projetoMima.dto.itemDto.ItemMapper;
import school.sptech.projetoMima.dto.itemDto.ItemResponseDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.CategoriaDto;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.entity.item.Item;
import school.sptech.projetoMima.exception.Item.ItemNaoEncontradoException;
import school.sptech.projetoMima.repository.ItemRepository;
import school.sptech.projetoMima.repository.FornecedorRepository;

import java.util.List;
import java.util.Random;

@Service
public class ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public Item buscarPorId(int id) {
        return itemRepository.findById(id).orElseThrow(() -> new ItemNaoEncontradoException("Item com o id " + id + " não encontrado."));
    }

    public List<Item> listarEstoque() {
        return itemRepository.findAll();
    }

    public ItemResponseDto vender(ItemResponseDto item, int qtd) {
        Item entity = ItemMapper.fromResponseToEntity(item);
        int qtdAtualizada = entity.getQtdEstoque() - qtd;
        entity.setQtdEstoque(qtdAtualizada);
        return ItemMapper.toResponse(entity);
    }

    public void deletar(Item itemParaDeletar) {
        if (itemParaDeletar == null) {
            throw new ItemNaoEncontradoException("Item para deletar não pode ser nulo.");
        }
        itemRepository.delete(itemParaDeletar);
    }

    public boolean existsByCodigo(String codigo) {
        return itemRepository.existsByCodigo(codigo);
    }

    public boolean isCategoriaValida(String nome) {
        return nome.contains("BERMUDA") || nome.contains("BLAZER") || nome.contains("BLUSA") ||
                nome.contains("BRACELETE") || nome.contains("BRINCO") || nome.contains("CALÇA") ||
                nome.contains("CAMISA") || nome.contains("CAMISETA") || nome.contains("CARDIGAN") ||
                nome.contains("CHEMISE") || nome.contains("COLAR") || nome.contains("CONJUNTO") ||
                nome.contains("CROPPED") || nome.contains("ELASTICO") || nome.contains("JAQUETA") ||
                nome.contains("LENÇO") || nome.contains("MACACÃO") || nome.contains("MACAQUINHO") ||
                nome.contains("PARKA") || nome.contains("PONCHO") || nome.contains("PULSEIRA") ||
                nome.contains("REGATA") || nome.contains("SAIA") || nome.contains("SHORT") ||
                nome.contains("TOMARA QUE CAIA") || nome.contains("TRICOT") || nome.contains("T-SHIRT") ||
                nome.contains("VESTIDO");
    }

    public Item cadastrarItem(Item item, Fornecedor fornecedor) {
        String nome = item.getCategoria().getNome().toUpperCase();
        String tamanho = item.getTamanho().getTamanho().toUpperCase();
        String codigoIdentificacao = null;

        if (nome.contains("BERMUDA")) codigoIdentificacao = "BZ";
        else if (nome.contains("BLAZER")) codigoIdentificacao = "BL";
        else if (nome.contains("BLUSA")) codigoIdentificacao = "BL";
        else if (nome.contains("BRACELETE")) codigoIdentificacao = "BR";
        else if (nome.contains("BRINCO")) codigoIdentificacao = "BC";
        else if (nome.contains("CALÇA")) codigoIdentificacao = "CL";
        else if (nome.contains("CAMISA")) codigoIdentificacao = "CA";
        else if (nome.contains("CAMISETA")) codigoIdentificacao = "BL";
        else if (nome.contains("CARDIGAN")) codigoIdentificacao = "TR";
        else if (nome.contains("CHEMISE")) codigoIdentificacao = "CH";
        else if (nome.contains("COLAR")) codigoIdentificacao = "CR";
        else if (nome.contains("CONJUNTO")) codigoIdentificacao = "CO";
        else if (nome.contains("CROPPED")) codigoIdentificacao = "BL";
        else if (nome.contains("ELASTICO")) codigoIdentificacao = "EL";
        else if (nome.contains("JAQUETA")) codigoIdentificacao = "JA";
        else if (nome.contains("LENÇO")) codigoIdentificacao = "LE";
        else if (nome.contains("MACACÃO")) codigoIdentificacao = "MA";
        else if (nome.contains("MACAQUINHO")) codigoIdentificacao = "MA";
        else if (nome.contains("PARKA")) codigoIdentificacao = "PK";
        else if (nome.contains("PONCHO")) codigoIdentificacao = "TR";
        else if (nome.contains("PULSEIRA")) codigoIdentificacao = "PU";
        else if (nome.contains("REGATA")) codigoIdentificacao = "BL";
        else if (nome.contains("SAIA")) codigoIdentificacao = "SA";
        else if (nome.contains("SHORT")) codigoIdentificacao = "SH";
        else if (nome.contains("TOMARA QUE CAIA")) codigoIdentificacao = "BL";
        else if (nome.contains("TRICOT")) codigoIdentificacao = "TR";
        else if (nome.contains("T-SHIRT")) codigoIdentificacao = "BL";
        else if (nome.contains("VESTIDO")) codigoIdentificacao = "VE";

        int numeroAleatorio = 1000000 + new Random().nextInt(9000000);
        String codigoFinal = codigoIdentificacao + numeroAleatorio + tamanho;

        item.setCodigo(codigoFinal);
        item.setFornecedor(fornecedor);

        return itemRepository.save(item);
    }

    public List<Item> filtrarPorCategoria (String categoria) {
        List<Item> itens = itemRepository.findByCategoriaNomeContainsIgnoreCase(categoria);
        return itens;
    }

    public List<Item> filtrarPorFornecedor (String nome) {
        List<Item> itens = itemRepository.findByFornecedorNomeContainsIgnoreCase(nome);
        return itens;
    }

    public List<Item> filtrarPorNome (String nome) {
        List<Item> itens = itemRepository.findByNomeContainsIgnoreCase(nome);
        return itens;
    }
}

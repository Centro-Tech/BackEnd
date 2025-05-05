package school.sptech.projetoMima.dto.itemDto;

import school.sptech.projetoMima.dto.fornecedorDto.FornecedorMapper;
import school.sptech.projetoMima.dto.itemDto.auxiliares.CategoriaDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.CorDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.MaterialDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.TamanhoDto;
import school.sptech.projetoMima.entity.item.Categoria;
import school.sptech.projetoMima.entity.item.Item;
import school.sptech.projetoMima.entity.item.Tamanho;

public class ItemMapper {

    public static ItemResponseDto toResponse (Item item) {
        ItemResponseDto response = new ItemResponseDto();


        response.setCodigo(item.getCodigo());
        response.setNome(item.getNome());
        response.setQtdEstoque(item.getQtdEstoque());

        return response;
    }

    public static ItemListDto toList (Item item) {
        ItemListDto response = new ItemListDto();

        response.setCodigo(item.getCodigo());
        response.setPreco(item.getPreco());
        response.setNome(item.getNome());
        response.setTamanho(item.getTamanho());
        response.setPreco(item.getPreco());
        response.setFornecedor(FornecedorMapper.toResponse(item.getFornecedor()));

        return response;
    }

    public static Item toEntity(ItemRequestDto request) {
        Item item = new Item();

        item.setNome(request.getNome());
        item.setQtdEstoque(request.getQtdEstoque());

        if (request.getTamanho() != null) {
            item.setTamanho(TamanhoDto.toEntity(request.getTamanho()));
        }

        if (request.getCor() != null) {
            item.setCor(CorDto.toEntity(request.getCor()));
        }

        if (request.getMaterial() != null) {
            item.setMaterial(MaterialDto.toEntity(request.getMaterial()));
        }

        if (request.getCategoria() != null) {
            item.setCategoria(CategoriaDto.toEntity(request.getCategoria()));
        }

        item.setPreco(request.getPreco());
        item.setFornecedor(request.getFornecedor());

        return item;
    }

    public static Item fromResponseToEntity (ItemResponseDto request) {
        Item response = new Item();

        response.setCodigo(request.getCodigo());
        response.setNome(request.getNome());
        response.setQtdEstoque(request.getQtdEstoque());

        return response;
    }

    public static Item fromListToEntity (ItemListDto request) {
        Item response = new Item();

        response.setCodigo(request.getCodigo());
        response.setNome(request.getNome());
        response.setTamanho(request.getTamanho());
        response.setQtdEstoque(request.getQtdEstoque());
        response.setPreco(request.getPreco());

        return response;
    }
}

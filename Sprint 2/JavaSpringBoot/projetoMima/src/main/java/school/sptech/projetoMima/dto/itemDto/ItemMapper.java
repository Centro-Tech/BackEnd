package school.sptech.projetoMima.dto.itemDto;

import school.sptech.projetoMima.dto.fornecedorDto.FornecedorMapper;
import school.sptech.projetoMima.dto.itemDto.auxiliares.CategoriaDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.CorDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.MaterialDto;
import school.sptech.projetoMima.dto.itemDto.auxiliares.TamanhoDto;
import school.sptech.projetoMima.entity.Fornecedor;
import school.sptech.projetoMima.entity.item.*;

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
        response.setQtdEstoque(item.getQtdEstoque());
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
        item.setPreco(request.getPreco());

        if (request.getTamanho() != null) {
            Tamanho tamanho = new Tamanho();
            tamanho.setNome(request.getTamanho());
            item.setTamanho(tamanho);
        }

        if (request.getCor() != null) {
            Cor cor = new Cor();
            cor.setNome(request.getCor());
            item.setCor(cor);
        }

        if (request.getMaterial() != null) {
            Material material = new Material();
            material.setNome(request.getMaterial());
            item.setMaterial(material);
        }


        if (request.getCategoria() != null) {
            Categoria categoria = new Categoria();
            categoria.setNome(request.getCategoria());
            item.setCategoria(categoria);
        }

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

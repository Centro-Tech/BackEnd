package school.sptech.projetoMima.core.application.command.Cliente;

import java.math.BigDecimal;

public record CriarItemCommand(
        String nome,
        Integer preco,
        Integer qtdEstoque,
        Integer idTamanho,
        Integer idCor,
        Integer idMaterial,
        Integer idCategoria,
        Integer idFornecedor
) { }

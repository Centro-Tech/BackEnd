package school.sptech.projetoMima.SemCleanArch;

import school.sptech.projetoMima.core.adapter.fornecedor.FornecedorRequestDto;
import school.sptech.projetoMima.core.adapter.fornecedor.FornecedorResponseDto;
import school.sptech.projetoMima.core.domain.Fornecedor;

public class FornecedorMapper {

    public static FornecedorResponseDto toResponse(Fornecedor fornecedor) {
        FornecedorResponseDto response = new FornecedorResponseDto();

        response.setId(fornecedor.getId());
        response.setNome(fornecedor.getNome());
        response.setTelefone(fornecedor.getTelefone());
        response.setEmail(fornecedor.getEmail());

        return response;
    }

    public static Fornecedor toEntity(FornecedorRequestDto fornecedor) {
        Fornecedor response = new Fornecedor();

        response.setNome(fornecedor.getNome());
        response.setEmail(fornecedor.getEmail());
        response.setTelefone(fornecedor.getTelefone());

        return response;
    }





}

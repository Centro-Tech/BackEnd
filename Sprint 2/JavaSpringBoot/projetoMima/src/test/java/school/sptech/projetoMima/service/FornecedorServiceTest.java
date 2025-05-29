package school.sptech.projetoMima.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FornecedorServiceTest {

    @Test
    @DisplayName("Quando tentar cadastrar com todos os campos, deve retornar o fornecedor cadastrado")
    void cadastrarQuandoAcionadoComTodosOsCamposPreenchidosCorretamenteDeveRetornarODtoResponseDoFornecedorCadastrado() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar com todos os campos vazios, deve estourar exceção")
    void cadastrarQuandoAcionadoComCorpoNuloDeveRetornarNullPointerException() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar com apenas o nome preenchido, deve estourar exceção")
    void cadastrarQuandoAcionadoComCorpoIncompletoDeveRetornarNullPointerException() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar com email que não é válido, deve estourar exceção")
    void cadastrarQuandoAcionadoComEmailInvalidoDeveRetornarBadRequest() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar com telefone contendo caracteres não numéricos, deve estourar exceção")
    void cadastrarQuandoAcionadoComTelefoneQueTenhaParentesesDeveRetornarBadRequest() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar com telefone de mais de 11 dígitos, deve estourar exceção")
    void cadastrarQuandoAcionadoComTelefoneQueTenha13DigitosDeveRetornarBadRequest() {

    }

    @Test
    @DisplayName("Quando tentar cadastrar fornecedor já cadastrado, deve estourar exceção")
    void cadastrarQuandoAcionadoComFornecedorJaCadastradoNoSistemaDeveRetornarConflict() {
        
    }

}
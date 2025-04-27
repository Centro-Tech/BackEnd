package school.sptech.projetoMima.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioCadastroDto;
import school.sptech.projetoMima.dto.usuarioDto.UsuarioMapper;
import school.sptech.projetoMima.entity.Usuario;
import school.sptech.projetoMima.exception.Usuario.UsuarioListaVaziaException;
import school.sptech.projetoMima.exception.Usuario.UsuarioNaoEncontradoException;
import school.sptech.projetoMima.exception.Usuario.UsuarioNaoEncontradoException;
import school.sptech.projetoMima.repository.UsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario findFuncionarioById(int id) {
        return usuarioRepository.findById(id).orElseThrow(() -> new UsuarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado!"));
    }

    public Usuario cadastrarFuncionario(UsuarioCadastroDto dto) {
        Usuario usuarioCad = UsuarioMapper.toEntity(dto);
        return usuarioRepository.save(usuarioCad);
    }

    public Usuario atualizarFuncionario(UsuarioCadastroDto dto, Integer id) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNaoEncontradoException("Funcionário com o ID " + id + " não encontrado!"));

        usuarioExistente.setNome(dto.getNome());
        usuarioExistente.setTelefone(dto.getTelefone());
        usuarioExistente.setEmail(dto.getEmail());
        usuarioExistente.setCargo(dto.getCargo());
        usuarioExistente.setEndereco(dto.getEndereco());

        return usuarioRepository.save(usuarioExistente);
    }


    public List<Usuario> listarFuncionarios() {
        List <Usuario> usuarios = new ArrayList<>();
        for(Usuario usuario : usuarioRepository.findAll()) {
            usuarios.add(usuario);
        }

        if(usuarios.isEmpty()){
            throw new UsuarioListaVaziaException("Lista de funcionário está vazia");
        }
        return usuarios;

    }


    public void excluir(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new UsuarioNaoEncontradoException("Curso não encontrado");
        }
        usuarioRepository.deleteById(id);
    }



}

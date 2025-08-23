package school.sptech.projetoMima.SemCleanArch.serviceSemCleanArch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import school.sptech.projetoMima.core.adapter.usuario.UsuarioDetalhesDto;
import school.sptech.projetoMima.core.domain.Usuario;
import school.sptech.projetoMima.SemCleanArch.repositorySemCleanArch.UsuarioRepository;

import java.util.Optional;

@Service
public class AutenticacaoService implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(username);
        if (usuarioOpt.isEmpty()) {

            throw new UsernameNotFoundException(String.format("usuario: %s nao encontrado", username));
        }
        return new UsuarioDetalhesDto(usuarioOpt.get());
    }
}

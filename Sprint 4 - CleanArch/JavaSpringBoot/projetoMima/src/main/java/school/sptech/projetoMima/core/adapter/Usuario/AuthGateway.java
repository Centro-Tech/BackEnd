package school.sptech.projetoMima.core.adapter.Usuario;

public interface AuthGateway {
    Object authenticate(String email, String senha); // retorna o Authentication, mas tipado como Object para não acoplar
}
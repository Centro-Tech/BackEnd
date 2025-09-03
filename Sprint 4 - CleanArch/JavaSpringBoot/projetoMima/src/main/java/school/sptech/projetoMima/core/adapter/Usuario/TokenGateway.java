package school.sptech.projetoMima.core.adapter.Usuario;

public interface TokenGateway {
    String generate(Object authentication); // idem: evita acoplamento com classe de infra
}

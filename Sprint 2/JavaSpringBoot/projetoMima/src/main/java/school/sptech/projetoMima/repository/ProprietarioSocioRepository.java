package school.sptech.projetoMima.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import school.sptech.projetoMima.versãoAntiga.ProprietarioSocio;

@Repository
public interface ProprietarioSocioRepository extends JpaRepository<ProprietarioSocio, Integer> {

}

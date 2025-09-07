package school.sptech.projetoMima.infrastructure.persistance.ItemPersistance.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tamanho")
public class TamanhoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, unique = true)
    private String nome;

    public TamanhoEntity() {}

    public TamanhoEntity(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

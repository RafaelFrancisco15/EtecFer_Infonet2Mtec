package br.com.etecfer.etecfer.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Professor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idProfessor;

    @Column(nullable = false, length = 40)
    private String nomeProfessor;

    @Column(nullable = false, length = 15)
    private String telProfessor;

    // Nullable para permitir que bancos já existentes sejam atualizados sem falhar.
    @Column(length = 11)
    private String cpfProfessor;

    @Column(nullable = false)
    private String graduacaoProfessor;

    @OneToMany
    @JoinColumn(name = "idDisciplina_fk")
    private Disciplina diciplina;

    @OneToMany
    @JoinColumn(name = "idCurso_fk")
    private Curso curso;


}

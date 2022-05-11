package com.bueno.PesquisaTopicos.domain;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TopicosPesquisa")
@Getter
@Setter
public class Topico {
    @Id
    @Column(name = "idTopico")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTopico;
    @OneToOne
    @JoinColumn(name = "autor_id")
    @NotNull
    private UserSystem autor = new UserSystem();
    @Column(name = "titulo")
    @NotNull
    private String titulo;
    @Column(name = "descricao")
    @NotNull
    private String descricao;
    @Column(name = "dataApresentacao")
    @NotNull
    private String dataApresentacao;
    @Column(name = "dataManutencao")
    @NotNull
    private String dataManutencao;
    @Column(name = "tipoGraduacao")
    @NotNull
    private String tipoGraduacao;
    @Column(name = "campus")
    @NotNull
    private String campus;
    @Column(name = "programaPosGrad")
    @NotNull
    private String programaPosGrad;
    @Column(name = "linhaDePesq")
    @NotNull
    private String linhaDePesq;


    public Topico() {
    }

    public Topico(Long idTopico, UserSystem autor, String titulo, String descricao, String dataApresentacao, String dataManutencao, String tipoGraduacao, String campus, String programaPosGrad, String linhaDePesq) {
        this.idTopico = idTopico;
        this.autor = autor;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataApresentacao = dataApresentacao;
        this.dataManutencao = dataManutencao;
        this.tipoGraduacao = tipoGraduacao;
        this.campus = campus;
        this.programaPosGrad = programaPosGrad;
        this.linhaDePesq = linhaDePesq;
    }

}

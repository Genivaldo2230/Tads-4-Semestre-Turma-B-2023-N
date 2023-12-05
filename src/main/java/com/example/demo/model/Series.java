package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "series")
@Table(name = "series")
@Getter
@Setter
public class Series {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String genero;
    @Column(name = "ano_lancamento")
    private Integer anoLancamento;


    // Construtores
    public Series() {
        // Construtor vazio padrão
    }

    public Series( Long id, String titulo, String genero, Integer anoLancamento ) {
        this.id = id;
        this.titulo = titulo;
        this.genero = genero;
        this.anoLancamento = anoLancamento;

    }

    public Integer getAnoLancamento() {
        return anoLancamento;
    }

//    public List<Series> setAnoLancamento( Integer anoLancamento ) {
//        if (anoLancamento != null && (anoLancamento < 1000 || anoLancamento > 9999)) {
//            throw new IllegalArgumentException("anoLancamento deve ter 4 dígitos");
//        }
//        this.anoLancamento = anoLancamento;
//
//
//        try {
//            return (List<Series>) Series.this;
//        } catch (Exception e) {
//            System.out.println("Ocorreu um erro ao listar as séries: " + e.getMessage());
//            return new ArrayList<>();
//        }
//    }



}
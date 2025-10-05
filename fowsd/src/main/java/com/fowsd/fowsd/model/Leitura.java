package com.fowsd.fowsd.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "LEITURA")
public class Leitura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_leitura")
    private Long idLeitura;

    // Relacionamento com a entidade Sensor (N:1 - muitas Leituras para um Sensor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sensor", nullable = false)
    private Sensor sensor;

    @Column(name = "umidade")
    private BigDecimal umidade;

    @Column(name = "temperatura")
    private BigDecimal temperatura;

    @Column(name = "ph_solo")
    private BigDecimal phSolo; // Corrigido para ph_solo conforme seu DB

    @Column(name = "data_leitura", nullable = false)
    private LocalDateTime dataLeitura;

    
    // --- CONSTRUTORES ---
    public Leitura() {}
    
    // --- GETTERS e SETTERS ---
    // (Omitidos por brevidade, mas você deve incluí-los aqui)
    // Exemplo:
    public Long getIdLeitura() { return idLeitura; }
    public void setIdLeitura(Long idLeitura) { this.idLeitura = idLeitura; }
    // ...
}
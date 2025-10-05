package com.fowsd.fowsd.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SENSOR")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_sensor")
    private Integer idSensor;

    @Column(name = "codigo_sensor", nullable = false)
    private String codigoSensor;

    @Column(name = "tipo")
    private String tipo; // Poderia ser Enum, mas String é mais simples por agora

    @Column(name = "status")
    private String status; // Poderia ser Enum, mas String é mais simples por agora
    
    // Você pode adicionar mais campos da sua tabela SENSOR aqui.
    // Exemplo:
    // @Column(name = "id_terreno")
    // private Integer idTerreno;


    // --- CONSTRUTORES ---
    public Sensor() {}

    // --- GETTERS e SETTERS ---
    public Integer getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(Integer idSensor) {
        this.idSensor = idSensor;
    }

    public String getCodigoSensor() {
        return codigoSensor;
    }

    public void setCodigoSensor(String codigoSensor) {
        this.codigoSensor = codigoSensor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
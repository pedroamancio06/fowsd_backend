package com.fowsd.fowsd.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class SensorDashboardDTO {
    // Campos principais do Dashboard
    private Integer id;
    private String codigo;
    private String tipo;
    private String status;
    private BigDecimal indiceSaude;
    private String terrenoNome; 

    // CLASSE ANINHADA ESTÁTICA CORRIGIDA
    private LeituraDTO ultimaLeitura;

    // --- CONSTRUTORES, GETTERS E SETTERS para SensorDashboardDTO ---
    public SensorDashboardDTO() {}

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public BigDecimal getIndiceSaude() { return indiceSaude; }
    public void setIndiceSaude(BigDecimal indiceSaude) { this.indiceSaude = indiceSaude; }

    public String getTerrenoNome() { return terrenoNome; }
    public void setTerrenoNome(String terrenoNome) { this.terrenoNome = terrenoNome; }

    public LeituraDTO getUltimaLeitura() { return ultimaLeitura; }
    public void setUltimaLeitura(LeituraDTO ultimaLeitura) { this.ultimaLeitura = ultimaLeitura; }

    // =================================================================
    // DTO ANINHADO CORRIGIDO (public static)
    // =================================================================
    public static class LeituraDTO {
        private BigDecimal umidade;
        private BigDecimal temperatura;
        private BigDecimal ph;
        private LocalDateTime dataLeitura;

        // --- CONSTRUTORES, GETTERS E SETTERS para LeituraDTO ---
        public LeituraDTO() {}
        
        public BigDecimal getUmidade() { return umidade; }
        public void setUmidade(BigDecimal umidade) { this.umidade = umidade; }

        public BigDecimal getTemperatura() { return temperatura; }
        public void setTemperatura(BigDecimal temperatura) { this.temperatura = temperatura; }

        public BigDecimal getPh() { return ph; }
        public void setPh(BigDecimal ph) { this.ph = ph; }

        public LocalDateTime getDataLeitura() { return dataLeitura; }
        public void setDataLeitura(LocalDateTime dataLeitura) { this.dataLeitura = dataLeitura; }
    }
}
package com.fowsd.fowsd.service;

import com.fowsd.fowsd.model.Leitura; 
import com.fowsd.fowsd.dto.SensorDashboardDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime; // IMPORT NECESSÁRIO PARA OTERHISTORICO
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeituraService {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Endpoint: GET /api/v1/leituras/sensor/{id}/saude
     */
    @Transactional(readOnly = true)
    public BigDecimal calcularIndiceSaude(Integer idSensor, Integer periodoDias) {
        // SQL Nativo para chamar a FUNCTION
        Query query = entityManager.createNativeQuery(
            "SELECT FN_INDICE_SAUDE_SOLO(:idSensor, :periodoDias)"
        );
        query.setParameter("idSensor", idSensor);
        query.setParameter("periodoDias", periodoDias);

        return (BigDecimal) query.getSingleResult();
    }

    /**
     * Endpoint: GET /api/v1/leituras/sensor/{id}/historico
     * CORREÇÃO: Usa LocalDateTime para buscar o histórico sem usar DATE_SUB.
     */
    @Transactional(readOnly = true)
    public List<Leitura> obterHistorico(Integer idSensor, Integer dias) {
        
        // Calcula a data limite no Java
        LocalDateTime dataLimite = LocalDateTime.now().minusDays(dias);
        
        // JPQL compatível com JPA
        return entityManager.createQuery(
                "SELECT l FROM Leitura l WHERE l.sensor.idSensor = :idSensor AND l.dataLeitura >= :dataLimite ORDER BY l.dataLeitura DESC", Leitura.class)
                .setParameter("idSensor", idSensor)
                .setParameter("dataLimite", dataLimite) // Usa o objeto LocalDateTime
                .getResultList();
    }

    /**
     * Endpoint: GET /api/v1/dashboard
     * Mapeia os dados da VIEW para o DTO.
     */
    @Transactional(readOnly = true)
    public List<SensorDashboardDTO> obterDashboard() {
        
        // ATENÇÃO: Se quiser usar a VIEW real, substitua o mock:
        // Query query = entityManager.createNativeQuery("SELECT id, codigo, tipo, status, indice_saude, terreno_nome, umidade, temperatura, ph, data_leitura FROM VL_DASHBOARD_TERRENO");
        // List<Object[]> resultados = query.getResultList();
        // return resultados.stream().map(this::mapearParaDTO).collect(Collectors.toList());
        
        // Simulação com dados estáticos/mock (Para compilar e rodar antes da integração completa com o DB)
        SensorDashboardDTO.LeituraDTO ultimaLeitura = new SensorDashboardDTO.LeituraDTO();
        ultimaLeitura.setUmidade(new BigDecimal("45.2"));
        ultimaLeitura.setTemperatura(new BigDecimal("24.8"));
        ultimaLeitura.setPh(new BigDecimal("6.5"));
        ultimaLeitura.setDataLeitura(LocalDateTime.now());
        
        SensorDashboardDTO mockSensor = new SensorDashboardDTO();
        mockSensor.setId(1);
        mockSensor.setCodigo("SNS-001");
        mockSensor.setTipo("UMIDADE");
        mockSensor.setStatus("ATIVO");
        mockSensor.setIndiceSaude(new BigDecimal("85.5"));
        mockSensor.setTerrenoNome("Terreno Principal");
        mockSensor.setUltimaLeitura(ultimaLeitura);
        
        return List.of(mockSensor);
    }
}
package com.fowsd.fowsd.controller;

import com.fowsd.fowsd.dto.SensorDashboardDTO;
import com.fowsd.fowsd.model.Leitura; // Entidade Leitura.java deve ser criada
import com.fowsd.fowsd.service.LeituraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/v1") // Mantendo o padrão do seu api.config.js
public class LeituraController {

    private final LeituraService leituraService;

    public LeituraController(LeituraService leituraService) {
        this.leituraService = leituraService;
    }

    // Mapeia: LeituraService.calcularIndiceSaude(idSensor, 7) do React Native
    @GetMapping("/leituras/sensor/{idSensor}/saude")
    public ResponseEntity<BigDecimal> getIndiceSaude(
            @PathVariable Integer idSensor,
            @RequestParam(defaultValue = "7") Integer periodoDias) {

        BigDecimal indice = leituraService.calcularIndiceSaude(idSensor, periodoDias);
        return ResponseEntity.ok(indice);
    }

    // Mapeia: LeituraService.obterHistorico(idSensor, 7) do React Native
    @GetMapping("/leituras/sensor/{idSensor}/historico")
    public ResponseEntity<List<Leitura>> getHistoricoLeituras(
            @PathVariable Integer idSensor,
            @RequestParam(defaultValue = "7") Integer dias) {

        List<Leitura> historico = leituraService.obterHistorico(idSensor, dias);
        return ResponseEntity.ok(historico);
    }
    
    // Mapeia: Para substituir os mocks em dashboard.jsx
    @GetMapping("/dashboard")
    public ResponseEntity<List<SensorDashboardDTO>> getDashboardData() {
        List<SensorDashboardDTO> sensores = leituraService.obterDashboard();
        return ResponseEntity.ok(sensores);
    }

    // Opcional, para o LeituraService.gerarRelatorio(idSensor) que você tem no React Native
    @GetMapping("/leituras/sensor/{idSensor}/relatorio")
    public ResponseEntity<String> gerarRelatorio(@PathVariable Integer idSensor) {
        // Implementar a lógica de geração de relatório (ex: Procedure no MySQL)
        return ResponseEntity.ok("Relatório gerado com sucesso para o sensor " + idSensor);
    }
}
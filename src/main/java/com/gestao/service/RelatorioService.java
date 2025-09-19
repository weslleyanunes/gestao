package com.gestao.service;

import com.gestao.model.Relatorio;
import com.gestao.repository.RelatorioRepositorio;

public class RelatorioService {
    private RelatorioRepositorio relatorioRepositorio = new RelatorioRepositorio();

    public boolean gerarRelatorio(Relatorio relatorio) {
        if (relatorio.getTitulo() == null || relatorio.getTitulo().isEmpty()) {
            return false;
        }
        relatorioRepositorio.adicionar(relatorio);
        return true;
    }
}

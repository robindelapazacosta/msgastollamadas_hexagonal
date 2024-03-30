package com.provari.llamadas.domain.service;

import com.provari.llamadas.domain.dto.DtoRangoSearch;
import com.provari.llamadas.domain.dto.DtoResumenPorExt;
import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;
import com.provari.llamadas.domain.port.inbound.QueryLlamadas;
import com.provari.llamadas.domain.port.inbound.QueryResumenLlamadas;
import com.provari.llamadas.domain.port.outbound.RetrieveLlamadas;
import com.provari.llamadas.domain.port.outbound.RetrieveResumenLlamadas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LlamadasQueryService implements QueryLlamadas, QueryResumenLlamadas {

    private RetrieveLlamadas retrieveLlamadas;
    private RetrieveResumenLlamadas retrieveResumenLlamadas;

    public LlamadasQueryService(RetrieveLlamadas retrieveLlamadas, RetrieveResumenLlamadas retrieveResumen ) {
        this.retrieveLlamadas = retrieveLlamadas;
        this.retrieveResumenLlamadas= retrieveResumen;
    }

    @Override
    public List<Llamada> filtrarLlamadas(DtoSearch dtoSearch) {
        return retrieveLlamadas.filtrarLlamadas(dtoSearch);
    }

    @Override
    public List<DtoResumenPorExt> resumenPorExtension(DtoRangoSearch dtoRangoSearch) {
        return retrieveResumenLlamadas.getResumenPorExt(dtoRangoSearch);
    }
}

package com.provari.llamadas.domain.port.inbound;

import com.provari.llamadas.domain.dto.DtoRangoSearch;
import com.provari.llamadas.domain.dto.DtoResumenPorExt;
import com.provari.llamadas.domain.model.Llamada;

import java.util.List;

public interface QueryResumenLlamadas {

    /*Resumen en un rango de fecha, si no se especifica se busca para todas las llamadas*/
    public List<DtoResumenPorExt> resumenPorExtension(DtoRangoSearch dtoRangoSearch);
}

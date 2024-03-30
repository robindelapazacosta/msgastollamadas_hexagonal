package com.provari.llamadas.domain.port.outbound;

import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;

import java.util.List;

public interface RetrieveLlamadas {

    public List<Llamada> filtrarLlamadas(DtoSearch dtoSearch);
}

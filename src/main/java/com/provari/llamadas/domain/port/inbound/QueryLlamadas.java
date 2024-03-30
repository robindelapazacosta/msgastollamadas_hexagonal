package com.provari.llamadas.domain.port.inbound;

import com.provari.llamadas.domain.dto.DtoSearch;
import com.provari.llamadas.domain.model.Llamada;

import java.util.List;

public interface QueryLlamadas {

     public List<Llamada> filtrarLlamadas(DtoSearch dtoSearch);

}

package com.provari.llamadas.domain.port.outbound;

import com.provari.llamadas.domain.dto.DtoRangoSearch;
import com.provari.llamadas.domain.dto.DtoResumenPorExt;

import java.util.List;

public interface RetrieveResumenLlamadas {

    List<DtoResumenPorExt> getResumenPorExt(DtoRangoSearch dtoRangoSearch);
}

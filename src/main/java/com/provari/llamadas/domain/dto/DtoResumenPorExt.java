package com.provari.llamadas.domain.dto;

import lombok.Data;

@Data
public class DtoResumenPorExt {

    private String _id;
    private int cant_llamadas;
    private int tiempototalenseg;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DtoResumenPorExt that = (DtoResumenPorExt) o;
        return _id.equals(that._id);
    }


}

package com.provari.llamadas.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DtoSearch {

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date fechai;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    Date fechaf;
    String num_extension;
}

package com.provari.llamadas.domain.utiles;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ValidatorTest {

    @Test
    void esUnEntero() {
        assertEquals(false, Validator.EsUnEntero("2.0"));
        assertEquals(true, Validator.EsUnEntero("0"));

    }

    @Test
    void validarHora() {
        /*Aqui detecté un error, el primer digito no puede ser mayor que 12, si es 13 ya es 1 PM
        * La prueba debería dar false y no true*/
        assertEquals(true, Validator.validarHora("10:30AM"));
        assertEquals(true, Validator.validarHora("12:30PM"));

        assertEquals(false, Validator.validarHora("12:00AM"));


    }

    @Test
    void esUnaFechaValida() {


    }

    @Test
    void validarDuracion() {
    }
}
package com.provari.llamadas.domain.utiles;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UtilCadenaTest {

    /*@Autowired
    private UtilCadena utilCadena;*/
/*
    @PostConstruct
    private void Init()
    {
        
    }*/


    @Test
    void comienzaConDigitoTest() {

        assertEquals(false, UtilCadena.ComienzaConDigito("Duration data 99 deleted"));
        assertEquals(false, UtilCadena.ComienzaConDigito(" Fecha     Hora  Ext. LN      Número Marcado      Duración   Cargo       Codg"));
        assertEquals(true, UtilCadena.ComienzaConDigito(" 12/18/20   4:27AM 221  02   < DE >                 00:00'38\"              .... 0"));
    }


    @Test
    void QuitarEspaciosDeMAsTest()
    {
        assertEquals("12/18/20 4:27AM 221 02 < DE > 00:00'38", UtilCadena.QuitarEspaciosDeMAs("12/18/20   4:27AM 221  02   < DE >                 00:00'38"));

    }

    @Test
    void convertirHoraTest()
    {
        assertEquals("16:20", UtilCadena.convertirHora("4:20PM"));
        assertEquals("4:20", UtilCadena.convertirHora("4:20AM"));
        assertEquals("0:20", UtilCadena.convertirHora("00:20AM"));
        assertEquals("23:50", UtilCadena.convertirHora("11:50PM"));

        /*Cómo hace un accert de que lance un tipo de excepcion*/
       // assertThrows(Exception.class, UtilCadena.convertirHora("11:50PM"));

    }


    @Test
    void ConvertirDuracionASegundosTest()
    {
         assertEquals(158, UtilCadena.ConvertirDuracionASegundos("00:02'38\""));
         /*Probando cuando funciona para un tiempo superior a 1 hora*/
         assertEquals(3757, UtilCadena.ConvertirDuracionASegundos("01:02'38\""));
    }



}
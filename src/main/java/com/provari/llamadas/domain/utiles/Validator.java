package com.provari.llamadas.domain.utiles;

public class Validator {



    static public boolean EsUnEntero(String cadena)
    {

        boolean resultado;

        try
        {
            Integer.parseInt(cadena);
            resultado=true;
        }
        catch(NumberFormatException ex)
        {
            resultado=false;
        }

        return resultado;

    }

    /*Valida que el primer numero de la fecha AM o PM no sea mayor que 12*/
    static private boolean validarPrimerNumHora(String valor, String AMoPM) {
        if (EsUnEntero(valor)) {
            if(AMoPM.equalsIgnoreCase("AM") && Integer.parseInt(valor)>11)
            {
                return false;
            }
            else if(AMoPM.equalsIgnoreCase("PM") && Integer.parseInt(valor)>12)
            {
                return false;
            }
            else
                return true;


        } else
        {
            return false;
        }
    }

    static private boolean validarSegundoNumHora(String val)
    {
        int num= Integer.parseInt(val);

        return num>=0  && num<=59;


    }

    /*Valida la hora, tiene que estar en formato AM y PC*/
    static  public boolean validarHora(String hora)
    {
        //Aqui no se cumple el principio de clean code. Est'a repetido hora.substring 2 veces
        if(!hora.substring(hora.length()-2).equalsIgnoreCase("AM") && !hora.substring(hora.length()-2).equalsIgnoreCase("PM")) {
            return false;
        }
        else
        {
            String AMoPM= hora.substring(hora.length()-2);
            hora= hora.replace("AM", "");
            hora= hora.replace("PM", "");

            String[] array_hora= hora.split(":");

            //Si no tiene 2 partes y la primera no es un dígito retorna false
            if(array_hora.length!=2 || !validarPrimerNumHora(array_hora[0],AMoPM) || !validarSegundoNumHora(array_hora[1]) )
                return false;
            else
                return true;
        }

    }


    static public boolean EsUnaFechaValida(String fechastr)
    {
        String[] array_fecha=fechastr.split("/");  //fecha

        if(array_fecha.length!=3 || !EsUnEntero(array_fecha[0]) || !EsUnEntero(array_fecha[1]) || !EsUnEntero(array_fecha[2]) )
        {
            return false;
        }

        int mes= Integer.valueOf(array_fecha[0]);
        int dia= Integer.valueOf(array_fecha[1]);

        if(mes<1 || mes>12)
        {
            return false;
        }

        if(dia<1 || dia>31)
        {
            return false;
        }

        return true;


    }


    static public boolean validarDuracion(String duracionStr)
    {
        duracionStr=   duracionStr.replace("'",":");
        duracionStr= duracionStr.replace("\"", "");

        String[] array_temp= duracionStr.split(":");

        if(array_temp.length==3 && EsUnEntero(array_temp[0]) && EsUnEntero(array_temp[1]) && EsUnEntero(array_temp[2]) )
        {
            return true;
        }
        else
        {
            return false;
        }

    }






}

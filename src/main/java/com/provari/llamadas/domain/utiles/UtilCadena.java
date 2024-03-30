package com.provari.llamadas.domain.utiles;


public class UtilCadena {


    public static  boolean ComienzaConDigito(String cadena)
    {

        //mientras haya espacio avanzo
        int posi=0;
        while(posi<cadena.length() && cadena.charAt(posi)==' ') {
            posi++;
        }

        if(posi==cadena.length()) {
            return false;
        }
        else
        {
            if(Character.isDigit( cadena.charAt(posi)))
                return true;
            else
                return false;

        }

    }


    public static String QuitarEspaciosDeMAs(String pcadena)
    {
        String cadena_result="";

        if(pcadena.charAt(0)==' ')
            pcadena= pcadena.replaceFirst(" ", "");

        for(int i=0; i<pcadena.length();i++)
        {
            if(pcadena.charAt(i)!=' ')
                cadena_result+=pcadena.charAt(i);
            else
            {
                if(i-1 > 0 && pcadena.charAt(i-1)!='/')
                {
                    cadena_result+=pcadena.charAt(i);
                }
                i++;
                while(i<pcadena.length() && pcadena.charAt(i)==' ')
                    i++;

                if(i<pcadena.length())
                    cadena_result+=pcadena.charAt(i);

            }

        }

        return cadena_result;
    }

    /*Convierte la hora del formato del fichero a un formato manejable por Java*/
    public  static String convertirHora(String phora)
    {
        String hora="";
        String AMoPM= phora.substring(phora.length()-2);

        phora= phora.replace("AM","");
        phora= phora.replace("PM","");

        String[] array_hora= phora.split(":");
        Integer horas= Integer.parseInt(array_hora[0]);

        if(AMoPM.equalsIgnoreCase("pm") && horas<12)
            horas= horas+12;

        hora= horas.toString()+":"+array_hora[1];


        return hora;
    }

    /*Calcula la duracion en segundos de la llamada sobre la base de la duracion leida en el formato del fichero (Ejemplo: 00:02'50")*/
    public static Integer ConvertirDuracionASegundos(String duracion)
    {
        int duracionseg=0;

        duracion=   duracion.replace("'",":");
        duracion= duracion.replace("\"", "");

        String[] array_temp= duracion.split(":");

        duracionseg= Integer.parseInt(array_temp[0])*3600+Integer.parseInt(array_temp[1])*60+Integer.parseInt(array_temp[2]);


        return duracionseg;
    }

}

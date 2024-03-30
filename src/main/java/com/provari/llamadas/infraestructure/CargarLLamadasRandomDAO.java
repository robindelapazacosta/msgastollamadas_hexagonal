package com.provari.llamadas.infraestructure;

import com.provari.llamadas.domain.model.ExtensionTelefonica;
import com.provari.llamadas.domain.model.Llamada;
import com.provari.llamadas.domain.port.outbound.LoadAndPersistLlamadas;
import com.provari.llamadas.domain.service.ExtensionesService;
import com.provari.llamadas.domain.utiles.UtilCadena;
import com.provari.llamadas.domain.utiles.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class CargarLLamadasRandomDAO  implements LoadAndPersistLlamadas {


    final Logger logger= LoggerFactory.getLogger(CargarLLamadasRandomDAO.class);
    private Long posFile=0l;
    RandomAccessFile rafPos;

    @Autowired
    private llamadasDAO llamadasService;

    @Autowired
    private ExtensionesService extService;


    public CargarLLamadasRandomDAO() throws IOException {


        rafPos= new RandomAccessFile("D:\\posLlamadas","r");
        rafPos.seek(0);

        Long posTemp;
        if((posTemp= rafPos.readLong())!=null)
        {
            this.posFile= posTemp;
        }
        rafPos.close();

    }

    @Override
    @Scheduled(fixedRate = 60000)
    public List<Llamada> CargarLlamadas() throws IOException, ParseException {

        List<Llamada> llamadas= new ArrayList<>();


        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        logger.info("Ejecutando Cargar llamadas. "+sdf.format(new Date()));

        String filePath="D:\\CAPTURE2.TXT";
        RandomAccessFile raf= new RandomAccessFile(filePath,"r");
        raf.seek(posFile);

        Map<String, ExtensionTelefonica> mapExt= extService.mapExtensiones();
        String cadena="";
        while((cadena=raf.readLine())!=null)
        {
            if(!cadena.isEmpty() && UtilCadena.ComienzaConDigito(cadena) && !cadena.contains("E"))
            {
                cadena= cadena.replace("*","");
                cadena= UtilCadena.QuitarEspaciosDeMAs(cadena);

                logger.info(cadena);

                String[] array_valores= cadena.split(" ");

                if(array_valores.length>=6)
                {
                    //Si la fecha o la hora no son válidas, sale del ciclo y pasa a la proxima llamada
                    if(!Validator.EsUnaFechaValida(array_valores[0]) || !Validator.validarHora(array_valores[1]))
                    {
                        continue;
                    }
                    String[] array_fecha= array_valores[0].split("/");
                    String fechaHorastr= "20"+array_fecha[2]+'-'+array_fecha[0]+'-'+array_fecha[1]; //formato año-mes-dia

                    fechaHorastr+=" "+UtilCadena.convertirHora(array_valores[1]);
                    Date fechaLlamada= new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fechaHorastr);

                    Llamada ultimaLlamada = llamadasService.getUltimaLlamada();



                    //Si la llamada es posterior  a la última la cargo en la BD
                    if( ultimaLlamada==null || fechaLlamada.after(ultimaLlamada.getFechahora()))
                    {
                        String num_extension=array_valores[2];
                        String linea=array_valores[3];
                        String num_marcado=array_valores[4];

                        //Si la duracion no es válida, sale del ciclo y pasa a la proxima llamada
                        if(!Validator.validarDuracion(array_valores[5]))
                        {
                            continue;
                        }
                        int duracion= UtilCadena.ConvertirDuracionASegundos(array_valores[5]);
                        Llamada llamadaNew= new Llamada(fechaLlamada,mapExt.get(num_extension),linea,num_marcado,duracion);
                        llamadasService.adicionarLlamada(llamadaNew);
                        llamadas.add(llamadaNew);

                        System.out.println(llamadaNew);
                    }
                }
            }
        }

        //Longitud del fichero
        posFile= raf.length();


        this.rafPos = new RandomAccessFile("D:\\posLlamadas","rw");
        this.rafPos.setLength(0);
        rafPos.writeLong(posFile);
        rafPos.close();


        return llamadas;

    }
}

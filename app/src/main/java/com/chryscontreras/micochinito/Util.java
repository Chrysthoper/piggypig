package com.chryscontreras.micochinito;

import java.text.DateFormatSymbols;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Chrysthoper on 28/08/2016.
 */
public class Util {
    public static String PriceFormat(Double costo){
        DecimalFormat formatter = new DecimalFormat("#,##0.00");
        return "$ " + formatter.format(costo);
    }

    public static String FechaToFormat(Date fecha){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(fecha.getTime());
    }

    public static Date FormatToFecha(String format){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return dateFormat.parse(format);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static String FormatToShort(String format){
        Date fecha = Util.FormatToFecha(format);
        String stringMonth = (String) android.text.format.DateFormat.format("MMM", fecha);
        String day = (String) android.text.format.DateFormat.format("dd", fecha);
        return stringMonth.toUpperCase() + " " + day;
    }

    public static String getMonthForInt(int num){
        String month = "wrong";
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();
        if (num >= 0 && num <= 11 ) {
            month = months[num];
        }
        return month;
    }

    public static int[] imagenes = new int[]{
            R.mipmap.gas,
            R.mipmap.chucherias,
            R.mipmap.camion,
            R.mipmap.agua,
            R.mipmap.casa,
            R.mipmap.comida,
            R.mipmap.compras,
            R.mipmap.luz,
            R.mipmap.gasolina,
            R.mipmap.pareja,
            R.mipmap.ropa,
            R.mipmap.carro,
            R.mipmap.internet,
            R.mipmap.cellphone,
            R.mipmap.credito,
            R.mipmap.trabajo,
            R.mipmap.phone,
            R.mipmap.prestamo,
            R.mipmap.monedas
    };
    public static int[] imagenesFull = new int[]{
            R.mipmap.gas,
            R.mipmap.chucherias,
            R.mipmap.camion,
            R.mipmap.agua,
            R.mipmap.casa,
            R.mipmap.comida,
            R.mipmap.compras,
            R.mipmap.luz,
            R.mipmap.gasolina,
            R.mipmap.pareja,
            R.mipmap.ropa,
            R.mipmap.carro,
            R.mipmap.internet,
            R.mipmap.cellphone,
            R.mipmap.credito,
            R.mipmap.trabajo,
            R.mipmap.phone,
            R.mipmap.prestamo,
            R.mipmap.monedas,
            R.mipmap.sin_categoria,
            R.mipmap.agregar_blanco
    };
}

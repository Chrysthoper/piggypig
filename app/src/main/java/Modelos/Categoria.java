package Modelos;

import java.io.Serializable;

/**
 * Created by Chrysthoper on 24/08/2016.
 */
public class Categoria implements Serializable
{
    public int id;
    public String nombre;
    public int resource;
    public int color;
    public int forma;
    public int tipo;
    public double total;
    public int es_cuenta;

    public Categoria(){}

}

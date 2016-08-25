package Persistencia;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import Modelos.Categoria;

public class Categorias {

    private ContextoBD ContextoBD;

    public Categorias(ContextoBD contextoBD)
    {
        this.ContextoBD = contextoBD;
    }

    public ArrayList<Categoria> Obten(){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + EsquemaBD.CATEGORIAS.TB_NOMBRE, null);
        ArrayList<Categoria> lista = GetObject(c,false);
        return lista;
    }

    private ArrayList<Categoria> GetObject(Cursor c, boolean sum){
        ArrayList<Categoria> lista = new ArrayList<Categoria>();
        if (c.moveToFirst()) {
            do {
                Categoria categoria = new Categoria();
                categoria.id = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.ID));
                categoria.nombre = c.getString(c.getColumnIndex(EsquemaBD.CATEGORIAS.NOMBRE));
                categoria.resource = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.RECURSO_ID));
                categoria.forma = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.FORMA_ID));
                categoria.color = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.COLOR_ID));
                categoria.tipo = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.TIPO));
                categoria.es_cuenta = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.ES_CUENTA));
                if(sum)
                    categoria.total = c.getInt(c.getColumnIndex("TOTAL"));
                lista.add(categoria);
            } while(c.moveToNext());
        }
        return lista;
    }
}

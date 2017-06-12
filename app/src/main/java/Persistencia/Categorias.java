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

    public ArrayList<Categoria> ObtenCategorias(){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + EsquemaBD.CATEGORIAS.TB_NOMBRE +
                " WHERE " + EsquemaBD.CATEGORIAS.ES_CUENTA + " = 0", null);
        ArrayList<Categoria> lista = GetObject(c,false);
        return lista;
    }

    public ArrayList<Categoria> ObtenCuentas(){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * FROM " + EsquemaBD.CATEGORIAS.TB_NOMBRE +
                " WHERE " + EsquemaBD.CATEGORIAS.ES_CUENTA + " = 1", null);
        ArrayList<Categoria> lista = GetObject(c,false);
        return lista;
    }

    public Categoria Obten(int ID){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();
        String[] projection = {
                EsquemaBD.CATEGORIAS.ID,
                EsquemaBD.CATEGORIAS.NOMBRE,
                EsquemaBD.CATEGORIAS.RECURSO_ID,
                EsquemaBD.CATEGORIAS.FORMA_ID,
                EsquemaBD.CATEGORIAS.COLOR_ID,
                EsquemaBD.CATEGORIAS.TIPO,
                EsquemaBD.CATEGORIAS.ES_CUENTA,
        };
        String sortOrder = EsquemaBD.CATEGORIAS.ID + " DESC";
        Cursor c = db.query(
                EsquemaBD.CATEGORIAS.TB_NOMBRE,
                projection,
                EsquemaBD.CATEGORIAS.ID + "=?",
                new String[]{String.valueOf(ID)},
                null,
                null,
                sortOrder
        );
        Categoria lista = GetObject(c);
        return lista;
    }

    public ArrayList<Categoria> ObtenPorTipo(int Tipo_Transaccion){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();
        String[] projection = {
                EsquemaBD.CATEGORIAS.ID,
                EsquemaBD.CATEGORIAS.NOMBRE,
                EsquemaBD.CATEGORIAS.RECURSO_ID,
                EsquemaBD.CATEGORIAS.FORMA_ID,
                EsquemaBD.CATEGORIAS.COLOR_ID,
                EsquemaBD.CATEGORIAS.TIPO,
                EsquemaBD.CATEGORIAS.ES_CUENTA
        };

        String sortOrder = EsquemaBD.CATEGORIAS.ID + " DESC";
        Cursor c = db.rawQuery("SELECT * FROM " + EsquemaBD.CATEGORIAS.TB_NOMBRE +
                " WHERE " + EsquemaBD.CATEGORIAS.TIPO + " = " + Tipo_Transaccion +
                " ORDER BY " + EsquemaBD.CATEGORIAS.ID + " DESC", null);
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

    private Categoria GetObject(Cursor c) {
        Categoria categoria = new Categoria();
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya mas registros
            do {
                categoria.id = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.ID));
                categoria.nombre = c.getString(c.getColumnIndex(EsquemaBD.CATEGORIAS.NOMBRE));
                categoria.resource = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.RECURSO_ID));
                categoria.forma = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.FORMA_ID));
                categoria.color = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.COLOR_ID));
                categoria.tipo = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.TIPO));
                categoria.es_cuenta = c.getInt(c.getColumnIndex(EsquemaBD.CATEGORIAS.ES_CUENTA));
            } while(c.moveToNext());
        }
        return categoria;
    }

}

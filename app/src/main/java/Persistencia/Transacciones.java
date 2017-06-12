package Persistencia;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.io.Serializable;
import java.util.ArrayList;

import Modelos.Transaccion;

/**
 * Created by Chrysthoper on 28/08/2016.
 */
public class Transacciones {

    private ContextoBD ContextoBD;

    public Transacciones(ContextoBD contextoBD)
    {
        this.ContextoBD = contextoBD;
    }

    public ArrayList<Transaccion> Obten(String fechaIni, String fechaFin){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery(
            "SELECT * FROM " + EsquemaBD.TRANSACCIONES.TB_NOMBRE +
            " WHERE " + EsquemaBD.TRANSACCIONES.FEC_ALTA + " >= Datetime('" + fechaIni.substring(0,10) + "')" +
            " AND " + EsquemaBD.TRANSACCIONES.FEC_ALTA + " < Datetime('" + fechaFin.substring(0,10) + "')" +
            " ORDER BY " + EsquemaBD.TRANSACCIONES.FEC_ALTA + " DESC, " +
            EsquemaBD.TRANSACCIONES.ID + " ASC", null);

        ArrayList<Transaccion> lista = GetObject(c);
        return lista;
    }

    public ArrayList<Transaccion> Obten(){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery(
        "SELECT * FROM " + EsquemaBD.TRANSACCIONES.TB_NOMBRE +
        " ORDER BY " + EsquemaBD.TRANSACCIONES.FEC_ALTA + " DESC, " +
        EsquemaBD.TRANSACCIONES.ID + " ASC", null);

        ArrayList<Transaccion> lista = GetObject(c);
        return lista;
    }

    public Transaccion Obten(int transaccion_id){
        SQLiteDatabase db = ContextoBD.getReadableDatabase();

        Cursor c = db.rawQuery(
                "SELECT * FROM " + EsquemaBD.TRANSACCIONES.TB_NOMBRE +
                " WHERE " + EsquemaBD.TRANSACCIONES.ID + " = " + transaccion_id +
                " ORDER BY " + EsquemaBD.TRANSACCIONES.FEC_ALTA + " DESC", null);

        Transaccion transaccion = GetObjectTransaccion(c);
        return transaccion;
    }

    public Transaccion Inserta(Transaccion trans){
        SQLiteDatabase db = ContextoBD.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(EsquemaBD.TRANSACCIONES.COSTO, trans.costo);
        values.put(EsquemaBD.TRANSACCIONES.CAT_ID, trans.numeroCategoria);
        values.put(EsquemaBD.TRANSACCIONES.FEC_ALTA, trans.fecha_alta);
        values.put(EsquemaBD.TRANSACCIONES.NOTA, trans.nota);
        values.put(EsquemaBD.TRANSACCIONES.DESCRIPCION, trans.descripcion);
        values.put(EsquemaBD.TRANSACCIONES.CTA_PRIN_ID, trans.cuenta_prin_id);
        values.put(EsquemaBD.TRANSACCIONES.CTA_SECU_ID, trans.cuenta_secu_id);
        values.put(EsquemaBD.TRANSACCIONES.TIPO_TRANSACCION, trans.tipo_transaccion);

        long newRowId;
        newRowId = db.insert(EsquemaBD.TRANSACCIONES.TB_NOMBRE,null,values);
        if (newRowId > 0)
        {
            //ActualizaCuentas((int) newRowId);
            return this.Obten((int) newRowId);
        }
        else
            return null;
    }

    /*
    public Transaccion Modifica(Transaccion trans){

        if(this.RestablecerCuentas(trans.id))
        {
            SQLiteDatabase db = ContextoBD.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(EsquemaBD.TRANSACCIONES.COSTO, trans.costo);
            values.put(EsquemaBD.TRANSACCIONES.CAT_ID, trans.numeroCategoria);
            values.put(EsquemaBD.TRANSACCIONES.FEC_ALTA, trans.fecha_alta);
            values.put(EsquemaBD.TRANSACCIONES.NOTA, trans.nota);
            values.put(EsquemaBD.TRANSACCIONES.DESCRIPCION, trans.descripcion);
            values.put(EsquemaBD.TRANSACCIONES.CTA_PRIN_ID, trans.cuenta_prin_id);
            values.put(EsquemaBD.TRANSACCIONES.CTA_SECU_ID, trans.cuenta_secu_id);
            values.put(EsquemaBD.TRANSACCIONES.TIPO_TRANSACCION, trans.tipo_transaccion);

            long newRowId;
            newRowId = db.update(EsquemaBD.TRANSACCIONES.TB_NOMBRE,
                    values,
                    EsquemaBD.TRANSACCIONES.ID + "=" + trans.id,
                    null);

            if (newRowId > 0)
            {
                this.ActualizaCuentas(trans.id);
                return this.Obten(trans.id);
            }
            else
                return null;
        }
        else
            return null;
    }


    public Boolean ActualizaCuentas(int transaccion_id) {
        Transaccion transaccion = this.Obten(transaccion_id);
        switch(transaccion.tipo_transaccion)
        {
            case 0:
                transaccion.cuentaPrincipalObj.total -= transaccion.costo;
                return this.ContextoBD.Categorias.Actualiza(transaccion.cuentaPrincipalObj);
            case 1:
                transaccion.cuentaPrincipalObj.total += transaccion.costo;
                return this.ContextoBD.Categorias.Actualiza(transaccion.cuentaPrincipalObj);
            case 2:
                if(transaccion.cuenta_prin_id != transaccion.cuenta_secu_id)
                {
                    transaccion.cuentaPrincipalObj.total -= transaccion.costo;
                    transaccion.cuentaSecundariaObj.total += transaccion.costo;
                    return this.ContextoBD.Categorias.Actualiza(transaccion.cuentaPrincipalObj) && this.ContextoBD.Categorias.Actualiza(transaccion.cuentaSecundariaObj);
                }
                else
                    return true;
            default:
                return false;
        }
    }

    public Boolean RestablecerCuentas(int transaccion_id) {
        Transaccion transaccion = this.Obten(transaccion_id);
        switch(transaccion.tipo_transaccion)
        {
            case 0:
                transaccion.cuentaPrincipalObj.total += transaccion.costo;
                return this.dbHelper.Cuentas.Actualiza(transaccion.cuentaPrincipalObj);
            case 1:
                transaccion.cuentaPrincipalObj.total -= transaccion.costo;
                return this.dbHelper.Cuentas.Actualiza(transaccion.cuentaPrincipalObj);
            case 2:
                if(transaccion.cuenta_prin_id != transaccion.cuenta_secu_id)
                {
                    transaccion.cuentaPrincipalObj.total += transaccion.costo;
                    transaccion.cuentaSecundariaObj.total -= transaccion.costo;
                    return this.dbHelper.Cuentas.Actualiza(transaccion.cuentaPrincipalObj) && this.dbHelper.Cuentas.Actualiza(transaccion.cuentaSecundariaObj);
                }
                else
                    return true;
            default:
                return false;
        }
    }

    public Boolean Elimina(int transaccion_id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Transaccion trans = this.Obten(transaccion_id);
        boolean actualiza = false;
        switch(trans.tipo_transaccion)
        {
            case 0:
                trans.cuentaPrincipalObj.total += trans.costo;
                actualiza = this.dbHelper.Cuentas.Actualiza(trans.cuentaPrincipalObj);
                break;
            case 1:
                trans.cuentaPrincipalObj.total -= trans.costo;
                actualiza = this.dbHelper.Cuentas.Actualiza(trans.cuentaPrincipalObj);
                break;
            case 2:
                if(trans.cuenta_prin_id != trans.cuenta_secu_id)
                {
                    trans.cuentaPrincipalObj.total += trans.costo;
                    trans.cuentaSecundariaObj.total -= trans.costo;
                    actualiza = this.dbHelper.Cuentas.Actualiza(trans.cuentaPrincipalObj) && this.dbHelper.Cuentas.Actualiza(trans.cuentaSecundariaObj);
                }
                else
                    actualiza = true;
                break;
            default:
                actualiza = false;
                break;
        }
        if(actualiza)
            return db.delete(
                    EsquemaBD.TRANSACCIONES.TABLE_NAME,
                    EsquemaBD.TRANSACCIONES.COLUMN_NAME_ID + "=" + transaccion_id,
                    null) > 0;
        else
            return false;

    }
*/

    private ArrayList<Transaccion> GetObject(Cursor c){
        ArrayList<Transaccion> lista = new ArrayList<Transaccion>();
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya mas registros
            do {
                Transaccion transaccion = new Transaccion();
                transaccion.id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.ID));
                transaccion.costo = c.getDouble(c.getColumnIndex(EsquemaBD.TRANSACCIONES.COSTO));
                transaccion.numeroCategoria = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CAT_ID));
                transaccion.fecha_alta = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.FEC_ALTA));
                transaccion.nota = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.NOTA));
                transaccion.descripcion = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.DESCRIPCION));
                transaccion.cuenta_prin_id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CTA_PRIN_ID));
                transaccion.cuenta_secu_id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CTA_SECU_ID));
                transaccion.tipo_transaccion = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.TIPO_TRANSACCION));
                transaccion.categoriaObj = (transaccion.numeroCategoria != 0) ? ContextoBD.Categorias.Obten(transaccion.numeroCategoria) : null;
                transaccion.cuentaPrincipalObj = ContextoBD.Categorias.Obten(transaccion.cuenta_prin_id);
                transaccion.cuentaSecundariaObj = (transaccion.cuenta_secu_id != 0) ? ContextoBD.Categorias.Obten(transaccion.cuenta_secu_id) : null;
                transaccion.textoKeyPad = "";
                lista.add(transaccion);
            } while(c.moveToNext());
        }
        return lista;
    }

    private Transaccion GetObjectTransaccion(Cursor c){
        Transaccion Transaccion = new Transaccion();
        //Nos aseguramos de que existe al menos un registro
        if (c.moveToFirst()) {
            //Recorremos el cursor hasta que no haya mas registros
            do {
                Transaccion.id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.ID));
                Transaccion.costo = c.getDouble(c.getColumnIndex(EsquemaBD.TRANSACCIONES.COSTO));
                Transaccion.numeroCategoria = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CAT_ID));
                Transaccion.fecha_alta = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.FEC_ALTA));
                Transaccion.nota = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.NOTA));
                Transaccion.descripcion = c.getString(c.getColumnIndex(EsquemaBD.TRANSACCIONES.DESCRIPCION));
                Transaccion.cuenta_prin_id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CTA_PRIN_ID));
                Transaccion.cuenta_secu_id = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.CTA_SECU_ID));
                Transaccion.tipo_transaccion = c.getInt(c.getColumnIndex(EsquemaBD.TRANSACCIONES.TIPO_TRANSACCION));
                Transaccion.categoriaObj = (Transaccion.numeroCategoria != 0) ? ContextoBD.Categorias.Obten(Transaccion.numeroCategoria) : null;
                Transaccion.cuentaPrincipalObj = ContextoBD.Categorias.Obten(Transaccion.cuenta_prin_id);
                Transaccion.cuentaSecundariaObj = (Transaccion.cuenta_secu_id != 0) ? ContextoBD.Categorias.Obten(Transaccion.cuenta_secu_id) : null;
                Transaccion.textoKeyPad = "";
            } while(c.moveToNext());
        }
        return Transaccion;
    }

}

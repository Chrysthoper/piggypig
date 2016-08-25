package Persistencia;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Chrysthoper on 24/08/2016.
 */
public class ContextoBD extends SQLiteOpenHelper {

    public Categorias Categorias;

    public static final int BD_VERSION = 1;
    public static final String BD_NOMBRE = "PiggyPig.db";

    private static final String TEXT_TYPE = " TEXT";
    private static final String INTEGER_TYPE = " INTEGER";
    private static final String DOUBLE_TYPE = " DOUBLE";
    private static final String DATETIME_TYPE = " DATETIME";
    private static final String COMMA_SEP = ",";

    private static final String CREATE_CATEGORIAS =
            "CREATE TABLE " + EsquemaBD.CATEGORIAS.TB_NOMBRE +
            " (" +
                EsquemaBD.CATEGORIAS.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                EsquemaBD.CATEGORIAS.NOMBRE + TEXT_TYPE + COMMA_SEP +
                EsquemaBD.CATEGORIAS.RECURSO_ID + INTEGER_TYPE + COMMA_SEP +
                EsquemaBD.CATEGORIAS.FORMA_ID + INTEGER_TYPE + COMMA_SEP +
                EsquemaBD.CATEGORIAS.COLOR_ID + INTEGER_TYPE + COMMA_SEP +
                EsquemaBD.CATEGORIAS.TIPO + INTEGER_TYPE + COMMA_SEP +
                EsquemaBD.CATEGORIAS.ES_CUENTA + INTEGER_TYPE +
            " )";

    private static final String CREATE_TRANSACCIONES =
            "CREATE TABLE " + EsquemaBD.TRANSACCIONES.TB_NOMBRE +
            " (" +
                    EsquemaBD.TRANSACCIONES.ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                    EsquemaBD.TRANSACCIONES.COSTO + DOUBLE_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.CAT_ID + INTEGER_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.NOTA + TEXT_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.FEC_ALTA + DATETIME_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.DESCRIPCION + TEXT_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.CTA_PRIN_ID + INTEGER_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.CTA_SECU_ID + INTEGER_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.TIPO_TRANSACCION + INTEGER_TYPE + COMMA_SEP +
                    EsquemaBD.TRANSACCIONES.TRANSACCION_PROG_ID + INTEGER_TYPE +
            " )";

    private static final String DELETE_CATEGORIAS = "DROP TABLE IF EXISTS " + EsquemaBD.CATEGORIAS.TB_NOMBRE;
    private static final String DELETE_TRANSACCIONES = "DROP TABLE IF EXISTS " + EsquemaBD.TRANSACCIONES.TB_NOMBRE;

    public ContextoBD(Context context) {
        super(context, BD_NOMBRE, null, BD_VERSION);
        Categorias = new Categorias(this);
        //Transacciones = new TD_Transacciones(this);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_CATEGORIAS);
        db.execSQL(DELETE_TRANSACCIONES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CATEGORIAS);
        db.execSQL(CREATE_TRANSACCIONES);
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('SIN CATEGORIA',19,10,10,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('SUELDO',15,6,6,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('CREDITO',14,0,0,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('PRESTAMO',17,8,8,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('AGUA',3,0,0,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('LUZ',7,1,1,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('CABLE',4,2,2,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('CAMION',2,3,3,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('CHUCHERIAS',1,4,4,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('COMIDA',5,5,5,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('COMPRAS',6,6,6,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('GAS',0,7,7,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('GASOLINA',8,8,8,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('PAREJA',9,9,9,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,FORMA_ID,COLOR_ID,TIPO) VALUES ('ROPA',10,9,9,0)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,COLOR_ID,ES_CUENTA) VALUES ('BANAMEX',14,3,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,COLOR_ID,ES_CUENTA) VALUES ('BANCOMER',14,6,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,COLOR_ID,ES_CUENTA) VALUES ('EFECTIVO',18,9,1)");
        db.execSQL("INSERT INTO " + EsquemaBD.CATEGORIAS.TB_NOMBRE + " (NOMBRE,RECURSO_ID,COLOR_ID,ES_CUENTA) VALUES ('FINANCIAMIENTO',17,1,1)");
    }
}

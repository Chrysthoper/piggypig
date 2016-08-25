package Persistencia;

import android.provider.BaseColumns;

public class EsquemaBD {

    public EsquemaBD() {}

    public static abstract class TRANSACCIONES implements BaseColumns {
        public static final String TB_NOMBRE = "TRANSACCIONES";
        public static final String ID = "ID";
        public static final String COSTO = "COSTO";
        public static final String CAT_ID = "CATEGORIA_ID";
        public static final String FEC_ALTA = "FEC_ALTA";
        public static final String NOTA = "NOTA";
        public static final String DESCRIPCION = "DESCRIPCION";
        public static final String CTA_PRIN_ID = "CUENTA_PRIN_ID";
        public static final String CTA_SECU_ID = "CUENTA_SECU_ID";
        public static final String TIPO_TRANSACCION = "TIPO_TRANSACCION";//0 = SALIDA, 1 = ENTRADA, 2 = TRANSFERENCIA
        public static final String TRANSACCION_PROG_ID = "TRANSACCION_PROG_ID";
    }

    public static abstract class TRANSACCIONES_PROGRAMADAS implements BaseColumns {
        public static final String TB_NOMBRE = "TRANSACCIONES_PROGRAMADAS";
        public static final String ID = "ID";
        public static final String COSTO = "COSTO";
        public static final String CAT_ID = "CATEGORIA_ID";
        public static final String FEC_ALTA = "FECHA_ALTA";
        public static final String NOTA = "NOTA";
        public static final String DESCRIPCION = "DESCRIPCION";
        public static final String CUENTA_PRIN = "CUENTA_PRIN_ID";
        public static final String TIPO_TRANSACCION = "TIPO_TRANSACCION";
        public static final String FEC_SIGUIENTE = "FECHA_SIGUIENTE";
        public static final String INTERVALO = "INTERVALO";
        public static final String ACTIVA = "ACTIVA";
    }

    public static abstract class CATEGORIAS implements BaseColumns {
        public static final String TB_NOMBRE = "CATEGORIAS";
        public static final String ID = "ID";
        public static final String NOMBRE = "NOMBRE";
        public static final String RECURSO_ID = "RECURSO_ID";
        public static final String FORMA_ID = "FORMA_ID";
        public static final String COLOR_ID = "COLOR_ID";
        public static final String TIPO = "TIPO";
        public static final String ES_CUENTA = "ES_CUENTA";
    }
}

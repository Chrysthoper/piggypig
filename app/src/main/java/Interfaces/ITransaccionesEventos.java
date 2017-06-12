package Interfaces;

import java.io.Serializable;

/**
 * Created by Chrysthoper on 14/08/2016.
 */
public interface ITransaccionesEventos extends Serializable {
    void tecladoCalculadora(String tecla);
    void cuentaSeleccionada(int cuenta);
}

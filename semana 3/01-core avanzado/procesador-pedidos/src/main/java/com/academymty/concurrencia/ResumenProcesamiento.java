package com.academymty.concurrencia;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * El resultado final del procesamiento. Es el UNICO objeto que se guarda en
 * disco con serializacion Java (ObjectOutputStream). Los resultados
 * individuales de cada pedido se guardan como texto plano con Files
 * (ver ProcesadorPedidos) -- son cosas distintas a proposito, para que
 * quede claro cuando usar cada una.
 *
 * Implementa Serializable: es el "contrato" que le dice a la JVM que sabe
 * convertirse a bytes y reconstruirse desde bytes. Sin este "implements",
 * ObjectOutputStream.writeObject() lanza NotSerializableException en
 * cuanto lo intentas.
 */
public class ResumenProcesamiento implements Serializable {

    /**
     * PREGUNTA 1 del documento: por que existe serialVersionUID.
     *
     * Es la "version" del formato serializado de esta clase. Cuando lees un
     * objeto de vuelta con ObjectInputStream, Java compara el UID que trae
     * el archivo contra el UID de la clase que tienes cargada AHORA. Si no
     * coinciden, lanza InvalidClassException y se acabo la lectura, aunque
     * los campos sean practicamente los mismos.
     *
     * Si NO lo declaras a mano, el compilador genera uno automaticamente a
     * partir de detalles de la clase (nombre, campos, metodos...). El
     * problema es que ese calculo cambia si tocas casi cualquier cosa de la
     * clase -- hasta agregar un metodo puede cambiarlo -- y entonces un
     * archivo .dat guardado la semana pasada deja de poder leerse con el
     * .jar de hoy, aunque el cambio que hiciste no tuviera nada que ver con
     * los datos. Declararlo a mano (aqui, en 1L) fija ese numero y lo pone
     * bajo tu control: tu decides cuando "romper" la compatibilidad, no el
     * compilador por accidente.
     */
    private static final long serialVersionUID = 1L;

    private final int totalPedidos;
    private final int exitosos;
    private final int fallidos;
    private final double montoTotal;
    private final LocalDateTime generadoEn;

    /**
     * PREGUNTA 2 del documento: que le pasa a un campo transient.
     *
     * Este campo guarda el nombre del hilo que arma el resumen. Es un dato
     * de ESTA ejecucion nada mas: si maniana deserializas el archivo, ese
     * hilo ya ni existe. Guardarlo no tendria sentido -- por eso es
     * transient.
     *
     * ObjectOutputStream SALTA los campos transient al escribir: no van
     * dentro del archivo. Al leer con ObjectInputStream, Java reconstruye
     * el objeto y a ese campo le pone el valor por defecto de su tipo
     * (null para objetos, 0 para numeros, false para boolean) -- el
     * constructor NO se vuelve a ejecutar, asi que no hay forma de que se
     * "recalcule" solo. Lo vamos a comprobar en Main: se imprime antes de
     * guardar (con el nombre del hilo) y despues de leer el .dat (sale null).
     */
    private transient String hiloQueLoGenero;

    public ResumenProcesamiento(int totalPedidos, int exitosos, int fallidos, double montoTotal) {
        this.totalPedidos = totalPedidos;
        this.exitosos = exitosos;
        this.fallidos = fallidos;
        this.montoTotal = montoTotal;
        this.generadoEn = LocalDateTime.now();
        this.hiloQueLoGenero = Thread.currentThread().getName();
    }

    public int totalPedidos() {
        return totalPedidos;
    }

    public int exitosos() {
        return exitosos;
    }

    public int fallidos() {
        return fallidos;
    }

    public double montoTotal() {
        return montoTotal;
    }

    public LocalDateTime generadoEn() {
        return generadoEn;
    }

    public String hiloQueLoGenero() {
        return hiloQueLoGenero;
    }

    @Override
    public String toString() {
        return "ResumenProcesamiento{" +
                "totalPedidos=" + totalPedidos +
                ", exitosos=" + exitosos +
                ", fallidos=" + fallidos +
                ", montoTotal=" + montoTotal +
                ", generadoEn=" + generadoEn +
                ", hiloQueLoGenero=" + hiloQueLoGenero +
                '}';
    }
}

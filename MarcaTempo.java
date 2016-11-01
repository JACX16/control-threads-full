package programacaoconcorrente;

/**
 *
 * @author Jose
 */
class MarcaTempo {

    private  long inicio = System.currentTimeMillis();

    public double tempo() {
        long agora = System.currentTimeMillis();
        return (((agora - inicio) / 1000.0) / 60.0);
    }
}

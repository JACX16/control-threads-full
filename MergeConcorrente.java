
package programacaoconcorrente;

import java.util.concurrent.Semaphore;


class MergeConcorrente extends Thread{
   
    private int tamanho;
    private int controle;
    static volatile Semaphore semaforoA = new Semaphore(0, false);
    static volatile Semaphore semaforoB = new Semaphore(0, false);
    private Sequencial merge;
    private int meio;

    public MergeConcorrente(int vetor[],int control){
        tamanho = vetor.length;
        controle = control;
        meio = (vetor.length +1)/2;
        merge = new Sequencial(vetor);

    }
    @Override
    public void run() {
        if (controle == 1) {
            merge.sort(0, meio);
            semaforoA.release(1);
    }
      if (controle == 2) {
            merge.sort(meio, tamanho);
            semaforoB.release(1);
      }
        if (controle == 3) {
            try {
                semaforoA.acquire(1);//para a thread sort1
                semaforoB.acquire(1);//para a thread sort2 
                merge.intercala(0, meio, tamanho);
            } catch (InterruptedException e) {
      }
    }

}
}

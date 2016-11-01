/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package programacaoconcorrente;

/**
 *
 * @author Jose
 */
public class Sequencial {
    //ATRIBUTO.

    private int vetor[];

    //CONSTRUTOR.
    public Sequencial(int[] vetor) {
        setV(vetor);
    }
    //SETV ATRIBUI O VETOR RECEBIDO NO CONSTRUTOR AO ATRIBUTO V.

    public void setV(int[] v) {
        vetor = v;
    }
    //-------------------------------------------------------------------------------------------------------------------------------------

    /*METODO RESPONSAVEL POR INTERCALAR AS METADES DOS ARRAYS, QUE ESTAO RESPECTIVAMENTE DIVIDIDOS. ELE UTILIZA UM VETOR AUXILIAR PARA IR
    COLOCANDO OS VALORES EM ORDEM E DEPOIS VOLTA-OS PARA O VETOR ORIGINAL.*/
    public void intercala(int posiçaoinicial, int meio, int posiçaofinal) {
        int w[] = new int[posiçaofinal - posiçaoinicial]; // vetor auxiliar
        int i = posiçaoinicial; // itera pelo primeiro vetor ordenado
        int j = meio; // itera pelo segundo vetor ordenado
        int k = 0;

        while (i < meio && j < posiçaofinal) {
            if (vetor[i] <= vetor[j]) {
                w[k++] = vetor[i++];
            } else {
                w[k++] = vetor[j++];
            }
        }

        while (i < meio) {
            w[k++] = vetor[i++];
        }
        while (j < posiçaofinal) {
            w[k++] = vetor[j++];
        }

        for (i = posiçaoinicial; i < posiçaofinal; ++i) {
            vetor[i] = w[i - posiçaoinicial];
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------

    /*METODO QUE FAZ AS CHAMADAS RECURSIVAS DIVIDINDO O VETOR AO MEIO (SE POSSIVEL) ATE QUE NAO SEJA MAIS POSSIVEL REPARTI-LO, APOS ISSO
    PRA CADA CHAMADA RECURSIVA QUE TINHA NA PILHA, CHAMA O METODO INTERCALA.*/
    public void sort(int p, int r) {
        if (p < r - 1) {
            int q = (p + r) / 2;
            sort(p, q);
            sort(q, r);
            intercala(p, q, r);
        }
    }
    //-------------------------------------------------------------------------------------------------------------------------------------

    //CHAMA O METODO SORT RECURSIVO, ACIMA.
    public void sort() {
        sort(0, vetor.length);
    }
    //-------------------------------------------------------------------------------------------------------------------------------------
}

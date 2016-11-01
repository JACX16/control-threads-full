package programacaoconcorrente;

public class TesteA {

    private CriaVetor criaVetor;               //cria objeto do tipo CriaVetor, para criar um vetor aleatório
    private int[] vetor;                       //vetor aleatóreo para ordenação concorente
    private int[] vetor2;                      //vetor aleatóreo para ordenação sequencial
    private MarcaTempo marcaTempoSequencial;   //cria objeto que marca o tempo do olgoritimo sequencial
    private MarcaTempo marcaTempoConcorrente;  //cria objeto que marca o tempo do olgoritimo concorente
    private double[] tempoSequencial;          //vetor que maraca tempo das 10 execuções sequencial
    private double[] tempoConcorrente;         //vetor que maraca tempo das 10 execuções concorente
    private MergeConcorrente mergeParte1;      //thead sort1
    private MergeConcorrente mergeParte2;      //thead sort2
    private MergeConcorrente mergeIntercala;   //thead intercala
    private Sequencial mergeSequencial;        //merge sequencial (original)

//-----------------------------Construtor Padrão--------------------------------
    public TesteA() {
        tempoSequencial = new double[10];      //inicia vetor que armazena o tempo do algoritimo sequencial
        tempoConcorrente = new double[10];     //inicia vetor que armazena o tempo do algoritimo concorente
        for (int i = 0; i < 10; i++) {         //Roda os algoritimos concorente e sequencial 10 vezes
            criaVetor = new CriaVetor();       //
            vetor = criaVetor.escreveVet();
            vetor2 = new int[22000];
            copiaVetor();
            mergeParte1 = new MergeConcorrente(vetor, 1);
            mergeParte2 = new MergeConcorrente(vetor, 2);
            mergeIntercala = new MergeConcorrente(vetor, 3);
            marcaTempoConcorrente = new MarcaTempo(); //inicia o marcador de tempo (inicia o cronometro) para o algoritimo concorente
            mergeParte1.start();             //inicia a trhead parte 1 (ver pagina 30 da classe MergeConcorente)
            mergeParte2.start();             //inicia a trhead parte 2 (ver pagina 34 da classe MergeConcorente)
            mergeIntercala.start();          //inicia a trhead intercala (ver pagina 38 da classe MergeConcorente)
            try {
                mergeParte1.join();
                mergeParte2.join();
                mergeIntercala.join();
            } catch (InterruptedException e) {
            }                                //garante a execução das trheads até o fim para depois marcar o tempo;
            tempoConcorrente[i] = marcaTempoConcorrente.tempo(); //para o cronometro e salva o tempo gasto  no algoritimo concorente


            marcaTempoSequencial = new MarcaTempo();  //inicia o marcador de tempo (inicia o cronometro) para o algoritimo sequencial
            mergeSequencial = new Sequencial(vetor2);
            mergeSequencial.sort();          //inicia o gerge sequencial;
            tempoSequencial[i] = marcaTempoSequencial.tempo(); //para o cronometro e salva o tempo gasto no algoritimo sequencial
        }
        imprime();  //imprime o tempo de cada execução e calcula a media

    }
//------------------------------------------Método imprime e calcula media------------------------------------------------------------

    private void imprime() {
        double media = 0;
        double soma = 0;
        int tmp;
        System.out.println("--------------------------\nAlgoritimo Concorente\n-------------------------\n");
        for (int i = 0; i < 10; i++) {
            soma = tempoConcorrente[i] + soma;
            tmp = i + 1;
            System.out.printf("0%d  %f", tmp, tempoConcorrente[i]);
            System.out.println(" min.");
        }
        media = soma / 10;
        System.out.printf("Media: %f", media);
        System.out.println(" min.");
        double dPadrao = caculaDesvioPadrao(media, 1);
        System.out.printf("Desvio Padrão: %f", dPadrao);
        System.out.println(" min.");

        System.out.print("-------------------------\nAlgoritimo Sequencial\n--------------------------\n");
        double soma2 = 0;
        for (int i = 0; i < 10; i++) {

            soma2 = tempoSequencial[i] + soma2;
            tmp = i + 1;
            System.out.printf("0%d  %f", tmp, tempoSequencial[i]);
            System.out.println(" min.");
        }
        media = soma2 / 10;
        System.out.printf("Media: %f", media);
        System.out.println(" min.");
        double dPadraoS = caculaDesvioPadrao(media, 2);
        System.out.printf("Desvio Padrão: %f", dPadraoS);
        System.out.println(" min.");

    }
//--------------------------------------Método Calcula Desvio Padrão-----------------------------------------------

    public double caculaDesvioPadrao(double media, int controle) {
        if (controle == 1) {
            double tmp = 0;
            for (int i = 0; i < 10; i++) {
                tmp = ((tempoConcorrente[i] - media) * (tempoConcorrente[i] - media) + tmp);
            }
            tmp = tmp / 10;

            return Math.sqrt(tmp);
        } else {
            double tmp = 0;
            for (int i = 0; i < 10; i++) {
                tmp = ((tempoSequencial[i] - media) * (tempoSequencial[i] - media) + tmp);
            }
            tmp = tmp / 10;

            return Math.sqrt(tmp);

        }
    }
//--------------------------------Método main----------------------------------------------

    public static void main(String[] args) {
        TesteA inicio = new TesteA();
    }

//--Método copia Vetor faz uma copia dos elementos do vetor para vetor2(vetor executa concorrente e vetor2 sequencial--
    private void copiaVetor() {
        for (int i = 0; i < 22000; i++) {
            vetor2[i] = vetor[i];
        }
    }
}

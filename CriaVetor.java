package programacaoconcorrente;

class CriaVetor {

    private int vet[];

    public CriaVetor() {
        vet = new int[22000];
        escreveVet();
    }

    public int[] escreveVet() {
        for (int i = 0; i < 22000; i++) {
            vet[i] = (int) (Math.random() * 12001-6000);
        }
        return vet;
    }

}

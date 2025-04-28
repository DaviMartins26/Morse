public class Main {
    public static void main(String[] args) {
        Morse morse = new Morse();
        morse.iniciar();
        morse.inserirElementos();

        //morse.imprimirArvore();

        System.out.println(morse.buscaCodigo(".-"));
        System.out.println(morse.buscaFrase(".- -- ... -"));
        System.out.println(morse.buscaFrase(".- -- ... - "));
        System.out.println(morse.decifrarMorse('V')); // tem qu8e usar aspa simples e MAISCULO

        System.out.println("Programa encerrado"); // pra saber se não ta preso em algum loop

    }
}
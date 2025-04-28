public class Morse{
    // classe que cria o nosso NO
    private class No {
        char elemento; // char é tipo uma String, porem de um unico elemento so podemos ter A em vez de ABC...
        No esquerda; 
        No direita;

        No (char elemento){
            this.elemento = elemento;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private No raiz; // criando o no Raiz
    public void iniciar(){
        raiz = new No('\0'); // '\0' é o null para String e Char(que é nosso caso)
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------INSERT---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    public void inserir(String codigo, char elemento){ // o codigo morse tem que ser String pois é varios . e -
        No atual = raiz; // cria o no atual pra ser o raiz
        int i = 0; // iniciando contador pro while
        while (i < codigo.length()) {// este length esta sendo usado para contar o valor de uma String
            char simbolo = codigo.charAt(i); // charAt retonra um elemento especifico de uma String, no caso vai retonrar . ou - de acordo com o indice
            if (simbolo == '.'){ // se for . segue o caminho da esquerda
                if (atual.esquerda == null){ // se esquerda for null
                    atual.esquerda = new No('\0'); // seta qual o atual pra adicionar o elemento assim que sair do while no No correto
                }
                atual = atual.esquerda; // se não esquesda não for null avança pra esquerda
            } else if (simbolo == '-') { // se for - segue pra direita
                if (atual.direita == null){ // se não apontar pra nada
                    atual.direita = new No('\0'); // cria o No NUll pra ser adicionado depois o elemento
                }
                atual = atual.direita; // seta qual o atual pra adicionar o elemento assim que sair do while no No correto
            }
            i = i +1; // faz o controle do i
        }  
        // importante adicionar aqui pq se adicionar na hora que cria pode fazer uma arvore infinita apenas com o elemento que ta sendo adicionado
        atual.elemento = elemento; // Adiciona o Elemento no ultimo No criado
    }

    public void inserirElementos() { // logica que cria a arvore de forma automatica
        String[] codigos = { // acredito que esse tipo de array é permitido visto que so é utilizado pra criar o No
                ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
                ".---", "-.-", ".-..", "--", "-.", "---", ".--.", "--.-", ".-.",
                "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..",
                "-----", ".----", "..---", "...--", "....-", ".....",
                "-....", "--...", "---..", "----."
        };

        char[] letras = {
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
                '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'
        };

        int i = 0;
        while (i < 36) {
            inserir(codigos[i], letras[i]); // chama o metodo de inserir enquanto passa os metodos de acordo com o indice de cada array
            // System.out.println("interindo"+codigos[i]+letras[i]); // pra testar
            i = i + 1;
        }
    }

    
    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------INSERT---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 
}

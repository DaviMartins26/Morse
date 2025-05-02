public class Morse{
    // classe que cria o nosso NO
    private class No {
        String elemento;
        No esquerda; 
        No direita;

        No (String elemento){
            this.elemento = elemento;
            this.esquerda = null;
            this.direita = null;
        }
    }

    private No raiz; // criando o no Raiz
    public void iniciar(){
        raiz = new No(""); // passa o elemento como vazio e não null
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------INSERT---------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    public void inserir(String codigo, String elemento){ // o codigo morse tem que ser String pois é varios . e -
        No atual = raiz; // cria o no atual pra ser o raiz
        int i = 0; // iniciando contador pro while
        while (i < codigo.length()) {// este length esta sendo usado para contar o valor de uma String
            String simbolo = codigo.substring(i, i + 1); // substring está procurando o elemento da String correspondente ao i
            if (simbolo.equals(".")) { // equals faz a comparação de string, se for . segue o caminho da esquerda
                if (atual.esquerda == null){ // se esquerda for null
                    atual.esquerda = new No(""); // seta qual o atual pra adicionar o elemento assim que sair do while no No correto
                }
                atual = atual.esquerda; // se não esquesda não for null avança pra esquerda
            } else if (simbolo.equals("-")) { // se for - segue pra direita
                if (atual.direita == null){ // se não apontar pra nada
                    atual.direita = new No(""); // cria o No NUll pra ser adicionado depois o elemento
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

        String[] letras = {
                "A", "B", "C", "D", "E", "F", "G", "H", "I",
                "J", "K", "L", "M", "N", "O", "P", "Q", "R",
                "S", "T", "U", "V", "W", "X", "Y", "Z",
                "0", "1", "2", "3", "4", "5", "6", "7", "8", "9"
        };

        int i = 0;
        while (i < 36) {
            inserir(codigos[i], letras[i]); // chama o metodo de inserir enquanto passa os metodos de acordo com o indice de cada array
            // System.out.println("interindo"+codigos[i]+letras[i]); // pra testar
            i = i + 1;
        }
    }


    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------Imprimir--------------------------------------------------- 
    ---------------------------------------------------------------------------------------------------*/ 

    // passando os Parametros para imprimir, no caso a Raiz da arvore e sua camada que é 0 ou 1 depende da literatuira
    public void imprimirArvore(){
        parametroArvore(raiz,0);
    }

    public void parametroArvore(No no, int nivel) { 
        if (no == null) { // se for null retorna nada pra saber que ta na hora de parar
            return;
        }
        // Imprimi primeiro a direira (os -)
        parametroArvore(no.direita, nivel +1);

        // gera os espaços pra dar o efeito de arvore
        int i = 0;
        while (i < nivel) {
            System.out.print("    ");
            i = i+1;         
        }

        // imprime o elemento do No (letra ou numero)
        if(no.elemento == ""){ // verifica se o elemento esta vazio
            System.out.println("null"); // se for null imprime null
        } else {
            System.out.println(no.elemento); // imprime o elemento (Letra ou numero)
        }

        // depois iimprime os da esquerda (os .)
        parametroArvore(no.esquerda, nivel +1);
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------De Codigo pra Letra----------------------------------------
    ---------------------------------------------------------------------------------------------------*/ 

    public String buscaCodigo(String codigo){
        No atual = raiz; // volta pra raiz
        int i = 0;
        while (i <codigo.length()) { // esse length pode ser usado pois é uma sting que esta sendo lida
            String simbolo = codigo.substring(i, i + 1);; // substring está procurando o elemento da String correspondente ao i e  caso vai retornar . ou -
            if (simbolo.equals(".")){ // equals faz a comparação,se for . deve ir pra esquerda

                if (atual.esquerda != null){ // se apontar pra um valor diferente de null avança a arvore
                    atual = atual.esquerda;
                } else {
                    return "#"; //retorna # em string pra sinalziar que não há nada ali
                }
            } else if (simbolo.equals("-")){ // se for - deve ir pra direita
                if (atual.direita != null){ // enquanto não for null vai avançando
                    atual = atual.direita;
                } else{
                    return "#";// retorna # em string
                }
            } 
            i = i +1;
        }
        return atual.elemento; // retorna o elemento do atual 
    }

    public String buscaFrase(String codigoFrase){
        String resultado = ""; // variavel pra retonrar oque foi decifrado
        String codigoAtual = ""; //  variavel pra saber o codigo da letra da vez
        int i = 0;

        while (i < codigoFrase.length()) { // length pra calcular o valor de uma string que pode variar
            String c = codigoFrase.substring(i, i + 1); // pego o elemento da string correspondente a i
            if (!c.equals(" ")){  // se o elemento da string não for um espaço eu adiciono este elemento ao Codigo atual
                codigoAtual = codigoAtual + c;
            } else{
                if (codigoAtual.length()>0){ // quando o elemento for um space significa que temos o codigo da letra
                    String letra = buscaCodigo(codigoAtual); // chama o metodo pra buscar a letra baseada no codigo que foi passado antes do espaço
                    resultado = resultado + letra; // adiciona a letra em resultado
                    resultado = resultado + " "; // da um espaço pra proxima letra
                    codigoAtual = " "; // zera o codigo atual pra poder pesquisar a proxima letra

                }
            }
            i = i +1; // avança o while
        }
        // por algum motivo não le o ultimo codigo se a String não for finalizada com " ", isso deve resolver
        if (codigoAtual.length() >0){ // se a length da string for maioor que 0 entra ai
            String letra = buscaCodigo(codigoAtual); // procura a letra correspondente ao codigo
            resultado = resultado +letra; // adiciona a letra ao resultado
        }
        return resultado; // retorna o resultado
    }

    /* ---------------------------------------------------------------------------------------------------
    -------------------------------------------De Letra pra Morse----------------------------------------
    ---------------------------------------------------------------------------------------------------*/ 

    public String decifrarMorse(String letra){ // passa o parametro da Letra pra ser codificada
        return buscarCodigoMorse(raiz,letra,"");
    }

    public String buscarCodigoMorse(No no, String letra, String caminho){ // recebe o parametro pra ser codificado
        if (no == null){ // se o no for null retorna null como String
            return "";
        }
        if (no.elemento.equals(letra)){ // se o elemento do No for iigual a letra informada
            return caminho;
        }

        // começa a procurar pela esquerda (.)
        String caminhoEsquerda = buscarCodigoMorse(no.esquerda, letra, caminho+"."); // olha sempre pro no esquerdo primeiro
        if (caminhoEsquerda.length()>0){ // fica procurando até o final do raiz
            return caminhoEsquerda; // se achar retorna o elmento
        }

        // se não achar vai pra direito (-)
        String caminhoDireita = buscarCodigoMorse(no.direita,letra,caminho+"-");
        return caminhoDireita;

    }

    public String fraseParaMorse(String frase) {
        String morse = ""; // string final com o resultado
        int i = 0;
    
        while (i < frase.length()) {
            String c = frase.substring(i, i + 1); // pega letra por letra
    
            if (c.equals(" ")) {
                morse = morse + "  "; // dois espaços para separar palavras
            } else {
                String letra = c.toUpperCase(); // força letra maiúscula que talvez n seja necessario agora
                try {
                    String codigo = decifrarMorse(letra);
                    if (codigo.length() > 0) {
                        morse = morse + codigo + " "; // espaço entre letras
                    } else {
                        morse = morse + "? "; // caractere desconhecido
                    }
                } catch (Exception e) {
                    morse = morse + "? ";
                }
            }
    
            i = i + 1;
        }
    
        return morse;
    }
    
}

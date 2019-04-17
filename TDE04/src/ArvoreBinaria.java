class ArvoreBinaria {

    private Node raiz;

    Node getRaiz() {
        return raiz;
    }

    void insereElemento(int dado) {

        Node novo = new Node(dado);

        if (vazia()){
            raiz = novo;
            return;
        }

        Node pai = raiz;

        while ((pai.esquerda != null && dado < pai.dado) || (pai.direita != null && dado > pai.dado))
        {
            if (dado < pai.dado)
                pai = pai.esquerda;
            else
                pai = pai.direita;
        }
        if (dado < pai.dado)
            pai.esquerda = novo;
        else
            pai.direita = novo;
        rebalancearAcima(novo);
    }

    Node removeElemento(int dado) {
        if (vazia())
            return null;

        Node raiz_atual = raiz;
        Node pai = raiz;

        while (raiz_atual != null) {
            if (dado == raiz_atual.dado)
                break;
            pai = raiz_atual;
            if (dado < raiz_atual.dado)
                raiz_atual = raiz_atual.esquerda;
            else
                raiz_atual = raiz_atual.direita;
        }

        if (raiz_atual == null)
            return null; // Não existe


        if (pai.direita == raiz_atual)
            pai.direita = null;
        else
            pai.esquerda = null;

        return raiz_atual;
    }

    boolean existeElemento(int dado) {
        if (vazia())
            return false;

        Node raiz_atual = raiz;

        while (raiz_atual != null) {
            if (dado == raiz_atual.dado)
                break;

            if (dado < raiz_atual.dado)
                raiz_atual = raiz_atual.esquerda;
            else
                raiz_atual = raiz_atual.direita;
        }

        return raiz_atual != null;
    }

    Node encontraElemento(int dado) {
        if (vazia())
            return null;

        Node raiz_atual = raiz;

        while (raiz_atual != null) {
            if (dado == raiz_atual.dado)
                break;

            if (dado < raiz_atual.dado)
                raiz_atual = raiz_atual.esquerda;
            else
                raiz_atual = raiz_atual.direita;
        }

        return raiz_atual;
    }

    private boolean vazia() {
        return raiz == null;
    }

    void imprimePreordem(Node node) {
        if (node == null)
            return;
        System.out.println(node.dado);
        imprimePreordem(node.esquerda);
        imprimePreordem(node.direita);
    }

    void imprimeInordem(Node node) {
        if (node == null)
            return;
        imprimePreordem(node.esquerda);
        System.out.println(node.dado);
        imprimePreordem(node.direita);
    }

    void imprimePosordem(Node node) {
        if (node == null)
            return;
        imprimePreordem(node.esquerda);
        imprimePreordem(node.direita);
        System.out.println(node.dado);
    }

    private int altura(Node node) {
        if (node != null)
            return 1 + Math.max(altura(node.esquerda), altura(node.direita));
        return 0;
    }

    Node getPai(Node node){

        if (node == raiz) return null;

        Node raiz_atual = raiz;

        while (raiz_atual != null) {
            if (node == raiz_atual.esquerda || node == raiz_atual.direita)
                break;
            if (node.dado < raiz_atual.dado)
                raiz_atual = raiz_atual.esquerda;
            else
                raiz_atual = raiz_atual.direita;
        }

        return raiz_atual;

    }

    int balanceamento(Node node){
        if(node == null) return 0;
        return altura(node.direita) - altura(node.esquerda);
    }

    private void rebalancearAcima(Node node){
        if(node == null) return;
        rebalancear(node);
        
    }

    private void rebalancear(Node node) {
        if (balanceamento(node) < -1){
            if (balanceamento(node.esquerda) > 0){
                rotacaoEsquerda(node.esquerda);
            }
            rotacaoDireita(node);
        }
        else if(balanceamento(node) > 1){
            if (balanceamento(node.direita) > 0){
                rotacaoDireita(node.direita);
            }
            rotacaoEsquerda(node);
        }
    }

    void rotacaoEsquerda(Node node) {

        Node pai = getPai(node);

        //  pai
        //   |
        //  node
        //    \
        //     x
        //    /
        //   y

        Node x = node.direita;
        Node y = x.esquerda;

        //   pai
        //    |
        //    x
        //   / \
        // node y

        x.esquerda = node;
        x.direita = y;

        if (pai.dado <= x.dado){
            pai.direita = x;
        }
        else {
            pai.esquerda = x;
        }

    }

    void rotacaoDireita(Node node) {
        Node pai = getPai(node);

        //  pai
        //   |
        //  node
        //   /
        //  x
        //   \
        //    y

        Node x = node.esquerda;
        Node y = x.direita;

        //   pai
        //    |
        //    x
        //   / \
        //  y  node

        x.direita = node;
        x.esquerda = y;

        if (pai.dado <= x.dado){
            pai.direita = x;
        }
        else {
            pai.esquerda = x;
        }
    }
}

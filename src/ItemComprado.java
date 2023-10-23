public class ItemComprado {
    private int quantidade;
    private String nome;
    private float precoUnitario;
    private float valorTotal;
    private Produto produto;

    public ItemComprado(Produto produto, int quantidade, float precoUnitario) {
        this.quantidade = quantidade;
        this.nome = produto.getNome();
        this.precoUnitario = precoUnitario;
        this.valorTotal = precoUnitario * quantidade;
        this.produto = produto;
    }

    //todo
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public float getPrecoUnitario() {
        return this.precoUnitario;
    }

    //todo
    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getValorTotal() {
        return this.valorTotal;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Produto getProduto() {
        return this.produto;
    }

}

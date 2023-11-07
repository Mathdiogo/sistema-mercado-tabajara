import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ItemComprado {
    private int quantidade;
    private String nome;
    private float precoUnitario;
    private float valorTotal;
    private Produto produto;

    //Abaixo existem anotações no construtor, essas anotações são para a biblioteca Jackson, que transforma classes em JSON e vice-versa
    //Para que essa conversão seja feita, as anotações definem como os campos serão ao serem transformados para JSON

    @JsonCreator
    public ItemComprado(@JsonProperty("produto") Produto produto, @JsonProperty("quantidade") int quantidade, @JsonProperty("precoUnitario") float precoUnitario) {
        this.quantidade = quantidade;
        this.nome = produto.getNome();
        this.precoUnitario = precoUnitario;
        this.valorTotal = precoUnitario * quantidade;
        this.produto = produto;
    }

    //funções getters e setters

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

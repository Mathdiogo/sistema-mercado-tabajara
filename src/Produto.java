import java.util.Date;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private Date dataDeValidade;

    public Produto(int codigo, String nome, String descricao, Date dataDeValidade) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.dataDeValidade = dataDeValidade;
    }

    public boolean verificarValidade() {
        Date dataAtual = new Date();
        return dataAtual.after(this.dataDeValidade);
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return this.nome;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDataDeValidade(Date data) {
        this.dataDeValidade = data;
    }

    public Date getDataDeValidade() {
        return this.dataDeValidade;
    }
}
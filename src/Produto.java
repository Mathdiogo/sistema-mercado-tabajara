import java.util.Calendar;
import java.util.Date;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private Date dataDeValidade;

    public Produto(int codigo, String nome, String descricao, int dia, int mes, int ano) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        setDataDeValidade(dia, mes, ano);
    }

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

    public void setDataDeValidade(int dia, int mes, int ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, dia);

        this.dataDeValidade = calendario.getTime();
    }

    public void setDataDeValidade(Date dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public Date getDataDeValidade() {
        return this.dataDeValidade;
    }
}
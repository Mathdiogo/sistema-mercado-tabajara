import java.util.Calendar;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Produto {
    private int codigo;
    private String nome;
    private String descricao;
    private Date dataDeValidade;

    @JsonCreator
    public Produto(@JsonProperty("codigo") int codigo, @JsonProperty("nome") String nome, @JsonProperty("descricao") String descricao, 
                   @JsonProperty("dia") int dia, @JsonProperty("mes") int mes, @JsonProperty("ano") int ano) {
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
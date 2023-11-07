import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PessoaJuridica extends Cliente {
    private String razaoSocial;
    private int prazoMaximoDePagamento;

    //Abaixo existem anotações no construtor, essas anotações são para a biblioteca Jackson, que transforma classes em JSON e vice-versa
    //Para que essa conversão seja feita, as anotações definem como os campos serão ao serem transformados para JSON

    @JsonCreator
    public PessoaJuridica(@JsonProperty("nome") String nome, @JsonProperty("razaoSocial") String razaoSocial, @JsonProperty("endereco") Endereco endereco, 
                          @JsonProperty("cnpj") String cnpj, @JsonProperty("prazoMaximoDePagamento") int prazoMaximoDePagamento, @JsonProperty("dataDia") int dataDia, 
                          @JsonProperty("dataMes") int dataMes, @JsonProperty("dataAno") int dataAno) {
        super(nome, cnpj, endereco, dataDia, dataMes, dataAno, "juridica");
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

    //construtor alternativo

    public PessoaJuridica(String nome, String razaoSocial, Endereco endereco, String cnpj, int prazoMaximoDePagamento, Date data) {
        super(nome, cnpj, endereco, data, "juridica");
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

    //métodos getters e setters

    public String getRazaoSocial() {
        return razaoSocial;
    }
    
    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public int getPrazoMaximoDePagamento() {
        return prazoMaximoDePagamento;
    }
    
    public void setPrazoMaximoDePagamento(int prazoMaximoDePagamento) {
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }
}

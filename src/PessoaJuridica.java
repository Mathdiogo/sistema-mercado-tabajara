import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PessoaJuridica extends Cliente {
    private String razaoSocial;
    private int prazoMaximoDePagamento;

    @JsonCreator
    public PessoaJuridica(@JsonProperty("nome") String nome, @JsonProperty("razaoSocial") String razaoSocial, @JsonProperty("endereco") Endereco endereco, 
                          @JsonProperty("cnpj") String cnpj, @JsonProperty("prazoMaximoDePagamento") int prazoMaximoDePagamento, @JsonProperty("dataDia") int dataDia, 
                          @JsonProperty("dataMes") int dataMes, @JsonProperty("dataAno") int dataAno) {
        super(nome, cnpj, endereco, dataDia, dataMes, dataAno, "juridica");
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

    public PessoaJuridica(String nome, String razaoSocial, Endereco endereco, String cnpj, int prazoMaximoDePagamento, Date data) {
        super(nome, cnpj, endereco, data, "juridica");
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

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

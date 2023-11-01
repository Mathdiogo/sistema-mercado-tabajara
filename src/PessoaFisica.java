import java.util.Date;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PessoaFisica extends Cliente {
    private int quantidadeParcelas;

    @JsonCreator
    public PessoaFisica(@JsonProperty("nome") String nome, @JsonProperty("endereco") Endereco endereco, @JsonProperty("cpf") String cpf, 
                        @JsonProperty("quantidadeParcelas") int quantidadeParcelas, @JsonProperty("dataDia") int dataDia, @JsonProperty("dataMes") int dataMes, 
                        @JsonProperty("dataAno") int dataAno) {
        super(nome, cpf, endereco, dataDia, dataMes, dataAno, "fisica");
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public PessoaFisica(String nome, Endereco endereco, String cpf, int quantidadeParcelas, Date data) {
        super(nome, cpf, endereco, data, "fisica");
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}

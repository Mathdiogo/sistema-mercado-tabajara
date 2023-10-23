import java.util.Date;

public class PessoaFisica extends Cliente {
    public String cpf;
    public int quantidadeParcelas;

    public PessoaFisica(String nome, Endereco endereco, String cpf, int quantidadeParcelas, int dataDia, int dataMes, int dataAno) {
        super(nome, endereco, dataDia, dataMes, dataAno);
        this.cpf = cpf;
        this.quantidadeParcelas = quantidadeParcelas;
    }

    public PessoaFisica(String nome, Endereco endereco, String cpf, int quantidadeParcelas, Date data) {
        super(nome, endereco, data);
        this.cpf = cpf;
        this.quantidadeParcelas = quantidadeParcelas;
    }
    
    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public int getQuantidadeParcelas() {
        return quantidadeParcelas;
    }

    public void setQuantidadeParcelas(int quantidadeParcelas) {
        this.quantidadeParcelas = quantidadeParcelas;
    }
}

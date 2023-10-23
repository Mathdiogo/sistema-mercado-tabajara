import java.util.Date;

public class PessoaJuridica extends Cliente {
    public String cnpj;
    public String razaoSocial;
    public int prazoMaximoDePagamento;

    public PessoaJuridica(String nome, Endereco endereco, String cnpj, String razaoSocial, int prazoMaximoDePagamento, int dataDia, int dataMes, int dataAno) {
        super(nome, endereco, dataDia, dataMes, dataAno);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

    public PessoaJuridica(String nome, Endereco endereco, String cnpj, String razaoSocial, int prazoMaximoDePagamento, Date data) {
        super(nome, endereco, data);
        this.cnpj = cnpj;
        this.razaoSocial = razaoSocial;
        this.prazoMaximoDePagamento = prazoMaximoDePagamento;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
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

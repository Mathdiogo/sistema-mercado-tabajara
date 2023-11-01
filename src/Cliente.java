import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Cliente {
    protected String tipo;
    protected String nome;
    protected String numeroCadastro;
    protected Endereco endereco;
    protected Date data;
    protected List<Compra> compras;
    
    public void paraString() { }

    public Cliente(String nome, String numeroCadastro, Endereco endereco, Date data, String tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.numeroCadastro = numeroCadastro;
        this.endereco = endereco;
        this.data = data;
    }

    public Cliente(String nome, String numeroCadastro, Endereco endereco, int dataDia, int dataMes, int dataAno, String tipo) {
        this.tipo = tipo;
        this.nome = nome;
        this.numeroCadastro = numeroCadastro;
        this.endereco = endereco;
        this.setData(dataDia, dataMes, dataAno);
    }

    public void adicionarCompra(Compra compra) {
        compras.add(compra);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTipo() {
        return this.tipo;
    }

    public void setNumeroCadastro(String numeroCadastro) {
        this.numeroCadastro = numeroCadastro;
    }

    public String getNumeroCadastro() {
        return this.numeroCadastro;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Date getData() {
        return data;
    }

    public void setData(int dia, int mes, int ano) {
        Calendar calendario = Calendar.getInstance();
        calendario.set(Calendar.YEAR, ano);
        calendario.set(Calendar.MONTH, mes);
        calendario.set(Calendar.DAY_OF_MONTH, dia);

        this.data = calendario.getTime();
    }

    public void setData(Date data) {
        this.data = data;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }
}
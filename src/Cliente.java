import java.util.Date;
import java.util.Calendar;
import java.util.List;

public class Cliente {
    //O campo tipo é definido para verificar se o cliente é da classe PessoaFisica ou PessoaJuridica
    //Isso é utilizado na hora de ler os arquivos JSON e verificar facilmente o tipo da classe do Cliente
    protected String tipo; 

    protected String nome;
    protected String numeroCadastro; //Tanto o cliente físico como o jurídico usam o mesmo campo para o cpf/cnpj, isso facilita na codificação do sistema
    protected Endereco endereco;
    protected Date data;
    protected List<Compra> compras;

    //Obs.: na descrição da atividade era descrito que era necessário uma função paraString(), 
    //Porém, como a biblioteca Jackson já possui uma funcionalidade incorporada e possui uma outra dinâmica de leitura de dados, não houve a necessidade da função

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

    //métodos getters e setters

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

    //existem duas possibilidades para definir uma data, passando um objeto ou os dados que estarão neste objeto
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
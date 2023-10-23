import java.sql.Date;
import java.util.List;
public class Compra {
    private int codigo;
    private Date data;
    private float valorTotal;
    private int cpf_cnpj;
    private float valorPago;
    private float valorRestante;
    private List<ItemComprado> itensComprados;

    public Compra(int codigo, Date data, float valorTotal, int cpf_cnpj, float valorPago, float valorRestante, List<ItemComprado> itensComprados) {
        this.codigo = codigo;
        this.data = data;
        this.valorTotal = valorTotal;
        this.cpf_cnpj = cpf_cnpj;
        this.valorPago = valorPago;
        this.valorRestante = valorRestante;
        this.itensComprados = itensComprados;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return this.codigo;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return this.data;
    }

    public void setValorTotal(float valorTotal) {
        this.valorTotal = valorTotal;
    }

    public float getValorTotal() {
        return this.valorTotal;
    }

    public void setCpfCnpj(int cpf_cnpj) {
        this.cpf_cnpj = cpf_cnpj;
    }

    public int getCpfCnpj() {
        return this.cpf_cnpj;
    }

    public void setValorPago(float valorPago) {
        this.valorPago = valorPago;
    }

    public float getValorPago() {
        return this.valorPago;
    }

    public void setValorRestante(float valorRestante) {
        this.valorRestante = valorRestante;
    }

    public float getValorRestante() {
        return this.valorRestante;
    }

    public void setItensComprados(List<ItemComprado> itensComprados) {
        this.itensComprados = itensComprados;
    }

    public List<ItemComprado> getItensComprados() {
        return this.itensComprados;
    }

}

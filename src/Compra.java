import java.util.Date;
import java.util.List;

public class Compra {
    private int codigo;
    private Date data;
    private float valorTotal;
    private String documentoCliente; //cpf ou cnpj
    private float valorPago;
    private float valorRestante;
    private List<ItemComprado> itensComprados;

    public Compra(int codigo, String documentoCliente, float valorPago, List<ItemComprado> itensComprados) {
        this.codigo = codigo;
        this.data = new Date(); //a data da compra sempre será a data atual da criação, ou seja, data de processamento da compra
        this.documentoCliente = documentoCliente;
        this.itensComprados = itensComprados;

        float somaDosItens = 0;
        for(ItemComprado item : itensComprados) {
            somaDosItens += item.getValorTotal();
        }

        this.valorTotal = somaDosItens;
        this.valorPago = valorPago;
        this.valorRestante = somaDosItens - valorPago;
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

    public void setDocumentoCliente(String documentoCliente) {
        this.documentoCliente = documentoCliente;
    }

    public String getDocumentoCliente() {
        return this.documentoCliente;
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

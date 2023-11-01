import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Compra {
    private int codigo;
    private Date data;
    private float valorTotal;
    private String documentoCliente; //cpf ou cnpj
    private float valorPago;
    private float valorRestante;
    private List<ItemComprado> itensComprados;

    @JsonCreator
    public Compra(@JsonProperty("codigo") int codigo, @JsonProperty("documentoCliente") String documentoCliente, @JsonProperty("valorPago") float valorPago, 
                  @JsonProperty("itensComprados") List<ItemComprado> itensComprados) {
        this.codigo = codigo;
        this.data = new Date(); //a data da compra sempre será a data atual da criação, ou seja, data de processamento da compra
        this.documentoCliente = documentoCliente;
        this.itensComprados = itensComprados;

        float somaDosItens = 0;
        if (itensComprados.size() > 0) {
            for(ItemComprado item : itensComprados) {
                somaDosItens += item.getValorTotal();
            }
        } else {
            somaDosItens = 0;
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
        this.valorRestante = this.valorTotal - this.valorPago;
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
        this.valorRestante = this.valorTotal - this.valorPago;
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

    public void adicionarItemComprado(ItemComprado itemComprado) {
        this.itensComprados.add(itemComprado);
        this.valorTotal += itemComprado.getValorTotal();
        this.valorRestante = this.valorTotal - this.valorPago;
    }

    public void setItensComprados(List<ItemComprado> itensComprados) {
        this.itensComprados = itensComprados;
    }

    public List<ItemComprado> getItensComprados() {
        return this.itensComprados;
    }

}

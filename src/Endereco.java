import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Endereco {
    private String rua;
    private int numero;
    private String bairro;
    private int cep;
    private String cidade;
    private String estado;
    
    //Abaixo existem anotações no construtor, essas anotações são para a biblioteca Jackson, que transforma classes em JSON e vice-versa
    //Para que essa conversão seja feita, as anotações definem como os campos serão ao serem transformados para JSON

    //Obs.: na descrição da atividade era descrito que era necessário uma função paraString(), 
    //Porém, como a biblioteca Jackson já possui uma funcionalidade incorporada e possui uma outra dinâmica de leitura de dados, não houve a necessidade da função

    @JsonCreator
    public Endereco(@JsonProperty("rua") String rua, @JsonProperty("numero") int numero, @JsonProperty("bairro") String bairro, @JsonProperty("cep") int cep, 
                    @JsonProperty("cidade") String cidade, @JsonProperty("estado") String estado) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
    }

    //métodos getters e setters

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getRua() {
        return this.rua;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return this.numero;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getBairro() {
        return this.bairro;
    }

    public void setCep(int cep) {
        this.cep = cep;
    }

    public int getCep() {
        return this.cep;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getCidade() {
        return this.cidade;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return this.estado;
    }

}

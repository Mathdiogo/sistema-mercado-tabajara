import java.sql.Date;
import java.util.List;

public class Cliente {
    protected String nome;
    protected Endereco endereco;
    protected Date data;
    protected List<Compra> compras;
    
    public void paraString() { }

}
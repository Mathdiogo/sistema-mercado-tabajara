import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Aplicativo {
    public static void main(String[] args) throws Exception {
        //popula o banco de dados de produtos
        Produto arrozProduto = new Produto(1, "Arroz", "1 Kg; Serve até 4 pessoas", 3, 4, 2024);
        Produto leiteProduto = new Produto(2, "Leite", "1.5 L; Marca Leite Ninho", 1, 2, 2024);

        //cadastra um cliente
        Endereco endereco1 = new Endereco("Rua Paschoal Colombo", 54, "Parque Bela Vista", 18110230, "Sorocaba", "SP");
        PessoaFisica cliente1 = new PessoaFisica("Rogerio", endereco1, "53255829390", 3, new Date());

        //realiza uma compra
        List<ItemComprado> itensComprados = new ArrayList<ItemComprado>();

        ItemComprado itemComprado1 = new ItemComprado(arrozProduto, 3, 34.23f);
        itensComprados.add(itemComprado1);

        ItemComprado itemComprado2 = new ItemComprado(leiteProduto, 2, 8.23f);
        itensComprados.add(itemComprado2);

        Compra compra = new Compra(0, cliente1.getCpf(), 13.0f, itensComprados);

        //teste
        System.out.println("id da compra: " + compra.getCodigo());
        System.out.println("documento do cliente: " + compra.getDocumentoCliente());
        System.out.println("valor total: " + compra.getValorTotal());
        System.out.println("valor pago: " + compra.getValorPago());
        System.out.println("valor restante: " + compra.getValorRestante());
        System.out.println("------------------------");
        System.out.println("itens comprados: ");
        for(ItemComprado item : compra.getItensComprados()) {
            System.out.println(item.getNome());
        }
    }
}

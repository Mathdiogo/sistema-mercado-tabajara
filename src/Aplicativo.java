import java.util.List;
import javax.swing.JOptionPane;

public class Aplicativo {
    public static void main(String[] args) throws Exception {
        ConversorDados dados = new ConversorDados();

        List<Cliente> clientes = dados.CarregarDadosClientes();
        List<Produto> produtos = dados.CarregarDadosProdutos();
        List<Compra> compras = dados.CarregarDadosCompras();

        String[] opcoes = { "1. Cadastro de cliente", "2. Deletar cliente pelo CPF ou CNPJ", "3. Deletar cliente pelo nome", "4. Cadastro de produtos", 
                            "5. Efetuacao de compra", "6. Atualizacao da situacao de pagamentos de uma compra", "7. Relatorios", "8. Sair" };
        String menu = "";

        while (!menu.equals("8. Sair")) {
            menu = (String) JOptionPane.showInputDialog(null, "<html>Choose A Menu Item:<br><br></html>", "Main Menu", 
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            switch (menu) {
                case "1. Cadastro de cliente":
                    CadastroCliente();
                    break;
                case "2. Deletar cliente pelo CPF ou CNPJ":
                    DeletarClienteCpfOuCnpj();
                    break;
                case "3. Deletar cliente pelo nome":
                    DeletarClienteNome();
                    break;
                case "4. Cadastro de produtos":
                    CadastroProdutos();
                    break;
                case "5. Efetuacao de compra":
                    EfetuacaoDeCompra();
                    break;
                case "6. Atualizacao da situacao de pagamentos de uma compra":
                    AtualizarSituacaoDeCompra();
                    break;
                case "7. Relatorios":
                    Relatorios();
                    break;
            }
        }        
        
        dados.SalvarDados(clientes, produtos, compras);

        /* 
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
        */
    }

    private static void CadastroCliente() {

    }

    private static void DeletarClienteCpfOuCnpj() {

    }

    private static void DeletarClienteNome() {

    }

    private static void CadastroProdutos() {

    }

    private static void EfetuacaoDeCompra() {

    }

    private static void AtualizarSituacaoDeCompra() {

    }

    private static void Relatorios() {

    }
}

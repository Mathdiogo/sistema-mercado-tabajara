import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
                    CadastroCliente(clientes);
                    break;
                case "2. Deletar cliente pelo CPF ou CNPJ":
                    DeletarClienteCpfOuCnpj(clientes);
                    break;
                case "3. Deletar cliente pelo nome":
                    DeletarClienteNome(clientes);
                    break;
                case "4. Cadastro de produtos":
                    CadastroProdutos(produtos);
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
    }

    private static void CadastroCliente(List<Cliente> clientes) {
        String[] botoes = { "Física", "Jurídica" };    
        int retorno = JOptionPane.showOptionDialog(null, "O cliente é uma pessoa física ou jurídica?", "Cadastro de Cliente",
                      JOptionPane.WARNING_MESSAGE, 3, null, botoes, botoes[0]);
        
        if (botoes[retorno] == "Física") {
            CadastroPessoaFisica(clientes);
        } else {
            CadastroPessoaJuridica(clientes);
        }
    }

    private static void CadastroPessoaFisica(List<Cliente> clientes) {
        JTextField nome = new JTextField();
        JTextField cpf = new JTextField();
        JTextField numeroParcelas = new JTextField();
        JTextField rua = new JTextField();
        JTextField numero = new JTextField();
        JTextField bairro = new JTextField();
        JTextField cidade = new JTextField();
        JTextField estado = new JTextField();
        JTextField cep = new JTextField();
        Object[] mensagem = {
            "[Cliente]",
            "Nome:", nome,
            "CPF:", cpf,
            "Número de parcelas:", numeroParcelas,
            " ",
            "[Endereço]",
            "Rua:", rua,
            "Número:", numero,
            "Bairro:", bairro,
            "Cidade:", cidade,
            "Estado:", estado,
            "CEP:", cep
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Endereco endereco = new Endereco(rua.getText(), Integer.parseInt(numero.getText()), bairro.getText(), 
                                    Integer.parseInt(cep.getText()), cidade.getText(), estado.getText());

                PessoaFisica cliente = new PessoaFisica(nome.getText(), endereco, cpf.getText(), Integer.parseInt(numeroParcelas.getText()), new Date());

                clientes.add(cliente);

                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar cliente", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void CadastroPessoaJuridica(List<Cliente> clientes) {
        JTextField nomeFantasia = new JTextField();
        JTextField razaoSocial = new JTextField();
        JTextField cnpj = new JTextField();
        JTextField prazoMaximoDePagamento = new JTextField();
        JTextField rua = new JTextField();
        JTextField numero = new JTextField();
        JTextField bairro = new JTextField();
        JTextField cidade = new JTextField();
        JTextField estado = new JTextField();
        JTextField cep = new JTextField();
        Object[] mensagem = {
            "[Cliente]",
            "Nome Fantasia:", nomeFantasia,
            "Razão social:", razaoSocial,
            "CNPJ:", cnpj,
            "Prazo máximo de pagamento:", prazoMaximoDePagamento,
            " ",
            "[Endereço]",
            "Rua:", rua,
            "Número:", numero,
            "Bairro:", bairro,
            "Cidade:", cidade,
            "Estado:", estado,
            "CEP:", cep
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Cadastro de Cliente", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Endereco endereco = new Endereco(rua.getText(), Integer.parseInt(numero.getText()), bairro.getText(), 
                                    Integer.parseInt(cep.getText()), cidade.getText(), estado.getText());

                PessoaJuridica cliente = new PessoaJuridica(nomeFantasia.getText(), razaoSocial.getText(), endereco,
                                         cnpj.getText(), Integer.parseInt(prazoMaximoDePagamento.getText()), new Date());

                clientes.add(cliente);

                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso", "Cadastro de Cliente", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar cliente", "Cadastro de Cliente", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void DeletarClienteCpfOuCnpj(List<Cliente> clientes) {
        JTextField numeroCadastro = new JTextField();
        Object[] mensagem = {
            "CPF ou CNPJ:", numeroCadastro
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Excluir Cliente", JOptionPane.OK_CANCEL_OPTION, 0);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                if (clientes.removeIf(cliente -> cliente.getNumeroCadastro().equals(numeroCadastro.getText()))) {
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Excluir Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Excluir Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao deletar cliente", "Excluir Cliente", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void DeletarClienteNome(List<Cliente> clientes) {
        JTextField nome = new JTextField();
        Object[] mensagem = {
            "Nome do cliente:", nome
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Excluir Cliente", JOptionPane.OK_CANCEL_OPTION, 0);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                if (clientes.removeIf(cliente -> cliente.getNome().equals(nome.getText()))) {
                    JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso", "Excluir Cliente", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Excluir Cliente", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao deletar cliente", "Excluir Cliente", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void CadastroProdutos(List<Produto> produtos) {
        JTextField nome = new JTextField();
        JTextField descricao = new JTextField();
        JTextField diaValidade = new JTextField();
        JTextField mesValidade = new JTextField();
        JTextField anoValidade = new JTextField();
        Object[] mensagem = {
            "Nome:", nome,
            "Descrição:", descricao,
            "Dia de validade:", diaValidade,
            "Mês de validade:", mesValidade,
            "Ano de validade:", anoValidade
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Cadastro de Produto", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Produto produto = new Produto(produtos.size() + 1, nome.getText(), descricao.getText(), 
                                  Integer.parseInt(diaValidade.getText()), Integer.parseInt(diaValidade.getText()), Integer.parseInt(diaValidade.getText()));

                produtos.add(produto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Cadastro de Produto", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void EfetuacaoDeCompra() {

    }

    private static void AtualizarSituacaoDeCompra() {

    }

    private static void Relatorios() {

    }
}

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

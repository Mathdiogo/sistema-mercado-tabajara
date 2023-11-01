import java.util.ArrayList;
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
            menu = (String) JOptionPane.showInputDialog(null, "<html>MENU<br><br></html>", "Mercado Tabajara", 
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
                    EfetuacaoDeCompra(compras, produtos, clientes);
                    break;
                case "6. Atualizacao da situacao de pagamentos de uma compra":
                    AtualizarSituacaoDeCompra();
                    break;
                case "7. Relatorios":
                    Relatorios();
                    break;
            }

            dados.SalvarDados(clientes, produtos, compras);
        }        
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

    private static void EfetuacaoDeCompra(List<Compra> compras, List<Produto> produtos, List<Cliente> clientes) {
        String[] botoes = { "Adicionar itens a compra", "Proseguir", "Cancelar" };    
        
        Compra novaCompra = new Compra(compras.size() + 1, "", 0, new ArrayList<ItemComprado>());

        boolean repetir = true;
        boolean cancelarCompra = false;

        while (repetir) {
            int retorno = JOptionPane.showOptionDialog(null, "", "Compra",
                    JOptionPane.WARNING_MESSAGE, 3, null, botoes, botoes[0]);
                    
            if (botoes[retorno] == "Adicionar itens a compra") {
                ItemComprado itemComprado = ElaborarItemComprado(produtos);
                if (itemComprado != null) {
                    novaCompra.adicionarItemComprado(itemComprado);
                }
            } else if (botoes[retorno] == "Proseguir") {
                if (novaCompra.getItensComprados().size() > 0) {
                    ElaborarCompra(novaCompra, clientes);
                    if (novaCompra.getDocumentoCliente() != "") {
                        repetir = false;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum item adicionado na compra!", "Compra", JOptionPane.INFORMATION_MESSAGE);
                }
            } else {
                repetir = false;
                cancelarCompra = true;
            }
        }

        if(cancelarCompra) {
            JOptionPane.showMessageDialog(null, "Compra cancelada!", "Compra", JOptionPane.INFORMATION_MESSAGE);
        } else {
            compras.add(novaCompra);
            JOptionPane.showMessageDialog(null, "Compra efetuada com sucesso!", "Compra", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static ItemComprado ElaborarItemComprado(List<Produto> produtos) {
        JTextField nome = new JTextField();
        JTextField quantidade = new JTextField();
        JTextField precoUnitario = new JTextField();
        Object[] mensagem = {
            "Nome do produto:", nome,
            "Quantidade:", quantidade,
            "Preço unitário:", precoUnitario
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Compra", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Produto produto = produtos.stream().filter(item -> {return item.getNome().equals(nome.getText());}).findAny().orElse(null);
                if (produto == null) {
                    throw new Exception();
                }

                ItemComprado itemComprado = new ItemComprado(produto, Integer.parseInt(quantidade.getText()), Float.parseFloat(precoUnitario.getText()));
                JOptionPane.showMessageDialog(null, "Item adicionado a compra", "Compra", JOptionPane.INFORMATION_MESSAGE);

                return itemComprado;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado", "Compra", JOptionPane.ERROR_MESSAGE);
                return null;
            }
        } 

        return null;
    }

    private static void ElaborarCompra(Compra compra, List<Cliente> clientes) {
        JTextField documentoCliente = new JTextField();
        JTextField valorPago = new JTextField();
        Object[] mensagem = {
            "Documento do cliente (CPF ou CNPJ):", documentoCliente,
            "Valor pago:", valorPago
        };
        
        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Compra", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Cliente clienteProcurado = clientes.stream().filter(cliente -> {return cliente.getNumeroCadastro().equals(documentoCliente.getText());}).findAny().orElse(null);
                if (clienteProcurado == null) {
                    throw new Exception();
                }

                compra.setDocumentoCliente(documentoCliente.getText());
                compra.setValorPago(Float.parseFloat(valorPago.getText()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Cliente não encontrado", "Compra", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void AtualizarSituacaoDeCompra() {

    }

    private static void Relatorios() {

    }
}
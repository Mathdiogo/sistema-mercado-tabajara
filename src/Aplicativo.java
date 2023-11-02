import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Aplicativo {
    public static void main(String[] args) throws Exception {
        ConversorDados dados = new ConversorDados();

        List<Cliente> clientes = dados.carregarDadosClientes();
        List<Produto> produtos = dados.carregarDadosProdutos();
        List<Compra> compras = dados.carregarDadosCompras();

        String[] opcoes = { "1. Cadastro de cliente", "2. Deletar cliente pelo CPF ou CNPJ", "3. Deletar cliente pelo nome", "4. Cadastro de produtos", 
                            "5. Efetuacao de compra", "6. Atualizacao da situacao de pagamentos de uma compra", "7. Relatorios", "8. Sair" };
        String menu = "";

        while (!menu.equals("8. Sair")) {
            menu = (String) JOptionPane.showInputDialog(null, "<html>MENU<br><br></html>", "Mercado Tabajara", 
                    JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

            switch (menu) {
                case "1. Cadastro de cliente":
                    cadastrarCliente(clientes);
                    break;
                case "2. Deletar cliente pelo CPF ou CNPJ":
                    deletarClienteCpfOuCnpj(clientes);
                    break;
                case "3. Deletar cliente pelo nome":
                    deletarClienteNome(clientes);
                    break;
                case "4. Cadastro de produtos":
                    cadastrarProduto(produtos);
                    break;
                case "5. Efetuacao de compra":
                    efetuarCompra(clientes, produtos, compras);
                    break;
                case "6. Atualizacao da situacao de pagamentos de uma compra":
                    atualizarSituacaoDeCompra(compras);
                    break;
                case "7. Relatorios":
                    relatorios(clientes, produtos, compras);
                    break;
            }

            dados.salvarDados(clientes, produtos, compras);
        }        
    }

    private static void cadastrarCliente(List<Cliente> clientes) {
        String[] botoes = { "Física", "Jurídica" };    
        int retorno = JOptionPane.showOptionDialog(null, "O cliente é uma pessoa física ou jurídica?", "Cadastro de Cliente",
                      JOptionPane.WARNING_MESSAGE, 3, null, botoes, botoes[0]);
        
        if (botoes[retorno] == "Física") {
            cadastrarPessoaFisica(clientes);
        } else {
            cadastrarPessoaJuridica(clientes);
        }
    }

    private static void cadastrarPessoaFisica(List<Cliente> clientes) {
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
                if(clientes.stream().filter(cliente -> {return cliente.getNome().equals(nome.getText());}).findAny().orElse(null) != null) {
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    throw new Exception();
                }

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

    private static void cadastrarPessoaJuridica(List<Cliente> clientes) {
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
                if(clientes.stream().filter(cliente -> {return cliente.getNome().equals(nomeFantasia.getText());}).findAny().orElse(null) != null) {
                    JOptionPane.showMessageDialog(null, "Cliente já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    throw new Exception();
                }

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

    private static void deletarClienteCpfOuCnpj(List<Cliente> clientes) {
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

    private static void deletarClienteNome(List<Cliente> clientes) {
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

    private static void cadastrarProduto(List<Produto> produtos) {
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
                if(produtos.stream().filter(cliente -> {return cliente.getNome().equals(nome.getText());}).findAny().orElse(null) != null) {
                    JOptionPane.showMessageDialog(null, "Produto já cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                    throw new Exception();
                }

                Produto produto = new Produto(produtos.size() + 1, nome.getText(), descricao.getText(), 
                                  Integer.parseInt(diaValidade.getText()), Integer.parseInt(diaValidade.getText()), Integer.parseInt(diaValidade.getText()));

                produtos.add(produto);

                JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso", "Cadastro de Produto", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Falha ao cadastrar produto", "Cadastro de Produto", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void efetuarCompra(List<Cliente> clientes, List<Produto> produtos, List<Compra> compras) {
        String[] botoes = { "Adicionar itens a compra", "Proseguir", "Cancelar" };    
        
        Compra novaCompra = new Compra(compras.size() + 1, "", 0, new ArrayList<ItemComprado>());

        boolean repetir = true;
        boolean cancelarCompra = false;

        while (repetir) {
            int retorno = JOptionPane.showOptionDialog(null, "", "Compra",
                    JOptionPane.WARNING_MESSAGE, 3, null, botoes, botoes[0]);
                    
            if (botoes[retorno] == "Adicionar itens a compra") {
                ItemComprado itemComprado = elaborarItemComprado(produtos);
                if (itemComprado != null) {
                    novaCompra.adicionarItemComprado(itemComprado);
                }
            } else if (botoes[retorno] == "Proseguir") {
                if (novaCompra.getItensComprados().size() > 0) {
                    elaborarCompra(novaCompra, clientes);
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

    private static ItemComprado elaborarItemComprado(List<Produto> produtos) {
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

    private static void elaborarCompra(Compra compra, List<Cliente> clientes) {
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

    private static void atualizarSituacaoDeCompra(List<Compra> compras) {
        JTextField compraID = new JTextField();
        Object[] mensagem = {
            "Identificador da compra:", compraID
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Atualizar Situação da Compra", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Compra compraProcurada = compras.stream().filter(compra -> {return Integer.parseInt(compraID.getText()) == compra.getCodigo();}).findAny().orElse(null);
                if (compraProcurada == null) {
                    throw new Exception();
                }

                atualizarDadosCompra(compraProcurada);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Compra não encontrada", "Atualizar Situação da Compra", JOptionPane.ERROR_MESSAGE);
            }
        } 
    }

    private static void atualizarDadosCompra(Compra compra) {
        JTextField valorPago = new JTextField();
        Object[] mensagem = {
            "Valor total da compra:", compra.getValorTotal(),
            "Valor pago:", compra.getValorPago(),
            "Valor restante:", compra.getValorRestante(),
            "Adicionar ao valor pago:", valorPago
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Atualizar Situação da Compra", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                float novoValorPago = compra.getValorPago() + Float.parseFloat(valorPago.getText());
                if (novoValorPago > compra.getValorTotal() || Float.parseFloat(valorPago.getText()) > compra.getValorRestante()) {
                    JOptionPane.showMessageDialog(null, "Erro: Valor da compra ultrapassou o valor total ou valor restante", "Atualizar Situação da Compra", JOptionPane.ERROR_MESSAGE);
                    throw new Exception();
                }

                compra.setValorPago(novoValorPago);
                JOptionPane.showMessageDialog(null, "Dados da compra atualizados!", "Atualizar Situação da Compra", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Atualização não realizada", "Atualizar Situação da Compra", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void relatorios(List<Cliente> clientes, List<Produto> produtos, List<Compra> compras) {
        String[] opcoes = { "(a) Relação de todos os clientes que possuem o nome iniciado por uma determinada sequência de caracteres",
                            "(b) Relação de todos os produtos", 
                            "(c) Busca de um produto pelo nome", 
                            "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida", 
                            "(e) Relação de todas as compras", 
                            "(f) Busca de uma compra pelo número", 
                            "(g) Relação de todas as compras que não foram pagas ainda", 
                            "(h) Relação das 10 últimas compras pagas",
                            "(i) Apresentação das informações da compra mais cara", 
                            "(j) Apresentação das informações da compra mais barata", 
                            "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses" };
        String menu = "";

        menu = (String) JOptionPane.showInputDialog(null, "<html>Informações:<br><br></html>", "Relatórios", 
                JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);

        switch (menu) {
            case "(a) Relação de todos os clientes que possuem o nome iniciado por uma determinada sequência de caracteres":
                relatorioClientesPeloNome(clientes);
                break;
            case "(b) Relação de todos os produtos":
                listarProdutos(produtos);
                break;
            case "(c) Busca de um produto pelo nome":
                relatorioBuscaProdutoPeloNome(produtos);
                break;
            case "(d) Relação de produtos que são perecíveis e que estão com a data de validade vencida":
                List<Produto> produtosVencidos = produtos.stream().filter(produto -> {return produto.verificarValidade();}).collect(Collectors.toList());
                listarProdutos(produtosVencidos);
                break;
            case "(e) Relação de todas as compras":
                listarCompras(compras);
                break;
            case "(f) Busca de uma compra pelo número":
                relatorioCompraPeloID(compras);
                break;
            case "(g) Relação de todas as compras que não foram pagas ainda":
                List<Compra> comprasNaoPagas = compras.stream().filter(compra -> {return compra.getValorRestante() > 0;}).collect(Collectors.toList());
                listarCompras(comprasNaoPagas);
                break;
            case "(h) Relação das 10 últimas compras pagas":
                List<Compra> ultimasCompras = compras.subList(compras.size() - 10, compras.size());
                List<Compra> comprasPagas = ultimasCompras.stream().filter(compra -> {return compra.getValorRestante() > 0;}).collect(Collectors.toList());
                listarCompras(comprasPagas);
                break;
            case "(i) Apresentação das informações da compra mais cara":
                Compra compraMaisCara = compras.stream().max((primeiro, segundo) -> Float.compare(primeiro.getValorTotal(), segundo.getValorTotal())).get();
                listarCompras(Arrays.asList(compraMaisCara));
                break;
            case "(j) Apresentação das informações da compra mais barata":
                Compra compraMaisBarata = compras.stream().min((primeiro, segundo) -> Float.compare(primeiro.getValorTotal(), segundo.getValorTotal())).get();
                listarCompras(Arrays.asList(compraMaisBarata));
                break;
            case "(k) Relação do valor total de compras feitas em cada mês nos últimos 12 meses":
                Calendar calendario = Calendar.getInstance();  
                calendario.setTime(new Date());  
                calendario.set(Calendar.MONTH, -12);  
                Date umAnoAtras = calendario.getTime();

                List<Compra> comprasUltimoAno = compras.stream().filter(compra -> {return compra.getData().compareTo(umAnoAtras) > 0;}).collect(Collectors.toList());
                comprasUltimoAno.sort((primeiro, segundo) -> primeiro.getData().compareTo(segundo.getData()));
                listarCompras(comprasUltimoAno);
                break;
        }      
    }

    private static void relatorioClientesPeloNome(List<Cliente> clientes) {
        JTextField nome = new JTextField();
        Object[] mensagem = {
            "Nome:", nome,
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Clientes", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                List<Cliente> clientesEncontrados = clientes.stream().filter(cliente -> {return cliente.getNome().startsWith(nome.getText());}).collect(Collectors.toList());
                if (clientesEncontrados.size() <= 0) {
                    throw new Exception();
                }

                listarClientes(clientesEncontrados);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Nenhum cliente encontrado", "Clientes", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void relatorioBuscaProdutoPeloNome(List<Produto> produtos) {
        JTextField nome = new JTextField();
        Object[] mensagem = {
            "Nome do produto:", nome,
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Produto", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Produto produtoProcurado = produtos.stream().filter(produto -> {return nome.getText().equals(produto.getNome());}).findAny().orElse(null);
                listarProdutos(Arrays.asList(produtoProcurado));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Produto não encontrado", "Produto", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void relatorioCompraPeloID(List<Compra> compras) {
        JTextField compraID = new JTextField();
        Object[] mensagem = {
            "ID da compra:", compraID,
        };

        int opcao = JOptionPane.showConfirmDialog(null, mensagem, "Compra", JOptionPane.OK_CANCEL_OPTION, 1);
        if (opcao == JOptionPane.OK_OPTION) {
            try {
                Compra compraProcurada = compras.stream().filter(compra -> {return Integer.parseInt(compraID.getText()) == compra.getCodigo();}).findAny().orElse(null);
                listarCompras(Arrays.asList(compraProcurada));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Erro: Compra não encontrada", "Compra", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void listarCompras(List<Compra> compras) {
        String informacoes = "";

        for (Compra compra : compras) {
            informacoes += " ID: " + compra.getCodigo() + "\n Data: " + compra.getData() + "\n Valor total: " + compra.getValorTotal() + 
                       "\n Valor restante: " + compra.getValorRestante() + "\n Valor pago: " + compra.getValorPago() + "\n Documento cliente: " 
                       + compra.getDocumentoCliente() + "\n\n";
        }

        Object[] mensagem = {
            informacoes
        };

        JOptionPane.showConfirmDialog(null, mensagem, "Compras", JOptionPane.OK_CANCEL_OPTION, 1);
    }

    private static void listarProdutos(List<Produto> produtos) {
        String informacoes = "";

        for (Produto produto : produtos) {
            informacoes += " ID: " + produto.getCodigo() + "\n Nome:" + produto.getNome() + "\n Descrição: " + produto.getDescricao() + 
                       "\n Validade: " + produto.getDataDeValidade() + "\n\n";
        }

        Object[] mensagem = {
            informacoes
        };

        JOptionPane.showConfirmDialog(null, mensagem, "Produtos", JOptionPane.OK_CANCEL_OPTION, 1);
    }

    private static void listarClientes(List<Cliente> clientes) {
        String informacoes = "";

        for (Cliente cliente : clientes) {
            informacoes += " Nome: " + cliente.getNome() + "\n Tipo:" + cliente.getTipo() + "\n Documento: " + cliente.getNumeroCadastro() + "\n\n";
        }

        Object[] mensagem = {
            informacoes
        };

        JOptionPane.showConfirmDialog(null, mensagem, "Clientes", JOptionPane.OK_CANCEL_OPTION, 1);
    }
}
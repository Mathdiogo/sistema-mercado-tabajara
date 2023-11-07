import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

//Classe responsável por converter as classes em JSON e vice-versa

public class ConversorDados {
    //diretório dos arquivos que serão escritos e lidos pelo sistema
    private static String diretorioClientes = "./baseDados/clientes.json"; 
    private static String diretorioProdutos = "./baseDados/produtos.json";
    private static String diretorioCompras = "./baseDados/compras.json";

    //objeto responsável por sobreescrever ou ler arquivos
    private ObjectMapper mapeador = new ObjectMapper();

    //Transforma as listas de clientes, produtos e compras em JSON e depois guarda nos respectivos arquivos
    public void salvarDados(List<Cliente> clientes, List<Produto> produtos, List<Compra> compras) {
        try {
            mapeador.writeValue(new File(diretorioClientes), clientes);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mapeador.writeValue(new File(diretorioProdutos), produtos);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            mapeador.writeValue(new File(diretorioCompras), compras);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> carregarDadosClientes() {
        //Cria uma lista temporária para armazenar os dados carregados
        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            //O objeto mapeador lê o arquivo de dados dos clientes e salva em um dicionário 
            List<Map<String, Object>> clientesObjetos = mapeador.readValue(new File(diretorioClientes), new TypeReference<List<Map<String, Object>>>() {});

            List<String> clientesJson = new ArrayList<>();
            
            //Os dados dos clientes que foram lidos é guardado em uma lista de strings, cada item da lista de string é um objeto em formato JSON
            for (Map<String, Object> clienteObjeto : clientesObjetos) {
                String clienteString = mapeador.writeValueAsString(clienteObjeto);
                clientesJson.add(clienteString);
            }

            //Para cada objeto JSON da lista de strings, o mapeador irá ler e converter para um objeto Cliente
            for (String clienteJson : clientesJson) {
                JsonNode jsonNode = mapeador.readTree(clienteJson);
                String tipo = jsonNode.get("tipo").asText();

                //O primeiro campo 'tipo' é lido para verificar se o cliente é uma variação da classe PessoaFisica ou PessoaJuridica
                //O objeto JSON é convertido para sua respectiva classe conforme o campo 'tipo'
                if ("fisica".equals(tipo)) {
                    System.out.println("fisica:");
                    System.out.println(jsonNode);
                    PessoaFisica pessoaFisica = mapeador.treeToValue(jsonNode, PessoaFisica.class);
                    clientes.add(pessoaFisica);
                } else if ("juridica".equals(tipo)) {
                    System.out.println("juridica:");
                    System.out.println(jsonNode);
                    PessoaJuridica pessoaJuridica = mapeador.treeToValue(jsonNode, PessoaJuridica.class);
                    clientes.add(pessoaJuridica);
                }
            }
        } catch (IOException e) {
            e.printStackTrace(); 
        }

        return clientes;
    }

    //Usando a mesma metodologia do carregamento dos dados do clientes, também é carregado os dados dos produtos
    public List<Produto> carregarDadosProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            produtos = mapeador.readValue(new File(diretorioProdutos), 
                       mapeador.getTypeFactory().constructCollectionType(List.class, Produto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    //Usando a mesma metodologia do carregamento dos dados do clientes, também é carregado os dados de compras
    public List<Compra> carregarDadosCompras() {
        List<Compra> compras= new ArrayList<Compra>();

        try {
            compras = mapeador.readValue(new File(diretorioCompras), 
                      mapeador.getTypeFactory().constructCollectionType(List.class, Compra.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return compras;
    }
}

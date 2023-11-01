import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDados {
    private static String diretorioClientes = "./baseDados/clientes.json"; 
    private static String diretorioProdutos = "./baseDados/produtos.json";
    private static String diretorioCompras = "./baseDados/compras.json";

    private ObjectMapper mapeador = new ObjectMapper();

    public void SalvarDados(List<Cliente> clientes, List<Produto> produtos, List<Compra> compras) {
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

    public List<Cliente> CarregarDadosClientes() {
        List<Cliente> clientes = new ArrayList<Cliente>();

        try {
            List<Map<String, Object>> clientesObjetos = mapeador.readValue(new File(diretorioClientes), new TypeReference<List<Map<String, Object>>>() {});

            List<String> clientesJson = new ArrayList<>();
            
            for (Map<String, Object> clienteObjeto : clientesObjetos) {
                String clienteString = mapeador.writeValueAsString(clienteObjeto);
                clientesJson.add(clienteString);
            }

            for (String clienteJson : clientesJson) {
                JsonNode jsonNode = mapeador.readTree(clienteJson);
                String tipo = jsonNode.get("tipo").asText();

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

    public List<Produto> CarregarDadosProdutos() {
        List<Produto> produtos = new ArrayList<Produto>();

        try {
            produtos = mapeador.readValue(new File(diretorioProdutos), 
                       mapeador.getTypeFactory().constructCollectionType(List.class, Produto.class));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return produtos;
    }

    public List<Compra> CarregarDadosCompras() {
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

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDados {
    private static String diretorioClientes = "baseDados/clientes.json";
    private static String diretorioProdutos = "baseDados/produtos.json";
    private static String diretorioCompras = "baseDados/compras.json";

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
            clientes = mapeador.readValue(new File(diretorioClientes), 
            mapeador.getTypeFactory().constructCollectionType(List.class, Cliente.class));
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

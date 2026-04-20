package gerenciamentoProjetoLP;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Aqui esta o Main do Programa, onde quase tudo vai ser executado para o Cliente
public class ProgramaGerenciamento {
    public static void main(String [] args) {
        boolean continuarNaPagina = true;
        AssistenciaServiceImpl sistema = new AssistenciaServiceImpl();
        List<Equipamento>  sistemaEquipamentos = sistema.recuperarTodos();
        SalvarDados criarNovoGravador = new SalvarDados();

        while (continuarNaPagina) {
            String opcaoEscolha = JOptionPane.showInputDialog(null, "Assistência Técnica de Equipamentos\n\nBem-vindo(a) ao programa de gerenciamento de produtos\nVersão:" + sistema.getVersion() + "\n\n 1 - Cadastrar\n 2 - Listar\n 3 - Atualizar Produto\n 4 - Remover Produto \n 0 - Sair");

            // Depois dessa parte de NULL, e que realmente o codigo começa do programa.
            if (opcaoEscolha == null) {
                JOptionPane.showMessageDialog(null, "Programa foi encerrado!");
                break;
            } else if (opcaoEscolha.equals("1")) {
                int pegarId = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do Produto"));
                String pegarModel = JOptionPane.showInputDialog(null, "Digite o Modelo do Produto que deseja:");
                String pegarMarcaProduto = JOptionPane.showInputDialog(null, "Digite a marca do Produto que deseja:");
                String pegarDefeitoProduto = JOptionPane.showInputDialog(null, "Digite qual defeito esta sendo apresentado no produto:");

                // Essa parte vai cadastrar o novo produto, ele faz um Try com os comandos para AssistenciaServiceImpl pra fazer cadastrar na Lista.
                try {
                    Equipamento novoEquipamento = new Equipamento(pegarId, pegarModel, pegarMarcaProduto, pegarDefeitoProduto, "Aguardando");
                    sistema.cadastrar(novoEquipamento);
                    criarNovoGravador.salvarDadosGerenciamento(sistemaEquipamentos);
                    JOptionPane.showMessageDialog(null, "Produto cadastrado com sucesso! Modelo: " + pegarModel);
                } catch (IOException i){
                    JOptionPane.showMessageDialog(null, "Error no Momento de Salvar os Dados em Arquivo: " + i.getMessage());
                }
            } else if (opcaoEscolha.equals("2")) {
                List<Equipamento> dadosLista = sistema.recuperarTodos();
                String listaCompleta = "";

                if (dadosLista.isEmpty()) {
                    listaCompleta = "Sem Itens :(";
                } else {
                    for (Equipamento e : dadosLista) {
                        listaCompleta += "ID: " + e.getId() + " | Modelo: " + e.getModelo() + " | Marca: " + e.getMarca() + " | Defeito: " + e.getDescricaoDefeito() + "\n";
                    }
                }
                JOptionPane.showMessageDialog(null, "Dados dos Equipamentos: \n" + listaCompleta);
            } else if (opcaoEscolha.equals("3")) {
                int getNovoID = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o novo ID para o produto:"));
                String getNovoStatus = JOptionPane.showInputDialog(null, "Digite o novo status para o produto:");

                // Nessa parte vai localizar o produto com tal ID e vai trocar na parte de AssistenciaServiceImpl
                // Se caso tenha algum tipo de erro, conserte, tenho minhas duvidas sobre essa parte, mas pela parte da logica em qual foi trocada.
                try {
                    sistema.atualizarStatus(getNovoID, getNovoStatus);
                    sistema.salvarDadosGerenciamento();
                    JOptionPane.showMessageDialog(null, "Produto atualizado com sucesso! ID do Produto: " + getNovoID);
                } catch (EquipamentoNaoLocalizadoException e) {
                    JOptionPane.showMessageDialog(null, "Produto não localizado! ERROR: " + e.getMessage());
                } catch (IOException i){
                    JOptionPane.showMessageDialog(null, "Error no Momento de Salvar os Dados: " + i.getMessage());
                }
            } else if (opcaoEscolha.equals("4")) {
                int pegarID = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do Produto a qual deseja remover:"));

                // Essa parte é a mais simples possivel, ja que o unico objetivo dessa funçao vai ser remover o tal produto com o ID fornecido pelo usuario, que tambem acessa o AssistenciaServiceImpl
                try {
                    sistema.darBaixa(pegarID);
                    sistema.salvarDadosGerenciamento();
                    JOptionPane.showMessageDialog(null, "Produto com ID: " + pegarID + " foi removido!");
                } catch(EquipamentoNaoLocalizadoException e) {
                    JOptionPane.showMessageDialog(null, "Produto com ID " + pegarID + "não localizado com sucesso!");
                } catch (IOException i){
                    JOptionPane.showMessageDialog(null, "Error no Momento de Salvar os Dados: " + i.getMessage());
                }
            } else if (opcaoEscolha.equals("0")) {
                JOptionPane.showMessageDialog(null, "Obrigado por usar o programa! Volte Sempre :)");
                continuarNaPagina = false;
            } else {
                JOptionPane.showMessageDialog(null, "Numero Invalido! Tente colocar um numero que esteja na pagina.");
            }
        }
    }
}

package gerenciamentoProjetoLP;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class SalvarDados {
    private String nomeDoArquivo;
    private static final String NOME_ARQUIVO = "LOGS_EquipamentosSalvos.txt";
    private static final String SEPARADOR_ARQUVO = "###";

    public SalvarDados() {
        this.nomeDoArquivo = NOME_ARQUIVO;
    }

    // Essa parte de salvar dados em um arquivo, ainda esta bem incompleto, so tem a base.
    // Por favor alguem da Equipe olhe o codigo direito e complete, e faça a implementação no codigo principal
    public void salvarDadosGerenciamento(List<Equipamento> lista) throws IOException {
        BufferedWriter escritor = null;

        try {
            escritor = new BufferedWriter(new FileWriter(this.nomeDoArquivo));

            for (Equipamento e : lista) {
                String linha = e.getModelo() + SEPARADOR_ARQUVO + e.getMarca() + SEPARADOR_ARQUVO + e.getId();
                escritor.write(linha);
                escritor.newLine();
            }
        } finally {
            if (escritor != null) {
                escritor.close();
            }
        }
    }
}
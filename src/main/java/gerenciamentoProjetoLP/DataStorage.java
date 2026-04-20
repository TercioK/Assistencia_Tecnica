package gerenciamentoProjetoLP;

import java.util.List;

public interface DataStorage {
    void salvarDados(List<Equipamento> lista);
    List<Equipamento> carregarDados();
}
package gerenciamentoProjetoLP;
import java.io.IOException;
import java.util.List;

public interface AssistenciaService {
    void cadastrar(Equipamento novoEquipamento);

    void atualizarStatus(int id, String novoStatus) throws EquipamentoNaoLocalizadoException;

    void darBaixa(int id) throws EquipamentoNaoLocalizadoException;

    List<Equipamento> recuperarTodos() throws IOException;

    public void salvarDadosGerenciamento() throws IOException;
}
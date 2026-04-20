package gerenciamentoProjetoLP;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// IntelJ gerou o esqueleto, e so implementei algumas coisas que estava faltando.
public class AssistenciaServiceImpl implements AssistenciaService {
    private String programaVersion = "1.2";
    private List<Equipamento> lista = new ArrayList<>();
    private SalvarDados gravador = new SalvarDados();

    @Override
    public void cadastrar(Equipamento novoEquipamento) {
        lista.add(novoEquipamento);
    }

    public String getVersion() {
        return this.programaVersion;
    }

    @Override
    public void atualizarStatus(int id, String novoStatus) throws EquipamentoNaoLocalizadoException {
        Equipamento foiEncontrado = null;

        for (Equipamento e : this.lista) {
            if (e.getId() == id) {
                foiEncontrado = e;
                break;
            }
        }
        // Se caso ele nao ache a informaçao, ele solta um error, e nao quebra o programa.
        if (foiEncontrado == null) {
            throw new EquipamentoNaoLocalizadoException("Equipamento infelizmente não foi encontrado pra adicionar: " + id);
        }
        foiEncontrado.setDescricaoDefeito(novoStatus);
    }

    @Override
    public void darBaixa(int id) throws EquipamentoNaoLocalizadoException {
        Equipamento foiEncontrado = null;

        for (Equipamento e : this.lista) {
            if (e.getId() == id) {
                foiEncontrado = e;
                break;
            }
        }
        // Se caso ele nao ache a informaçao, ele solta um error, e nao quebra o programa.
        if (foiEncontrado == null) {
            throw new EquipamentoNaoLocalizadoException("Equipamento infelizmente não foi encontrado pra remover: " + id);
        }
        lista.remove(foiEncontrado);
    }

    @Override
    public List<Equipamento> recuperarTodos() {
        return lista;
    }

    // Arquivos
    public void salvarDadosGerenciamento() throws IOException {
        this.gravador.salvarDadosGerenciamento(lista);
    }
}

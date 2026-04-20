package gerenciamentoProjetoLP;

import java.util.Objects;

abstract class ItemTecnico {
    protected int id;
    protected String modelo;
    protected String marca;

    public ItemTecnico(int id, String modelo, String marca) {
        this.id = id;
        this.modelo = modelo;
        this.marca = marca;
    }
}

public class Equipamento extends ItemTecnico {
    private String descricaoDefeito;
    private String status;

    public Equipamento(int id, String modelo, String marca, String descricaoDefeito, String status) {
        super(id, modelo, marca);
        this.descricaoDefeito = descricaoDefeito;
        this.status = status;
    }

    public int getId() { return id; }
    public String getModelo() { return modelo; }
    public String getMarca() { return marca; }
    public String getDescricaoDefeito() { return descricaoDefeito; }
    public void setDescricaoDefeito(String descricaoDefeito) { this.descricaoDefeito = descricaoDefeito; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipamento that = (Equipamento) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return id + ";" + modelo + ";" + marca + ";" + descricaoDefeito + ";" + status;
    }
}
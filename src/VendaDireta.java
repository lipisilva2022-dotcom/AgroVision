public class VendaDireta {

    private int id;
    private int relatorioId;
    private double valor;

    public VendaDireta(int id, int relatorioId, double valor) {
        this.id = id;
        this.relatorioId = relatorioId;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRelatorioId() {
        return relatorioId;
    }

    public void setRelatorioId(int relatorioId) {
        this.relatorioId = relatorioId;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }
}
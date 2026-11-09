public class MetaMensal {

    private int id;
    private int relatorioId;
    private double meta;
    private double percentualRealizado;

    public MetaMensal(int id, int relatorioId,
                      double meta, double percentualRealizado) {

        this.id = id;
        this.relatorioId = relatorioId;
        this.meta = meta;
        this.percentualRealizado = percentualRealizado;
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

    public double getMeta() {
        return meta;
    }

    public void setMeta(double meta) {
        this.meta = meta;
    }

    public double getPercentualRealizado() {
        return percentualRealizado;
    }

    public void setPercentualRealizado(double percentualRealizado) {
        this.percentualRealizado = percentualRealizado;
    }
}
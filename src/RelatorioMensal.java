import java.time.LocalDate;

public class RelatorioMensal {

    private int id;
    private LocalDate dataReferencia;
    private double faturamento;
    private double comissao;
    private double margemMensal;
    private double retiradaMensal;
    private double saldo;

    // Construtor completo
    public RelatorioMensal(int id, LocalDate dataReferencia,
                           double faturamento, double comissao,
                           double margemMensal, double retiradaMensal,
                           double saldo) {

        this.id = id;
        this.dataReferencia = dataReferencia;
        this.faturamento = faturamento;
        this.comissao = comissao;
        this.margemMensal = margemMensal;
        this.retiradaMensal = retiradaMensal;
        this.saldo = saldo;
    }

// Getters e Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataReferencia() {
        return dataReferencia;
    }

    public void setDataReferencia(LocalDate dataReferencia) {
        this.dataReferencia = dataReferencia;
    }

    public double getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(double faturamento) {
        this.faturamento = faturamento;
    }

    public double getComissao() {
        return comissao;
    }

    public void setComissao(double comissao) {
        this.comissao = comissao;
    }

    public double getMargemMensal() {
        return margemMensal;
    }

    public void setMargemMensal(double margemMensal) {
        this.margemMensal = margemMensal;
    }

    public double getRetiradaMensal() {
        return retiradaMensal;
    }

    public void setRetiradaMensal(double retiradaMensal) {
        this.retiradaMensal = retiradaMensal;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

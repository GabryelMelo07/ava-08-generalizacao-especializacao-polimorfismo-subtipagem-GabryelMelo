package model.imobiliaria;

public class LocacaoApartamento extends Locacao {

  private int prazoMinimo;

  /**
   * Construtor da classe LocacaoApartamento.
   * Gera uma locação de imóvel recebendo:
   * @param endereco Endereço do imóvel.
   * @param quartos  Quantidade de quartos.
   * @param garagem  Quantidade de garagem(s).
   * @param valor    Valor do contrato.
   * @param extra    Valor extra do contrato (Condomínio ou taxa comercial).
   * @param prazoMinimo Quantidade mínima em mezes do contrato.
   */
  public LocacaoApartamento(String endereco, int quartos, int garagem, double valor, double extra) {
    super(endereco, quartos, garagem, valor, extra);
    this.prazoMinimo = 12;
  }

  public int getPrazoMinimo() {
    return this.prazoMinimo;
  }

  public String getTipo() {
    return "Apartamento";
  }

  /**
   * Sobrescreve o método getValorTotal(), da classe Locacao.
   * @return Retorna o valor total somado à taxa de condomínio.
   */
  @Override
  public double getValorTotal() {
    // locações de apartamentos cobram condomínio
    return this.getValor() + this.getValorCondominio();
  }

  /**
   * Sobrescreve o método que paga uma cobrança com atraso.
   * @param atraso Recebe como parâmetro o valor do atraso.
   */
  @Override
  public double pagar(int atraso) {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());
    valorPago += 0.10 * this.getValorCondominio();
    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  @Override
  public String toString() {
    return String.format("Apartamento %n", this.getValorTotal());
  }

}

package model.imobiliaria;

import exception.PrazoMinimoComercial;

public class LocacaoComercial extends Locacao {

  private int prazoMinimo;

  /**
   * Construtor da classe LocacaoComercial.
   * Gera uma locação de imóvel recebendo:
   * @param endereco    Endereço do imóvel.
   * @param quartos     Quantidade de quartos.
   * @param garagem     Quantidade de garagem(s).
   * @param valor       Valor do contrato.
   * @param extra       Valor extra do contrato (Condomínio ou taxa comercial).
   * @param prazoMinimo Quantidade mínima em mezes do contrato.
   */

  public LocacaoComercial(String endereco, int quartos, int garagem, double valor, double extra) {
    super(endereco, quartos, garagem, valor, extra);
    this.prazoMinimo = 6;
  }

  public int getPrazoMinimo() {
    return this.prazoMinimo;
  }

  public String getTipo() {
    return "Comercial";
  }

  /**
   * Sobrescreve o método getValorTotal(), da classe Locacao.
   * No primeiro mês, retorna o valor total somado a taxa de imóvel comercial.
   * @return Retorna o valor do contrato.
   */
  @Override
  public double getValorTotal() {
    if (this.getMeses() == prazo) { // locações comerciais pagam a taxa no primeiro pagamento
      return this.getValor() + this.getTaxaComercial();
    }
    return this.getValor();
  }

  /**
   * Sobrescreve o método setPrazo(), da classe Locacao.
   * @param prazo Recebe um inteiro equivalente a quantidade de meses do novo prazo.
   */

  @Override
  public void setPrazo(int prazo) {
    if (prazo < 6) {
      throw new PrazoMinimoComercial();
    }
    this.prazo = prazo;
    this.meses = prazo;
  }

  /**
   * Sobrescreve o método pagar(), da classe Locacao.
   * Paga uma cobrança com atraso.
   * @param atraso Recebe como parâmetro o valor do atraso.
   */

  @Override
  public double pagar(int atraso) {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    valorPago += 0.05 * this.getValorTotal() + (atraso * 0.01 * this.getValorTotal());
    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  /**
   * Sobrescreve o método renovar(), para renovar o contrato de locação.
   * @param percentual Recebe um percentual para reajuste de valor.
   */

  @Override
  public void renovar(double percentual) {
    this.renovar(6, percentual);
  }

  /**
   * Sobrescreve o método renovar(), para renovar o contrato de locação.
   * @param percentual Recebe um percentual para reajuste de valor.
   * @param extra      Recebe um valor extra a ser adicionado no contrato.
   */

  @Override
  public void renovar(double percentual, double extra) {
    this.renovar(6, percentual, extra);
  }

  @Override
  public String toString() {
    return String.format("Comercial %n", this.getValorTotal());
  }

}

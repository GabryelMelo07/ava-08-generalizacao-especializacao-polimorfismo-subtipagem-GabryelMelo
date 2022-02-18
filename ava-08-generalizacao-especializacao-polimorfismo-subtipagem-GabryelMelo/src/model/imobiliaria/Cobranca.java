package model.imobiliaria;

import exception.CobrancaRealizadaException;

public class Cobranca {

  private final Imobiliaria imobiliaria;
  private final int ano;
  private final int mes;
  private double valorArrecadado = 0.0;

  /**
   * Construtor da classe Cobrança.
   * Gera uma nova cobrança recebendo:
   * @param imobiliaria Nome da imobiliaria.
   * @param ano Ano de início da cobrança.
   * @param mes Mês de início da cobrança.
   */

  public Cobranca(Imobiliaria imobiliaria,  int ano, int mes) {
    this.imobiliaria = imobiliaria;
    this.ano = ano;
    this.mes = mes;
  }

  public int getAno() {
    return ano;
  }

  public int getMes() {
    return mes;
  }

  public String getPeriodo() {
    return mes + "/" + ano;
  }

  /**
   * Método que paga uma cobrança.
   * @throws CobrancaRealizadaException Lança a exceção caso o valor arrecadado,
   *                                    seja maior que 0 (a cobrança já foi paga).
   */

  public void pagar() {
    if (valorArrecadado > 0) {
      throw new CobrancaRealizadaException();
    }
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      valorArrecadado += locacao.pagar();
    }
  }

  /**
   * Método que paga uma cobrança com atraso.
   * @param atraso Recebe como parâmetro o valor do atraso.
   */

  public void pagar(int atraso) {
    if (valorArrecadado > 0) {
      return;
    }
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      valorArrecadado += locacao.pagar(atraso);
    }
  }

  public double getValorArrecadado() {
    return valorArrecadado;
  }

  /**
   * Método que recebe o valor estimado de todas locações em andamento.
   * @return Retorna um valor estimado calculado das locações.
   */

  public double getValorEstimado() {
    double total = 0.0;
    for (Object o: imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      // PATCH:
      total += locacao.getValorTotal();
    }
    return total;
  }

  @Override
  public String toString() {
    return "Cobranca referente ao período " + this.getPeriodo()
      + " no valor de " + this.getValorArrecadado();
  }
}

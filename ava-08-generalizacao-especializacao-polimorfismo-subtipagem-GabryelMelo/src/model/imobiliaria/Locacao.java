package model.imobiliaria;

import exception.GaragemInvalida;
import exception.ImovelInvalido;
import exception.PrazoMinimoResidencial;
import exception.QuartoInvalido;
import exception.ValorInvalido;

public abstract class Locacao {

  // Adicionei cada parâmetro na sua linha para ficar correto no CheckStyle.
  private final String endereco;
  private final int quartos;
  private final int garagem;
  private double valor;
  private double extra;
  protected int meses;
  protected int prazo;
  protected double ultimoValorPago;

  /**
   * Construtor da classe Locacao.
   * Gera uma locação de imóvel recebendo:
   * @param endereco Endereço do imóvel.
   * @param quartos  Quantidade de quartos.
   * @param garagem  Quantidade de garagem(s).
   * @param valor    Valor do contrato.
   * @param extra    Valor extra do contrato (Condomínio ou taxa comercial).
   * @throws GaragemInvalida Caso o número de garagens informado seja menor que 0.
   * @throws QuartoInvalido  Caso o número de quartos informado seja menor que 0.
   * @throws ValorInvalido   Caso o valor informado seja negativo.
   * @throws ImovelInvalido  Caso o tipo do imóvel seja diferente de:'A', 'C' ou 'R'.
   */

  protected Locacao(String endereco, int quartos, int garagem, double valor, double extra) {
    if (garagem < 0) {
      throw new GaragemInvalida();
    }
    if (quartos < 0) {
      throw new QuartoInvalido();
    }
    if (valor < 0) {
      throw new ValorInvalido();
    }
    if (extra < 0) {
      throw new ValorInvalido();
    }
    this.endereco = endereco;
    this.quartos = quartos;
    this.garagem = garagem;
    this.valor = valor;
    this.extra = extra;
  }

  public int getMeses() {
    return this.meses;
  }

  public double getValor() {
    return this.valor;
  }

  public double getValorCondominio() {
    return this.extra;
  }

  public double getUltimoValorPago() {
    return ultimoValorPago;
  }

  public double getTaxaComercial() {
    return this.extra;
  }

  public String getEndereco() {
    return endereco;
  }

  public double getExtra() {
    return extra;
  }

  public int getGaragem() {
    return garagem;
  }

  public int getQuartos() {
    return quartos;
  }

  /**
   * Método que retorna o valor total do contrato dos imóveis.
   * @return Valor total dos contratos.
   */

  public double getValorTotal() {
    return this.getValor();
  }

  /**
   * Método que define um prazo para o fim do contrato.
   * @param prazo Um inteiro referente a quantidade de meses do contrato.
   * @throws PrazoMinimoResidencial Caso o prazo informado seja menor que 12 meses.
   */

  public void setPrazo(int prazo) {
    if (prazo < 12) {
      throw new PrazoMinimoResidencial();
    }
    this.prazo = prazo;
    this.meses = prazo;
  }

  /**
   * Método para renovar o contrato de locação.
   * @param percentual Percentual para reajuste de valor.
   */
  public void renovar(double percentual) {
    this.renovar(12, percentual);
  }

  /**
   * Sobrecarga do método para renovar o contrato de locação.
   * @param percentual Percentual para reajuste de valor.
   * @param extra      Valor extra a ser adicionado no contrato.
   */
  public void renovar(double percentual, double extra) {
    this.renovar(12, percentual, extra);
  }

  /**
   * Sobrecarga do método para renovar o contrato de locação.
   * @param prazo      Novo prazo para o vencimento do contrato.
   * @param percentual Percentual para reajuste de valor.
   */
  public void renovar(int prazo, double percentual) {
    this.setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
  }

  /**
   * Sobrecarga do método para renovar o contrato de locação.
   * @param prazo      Novo prazo para o vencimento do contrato.
   * @param percentual Percentual para reajuste de valor.
   * @param extra      Valor extra a ser adicionado no contrato.
   */

  public void renovar(int prazo, double percentual, double extra) {
    this.setPrazo(prazo);
    this.valor += percentual / 100.0 * valor;
    this.extra = extra;
  }

  /**
   * Método para pagar a cobrança de uma locação.
   * Diminui um mês do contrato toda vez que paga,
   * e atualiza o ultimo valor pago com o atual.
   * @return Retorna o valor pago.
   */

  public double pagar() {
    if (this.meses <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  /**
   * Sobrecarga do método que paga uma cobrança,
   * recebendo um inteiro para reajuste de cobrança com atraso.
   * @param atraso Recebe como parâmetro o valor do atraso.
   * @return Retorna o valor pago.
   */

  public double pagar(int atraso) {
    if (this.getMeses() <= 0) {
      return 0;
    }
    double valorPago = this.getValorTotal();
    valorPago += 0.03 * this.getValor() + (atraso * 0.01 * this.getValor());
    this.meses = this.meses - 1;
    ultimoValorPago = valorPago;
    return valorPago;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj instanceof Locacao) {
      Locacao outraLocacao = (Locacao) obj;
      if (this.endereco == outraLocacao.endereco) {
        return true;
      }
    }
    return super.equals(obj);
  }

}

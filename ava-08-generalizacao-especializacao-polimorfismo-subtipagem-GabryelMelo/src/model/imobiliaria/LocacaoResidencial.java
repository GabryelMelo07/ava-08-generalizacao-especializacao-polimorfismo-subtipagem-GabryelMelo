package model.imobiliaria;

public class LocacaoResidencial extends Locacao {

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

  public LocacaoResidencial(String endereco, int quartos, int garagem, double valor, double extra) {
    super(endereco, quartos, garagem, valor, extra);
    this.prazoMinimo = 12;
  }

  public int getPrazoMinimo() {
    return this.prazoMinimo;
  }

  public String getTipo() {
    return "Residencial";
  }

  @Override
  public String toString() {
    return String.format("Residencial %n", this.getValorTotal());
  }

}

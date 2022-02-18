package model.imobiliaria;

import exception.LocacaoDeveSerRenovada;
import exception.LocacaoEmAndamento;
import exception.PrazoMinimoComercial;
import exception.PrazoMinimoResidencial;
import util.Bag;

public class Imobiliaria {

  private String nome;
  private Bag locacoes = new Bag(1000);
  private Bag cobrancas = new Bag(1000000);

  /**
   * Construtor da classe Imobiliaria.
   * Gera uma imobiliaria recebendo:
   * @param nome Nome da imobiliaria.
   */
  public Imobiliaria(String nome) {
    this.nome = nome;
  }

  public String getNome() {
    return nome;
  }

  @Override
  public String toString() {
    String str = "Imobiliaria " + nome + "\n";
    str += "Locações: \n";
    for (Object o : this.getLocacoesAtivas()) {
      str += o.toString() + "\n";
    }
    str += "Cobranças: \n";
    for (Object o : this.cobrancas.toArray()) {
      str += o.toString() + "\n";
    }
    return str;
  }

  /**
   * Retorna a quantidade atual de locações ativas.
   * @return Lista de locações, em formato de array(vetor).
   */

  public Object[] getLocacoesAtivas() {
    Bag ativas = new Bag(locacoes.count());
    for (Object o : locacoes.toArray()) {
      Locacao l = (Locacao) o;
      if (l.getMeses() > 0) {
        ativas.add(l);
      }
    }
    return ativas.toArray();
  }

  /**
   * Gera o contrato de locação de um imóvel, se o imóvel não estiver alugado ainda.
   * Define a quantidade mínima de meses de acordo com o tipo do imóvel.
   * @param locacao Recebe um imóvel Residencial, ou Apartamento.
   * @throws LocacaoDeveSerRenovada Lança a exceção caso o imóvel já estiver alugado.
   */

  public void contratar(Locacao locacao) {
    if (locacoes.has(locacao)) {
      throw new LocacaoDeveSerRenovada();
    }
    locacao.setPrazo(12); // mínimo não comercial
    locacoes.add(locacao);
  }

  /**
   * Sobrecarga do método contratar, recebe um imóvel do tipo Comercial.
   * @param locacao Recebe um imóvel Comercial alugável.
   * @throws LocacaoDeveSerRenovada Lança a exceção caso o imóvel já estiver alugado.
   */

  public void contratar(LocacaoComercial locacao) {
    if (locacoes.has(locacao)) {
      throw new LocacaoDeveSerRenovada();
    }
    locacao.setPrazo(6); // mínimo comercial
    locacoes.add(locacao);
  }

  /**
   * Renova o contrato de locação.
   * @param locacao  Recebe como paramêtro um imóvel Comercial para renovar o contrato.
   * @param reajuste O valor do reajuste (caso haja).
   * @param extra    O valor extra (caso haja).
   * @param prazo    O vencimento do contrato.
   * @throws LocacaoEmAndamento    Caso o contrato atual tenha mais de 0 meses.
   * @throws PrazoMinimoResidencia Caso o prazo minimo do contrato seja menor que 12 meses.
   * @throws PrazoMinimoComercial  Caso o prazo minimo do contrato seja menor que 6 meses.
   */

  public void renovar(LocacaoComercial locacao, double reajuste, double extra, int prazo) {
    if (locacao.getMeses() > 0) {
      throw new LocacaoEmAndamento();
    }
    if (prazo < 6) {
      throw new PrazoMinimoComercial();
    }
    locacao.setPrazo(prazo);
    locacao.renovar(reajuste, extra);
  }

  /**
   * Renova o contrato de locação.
   * @param locacao  Recebe um imóvel Residencial ou Apartamento para renovar o contrato.
   * @param reajuste O valor do reajuste (caso haja).
   * @param extra    O valor extra (caso haja).
   * @param prazo    O vencimento do contrato.
   * @throws LocacaoEmAndamento    Caso o contrato atual tenha mais de 0 meses.
   * @throws PrazoMinimoResidencia Caso o prazo minimo do contrato seja menor que 12 meses.
   * @throws PrazoMinimoComercial  Caso o prazo minimo do contrato seja menor que 6 meses.
   */

  public void renovar(Locacao locacao, double reajuste, double extra, int prazo) {
    if (locacao.getMeses() > 0) {
      throw new LocacaoEmAndamento();
    }
    if (prazo < 12) {
      throw new PrazoMinimoResidencial();
    }
    locacao.setPrazo(prazo);
    locacao.renovar(reajuste, extra);
  }

  /**
   * Gera uma nova cobrança a partir de um ano e um mês.
   * @param ano Recebe como parametro um ano para inicio da cobrança.
   * @param mes Recebe como parametro um mês para inicio da cobrança.
   * @return Retorna a nova cobrança gerada.
   */

  public Cobranca novaCobranca(int ano, int mes) {
    Cobranca cobranca = new Cobranca(this, ano, mes);
    cobrancas.add(cobranca);
    return cobranca;
  }

}

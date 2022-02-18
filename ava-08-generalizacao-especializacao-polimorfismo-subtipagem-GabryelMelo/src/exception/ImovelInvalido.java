package exception;

public class ImovelInvalido extends RuntimeException {

  public ImovelInvalido() {
    super("O tipo do imóvel não pode ser diferente de: 'A', 'C' ou 'R'");
  }

}

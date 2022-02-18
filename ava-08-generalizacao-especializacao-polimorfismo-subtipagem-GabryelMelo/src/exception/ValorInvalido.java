package exception;

public class ValorInvalido extends RuntimeException {

  public ValorInvalido() {
    super("O valor não pode ser negativo.");
  }

}

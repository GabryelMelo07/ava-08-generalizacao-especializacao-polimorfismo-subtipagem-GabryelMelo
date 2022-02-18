package exception;

public class CobrancaRealizadaException extends RuntimeException {

  public CobrancaRealizadaException() {
    super("Cobrança já realizada.");
  }

}

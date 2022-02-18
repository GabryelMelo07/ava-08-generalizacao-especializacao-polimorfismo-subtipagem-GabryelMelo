package exception;

public class LocacaoDeveSerRenovada extends RuntimeException {

  public LocacaoDeveSerRenovada() {
    super("Locacao deve ser renovada.");
  }

}

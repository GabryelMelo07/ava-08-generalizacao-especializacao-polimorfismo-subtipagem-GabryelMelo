package exception;

public class LocacaoEmAndamento extends RuntimeException {

  public LocacaoEmAndamento() {
    super("Locacao deve ser renovada.");
  }

}

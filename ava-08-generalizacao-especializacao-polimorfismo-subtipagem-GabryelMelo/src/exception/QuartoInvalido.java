package exception;

public class QuartoInvalido extends RuntimeException {

  public QuartoInvalido() {
    super("O número de quartos não pode ser menor que 0.");
  }

}

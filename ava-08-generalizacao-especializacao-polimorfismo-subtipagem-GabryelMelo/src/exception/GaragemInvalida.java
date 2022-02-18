package exception;

public class GaragemInvalida extends RuntimeException {

  public GaragemInvalida() {
    super("O número de garagens não pode ser menor que 0.");
  }

}

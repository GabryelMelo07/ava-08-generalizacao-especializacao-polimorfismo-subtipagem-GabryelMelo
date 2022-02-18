package exception;

public class PrazoMinimoResidencial extends RuntimeException {

  public PrazoMinimoResidencial() {
    super("Prazo minimo de 12 meses para residencia e apartamento.");
  }

}

package exception;

public class PrazoMinimoComercial extends RuntimeException {

  public PrazoMinimoComercial() {
    super("Prazo minimo de 6 meses para comercial.");
  }

}

package util;

class FullBagException extends RuntimeException {

  FullBagException(int cap) {
    super("A bolsa está na capacidade máxima de " + cap + " objetos");
  }

}

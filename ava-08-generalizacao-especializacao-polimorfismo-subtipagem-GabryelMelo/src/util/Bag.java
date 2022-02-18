package util;

public class Bag {

  private Object[] objetos;

  /**
   * Cria uma bolsa de objetos.
   * @param capacidade Capacidade da bolsa.
   */
  public Bag(int capacidade) {
    this.objetos = new Object[capacidade];
  }

  /**
   * Verifica se a bolsa está vazia.
   * @return true se a bolsa estiver vazia ou false caso contrário.
   */
  public boolean isEmpty() {
    return this.count() == 0;
  }

  /**
   * Conta quantos objetos estão na bolsa.
   * @return a quantidade de objetos na bolsa.
   */
  public int count() {
    int count = 0;
    for (Object o : this.objetos) {
      if (o != null) {
        count++;
      }
    }
    return count;
  }

  /**
   * Adiciona um objeto à bolsa.
   * @param o O objeto.
   * @throws FullBagException se a bolsa estiver cheia.
   */
  public void add(Object o) throws FullBagException {
    if (this.count() == this.objetos.length + 1) {
      throw new FullBagException(this.objetos.length);
    }
    for (int i = 0; i < this.objetos.length; i++) {
      if (this.objetos[i] == null) {
        this.objetos[i] = o;
        return;
      }
    }
  }

  /**
   * Verifica se um dado objeto está na bolsa.
   * @param o objeto a ser verificado.
   * @return true se o objeto estiver na bolsa e false caso contrário.
   */
  public boolean has(Object o) {
    for (Object e : this.objetos) {
      if (o.equals(e)) {
        return true;
      }
    }
    return false;
  }

  /**
   * Remove um objeto da bolsa.
   * @param o O objeto a ser removido.
   */
  public void remove(Object o) {
    for (int i = 0; i < objetos.length; i++) {
      if (this.objetos[i].equals(o)) {
        this.objetos[i] = null;
      }
    }
  }

  /**
   * Converte a bolsa em um array de objetos.
   * @return um array com apenas os objetos da bolsa.
   */
  public Object[] toArray() {
    Object[] array = new Object[this.count()];
    for (int i = 0, c = 0; i < this.objetos.length; i++) {
      if (this.objetos[i] != null) {
        array[c++] = this.objetos[i];
      }
    }
    return array;
  }

  @Override
  public String toString() {
    String s = "Bag content:\n";
    for (Object o : this.toArray()) {
      s += o.toString() + "\n";
    }
    return s;
  }
}

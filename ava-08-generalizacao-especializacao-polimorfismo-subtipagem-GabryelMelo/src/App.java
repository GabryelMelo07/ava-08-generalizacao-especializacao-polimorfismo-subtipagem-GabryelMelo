import java.util.Arrays;

import model.imobiliaria.Cobranca;
import model.imobiliaria.Imobiliaria;
import model.imobiliaria.Locacao;
import model.imobiliaria.LocacaoApartamento;
import model.imobiliaria.LocacaoComercial;
import model.imobiliaria.LocacaoResidencial;

class App {

  public static void main(String[] args) {

    System.out.println("Locações \n ------------------------");

    // Locação Residencial
    LocacaoResidencial loc1 = new LocacaoResidencial("Rua Alfredo Huch, 475", 2, 1, 900.0, 100.0);

    System.out.println(loc1.getTipo().equals("Residencial")); // Residencial
    System.out.println(loc1.getEndereco().equals("Rua Alfredo Huch, 475")); // Rua Alfredo Huch, 475
    System.out.println(loc1.getQuartos() == 2); // 2
    System.out.println(loc1.getGaragem() == 1); // 1 (carro)

    // 0 meses, porque ainda não foi contratada! Depois de contratado será 12.
    System.out.println(loc1.getMeses() == 0);

    System.out.println(loc1.getValor() == 900.0); // 900.0
    // NÃO CONSIDERA CONDOMÍNIO NAS LOCAÇÕES RESIDENCIAIS
    System.out.println(loc1.getValorTotal() == 900.0); // 900.0

    // Locação Apartamento
    LocacaoApartamento loc2 = new LocacaoApartamento("Av 24 de maio, 355", 1, 1, 400.0, 400.0);
    System.out.println(loc2.getTipo().equals("Apartamento")); // Apartamento
    System.out.println(loc2.getEndereco().equals("Av 24 de maio, 355")); // Av 24 de maio, 355
    System.out.println(loc2.getQuartos() == 1); // 1
    System.out.println(loc2.getGaragem() == 1); // 1 (carro)

    // 0 meses, porque ainda não foi contratada! Depois de contratado será 12.
    System.out.println(loc2.getMeses() == 0);
    System.out.println(loc2.getValor() == 400.0); // 400.0
    System.out.println(loc2.getValorCondominio() == 400.0); // 400.0
    // LOCAÇÃO DE APTO SOMA VALOR+CONDOMINIO
    System.out.println(loc2.getValorTotal() == 800.0); // 800.0*/

    // Locação Comercial
    LocacaoComercial loc3 = new LocacaoComercial("Aquidaban, 8350", 0, 0, 1000.0, 500.0);
    System.out.println(loc3.getEndereco().equals("Aquidaban, 8350")); // Aquidaban, 8350

    // 0 meses, porque ainda não foi contratada! Depois de contratado será 6.
    System.out.println(loc3.getMeses() == 0);
    System.out.println(loc3.getValor() == 1000.0); // 1000.0

    // NÃO CONSIDERA CONDOMÍNIO, MAS CONSIDERA TAXA NA PRIMEIRA PARCELA
    System.out.println(loc3.getValorTotal() == 1500.0); // 1500.0

    Imobiliaria imobiliaria = new Imobiliaria("Silva");

    imobiliaria.contratar(loc1);
    // PATCH:
    System.out.println(loc1.getMeses() == 12); // 12 meses, porque foi contratado!

    imobiliaria.contratar(loc2);
    // PATCH:
    System.out.println(loc2.getMeses() == 12); // 12 meses, porque foi contratado!*/

    imobiliaria.contratar(loc3);
    // PATCH:
    System.out.println(loc3.getMeses() == 6); // 6 meses, porque foi contratado e é comercial!

    System.out.println("Processo da Imobiliária\n------------------------");

    System.out.println(Arrays.toString(imobiliaria.getLocacoesAtivas())); // 3 Locações

    Cobranca cobrancaNovembro2020 = imobiliaria.novaCobranca(2020, 11);

    System.out.println(cobrancaNovembro2020.getValorEstimado() == 3200); // 900+800+1500=3200

    // Paga as 3 locações
    cobrancaNovembro2020.pagar();

    System.out.println(cobrancaNovembro2020.getValorArrecadado() == 3200); // 900+800+1500=3200

    System.out.println(loc1.getMeses() == 11); // 11
    System.out.println(loc2.getMeses() == 11); // 11
    System.out.println(loc3.getMeses() == 5); // 5

    Cobranca cobrancaDezembro2020 = imobiliaria.novaCobranca(2020, 12);

    System.out.println(cobrancaDezembro2020.getValorEstimado() == 2700.0);

    // paga com 1 dia de atraso: multa 3% + 1% ao dia para Residência
    // se for Apartamento incide multa de 10% sobre o valor do condomínio
    // multa 5% + 1% ao dia para comercial
    int umDiaDeAtraso = 1;
    cobrancaDezembro2020.pagar(umDiaDeAtraso);

    for (var o : imobiliaria.getLocacoesAtivas()) {
      Locacao locacao = (Locacao) o;
      System.out.println(locacao.getUltimoValorPago()); // 936.0, 856.0, 1060.0
    }

    System.out.println(loc1.getMeses() == 10); // 10
    System.out.println(loc2.getMeses() == 10); // 10
    System.out.println(loc3.getMeses() == 4); // 4

    // Pagando todo o ano de 2021
    for (int i = 0; i < 10; i++) {
      imobiliaria.novaCobranca(2021, i + 1).pagar();
    }

    System.out.println(loc1.getMeses() == 0); // 0
    imobiliaria.renovar(loc1, 5.0, 0.0, 12);
    System.out.println(loc1.getMeses() == 12); // 12 (meses)
    System.out.println(loc1.getValor() == 945.0); // 945.0
    System.out.println(loc1.getValorTotal() == 945.0); // 945.0

    System.out.println(imobiliaria);
  }
}

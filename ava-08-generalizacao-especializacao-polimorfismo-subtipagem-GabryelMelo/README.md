# Avaliação 08 Generalização e Especialização, e Polimorfismo!

Link do Classroom: <https://classroom.github.com/a/FWUCVNGV>

> "I know Kung Fu." -- Neo
> "Show me." -- Morpheus
>
> [Matrix Sparring Program](https://youtu.be/iHeofyFLjfQ)

## Implementar e testar segundo as especificações

- O esforço estimado desta atividade está entre 6 e 12h.
- Os Casos de Teste podem ser corrigidos se estiverem mal escritos, mas **a especificação dos objetos não pode ser alterada**.
- E, por fim, assegure-se de **assistir as videoaulas antes de começar**, pois lá estão explicados todos os conceitos e práticas presentes nesta atividade.


### Refatorar o projeto Imobiliária para generalização/especialização e polimorfismo

Considere a classe [App](src/App.java) e as instruções que verificam o funcionamento do sistema imobiliária, suas tarefas são:

0. **Entender**: entender as regras da imobiliária com base na leitura dos comentários e observação do seu funcionamento,
1. **Generalizar**: criar uma hierarquia de classes de forma que a [Locação](src/model/imobiliaria/Locacao.java) não precise do atributo de `tipo` e que [Cobrança](src/model/imobiliaria/Cobranca.java) não tenha detalhes dos tipos de imóveis,
2. **Testar**: converter os _prints_ do [App](src/App.java) para a forma de _Casos de Teste_ (assertivas),
3. **Refinar**: resolver os _warnings_ do _Check Style_, adicionar os JavaDocs e formatar o código conforme exigido,
4. **Tipar**: substituir as exceções de `IllegalArgumentException` e `IllegalStateException` por exceções personalizadas de acordo com o caso especial tratado (subtipar `RuntimeException` ou `Exception`), e
5. **Usar a `Bag`**: mover as classes [Bag](src/model/imobiliaria/Bag.java) e [FullBagException](src/model/imobiliaria/FullBagException.java) para o pacote [util](src/util/) e garantir que onde tenham coleções de objetos seja usada a classe [Bag](src/model/imobiliaria/Bag.java) (Bolsa) e não `ArrayList`, _arrays_ simples ou outras embutidas do Java.


- - -

> In theory, theory and practice are the same. In practice, they’re not.
>
> -- Yoggi Berra

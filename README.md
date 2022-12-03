
# Desafio VR Desenvolvimento

Este repositório contém as respostas das perguntas feitas ao teste de avaliação desenvolvedor java backend na sysmap. Abaixo estão as respostas às perguntas bem como a justificação das técnicas e decisões utilizadas durante o processo de desenvolvimento do teste prático.


## Respostas às perguntas

**1)**  O Sistema estando já subdivido em camadas e possuindo classes de domínio devia-se subdividir as responsabilidades de cada camada, respetivamente:   *o controller seria o responsável por simplesmente receber e responder requisições( utilizando classes dtos ).  O mesmo utilizaria uma classe de  serviço (service) para a execução da lógica da aplicação que por sua vez utilizaria a classe de repositório para persistência.* Seriam também implementados testes unitários para as classes de serviço, de persistência e nos controllers para iiiiii.

 **Notas**:
 *a)* Os controllers não devem saber nada sobre a lógica da aplicação e vice versa.

*b)* é aconselhável implementarmos arquiteturas que nos ajudam a atingir níveis elevados de abstração das classes de serviço e de domínio como a  `'Hexagonal'` , pois quanto melhor aplicarmos este tipo de prática mais manutenível fica o nosso código

*c)* Os testes unitários em cada camada nos dão garantia de que a "lógica" da nossa aplicação continua correta independentemente da mudança.  

**2)** A adoção de servidores de aplicação em uma arquitetura orientada a microsserviços pode causar problemas sérios a uma arquitetura, imaginemos que ocorra um erro em uma das aplicações e que este erro deixe o servidor inoperante, automaticamente todas as outras apps ficam inativas. 

**3)**  Os principais desafios ao se fazer uma mudança para servidor embutido são:
---**Muitas aplicações para monitorar )** o facto de cada aplicação ter o seu próprio servidor dificulta o monitoramento de todas as aplicações instaladas no servidor de forma simultânea. Não é possível parar ou iniciar todas aplicações em simultâneo, é necessário fazer a operação para cada servidor.

---**hot deployment não suportado )**   não é possível fazermos deploy da aplicação sem reiniciarmos  a mesma.

servidores embutidos são preferíveis no desenvolvimento de microsserviços  visto que cada está isolada em seu servidor e mesmo que uma das aplicações caia não afetará as outras.

## Teste prático

### Suposições

**1)**   Supondo que o   `'numeroCartao'`  é uma String de 16 algarismos e a `'senha'` é uma String de 16 algarismos em um objeto Cartao.  As validações foram feitas em função disto.

**2)** Considerei não ser necessário o versionamento da API visto que em nenhuma requisição é informado a versão da mesma.

**2)** Considerei não utilizar bibliotecas terciárias para diminuição de código "boiler plate" assumindo não ser permitido para o teste.
****
### Desenvolvimento

### TDD
Durante o desenvolvimento do projeto foi utilizado a abordagem do  _Test-Driven Development_ para confiabilidade do código por ser testado constantemente durante o desenvolvimento. Todas as funcionalidades das classes de Serviço, Repositório e Controllers foram  testadas isoladamente . Também foi utilizado o teste de integração da aplicação para garantia da funcionalidade dos processos da lógica de negócio.

### DDD
Durante o desenvolvimento do projeto foi utilizado a abordagem do _Domain-Driven Development_ que em resumo presa pelo isolamento da lógica de negócio em relação a entidades ou classes externas da aplicação, para isto, o projeto foi subdividido nas seguintes camadas : 

 * **Controller**: Camada responsável por tratar as requisições externas à aplicação;
 * **Service**: Camada onde esta localizada a aplicação das regras de negócios;
 * **Repository**: Isola os objetos ou entidades do domínio do código que acessa o banco de dados.


**1)** Decidi utilizar o design pattern `factory method` para o auxílio da criação de objetos mock nos testes unitários e de persistencia. Desta forma não poluímos a nossa classe de teste com métodos que apenas retornam objetos, além disso torna os nossos objetos mock reutilizáveis em outras classes de teste.

**2)** Utilizei a biblioteca  org.testcontainers para a realização dos testes integração. Ela permite a realização de testes E2E com o gerenciamento automatizado de containers.

### Desafios opcionais

**1) Não uso de `ifs, breaks, continues`**
para o não uso destas cláusulas, em todas estruturas de decisões foi utilizado o operador ternário com o auxílio de lançamento de exceções.    

**2) Prevenção contra transações concorrentes**
para a prevenção deste risco, foi utilizado o conceito de `Transactions` do SQL, em resumo, ele garante a Atomicidade, Consistência, Isolação de uma Transação.
O Spring tem implementado este conceito com a anotação 
 `@Transactional`, que deve ser declarada no método que executa a mesma




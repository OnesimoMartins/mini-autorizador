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
### Decisões

**1)** Decidi utilizar o design pattern `factory method` para o auxílio da criação de objetos mock nos testes unitários e de persistencia. Desta forma não poluímos a nossa classe de teste com métodos que apenas retornam objetos, além disso torna os nossos objetos mock reutilizáveis em outras classes de teste.

**2)** Utilizei a biblioteca design test container para a realização dos testes integração.



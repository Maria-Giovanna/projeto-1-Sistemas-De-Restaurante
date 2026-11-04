# Projeto 1 — Sistema de Restaurante

Sistema de console em Java para um restaurante: cardápio, pedidos, personalização dos lanches e fechamento do dia.

🎥 **Vídeo:**  [Assista ao vídeo clicando aqui](https://youtu.be/QjVbKOVmByU)

## ▶️ Como rodar

1. Abra o projeto no IntelliJ
2. Rode a classe `org.example.app.Main`
3. Para os testes: botão direito em `src/test/java` → **Run 'All Tests'**

## Organização
---

## 📁 Como organizei o projeto

Eu pensei no sistema como se fosse um restaurante de verdade, onde cada classe é um funcionário e cada um tem a sua função. Isso me ajudou muito a decidir onde cada coisa deveria ficar.

No pacote **app** fica o atendimento. O Main é o "dono": ele só decide a ordem das coisas e chama quem precisa, sem imprimir nem calcular nada. O MenuConsole é o "garçom", a única classe que conversa com a pessoa. E a RestauranteFacade é a "gerente": o Main pede tudo pra ela, e ela distribui o trabalho.

No pacote **cardapio** fica a cozinha: o Lanche, a interface ItemCardapio, a LancheFactory (que é quem sabe montar os lanches) e os adicionais, ComCheddar e ComBaconExtra.

No pacote **pedido** ficam as comandas. O Pedido cuida de uma comanda só, e o GerenciadorDePedidos é tipo um fichário que guarda todas as comandas do dia num HashMap.

### `app` — o atendimento
- **`Main`** → o dono: controla a ordem das coisas. Não imprime, não lê e não calcula nada, só chama quem precisa.
- **`MenuConsole`** → o garçom: é a **única** classe que conversa com a pessoa (mostra mensagens e lê o que ela digita).
- **`RestauranteFacade`** → o gerente: o Main pede tudo para ela, e ela distribui o trabalho para as outras classes.

### `cardapio` — a cozinha
- **`ItemCardapio`** → interface (contrato) com `getDescricao()` e `getPreco()`. Tudo que pode entrar numa comanda assina esse contrato.
- **`Lanche`** → um lanche do cardápio, com nome, preço e os ingredientes que podem ser retirados.
- **`LancheFactory`** → o único lugar que sabe montar os lanches do cardápio.
- **`ComCheddar`** e **`ComBaconExtra`** → os adicionais.

### `pedido` — as comandas
- **`Pedido`** → cuida de **uma** comanda: número, nome do cliente e a lista de itens.
- **`GerenciadorDePedidos`** → o "fichário": guarda **todas** as comandas do dia num `HashMap`, dá o número de cada pedido novo e calcula o total do dia.

---

## O que foi feito
### Nível 1 — O restaurante abre
- O `Lanche` valida os dados: o nome não pode ser vazio e o preço não pode ser negativo. O construtor reutiliza os setters, então a validação fica num lugar só.
- O `Pedido` valida o nome do cliente: não pode ser vazio, precisa ter pelo menos 3 letras e só pode ter letras.
- O número do pedido é gerado pelo `GerenciadorDePedidos` com um contador, e nunca é digitado pela pessoa. Assim, dois pedidos nunca têm o mesmo número e o `HashMap` nunca substitui uma comanda por engano.
- Se a pessoa digitar um nome inválido, o sistema avisa e pergunta de novo, sem voltar para o menu.

### Nível 2 — O cardápio ganha vida
- Criei a interface `ItemCardapio` e troquei a lista do `Pedido` para `ArrayList<ItemCardapio>`. Assim a comanda guarda tanto lanches quanto lanches com adicionais (é a mesma ideia do `Pet[]` que guardava Cachorro e Gato).
- A `LancheFactory` cria um lanche **novo** a cada chamada. Se duas pessoas pedirem X-Bacon, cada uma recebe o seu, e personalizar um não afeta o outro.
- Os adicionais usam o padrão **Decorator** e podem ser empilhados: `X-Bacon + cheddar + bacon extra`.

### Nível 3 — O restaurante fica profissional
- **Personalizar o lanche:** a pessoa pode tirar queijo, alface, tomate e cebola. Tirar não muda o preço. Como melhoria minha, ela também pode **devolver** um ingrediente que tirou (a opção muda de "Tirar" para "Colocar"), e a descrição do lanche atualiza na hora.
- **Revisar o pedido:** depois de montar o primeiro item, aparece o resumo da comanda e um menu para **acrescentar**, **remover**, **trocar** um item ou **cancelar** o pedido inteiro. Só depois de **concluir** o pedido vai para a cozinha.
- **Trocar um item** não edita o lanche que já existe: remove o antigo e monta um novo, como o enunciado pede.
- **Combinações do dia:** no fechamento, além do total, mostro quantas combinações diferentes foram pedidas, usando um `HashSet<String>` com as descrições (ele não aceita repetidos).
- **Entradas inválidas:** se a pessoa digitar uma letra onde era número, o sistema avisa e pergunta de novo. Fiz isso com um método ajudante no `MenuConsole` que trata o `InputMismatchException` e limpa o Scanner com `nextLine()`, para não entrar em loop infinito.

### Decorator (`ComCheddar`, `ComBaconExtra`)
**Problema:** como colocar adicionais sem mexer na classe do lanche, e permitindo vários?
**Solução:** cada adicional funciona como uma **caixinha**: guarda outro `ItemCardapio` dentro dela. Quando alguém pergunta o preço, a caixinha pergunta para o item de dentro e soma o dela. Por isso dá para colocar uma caixinha dentro da outra, e o lanche original nunca é alterado.

```java
ItemCardapio item = LancheFactory.xBacon();   // X-Bacon
item = new ComCheddar(item);                  // X-Bacon + cheddar
item = new ComBaconExtra(item);               // X-Bacon + cheddar + bacon extra
```

### Factory (`LancheFactory`)
**Problema:** a Facade sabia nome e preço de cada lanche.
**Solução:** só a Factory sabe criar os lanches. A Facade só pede `LancheFactory.xBacon()`. Se um preço mudar, mudo num lugar só.

### Facade (`RestauranteFacade`)
**Problema:** sem ela, o Main precisaria conhecer o fichário, a comanda e a cozinha.
**Solução:** o Main conversa só com a Facade. Tanto que, quando renomeei métodos no `GerenciadorDePedidos`, o Main nem precisou mudar.

---

## 🧱 SOLID

- **S (Responsabilidade Única):** foi o que mais apliquei. Cada classe tem uma função, como os funcionários do restaurante.
- **O (Aberto/Fechado):** com o Decorator, posso criar um adicional novo sem mexer no `Lanche` nem no `Pedido`.
- **L (Liskov):** a comanda trata lanche e adicional do mesmo jeito, porque todos cumprem o contrato `ItemCardapio`.
- **I (Segregação de Interface):** o `ItemCardapio` só tem o que todos precisam. O `setCebola` ficou fora, porque uma caixinha de cheddar não tem cebola.
- **D (Inversão de Dependência):** parcial. O `Pedido` depende da interface, e não do `Lanche`, mas a Facade ainda cria o gerenciador com `new`, em vez de receber por injeção de dependência.

---

## 🧪 Testes (JUnit 5)

- **Validação:** preço negativo é recusado (`assertThrows`)
- **Preço com adicional:** X-Bacon com cheddar custa a soma certa (`assertEquals` com tolerância `0.001`, porque o `double` guarda números de forma aproximada)
- Testes extras de descrição com adicionais, retirada de ingrediente e validação do nome do cliente

---

## 💭 Decisões que tomei

- **Edição só antes de concluir:** decidi que o pedido só pode ser alterado enquanto está sendo montado. Depois que vai para a cozinha, não faz sentido mudar. A busca por número ficou como **consulta**, para a pessoa acompanhar a comanda.
- **Números não são reaproveitados:** um pedido cancelado mantém o número, como uma senha de banco. Assim nenhum cliente recebe o número de outro.
- **Gerenciador sem Singleton:** cheguei a fazer o `GerenciadorDePedidos` como Singleton, mas, por orientação da professora, tirei. Como só a Facade cria o gerenciador, na prática já existe um só.

## Limitações percebidas
- `X-Bacon + cheddar + bacon extra` e `X-Bacon + bacon extra + cheddar` contam como combinações diferentes, porque o `HashSet` compara o texto.
- `ComCheddar` e `ComBaconExtra` são quase iguais; daria para ter uma classe `Adicional` genérica.
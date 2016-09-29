# escalonador
Implementação de algoritmos de escalonamento
##Descrição
3 algoritmos de escalonamento foram implementados, são eles:
- First Come First Service (FCFS)
- Shortest Job First (SJF)
- Round Robin (RR)

Cada um possui características específicas e critérios para escalonamento. Para este projeto, foram simulados os escalonamentos utilizando os 3 algoritmos e as seguintes métricas foram calculadas:
- **Tempo de Retorno (turnaround):** Todo o tempo que um processo leva para ser executado e produzir sua resposta.
- **Tempo de Resposta:** Tempo que um processo entra na fila para ser executado até realmente ser escalonado e estar em posse do processador.
- **Tempo de Espera:** Tempo que um processo passa esperando para ser escalonado.

##Como testar

Para realizar o teste, basta rodar o comando `make` e conferir as saídas:
```bash
$ cd pasta-pessoal/escalonador
$ make
```
conferindo:

```bash
$ diff outputs/output1.out expected_outcome/outcome1.out
$ diff outputs/output2.out expected_outcome/outcome2.out
$ diff outputs/output3.out expected_outcome/outcome3.out
```
##Entrada
A entrada se dá especificando dois atibutos dos Jobs. O tempo de **chegada** e o tempo **que ele necessita na CPU**. Essas informações estão no formato de dois número inteiros separados por espaço, como ilustrado abaixo:
```
0 20
0 10
4 6
4 8
```
##Saída
A saída será o tempo médio de cada métrica citada acima utilizando cada algoritmo, como mostrado abaixo:
```
FCFS 30.5 19.5 19.5
SJF 21.5 10.5 10.5
RR 31.5 2.0 20.5
```

####Debugando
Para facilitar o entendimento, alguns prints estão comentado nos arquivos, basta descomentá-los juntamente com o método da classe `Logger` e analisar o funcionamento.

*Qualquer outra dúvida entre em contato:* higor.araujo.anjos@gmail.com

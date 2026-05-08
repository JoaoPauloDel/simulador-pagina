# Simulador de Algoritmos de Substituição de Páginas: Análise de Desempenho e Implementação Visual 

**UNIVERSIDADE DE FORTALEZA** **CENTRO DE CIÊNCIAS TECNOLÓGICAS - CURSO: CIÊNCIA DA COMPUTAÇÃO** **Disciplina:** Sistemas Operacionais  
**Autores:** João Paulo Del Vecchio e João Victor Araújo

**Palavras-chave:** Sistemas Operacionais. Memória Virtual. Substituição de Páginas. Algoritmos. Java.

---
##  Resumo
Este trabalho propõe o desenvolvimento de um simulador para avaliar o desempenho de diferentes algoritmos de substituição de páginas em sistemas de gestão de memória virtual. Os algoritmos estudados e implementados incluem FIFO (First In, First Out), LRU (Least Recently Used), do Relógio (Clock) e o algoritmo Ótimo. Para enriquecer a análise e a experiência do utilizador, o sistema conta com uma Interface Gráfica (GUI) desenvolvida nativamente em Java que gera gráficos comparativos das faltas de página (page faults), permitindo uma visualização clara da eficiência de cada método perante a mesma carga de requisições.

##  Introdução
A gestão eficiente da memória virtual é crucial para o desempenho dos sistemas operativos contemporâneos. A alocação e substituição de páginas são tarefas complexas que afetam diretamente a experiência do utilizador e o rendimento do processador. Neste contexto, os algoritmos de substituição desempenham o papel de decidir qual a página que deve ser removida da memória principal quando ocorre um "page fault" e os frames físicos estão cheios. Este projeto visa simular cenários práticos para observar as anomalias e eficiências de métodos clássicos vistos em sala, servindo o algoritmo Ótimo como base referencial inalcançável em sistemas reais, mas essencial para benchmarking.

##  Metodologia
O simulador foi integralmente desenvolvido na linguagem de programação Java, operando com uma arquitetura que separa a lógica de negócio da interface visual. A Interface Gráfica foi construída com o pacote nativo `javax.swing`, projetada com um design moderno escuro. O programa recebe via interface uma sequência de números inteiros representando as chamadas de página, além da capacidade de frames na memória. O sistema processa as requisições iterando sobre estruturas de dados específicas: filas (`Queue`) para o FIFO, listas dinâmicas para o LRU e arrays com booleanos de controlo para o algoritmo do Relógio.

##  Resultados e Discussão
Após submeter o simulador a sequências de teste (como a cadeia referencial `7 0 1 2 0 3 0 4 2 3 0 3 2 1 2 0 1 7 0 1` com 3 frames de memória), observou-se claramente as disparidades entre as abordagens. Como esperado teoricamente, o método FIFO apresentou a maior quantidade de faltas de página por não considerar o princípio da localidade de referência. O LRU e o algoritmo do Relógio apresentaram taxas de *page fault* competitivas. 

Abaixo encontra-se o gráfico comparativo gerado pela interface de testes demonstrando a eficiência de cada método:

> 🖼️ **<img width="686" height="495" alt="image" src="https://github.com/user-attachments/assets/1a6897a6-0263-41e5-b1e2-d4eaaf64752f" />
**
##  Conclusão
O desenvolvimento do simulador cumpriu o seu objetivo pedagógico e técnico, ilustrando com exatidão matemática o impacto das políticas de substituição de memória. Com os resultados obtidos através da representação gráfica, consolida-se o entendimento de que algoritmos puramente temporais de chegada (como o FIFO) são subótimos, enquanto estratégias focadas no histórico recente de uso (LRU e Relógio) entregam uma relação custo-benefício excelente para arquiteturas reais de computadores.

## Referências
SILBERSCHATZ, A.; GALVIN, P. B.; GAGNE, G. Sistemas Operacionais com Java. 7. ed. Rio de Janeiro: Elsevier, 2008.
---

## 💻 Como Executar o Projeto

**Pré-requisitos:**
* Java Development Kit (JDK) 8 ou superior instalado.
* Editor de código (ex: Visual Studio Code com extensão "Extension Pack for Java").

### 2. Preparação do Ambiente
1. Clone este repositório:
   ```bash
   git clone  https://github.com/JoaoPauloDel/simulador-pagina.git
   ```
2. Abra a pasta do projeto no **VS Code**.
3. Certifique-se de que os três ficheiros (`Main.java`, `SimuladorAlgoritmos.java`, `InterfaceGrafica.java`) estão localizados na mesma diretoria raiz.

   ### 3. Compilação e Execução (Terminal)
Abra o terminal integrado do VS Code (`Ctrl` + `'`) e execute os seguintes comandos:
**Compilar todos os ficheiros:**
```bash
javac *.java
```
**Executar o programa:**
```bash
java Main
```

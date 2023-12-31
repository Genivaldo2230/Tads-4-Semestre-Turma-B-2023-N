# Tads-4-Semestre-Turma-B-2023-N
Series e filmes (Spring boot)
Autor: Genivaldo Alves dos Anjos



Documentação do Código em Spring Boot
Este documento fornece uma breve explicação do código em Spring Boot apresentado, descrevendo a estrutura e as principais funcionalidades. O código inclui um controlador (SeriesController), um modelo (Series), um repositório (SeriesRepository), e uma classe para exibição de filmes a partir de um banco de dados H2 (ExibirFilmesDto).

1. SeriesController
   O SeriesController é responsável por manipular as requisições HTTP relacionadas às séries.

mostrarPaginaInicial
Mapeia o endpoint "/" para a página "index".
mostrarPaginaIndex
Mapeia o endpoint "/index" para a página "listarSeries".
mostrarPaginaHtmlIndex
Mapeia o endpoint "/index.html" para a página "index".
mostrarFormularioAdicionarSeries
Mapeia o endpoint "/addSeries" para exibir o formulário de adição de séries.
adicionarFilme
Mapeia o endpoint "/adicionarFilme" para adicionar uma série ao banco de dados.
Salva a série no repositório e redireciona para a página "listarSeries" com uma mensagem de sucesso.
listarSeries
Mapeia o endpoint "/listarSeries" para exibir a lista de séries.
voltarParaIndexs
Mapeia o endpoint "/voltarParaIndexs" para redirecionar para a página "index".
paginaComVideo
Mapeia o endpoint "/paginaComVideo" para exibir a página "listarSeries".
2. ExibirFilmesDto
   A classe ExibirFilmesDto é um exemplo que realiza uma consulta SQL no banco de dados H2 e exibe os resultados em uma tabela HTML.

3. Series
   A classe Series representa a entidade de séries no banco de dados.

A anotação @Entity indica que esta classe é uma entidade JPA.
Os campos da classe correspondem às colunas da tabela "series".
A anotação @Id especifica a chave primária.
A anotação @GeneratedValue indica que a chave primária é gerada automaticamente.
4. SeriesRepository
   A interface SeriesRepository estende JpaRepository e fornece métodos para operações no banco de dados relacionadas às séries.

Considerações Finais
Este código implementa um aplicativo simples de Spring Boot para gerenciar informações sobre séries. Certifique-se de que o banco de dados H2 esteja configurado corretamente e que as dependências do Spring Boot estejam no arquivo pom.xml (ou build.gradle). Adapte o código conforme necessário para atender aos requisitos específicos do seu projeto.

Tabela do Banco de Dados - series
A tabela series é criada automaticamente pelo Spring Data JPA, baseada na entidade Series. Aqui está a representação da tabela no banco de dados:


CREATE TABLE series (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
titulo VARCHAR(255),
genero VARCHAR(255),
ano_lancamento INT
);
id (Tipo: BIGINT)

Identificador único para cada série.
Esta é a chave primária da tabela.
titulo (Tipo: VARCHAR(255))

Armazena o título da série.
Este campo representa o título da série e pode conter até 255 caracteres.
genero (Tipo: VARCHAR(255))

Armazena o gênero da série.
Este campo representa o gênero da série e pode conter até 255 caracteres.
ano_lancamento (Tipo: INT)

Armazena o ano de lançamento da série.
Este campo representa o ano de lançamento da série.
Observações:

A tabela series é criada automaticamente pelo Spring Data JPA durante a inicialização do aplicativo.
Os tipos de dados e limites de caracteres podem ser ajustados conforme necessário com base nos requisitos específicos do projeto.
Certifique-se de que o banco de dados H2 esteja configurado corretamente no arquivo application.properties ou application.yml do projeto Spring Boot. Ao iniciar o aplicativo, o Hibernate, que é a implementação do JPA usada pelo Spring Data JPA, se encarregará de criar a tabela no banco de dados configurado.

Aqui estão os elementos essenciais dessa explicação:

Banco de dados H2:

O H2 é um banco de dados relacional em memória que é frequentemente usado para desenvolvimento e teste de aplicativos Java. Ele pode ser facilmente integrado em aplicativos Spring Boot para proporcionar uma experiência de desenvolvimento mais ágil.
Arquivo application.properties ou application.yml:

O Spring Boot usa arquivos de propriedades (.properties) ou arquivos YAML (.yml) para configurar diferentes aspectos do aplicativo. Esses arquivos geralmente estão localizados no diretório src/main/resources do projeto.
Hibernate e JPA:

O Hibernate é uma estrutura de mapeamento objeto-relacional (ORM) que facilita a comunicação entre um banco de dados relacional e um aplicativo Java. O Spring Data JPA é uma camada de abstração sobre o Hibernate, facilitando ainda mais o trabalho com banco de dados usando JPA (Java Persistence API).
Configuração do H2 no arquivo application.properties ou application.yml:

No arquivo de configuração do aplicativo (application.properties ou application.yml), é necessário configurar as propriedades específicas do H2. Isso inclui informações como URL de conexão, nome de usuário, senha, etc.
Criação automática de tabelas pelo Hibernate:

Quando o aplicativo Spring Boot é iniciado, o Hibernate lê as configurações do banco de dados e verifica se as tabelas necessárias já existem. Se as tabelas não existirem, o Hibernate cria automaticamente as tabelas com base nas entidades JPA definidas no código (por exemplo, a classe Series).
Em resumo, essa instrução destaca a importância de configurar corretamente o banco de dados H2 no arquivo de configuração do aplicativo e explica que, ao iniciar o aplicativo, o Hibernate cuidará da criação automática das tabelas no banco de dados configurado, conforme definido pelas entidades JPA no código. Isso é particularmente útil para o desenvolvimento e teste de aplicativos, eliminando a necessidade de criar manualmente o esquema do banco de dados durante o ciclo de desenvolvimento.


Aqui estão os pontos-chave destacados pela instrução:

Configuração Adequada do Banco de Dados H2:

Antes de iniciar o aplicativo Spring Boot, é necessário configurar corretamente as propriedades do banco de dados H2 no arquivo de configuração (application.properties ou application.yml). Essas configurações incluem informações como URL de conexão, nome de usuário, senha e outras propriedades específicas do H2.
Hibernate como Implementação JPA:

O Hibernate é uma implementação da Java Persistence API (JPA), que é uma especificação para o mapeamento objeto-relacional em Java. O Hibernate simplifica a interação com o banco de dados, permitindo que as entidades JPA sejam representações diretas das tabelas do banco de dados.
Criação Automática de Tabelas:

Ao iniciar o aplicativo, o Hibernate examina as entidades JPA definidas no código (por exemplo, a classe Series) e verifica se as tabelas correspondentes já existem no banco de dados configurado. Se as tabelas não existirem, o Hibernate gera automaticamente as instruções SQL necessárias para criar as tabelas.
Utilidade para Desenvolvimento e Teste:

Essa abordagem é especialmente útil durante o desenvolvimento e teste de aplicativos. Elimina a necessidade de criar manualmente o esquema do banco de dados, simplificando o processo e permitindo que os desenvolvedores se concentrem na lógica do aplicativo em vez de tarefas administrativas relacionadas ao banco de dados.
Agilidade no Ciclo de Desenvolvimento:

Ao automatizar a criação de tabelas, o ciclo de desenvolvimento se torna mais ágil. Os desenvolvedores podem iterar rapidamente sobre o código e as alterações no modelo de dados refletirão automaticamente na estrutura do banco de dados.
Em resumo, essa abordagem facilita o desenvolvimento e o teste de aplicativos, proporcionando uma maneira eficiente e automatizada de gerenciar o esquema do banco de dados durante o ciclo de desenvolvimento.


 # Documentação Técnica: Tratamento de Erro com Spring Framework

Este documento técnico aborda o tratamento de erro no contexto de uma aplicação Spring Framework, com foco específico em lidar com exceções da classe ConstraintViolationException. A classe GlobalExceptionHandler é responsável por capturar e tratar essas exceções de forma centralizada, proporcionando uma resposta consistente em casos de violação de restrições de validação.

1. Introdução
   O tratamento de erro é uma parte essencial do desenvolvimento de software, garantindo que a aplicação possa lidar com falhas de maneira adequada e fornecer mensagens de erro úteis para os usuários. No caso de aplicações Spring, o uso de @ControllerAdvice e @ExceptionHandler permite a centralização do tratamento de exceções em um único local.

2. Configuração do Controlador de Exceções
   A classe GlobalExceptionHandler é anotada com @ControllerAdvice, indicando que é um controlador global de exceções. O método adicionarFilmes é marcado com @ExceptionHandler(ConstraintViolationException.class), especificando que este método deve ser invocado quando ocorrer uma exceção do tipo ConstraintViolationException.

3. Parâmetros do Método
   O método adicionarFilmes aceita três parâmetros:

ConstraintViolationException e: Representa a exceção capturada, que contém informações sobre as violações de restrição de validação.

@ModelAttribute HttpStatus.Series series: Permite a obtenção da série HTTP da resposta. No entanto, neste exemplo, não parece ser utilizado diretamente.

Model model: Objeto do Spring que permite adicionar atributos para serem usados na resposta da página.

4. Tratamento da Exceção
   Dentro do método, as violações de restrição são recuperadas da exceção e transformadas em uma lista de mensagens de erro. Essas mensagens são então adicionadas ao modelo (Model) para serem exibidas na camada de apresentação.

5. Comentários no Código
   O código contém um comentário relacionado a evitar a persistência no banco de dados se houver erros de validação. Esse comentário sugere uma estratégia de tratamento para evitar a persistência de dados inválidos no banco.


// Evita salvar no banco se houver erros de validação
// return "errorPage";
return "adicionarFilme";
O código atualmente está configurado para redirecionar para a página "adicionarFilme" em caso de violações de restrição. O comentário fornece uma sugestão de retorno para uma possível página de erro, indicando que a persistência no banco de dados deve ser evitada se houver erros de validação.

6. Conclusão
   Este documento fornece uma visão técnica do tratamento de erro usando Spring Framework, destacando a classe GlobalExceptionHandler e seu método adicionarFilmes. A estrutura permite um tratamento centralizado de exceções relacionadas a violações de restrição de validação, melhorando a manutenção e a consistência na resposta a erros em toda a aplicação.









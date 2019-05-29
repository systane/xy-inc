# DOCUMENTAÇÃO DO PROJETO

## Arquitetura
Para o desafio proposto foi desenvolvida uma arquitetura utilizando o Spring Cloud Netflix em conjunto com outros componentes, o que resultou em 4 microserviços que foram dockerizados, sendo eles:
    
- **ConfigServer**: Projeto elaborado para atuar como um servidor de arquivos de configuração para os demais microserviços implementados. Nesse [repositorio](https://github.com/systane/xy-inc-config-files) é possível encontrar todos os arquivos utilizados no teste.

- **DiscoveryServer**: Serviço responsável por registrar instancias e manter uma lista com a situação dos mesmos e assim conseguir realizar a "descoberta" dos serviços disponíveis. O projeto utiliza o componente Eureka Netflix do Spring Cloud.

- **Api**: Foi construída para atender os 3 serviços solicitados no desafio e também para se comunicar diretamenta com o MongoDB ([mlab](https://mlab.com)).

- **Gateway**: Projeto desenvolvido utilizando o Zuul do Spring Cloud Netflix. A finalidade desse serviço é atuar como proxy sendo a interface entre o mundo externo e a rede projetada na arquitetura.

<p >
  <img src="architectureDiagram.png" width="600"/> 
</p>

## Estrutura do Banco

Pensando em atender ao requisito de extensibilidade foi utilizado o [mlab](https://mlab.com) (um DBaaS que permite autoscaling). Assim, a seguinte modelagem foi definida para o projeto:

    name: String required,
    x: int required,
    y: int required

A URI de conexão para o banco principal: mongodb://admin:admin123@ds261626.mlab.com:61626/xy-inc

<br/>

## Descrição da API
A API pode ser acessada a partir do gateway que está configurado no docker para expor a porta **8765**.

- **Serviço de cadastro de POI**: Para cadastrar um novo POI basta acessar http://localhost:8765/api/poi e enviar no body da request um objeto JSON, por exemplo:

        {
            "name": "Zup de Uberlandia",
            "x": 152,
            "y": 123
        }

- **Serviço para listar todos POIs**: A partir de http://localhost:8765/api/poi é possível realizar a listagem de todos os POIs cadastrados na api.

- **Serviço para listar todos POIs por proximidade**: Para usar esse serviço basta acessar por exemplo, http://localhost:8765/api/poi/filter?x=20&y=10&d=10 no qual os parametros x e y são os valores referentes as coordenadas x e y do ponto de referencia e d a distância máxima do filtro.

<br/>

## Resumo das Rotas da API
| Método | URL  | Comportamento realizado                              | 
|--------|------|------------------------------------------------------|
| GET    | /api/poi     | Listagem de todos POIs             | 
| GET    | /api/poi/filter?x=20&y=10&d=10 | Recupera uma lista de POIs filtrados pela proximidade   | 
| POST   | /api/poi     | Insere um novo POI                     | 
| GET    | /api/swagger-ui.html | Acessa a documentação da API via Swagger| 
| GET  | /configServer/{fileName}/default | Retorna o configuration file correspondente  | 
            

**Obs:** O docker contendo o Eureka está exposto na porta 9090, caso seja necessário consultar os status dos serviços basta acessar: http://localhost:9090/
<br/>

## Executando o Projeto
Todos os 4 serviços foram dockerizados e a partir do docker-compose é possível iniciar o projeto seguindo os seguintes passos:

1. Verificar se o Docker e o docker-compose estão instalados:

        docker -v
        docker-compose -v

1. Entrar na pasta raiz do projeto na qual se encontra o docker-compose file e executar o comando para construir as imagens dos containers:

        docker-compose build

1. Após o build das imagens basta executar:

        docker-compose up


1. Para rodar os testes é necessário abrir a IDE e executa-los com o junit.
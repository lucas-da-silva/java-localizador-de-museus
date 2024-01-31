<h1 align="center">Localizador de Museus</h1>

## Sobre o projeto

API Rest desenvolvida para facilitar a busca por museus baseada na localização. Os dados foram retirados [desta](http://dados.cultura.gov.br/dataset/series-historicas-cadastro-nacional-de-museus) série histórica. Projeto utiliza o padrão DTO e está divido em 3 camadas: controller, service e database.

## API Endpoints

#### Retornar museu

```http
  GET /museums/{id}
```

#### Retornar museu mais próximo

```http
  GET /museums/closest/
```

| Parâmetro     | Tipo     | Descrição                            |
| :------------ | :------- | :----------------------------------- |
| `lat`         | `string` | **Required**. Latitude               |
| `lng`         | `string` | **Required**. Logintude              |
| `max_dist_km` | `string` | **Required**. Distância máxima em KM |

#### Cadastrar um novo museu

```http
  POST /museums
```

| Parâmetro        | Tipo                    | Descrição                                      |
| :--------------- | :---------------------- | :--------------------------------------------- |
| `name`           | `string`                | **Required**. Nome do banco                    |
| `description`    | `string`                | **Required**. Descrição do banco               |
| `address`        | `string`                | **Required**. Endereço do banco                |
| `collectionType` | `string`                | **Required**. Tipos de coleção                 |
| `subject`        | `string`                | **Required**. Assunto                          |
| `url`            | `string`                | **Required**. Site do banco                    |
| `coordinate`     | `{latitude, logintude}` | **Required**. Coordenadas latitude e logintude |

#### Contar o número de museus com certo tipo de coleção

```http
  GET /collections/count/{typesList}
```

- Para contar múltiplas coleções, utilize `,` entre elas. Exemplo: `hist,imag`.

## Tecnologias utilizadas

- [Java](https://www.java.com/pt-BR/) - Linguagem de programação orientada a objetos;
- [Spring](https://spring.io/) - Framework Java;
- [Maven](https://maven.apache.org/) - Gerenciador de dependências;
- [JUnit](https://junit.org/junit5/) - Framework para testes unitários.

## Como rodar

```bash
# Clonar Projeto
$ git clone git@github.com:lucas-da-silva/java-localizador-de-museus.git

# Entrar no diretório
$ cd java-localizador-de-museus

# Criar imagem
$ docker build -t localizador-de-museus .

# Executando container 
$ docker run --name api-localizador-de-museus -p 8080:8080 localizador-de-museus
```

## Autor

- [@lucas-da-silva](https://github.com/lucas-da-silva)

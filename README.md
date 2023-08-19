
# CosmosPizza API

Bem vindo(a)! 
#
Essa documentação tem como objetivo auxiliar no uso, implementação e edição da CosmosPizza, uma simples API que permite todos os métodos (**GET, POST, PUT, DELETE**) para os dados de uma pizzaria.


## Documentação da API
Usaremos como exemplo o atributo 'sabor'. Para o uso dos demais atributos, é necessário apenas a alteração de seu nome seguindo a estrutura abaixo:
#### Retorna uma lista de todos os sabores.

```http
  GET /api/sabor/todos
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `atributo/todos` | `string` | **Obrigatório**. O nome do atributo que você deseja retornar a lista. |

#### Retorna um item

```http
  GET /api/sabor/?id={id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `integer` | **Obrigatório**. O ID do item que você quer |

#### Cadastra um item

```http
  Post /api/pessoa/cadastrar
```
Exemplo de requisição POST, no atributo 'pessoas':

    
  
    
        "nome": "José",
        "cpf": "46065149063",
        "telefone": "45999999",
        "endereco": {
            "id": 3
        },
        "tipoPessoa": "CLIENTE"
    

#
Quando o corpo da requisição possui outro objeto, é necessário o preenchimento somente do id do objeto para o sucesso no cadastro. 

No atributo "tipoPessoa", é necessário preencher com um dos dois tipos de pessoa presentes no sistema: **CLIENTE ou FUNCIONARIO**.

#### Edita um item

```http
  PUT /api/pessoa/editar?id={id}
```
Exemplo de requisição PUT, no atributo 'pessoas':

    
  
        "id" : 1,
        "nome": "Henrique",
        "cpf": "46065149063",
        "telefone": "45999999",
        "endereco": {
            "id": 3
        },
        "tipoPessoa": "CLIENTE"
    

#
| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `/editar?id={id}`      | `integer` | **Obrigatório**. O ID do item que você quer editar.|

Para fazer a edição, é necessário que o id do parâmetro seja idêntico ao item que você deseja editar. No exemplo acima, alteramos o nome "José" para "Henrique".

#### Deleta um item.

```http
  DELETE /api/sabor/deletar?id={id}
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `/deletar?id={id}` | `integer` | **Obrigatório**. O nome do atributo que você deseja deletar |


## Retornos

Sempre que um retorno for bem sucedido, o corpo da requisição realizada será retornada no campo Response de sua ferramenta. 

Retorno de uma lista do atributo 'pessoa': 

```
[
    {
        "id": 2,
        "nome": "José",
        "cpf": "46065149063",
        "telefone": "45999999",
        "endereco": {
            "id": 1,
            "logradouro": "Songa-funda",
            "numero": 179,
            "complemento": "abc",
            "bairro": "Bubas",
            "cep": "85851-010"
        },
        "tipoPessoa": "CLIENTE"
    }
]
```
Alguns códigos de erro:




| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `400` | `application/json	` |	{"error": "Bad Request", "message": "Validation error", "status": 400}
| `500` | `application/json` |  {"error": "Internal Server Error", "message": "An unexpected error occurred.", "status": 500}
| `503` | `application/json` |  {"error": "Service Unavailable", "message": "The service is currently unavailable.", "status": 503}


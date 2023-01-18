# REST

## O que é?

- Representational state of transfer
- Surgiu em 2000 por Roy Fielding em uma disertação de doutorado
- Simplicidade (Estabelece comunicação pelo próprio HTTTP de forma padronizada)
- Stateless (Sem guardar estado)
- Cacheável

## Níveis de maturidade (Rochardson Maturity Model)

- Nível 0: The Swamp of POX (Não tem padronização)
- Nível 1: Utilização de resources
- Nível 2: Verbos HTTP
- Nível 3: HATEOAS -> Hypermedia as the Engine of Application State (API auto explicável, conceito de Hypermidia, link que apontam para outros recursos)

## Uma boa API REST

- Utiliza URIs únicas para serviços e itens que expostos para esses serviços
- Utiliza todos os verbos HTTP para realizar as operações em seus recursos, inclunido caching
- Provê links relacionais para os recursos exemplificando o que pode ser feito

## HTTP Method Negotiation

- HTTP possui um outro método `OPTION` que permite informar quais métodos são permitidos ou não em um determinado recurso

```http
    OPTIONS /api/produtct HTTP/1.1
    Host: api.com.br
```

- A resposta pdoe ser

```http
    HTTP:/1.1 200 OK
    Allow: GET, POST
```

## Content Negotiation

- Baseado na requisição que o client está fazendo para o server. Nesse caso, ele solicita o que e como ele quer a resposta. O server então retornará ou não a informação no formato desejado

```http
    GET /product
    Accept: application/json
```

- Resposta pode ser o retorno dos dados ou

```http
    HTTP/1.1 406 Not Acceptable
```

- Através content-type no header da request, o servidor consegue verificar se ele irá conseguir processar a informação para retornar a informação desejada

```http
    POST /product HTTP/1.1
    Accept: application/json
    Content-Type: application/json

    {
        "name": "Product 1"
    }
```

- Caso o servidor não aceite o content type ele retornará o erro

## Protocol Buffers

- É um projeto open source. Uma linguagem criada de forma neutra com mecanismo de extensebilidade e serialização
- Trabalha com arquivos binários. Menores do que JSON
- Processo de serialização é mais leve (CPU) do que JSON
- Gasta menos recursos de rede
- Processo é mais veloz

## REST x gRPC

### Rest

- Texto / Json
- Unidirecional
- Alta latência
- Sem contrato (maior chance de erros)
- Sem suporte a streaming
- Design pré-definido
- Bibliotecas de terceiro

### gRPC

- Protocol Buffers
- Bidirecional e Assíncrono
- Baixa latência
- Contrato definido (.proto)
- Suporte a streaming
- Design é livre
- Geração de código
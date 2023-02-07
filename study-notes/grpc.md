# gRPC

- Um framework desenvolvido pela google que tem objetivo facilitar o processo de comunicação entre sistemas de uma forma extramemente rápida, leve, independente de linguagem

- Faz parte da CNCF (Cloud Native Computing Foundation)

## Em quais casos podemos utilizar?

- Ideal para microsserviços
- Mobile, Browsers e backend
- Geração das bibliotecas de forma automática
- Streaming bidirecional utilizando HTTP/2

## Suporte oficial das linguagens

- Go
- Java
- C

## Definição de serviços

- gRPC utiliza o protocol buffers para definir a interface de serviços
- O arquivo possui a extenção .proto
- É definido os parâmetros de entrada e saida. Após a criação do arquivo, o plugin gRPC oficial é usado para gerar código do arquivo proto
- Exemplo de arquivo .proto

```proto
syntax = "proto3";

package ecommerce;

service ProductInfo {
  rpc addProduct(Product) returns (ProductID);
  rpc getProduct(ProductID) returns (Product);
}

message Product {
  string id = 1;
  string name = 2;
  string description = 3;
}

message ProductID {
  string value = 1;
}
```
## Servidor gRPC

- Com a definição da interface de serviço no arquivo .proto podemos gerar código gRPC do lado do servidor, do cliente bem como o código de buffer do protocolo
- O servidor gRPC implementa o serviço e atende as chamadas dos clientes

- Exemplo da implementação do serviço
```golang
import (
  ...
  "context"
  pb "github.com/grpc-up-and-running/samples/ch02/productinfo/go/proto"
  "google.golang.org/grpc"
  ...
)
// ProductInfo implementation with Go

// Add product remote method
func (s *server) AddProduct(ctx context.Context, in *pb.Product) (
*pb.ProductID, error) {
  // business logic
}

// Get product remote method
func (s *server) GetProduct(ctx context.Context, in *pb.ProductID) (
*pb.Product, error) {
  // business logic
}
```

- Executando o servidor

```golang
func main() {
  lis, _ := net.Listen("tcp", port)
  s := grpc.NewServer()
  pb.RegisterProductInfoServer(s, &server{})
  if err := s.Serve(lis); err != nil {
    log.Fatalf("failed to serve: %v", err)
  }
}
```

## Cliente gRPC

- Simliar ao servidor gRPC porém aqui é gerado o stub usando a definição de serviço
- O stub do cliente traduz as funções de chamadas que vão para o servidor
- Exemplo de cliente gRPC usando Java

```java
// Create a channel using remote server address
ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
  .usePlaintext(true)
  .build();
// Initialize blocking stub using the channel
ProductInfoGrpc.ProductInfoBlockingStub stub =
ProductInfoGrpc.newBlockingStub(channel);
// Call remote method using the blocking stub
StringValue productID = stub.addProduct(
Product.newBuilder()
  .setName("Apple iPhone 11")
  .setDescription("Meet Apple iPhone 11." +
  "All-new dual-camera system with " +
  "Ultra Wide and Night mode.")
  .build());
```

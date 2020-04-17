# POCAkka

Projeto para estudo de programação concorrente orientada a atores, utilizando Akka com SpringBoot, e serialização pelo protocolo ProtoBuf.
O sistema usa comunicação remota, e implementa o conceito de supervisor com informações de nível para gerar os atores.

Iniciar primeiro o ActorSystemPong, em seguida ActorSystemPing


- ActorSystemPing cria o actor SupervisorPing, que recebe uma mensagem inicial com informação de nível,
avalia o nível e delega o processamento da mensagem para o ActorPing responsável.
- ActorPing processa a mensagem e envia a resposta para o segundo sistema (ActorSystemPong).
- ActorSystemPong cria o ActorSupervisorPong, que recebe a mensagem e delega para o ActorPong responsável pelo processamento.
- ActorPong processa e envia a responsta para o SupervisorPing.

mensagem.proto foi compilada com o arquivo protoc.exe, e executada pelo Terminal da IDEA

comando:
dentro da pasta do mensagem.proto

/Users/exemplo.user/protoc-3.11.4-win64/bin/protoc --java_out=.. mensagem.proto

"/Users/exemplo.user/protoc-3.11.4-win64/bin/protoc"   caminho do protoc.exe

".. mensagem.proto" destino da pasta protobuf a ser criada e nome do arquivo.proto a ser executado.


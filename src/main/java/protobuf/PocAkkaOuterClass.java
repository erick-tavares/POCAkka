// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mensagem.proto

package protobuf;

public final class PocAkkaOuterClass {
  private PocAkkaOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_serializacao_PingMensagem_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_serializacao_PingMensagem_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_serializacao_PongMensagem_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_serializacao_PongMensagem_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_serializacao_ErroMensagem_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_serializacao_ErroMensagem_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016mensagem.proto\022\014serializacao\"D\n\014PingMe" +
      "nsagem\022\020\n\010mensagem\030\001 \002(\t\022\"\n\005nivel\030\002 \002(\0162" +
      "\023.serializacao.Nivel\"D\n\014PongMensagem\022\020\n\010" +
      "mensagem\030\001 \002(\t\022\"\n\005nivel\030\002 \002(\0162\023.serializ" +
      "acao.Nivel\" \n\014ErroMensagem\022\020\n\010mensagem\030\001" +
      " \002(\t*(\n\005Nivel\022\t\n\005BAIXO\020\000\022\n\n\006NORMAL\020\001\022\010\n\004" +
      "ALTO\020\002B\037\n\010protobufB\021PocAkkaOuterClassP\001"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_serializacao_PingMensagem_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_serializacao_PingMensagem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_serializacao_PingMensagem_descriptor,
        new java.lang.String[] { "Mensagem", "Nivel", });
    internal_static_serializacao_PongMensagem_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_serializacao_PongMensagem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_serializacao_PongMensagem_descriptor,
        new java.lang.String[] { "Mensagem", "Nivel", });
    internal_static_serializacao_ErroMensagem_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_serializacao_ErroMensagem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_serializacao_ErroMensagem_descriptor,
        new java.lang.String[] { "Mensagem", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

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
    internal_static_serializacao_MailBox_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_serializacao_MailBox_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\016mensagem.proto\022\014serializacao\" \n\014PingMe" +
      "nsagem\022\020\n\010mensagem\030\001 \001(\t\" \n\014PongMensagem" +
      "\022\020\n\010mensagem\030\001 \001(\t\"]\n\007MailBox\022(\n\004ping\030\001 " +
      "\001(\0132\032.serializacao.PingMensagem\022(\n\004pong\030" +
      "\002 \001(\0132\032.serializacao.PongMensagemB\037\n\010pro" +
      "tobufB\021PocAkkaOuterClassP\001b\006proto3"
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
        new java.lang.String[] { "Mensagem", });
    internal_static_serializacao_PongMensagem_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_serializacao_PongMensagem_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_serializacao_PongMensagem_descriptor,
        new java.lang.String[] { "Mensagem", });
    internal_static_serializacao_MailBox_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_serializacao_MailBox_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_serializacao_MailBox_descriptor,
        new java.lang.String[] { "Ping", "Pong", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}

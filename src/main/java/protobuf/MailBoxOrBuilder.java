// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: mensagem.proto

package protobuf;

public interface MailBoxOrBuilder extends
    // @@protoc_insertion_point(interface_extends:serializacao.MailBox)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>.serializacao.PingMensagem ping = 1;</code>
   * @return Whether the ping field is set.
   */
  boolean hasPing();
  /**
   * <code>.serializacao.PingMensagem ping = 1;</code>
   * @return The ping.
   */
  protobuf.PingMensagem getPing();
  /**
   * <code>.serializacao.PingMensagem ping = 1;</code>
   */
  protobuf.PingMensagemOrBuilder getPingOrBuilder();

  /**
   * <code>.serializacao.PongMensagem pong = 2;</code>
   * @return Whether the pong field is set.
   */
  boolean hasPong();
  /**
   * <code>.serializacao.PongMensagem pong = 2;</code>
   * @return The pong.
   */
  protobuf.PongMensagem getPong();
  /**
   * <code>.serializacao.PongMensagem pong = 2;</code>
   */
  protobuf.PongMensagemOrBuilder getPongOrBuilder();
}

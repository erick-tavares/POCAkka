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

  /**
   * <code>.serializacao.FilhoMensagem filho = 3;</code>
   * @return Whether the filho field is set.
   */
  boolean hasFilho();
  /**
   * <code>.serializacao.FilhoMensagem filho = 3;</code>
   * @return The filho.
   */
  protobuf.FilhoMensagem getFilho();
  /**
   * <code>.serializacao.FilhoMensagem filho = 3;</code>
   */
  protobuf.FilhoMensagemOrBuilder getFilhoOrBuilder();

  /**
   * <code>.serializacao.ErroMensagem erro = 4;</code>
   * @return Whether the erro field is set.
   */
  boolean hasErro();
  /**
   * <code>.serializacao.ErroMensagem erro = 4;</code>
   * @return The erro.
   */
  protobuf.ErroMensagem getErro();
  /**
   * <code>.serializacao.ErroMensagem erro = 4;</code>
   */
  protobuf.ErroMensagemOrBuilder getErroOrBuilder();
}

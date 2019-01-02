package org.gooru.ds.user.exceptions;

import org.gooru.ds.user.responses.MessageResponse;

/**
 * @author ashish on 10/1/18.
 */
public class MessageResponseWrapperException extends RuntimeException {
  private final MessageResponse response;

  public MessageResponseWrapperException(MessageResponse response) {
    this.response = response;
  }

  public MessageResponse getMessageResponse() {
    return this.response;
  }
}

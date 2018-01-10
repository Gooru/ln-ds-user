package org.gooru.ds.user.processor;

import org.gooru.ds.user.responses.MessageResponse;

import io.vertx.core.Future;

/**
 * @author ashish on 10/1/18.
 */
public interface MessageProcessor {

    Future<MessageResponse> process();

}

package org.gooru.ds.user.responses.auth;

/**
 * @author ashish on 10/1/18.
 */

public interface AuthResponseHolder {
    boolean isAuthorized();

    boolean isAnonymous();

    String getUser();
}

package org.gooru.ds.user.constants;

public enum ExecutionStatus {

    SUCCESSFUL(true), FAILED(false);

    private boolean status;

    ExecutionStatus(boolean status) {
        this.status = status;
    }

    public boolean isSuccessFul() {
        return this.status;
    }


}

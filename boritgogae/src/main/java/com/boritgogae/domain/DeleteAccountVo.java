package com.boritgogae.domain;

import java.sql.Timestamp;

public class DeleteAccountVo {
    private String memberId;
    private String deleteWhyMemo;
    private Timestamp deleteDate;
    private int deleteCode;
    
    public DeleteAccountVo() {
        super();
    }

    public DeleteAccountVo(String memberId, String deleteWhyMemo, Timestamp deleteDate, int deleteCode) {
        super();
        this.memberId = memberId;
        this.deleteWhyMemo = deleteWhyMemo;
        this.deleteDate = deleteDate;
        this.deleteCode = deleteCode;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getDeleteWhyMemo() {
        return deleteWhyMemo;
    }

    public void setDeleteWhyMemo(String deleteWhyMemo) {
        this.deleteWhyMemo = deleteWhyMemo;
    }

    public Timestamp getDeleteDate() {
        return deleteDate;
    }

    public void setDeleteDate(Timestamp deleteDate) {
        this.deleteDate = deleteDate;
    }

    public int getDeleteCode() {
        return deleteCode;
    }

    public void setDeleteCode(int deleteCode) {
        this.deleteCode = deleteCode;
    }

    @Override
    public String toString() {
        return "DeleteAccountVo [memberId=" + memberId + ", deleteWhyMemo=" + deleteWhyMemo + ", deleteDate="
                + deleteDate + ", deleteCode=" + deleteCode + "]";
    }
    
    
}

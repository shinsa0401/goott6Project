package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

import com.fasterxml.jackson.annotation.JsonFormat;

public class QuestionReplyVo {
   private int rno;
   private int bno;
   private String replyWriter;
   private String replyContent;
   @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
   private Timestamp replyWrittenDate;
   private int ref;
   private int step;
   private int refOrder;
   
   public QuestionReplyVo() {
      super();
   }
   
   public QuestionReplyVo(int rno, int bno, String replyWriter, String replyContent, Timestamp replyWrittenDate, int ref,
         int step, int refOrder) {
      super();
      this.rno = rno;
      this.bno = bno;
      this.replyWriter = replyWriter;
      this.replyContent = replyContent;
      this.replyWrittenDate = replyWrittenDate;
      this.ref = ref;
      this.step = step;
      this.refOrder = refOrder;
   }
   
   public int getRno() {
      return rno;
   }
   public void setRno(int rno) {
      this.rno = rno;
   }
   public int getBno() {
      return bno;
   }
   public void setBno(int bno) {
      this.bno = bno;
   }
   public String getReplyWriter() {
      return replyWriter;
   }
   public void setReplyWriter(String replyWriter) {
      this.replyWriter = replyWriter;
   }
   public String getReplyContent() {
      return replyContent;
   }
   public void setReplyContent(String replyContent) {
      this.replyContent = replyContent;
   }
   public Timestamp getReplyWrittenDate() {
      return replyWrittenDate;
   }
   public void setReplyWrittenDate(Timestamp replyWrittenDate) {
      this.replyWrittenDate = replyWrittenDate;
   }
   public int getRef() {
      return ref;
   }
   public void setRef(int ref) {
      this.ref = ref;
   }
   public int getStep() {
      return step;
   }
   public void setStep(int step) {
      this.step = step;
   }
   public int getRefOrder() {
      return refOrder;
   }
   public void setRefOrder(int refOrder) {
      this.refOrder = refOrder;
   }
   
   @Override
   public String toString() {
      return "QuestionReplyVo [rno=" + rno + ", bno=" + bno + ", replyWriter=" + replyWriter + ", replyContent="
            + replyContent + ", replyWrittenDate=" + replyWrittenDate + ", ref=" + ref + ", step=" + step
            + ", refOrder=" + refOrder + "]";
   }
   
   
}
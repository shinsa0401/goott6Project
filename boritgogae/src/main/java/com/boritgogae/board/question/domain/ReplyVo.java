package com.boritgogae.board.question.domain;

import java.sql.Timestamp;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonFormat;

=======
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
public class ReplyVo {
	private int rno;
	private int bno;
	private String replyWriter;
<<<<<<< HEAD
	private String replyContent;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private Timestamp replyWrittenDate;
	private int ref;
	private int step;
	private int refOrder;
=======
	private String replyPwd;
	private String replyContent;
	private Timestamp replyWrittenDate;
	private Timestamp replyUpdateDate;
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
	
	public ReplyVo() {
		super();
	}
	
<<<<<<< HEAD
	public ReplyVo(int rno, int bno, String replyWriter, String replyContent, Timestamp replyWrittenDate, int ref,
			int step, int refOrder) {
=======
	public ReplyVo(int rno, int bno, String replyWriter, String replyPwd, String replyContent,
			Timestamp replyWrittenDate, Timestamp replyUpdateDate) {
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
		super();
		this.rno = rno;
		this.bno = bno;
		this.replyWriter = replyWriter;
<<<<<<< HEAD
		this.replyContent = replyContent;
		this.replyWrittenDate = replyWrittenDate;
		this.ref = ref;
		this.step = step;
		this.refOrder = refOrder;
=======
		this.replyPwd = replyPwd;
		this.replyContent = replyContent;
		this.replyWrittenDate = replyWrittenDate;
		this.replyUpdateDate = replyUpdateDate;
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
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
<<<<<<< HEAD
=======
	public String getReplyPwd() {
		return replyPwd;
	}
	public void setReplyPwd(String replyPwd) {
		this.replyPwd = replyPwd;
	}
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
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
<<<<<<< HEAD
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
=======
	public Timestamp getReplyUpdateDate() {
		return replyUpdateDate;
	}
	public void setReplyUpdateDate(Timestamp replyUpdateDate) {
		this.replyUpdateDate = replyUpdateDate;
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
	}
	
	@Override
	public String toString() {
<<<<<<< HEAD
		return "ReplyVo [rno=" + rno + ", bno=" + bno + ", replyWriter=" + replyWriter + ", replyContent="
				+ replyContent + ", replyWrittenDate=" + replyWrittenDate + ", ref=" + ref + ", step=" + step
				+ ", refOrder=" + refOrder + "]";
	}
	
	
=======
		return "ReplyVo [rno=" + rno + ", bno=" + bno + ", replyWriter=" + replyWriter + ", replyPwd=" + replyPwd
				+ ", replyContent=" + replyContent + ", replyWrittenDate=" + replyWrittenDate + ", replyUpdateDate="
				+ replyUpdateDate + "]";
	}
	
>>>>>>> 8bda4a0bd2fad767ac598b6becc4a3474dd23044
}

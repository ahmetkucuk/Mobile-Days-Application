package com.bilkentieee.mobilgunler;

import com.bilkentieee.mobilgunler.R;
import java.io.Serializable;

public class Oturum implements Serializable{
	
	private String time;
	public String getDay() {
		return day;
	}
	public void setDay(String day) {
		this.day = day;
	}
	public String getSpeaker() {
		return Speaker;
	}
	public void setSpeaker(String speaker) {
		Speaker = speaker;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	private String day;
	private String Speaker;
	private String subject;
	private int paralel;
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getParalel() {
		return paralel;
	}
	public void setParalel(int paralel) {
		this.paralel = paralel;
	}

}

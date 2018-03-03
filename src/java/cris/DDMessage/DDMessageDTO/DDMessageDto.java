package cris.DDMessage.DDMessageDTO;

import java.io.Serializable;

public class DDMessageDto implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	   private String Receipient;
	   private String SupervisorId;
	   private long PRRNo=0L;
	   private long PWBNo=0L;
	   private String FromStation;
	   private String ToStation;
	   private String Subject;
	   private String Message;
	   private String ImgUpload;
	   
	   private String SendDate;
	   private String SenderID;
	   
	   
	   
	
	public long getPRRNo() {
		return PRRNo;
	}
	public void setPRRNo(long no) {
		PRRNo = no;
	}
	public long getPWBNo() {
		return PWBNo;
	}
	public void setPWBNo(long no) {
		PWBNo = no;
	}
	public String getReceipient() {
		return Receipient;
	}
	public void setReceipient(String receipient) {
		Receipient = receipient;
	}
	public String getSupervisorId() {
		return SupervisorId;
	}
	public void setSupervisorId(String supervisorId) {
		SupervisorId = supervisorId;
	}
	
	public String getFromStation() {
		return FromStation;
	}
	public void setFromStation(String fromStation) {
		FromStation = fromStation;
	}
	public String getToStation() {
		return ToStation;
	}
	public void setToStation(String toStation) {
		ToStation = toStation;
	}
	public String getSubject() {
		return Subject;
	}
	public void setSubject(String subject) {
		Subject = subject;
	}
	public String getMessage() {
		return Message;
	}
	public void setMessage(String message) {
		Message = message;
	}
	public String getImgUpload() {
		return ImgUpload;
	}
	public void setImgUpload(String imgUpload) {
		ImgUpload = imgUpload;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getSendDate() {
		return SendDate;
	}
	public void setSendDate(String sendDate) {
		SendDate = sendDate;
	}
	public String getSenderID() {
		return SenderID;
	}
	public void setSenderID(String senderID) {
		SenderID = senderID;
	}
	   
	   
   
}

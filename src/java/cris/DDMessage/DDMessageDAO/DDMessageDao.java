package cris.DDMessage.DDMessageDAO;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cris.DDMessage.DDMessageDTO.DDMessageDto;



public interface DDMessageDao 
{
	public String  SaveDDMessage(DDMessageDto dto);
	public String sendMail(DDMessageDto ddDto);
	public List<String> getStationList();
	public List<DDMessageDto> getDDMessageSaved(DDMessageDto ddMDto);
	/*
	public DDMessageDto setDDMessageToDto(HttpServletRequest request); 
	public List<DDMessageDto> getSupervisorID();*/
}

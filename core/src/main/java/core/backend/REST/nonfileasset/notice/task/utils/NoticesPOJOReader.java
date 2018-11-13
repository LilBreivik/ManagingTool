package core.backend.REST.nonfileasset.notice.task.utils;

import java.util.List;
import java.util.stream.Collectors;

import resources.components.elements.POJO.Notices.NoticesPOJO;
import resources.components.filehandler.JSON.general.GeneralJSONFileHandler;

public class NoticesPOJOReader {
 
	public static  List<NoticesPOJO>  GetAllNoticesPOJOs(GeneralJSONFileHandler noticeJSONFileHandler) {
		   
		return  noticeJSONFileHandler.getPathManager().getAllFilesOnPath()
							.stream().map(f -> f.getName())
								.map(fileName -> (NoticesPOJO) noticeJSONFileHandler.readFile(fileName) ) 
									.collect(Collectors.toList()); 
	}
}

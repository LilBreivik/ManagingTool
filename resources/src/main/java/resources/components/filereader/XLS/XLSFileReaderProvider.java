package resources.components.filereader.XLS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import resources.constraint.impl.LectureConstraint;
import resources.fileconnection.XLSFileConnection; 

@Configuration
public class XLSFileReaderProvider {


	@Autowired 
	private XLSFileConnection m_xlsFileConnection; 
	
	@Autowired
	private LectureConstraint m_LectureConstraint; 
	
	@Bean
	@Qualifier("XLSFileReader for LectureScheduleFile")
	public XLSFileReader provideXLSFileReaderForLectureScheduleFile( ){
		
		return new  GeneralXLSFileReader (m_xlsFileConnection, m_LectureConstraint);
	}
 
}
 
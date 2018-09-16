package resources.error.download;

import java.io.File;

import resources.error.FileIsMissingError;

public class AssetFileIsMissing
							extends FileIsMissingError{

	private String m_assetFileName;
	
	public AssetFileIsMissing(String errorMessage) {
		super(errorMessage); 
	}

	public String getAssetFileName() {
		return m_assetFileName;
	}

	public void setAssetFileName(String string) {
		this.m_assetFileName = string;
	}
 
}

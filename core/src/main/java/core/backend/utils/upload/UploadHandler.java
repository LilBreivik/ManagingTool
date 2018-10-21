package core.backend.utils.upload;

@FunctionalInterface
public interface UploadHandler<PersistentPOJO> {

	public PersistentPOJO handleUploadedFile(String fileName);
}

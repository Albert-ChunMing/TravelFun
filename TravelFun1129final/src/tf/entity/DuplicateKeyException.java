package tf.entity;

import tf.entity.VGBException;

public class DuplicateKeyException extends VGBException {
    private final String keyColumnName;

	public String getKeyColumnName() {
		return keyColumnName;
	}

	public DuplicateKeyException(String keyColumnName) {
		super();
		this.keyColumnName = keyColumnName;
	}

	public DuplicateKeyException(String message, Throwable cause, String keyColumnName) {
		super(message, cause);
		this.keyColumnName = keyColumnName;
	}

	public DuplicateKeyException(String message, String keyColumnName) {
		super(message);
		this.keyColumnName = keyColumnName;
	}

}
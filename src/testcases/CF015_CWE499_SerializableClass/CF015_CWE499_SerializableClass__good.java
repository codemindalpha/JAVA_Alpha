package testcases.CF014_CWE498_CloneableClass;

public class CF015_CWE499_SerializableClass__good {
	 		
	public class ReadClass {
        public byte[] serialize() {
		Book book = new Book(1, "AAA", "BBB", "CCC");

		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try (bos; ObjectOutputStream oos = new ObjectOutputStream(bos)) {
			oos.writeObject(book);
		} catch (Exception e) {
			// ... 
		}
		return bos.toByteArray();
	}

	public static void main(String[] args) {
		ReadClass cls = new ReadClass();
		byte[] serializedData = cls.serialize();
	}
	}
	
	public class Book implements Serializable {
	private String title;
	private String pubName;
	private String reporterName;

	private final void writeObject(ObjectOutputStream objOutputStream) throws java.io.IOException{
        throw new java.io.IOException("직렬화할 수 없음"); 
	}
	}
		
}

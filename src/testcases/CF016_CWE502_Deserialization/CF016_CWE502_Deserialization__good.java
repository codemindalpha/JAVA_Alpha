package testcases.CF014_CWE498_CloneableClass;

public class CF014_CWE498_CloneableClass__simple {
	 		
	public class ReadClass {
        public byte[] serialize() {
		// serialize Data
	}

	public Book deserialize(byte[] serializedData) {
		ByteArrayInputStream bis = new ByteArrayInputStream(serializedData);
		try (bis; ObjectInputStream ois = new ObjectInputStream(bis)) {
			Object obj = ois.readObject();
			return (Book)obj;
		} catch (Exception e) {
			// ... 
		}
		return null;
	}

	public static void main(String[] args) {
		ReadClass cls = new ReadClass();
		byte[] serializedData = cls.serialize();
		Book book = cls.deserialize(serializedData);
		System.out.println(book); 
	}
	}
	
	public class Book implements Serializable {
	private String title;
	private String pubName;
	private String reporterName;

	private final void readObj(ObjectInputStream objInputStream) throws java.io.IOException{
        throw new java.io.IOException("역직렬화할 수 없음"); 
	}


	}
		
}

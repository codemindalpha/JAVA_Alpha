package CF022_RESATTEMPTMORE__CWE1341;

import java.io.FileInputStream;
import java.io.FileOutputStream;

public class RESATTEMPTMORE_GOOD {
    public void INPUTSTREAM_GOOD(){
        String filePath = "src/FileExam.java";
        FileInputStream fileStream = null;
        int i = 0;

        try
        {
            fileStream = new FileInputStream(filePath);

            do
            {
                i = fileStream.read();
                System.out.print((char) i);
            }
            while(i != -1);

        }
        catch (Exception e)
        {
            System.out.println("file input error" + e);
        }
        finally
        {
            try
            {
                fileStream.close();
            }
            catch (Exception e)
            {
                System.out.println("close error" + e);
            }
        }
    }
    public void OUTPUTSTREAM_GOOD(){
        String filePath = "src/FileExam.java";
        FileOutputStream fileStream = null;
        String data = "DATA";
        byte[] dataBytes = data.getBytes();

        try
        {
            fileStream = new FileOutputStream(filePath);
            fileStream.write(dataBytes);

        }
        catch (Exception e)
        {
            System.out.println("file output error" + e);
        }
        finally
        {
            try
            {
                fileStream.close();
            }
            catch (Exception e)
            {
                System.out.println("close error" + e);
            }
        }
    }
}

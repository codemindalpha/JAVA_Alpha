package testcases.C7005_BADDESERIAL__CWE502;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

@Controller
public class C7005_BADDESERIAL_CWE502__bad {
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Student bad(@RequestParam("file") MultipartFile multipartFile) throws ClassNotFoundException, IOException {
        Student student = null;
        File targerFile = new File("/temp/" + multipartFile.getOriginalFilename());
        try (InputStream fileStream = multipartFile.getInputStream()) {
            try (ObjectInputStream ois = new ObjectInputStream(fileStream)) {
                //외부 입력값 multipartFile에서 가져온 직렬화된 바이트 값으로 readObject을 호출
                /* FLAW: CWE-502 */
                student = (Student) ois.readObject();
            }
        }
        return student;
    }

    public class Student {
        private String name;
        private int age;
        private String info;

        public Student(String name, int age, String info){
            this.name = name;
            this.age = age;
            this.info = info;
        }
    }

}

package testcases.C7005_BADDESERIAL__CWE502;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Controller
public class C7005_BADDESERIAL_CWE502__good {

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public Student good(@RequestParam("file") MultipartFile multipartFile) throws ClassNotFoundException, IOException {
        Student student = null;
        File targerFile = new File("/temp/" + multipartFile.getOriginalFilename());
        // 열직렬화 대상 클래스 이름의 화이트리스트 생성
        Set<String> whitelist = new HashSet<>(Arrays.asList(
                new String[] {
                        "Student"
                }
        ));
        try (InputStream fileStream = multipartFile.getInputStream()) {
            try (WhitelistedObjectInputStream ois = new WhitelistedObjectInputStream(fileStream, whitelist)) {
                // 화이트리스트에 없는 역직렬화 데이터의 경우 예외 발생
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

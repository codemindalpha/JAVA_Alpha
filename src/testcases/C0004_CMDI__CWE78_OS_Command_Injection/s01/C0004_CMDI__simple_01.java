package testcases.C0004_CMDI__CWE78_OS_Command_Injection.s01;

import testcasesupport.IO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class C0004_CMDI__simple_01 {

    public void bad(String args[]) throws IOException {

        if (args.length == 0) {
            System.err.println("실행할 프로그램 명을 입력하세요.");
            return;
        }

        String cmd = args[0];
        Process ps = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            /* FLAW : CWE-78 */
            ps = Runtime.getRuntime().exec(cmd);
            is = ps.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            if (br != null) br.close();
            if (isr != null) isr.close();
            if (is != null) is.close();
        }

    }

    public void good(String args[]) throws IOException {

        // 해당 어플리케이션에서 실행할 수 있는 프로그램을 노트패드와 계산기로 제한하고 있다.
        List<String> allowedCommands = new ArrayList<>();
        allowedCommands.add("notepad");
        allowedCommands.add("calc");
        if (args.length == 0) {
            System.err.println("실행할 프로그램 명을 입력하세요.");
            return;
        }

        String cmd = args[0];
        if (!allowedCommands.contains(cmd)) {
            System.err.println("허용되지 않은 명령어입니다.");
            return;
        }

        Process ps = null;
        InputStream is = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        try {
            ps = Runtime.getRuntime().exec(cmd);
            is = ps.getInputStream();
            isr = new InputStreamReader(is);
            br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            IO.logger.log(Level.WARNING, e.toString());
        } finally {
            if (br != null) br.close();
            if (isr != null) isr.close();
            if (is != null) is.close();
        }
    }
}

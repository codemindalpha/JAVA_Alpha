package testcases.C7005_BADDESERIAL__CWE502;

import java.io.*;
import java.util.Set;

public class WhitelistedObjectInputStream extends ObjectInputStream {
    public Set<String> whitelist;

    // WhitelistedObjectInputStream 생성할 때 화이트리스트를 입력받는다.
    public WhitelistedObjectInputStream(InputStream in, Set<String> wl) throws IOException {
        super(in);
        whitelist = wl;
    }

    @Override
    protected Class<?> resolveClass(ObjectStreamClass cls) throws IOException, ClassNotFoundException {
        // ObjectStreamClass의 클래스명이 화이트리스트에 있는지 확인
        if (!whitelist.contains(cls.getName())){
            throw new InvalidClassException("Unexpected serialized class", cls.getName());
        }
        return super.resolveClass(cls);
    }
}

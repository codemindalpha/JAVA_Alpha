package CF002_CHKERRPAG__CWE7;

import org.omg.CORBA.Any;
import org.omg.CORBA.Object;
import org.omg.CORBA.TypeCode;
import org.omg.CORBA.portable.ApplicationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.log4j.*;
import org.omg.CORBA.portable.InputStream;
public class CHKERRPAG_BAD{
    public class postEx {

        Logger logger = Logger.getLogger(postEx.class.getName());

        public void postEx(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            try {
                //...
                InputStream input = new InputStream() {
                    @Override
                    public boolean read_boolean() {
                        return false;
                    }

                    @Override
                    public char read_char() {
                        return 0;
                    }

                    @Override
                    public char read_wchar() {
                        return 0;
                    }

                    @Override
                    public byte read_octet() {
                        return 0;
                    }

                    @Override
                    public short read_short() {
                        return 0;
                    }

                    @Override
                    public short read_ushort() {
                        return 0;
                    }

                    @Override
                    public int read_long() {
                        return 0;
                    }

                    @Override
                    public int read_ulong() {
                        return 0;
                    }

                    @Override
                    public long read_longlong() {
                        return 0;
                    }

                    @Override
                    public long read_ulonglong() {
                        return 0;
                    }

                    @Override
                    public float read_float() {
                        return 0;
                    }

                    @Override
                    public double read_double() {
                        return 0;
                    }

                    @Override
                    public String read_string() {
                        return null;
                    }

                    @Override
                    public String read_wstring() {
                        return null;
                    }

                    @Override
                    public void read_boolean_array(boolean[] value, int offset, int length) {

                    }

                    @Override
                    public void read_char_array(char[] value, int offset, int length) {

                    }

                    @Override
                    public void read_wchar_array(char[] value, int offset, int length) {

                    }

                    @Override
                    public void read_octet_array(byte[] value, int offset, int length) {

                    }

                    @Override
                    public void read_short_array(short[] value, int offset, int length) {

                    }

                    @Override
                    public void read_ushort_array(short[] value, int offset, int length) {

                    }

                    @Override
                    public void read_long_array(int[] value, int offset, int length) {

                    }

                    @Override
                    public void read_ulong_array(int[] value, int offset, int length) {

                    }

                    @Override
                    public void read_longlong_array(long[] value, int offset, int length) {

                    }

                    @Override
                    public void read_ulonglong_array(long[] value, int offset, int length) {

                    }

                    @Override
                    public void read_float_array(float[] value, int offset, int length) {

                    }

                    @Override
                    public void read_double_array(double[] value, int offset, int length) {

                    }

                    @Override
                    public Object read_Object() {
                        return null;
                    }

                    @Override
                    public TypeCode read_TypeCode() {
                        return null;
                    }

                    @Override
                    public Any read_any() {
                        return null;
                    }
                };
                throw new ApplicationException("Application 예외", input);
            }
            catch (ApplicationException ase) {
                logger.error("Caught: " + ase.toString());
            }
        }

    }
}


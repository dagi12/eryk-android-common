package pl.edu.amu.wmi.erykandroidcommon.test;

/**
 * @author Eryk Mariankowski <eryk.mariankowski@247.codes> on 23.01.18.
 */
public class TestNonStatic {

    public String nonStaticMethod() {
        return "hello world";
    }

    public String throwMethod() {
        throw new RuntimeException();
    }

}

package itdr;

import org.junit.Test;

public class Test2 {
    @Test
    public void t(){
        String path="/login.do";
        String n=path.replace(".","/");
        System.out.println(n);
        String[] s = n.split("/");
        System.out.println(s[1]);
    }
}

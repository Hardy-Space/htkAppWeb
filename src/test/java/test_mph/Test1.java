package test_mph;

import org.junit.Test;

/**
 * Created by Administrator on 2017/12/28.
 */
public class Test1 {

    @Test
    public void name() {

        test01();
    }

    public void test01(){
        A aaa = new A(null,"baby");
    }

    public class A{

        int a ;

        String b ;

        public A(Integer a,String b){
            this.a = a;
            this.b = b;
        }
    }

}

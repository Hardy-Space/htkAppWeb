package test_mph;

import org.junit.Test;

/**
 * Created by Administrator on 2017/12/28.
 */
public class Test1 {

    @Test
    public void name() {

//        test01();
        String data = "alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017052607358206&biz_content=%7B%22body%22%3A%22%E5%9B%9E%E5%A4%B4%E5%AE%A2app%E6%94%AF%E4%BB%98%E5%AE%9D%E6%94%AF%E4%BB%98%E5%A4%96%E5%8D%96%E6%94%AF%E4%BB%98%22%2C%22out_trade_no%22%3A%221712316724113029%22%2C%22product_code%22%3A%22QUICK_TAKEOUT_PAY%22%2C%22subject%22%3A%22%E5%A4%96%E5%8D%96%E6%94%AF%E4%BB%98%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%22-0.98%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2F120.27.5.36%3A8080%2FhtkApp%2FAPI%2FpaymentInterfaceAPI%2FaliPayNotifyInterface&sign=ADDfpQs8VQ8F1qfGloHqrF5PVeE24L2Pipz75IxxmI9fCd%2B%2BGQM0JqK%2BsWBr2W7vNolZ%2FLcywGcJNTjtpBsYD%2Fu%2FM3pFFN92Q8okjkDeFLJCFLzJK6MfiFcfFyeeud0VEMxEF67oVymgFqDXgIxBm0yQeHcFvu5wrEkcv4QYBNi9hNW0Kj7NhIC%2BhRHoH85tu7ZCLsHLcHsq1JhomOMAlQkof1XoUDkAbS0eB4%2B3a8q1mPSTjEIhYFI6eH4UHYOJCclcsgsl3VCpXYlthMcPWgNqx7lVYpiu%2B%2BCNWEnhS1AtqqCa5Tuk0No4qPBKBxBTRMjtsApfOsfpoR6lkGjOjg%3D%3D&sign_type=RSA&timestamp=2017-12-31+18%3A40%3A41&version=1.0";
        String[] result = data.split("&");
        for (String d :
                result) {
            System.out.println(d);
        }

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

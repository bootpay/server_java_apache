package kr.co.bootpay.javaApache;

import com.google.gson.Gson;
import kr.co.bootpay.javaApache.model.request.Cancel;
import kr.co.bootpay.javaApache.model.request.SubscribeBilling;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

/**
 * Created by ehowlsla on 2017. 8. 17..
 */
public class Test {
    static BootpayApi api;
    public static void main(String[] args) {
        api = new BootpayApi("59bfc738e13f337dbd6ca48a", "FQj3jOvQYp053nxzWxHSuw+cq3zUlSWZV2ec/8fkiyA=");
        goGetToken();
        goVerfity();
        goCancel();
        goSubscribeBilling();
    }

    public static void goGetToken() {
        try {
            api.getAccessToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goVerfity() {
        try {
            HttpResponse res = api.verify("593f8febe13f332431a8ddae");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goCancel() {
        Cancel cancel = new Cancel();
        cancel.receipt_id = "593f8febe13f332431a8ddae";
        cancel.name = "관리자 홍길동";
        cancel.reason = "택배 지연에 의한 구매자 취소요청";

        try {
            HttpResponse res = api.cancel(cancel);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goSubscribeBilling() {
        SubscribeBilling subscribeBilling = new SubscribeBilling();
        subscribeBilling.billing_key = "5b025b33e13f33310ce560fb";
        subscribeBilling.item_name = "정기결제 테스트 아이템";
        subscribeBilling.price = 3000;
        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000);



        try {
            HttpResponse res = api.subscribe_billing(subscribeBilling);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
            System.out.println(new Gson().toJson(subscribeBilling));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

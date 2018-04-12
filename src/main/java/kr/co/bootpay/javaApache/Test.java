package kr.co.bootpay.javaApache;

import kr.co.bootpay.javaApache.model.request.Cancel;
import kr.co.bootpay.javaApache.model.request.Confirm;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;

/**
 * Created by ehowlsla on 2017. 8. 17..
 */
public class Test {
    public static void main(String[] args) {

        goConfirm();
        goCancel();
    }

    public static void goConfirm() {
        BootpayApi api = new BootpayApi("application_id_value_1234", "593f8febe13f332431a8ddaw");

        Confirm confirm = new Confirm();
        confirm.receipt_id = "593f8febe13f332431a8ddae";

        try {
            HttpResponse res = api.confirm(confirm);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goCancel() {
        BootpayApi api = new BootpayApi("application_id_value_1234", "593f8febe13f332431a8ddaw");

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
}

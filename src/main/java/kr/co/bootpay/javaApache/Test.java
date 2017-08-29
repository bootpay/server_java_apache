package kr.co.bootpay.javaApache;

import kr.co.bootpay.javaApache.model.request.Cancel;
import kr.co.bootpay.javaApache.model.request.Confirm;
import org.apache.http.HttpResponse;

/**
 * Created by ehowlsla on 2017. 8. 17..
 */
public class Test {
    public static void main(String[] args) {

        goConfirm();
    }

    public static void goConfirm() {
        BootpayApi api = new BootpayApi("application_id_value_1234", "593f8febe13f332431a8ddaw");

        Confirm confirm = new Confirm();
        confirm.receipt_id = "593f8febe13f332431a8ddae";

        try {
            api.confirm(confirm);
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
            api.cancel(cancel);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

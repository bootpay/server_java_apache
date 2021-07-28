package kr.co.bootpay.javaApache;

import kr.co.bootpay.javaApache.model.request.*;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;


public class Test {
    static Bootpay bootpay;
    public static void main(String[] args) {
        bootpay = new Bootpay("5b8f6a4d396fa665fdc2b5ea", "rm6EYECr6aroQVG2ntW0A6LpWnkTgP4uQ3H18sDDUYw=");
//        bootpay.getAccessToken();
//        api = new BootpayApi("5b8f6a4d396fa665fdc2b5ea", "rm6EYECr6aroQVG2ntW0A6LpWnkTgP4uQ3H18sDDUYw=");
        goGetToken();
//        getBillingKey();
//        requestSubscribe();
//        reserveSubscribe();
//        destroyBillingKey();
//        reserveCancelSubscribe();
//        receiptCancel();
//        getUserToken();
//        requestLink();
//        submit();
//        goVerfity();
//        certificate();
    }

    public static void goGetToken() {
        try {
            HttpResponse res = bootpay.getAccessToken();
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getBillingKey() {
        Subscribe subscribeBilling = new Subscribe();
        subscribeBilling.itemName = "정기결제 테스트 아이템";
        subscribeBilling.orderId = "" + (System.currentTimeMillis() / 1000);
        subscribeBilling.pg = "nicepay";
//        subscribeBilling.cardNo = "5570**********1074"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
//        subscribeBilling.cardPw = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
//        subscribeBilling.expireYear = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
//        subscribeBilling.expireMonth = "**"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
//        subscribeBilling.identifyNumber = ""; //주민등록번호
        subscribeBilling.cardNo = "5570420456641074"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribeBilling.cardPw = "83"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribeBilling.expireYear = "26"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribeBilling.expireMonth = "12"; //실제 테스트시에는 *** 마스크처리가 아닌 숫자여야 함
        subscribeBilling.identifyNumber = "8610141038021"; //주민등록번호

        try {
            HttpResponse res = bootpay.getBillingKey(subscribeBilling);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void destroyBillingKey() {
        try {
            HttpResponse res = bootpay.destroyBillingKey("6100e7ea0d681b001fd4de69");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestSubscribe() {
        SubscribePayload payload = new SubscribePayload();
        payload.billingKey = "6100e8c80d681b001dd4e0d7";
        payload.itemName = "아이템01";
        payload.price = 1000;
        payload.orderId = "" + (System.currentTimeMillis() / 1000);


        try {
            HttpResponse res = bootpay.requestSubscribe(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reserveSubscribe() {
        SubscribePayload payload = new SubscribePayload();

        payload.billingKey = "6100e77a0d681b002ad4e5d9";
        payload.itemName = "아이템01";
        payload.price = 1000;
        payload.orderId = "" + (System.currentTimeMillis() / 1000);
        payload.executeAt = (System.currentTimeMillis() / 1000) + 10000;

        try {
            HttpResponse res = bootpay.reserveSubscribe(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void reserveCancelSubscribe() {
        try {
            HttpResponse res = bootpay.reserveCancelSubscribe("6100e892019943002150fef3");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void receiptCancel() {
        Cancel cancel = new Cancel();
        cancel.receiptId = "6100e77a019943003650f4d5";
        cancel.name = "관리자";
        cancel.reason = "테스트 결제";

//        String receipt_id = "";
        try {
            HttpResponse res = bootpay.receiptCancel(cancel);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void getUserToken() {
        UserToken userToken = new UserToken();
        try {
            HttpResponse res = bootpay.getUserToken(userToken);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void requestLink() {
        Payload payload = new Payload();
        try {
            HttpResponse res = bootpay.requestLink(payload);
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void submit() {
        try {
            HttpResponse res = bootpay.submit("");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void goVerfity() {
        try {
            HttpResponse res = bootpay.verify("6100e8e7019943003850f9b0");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void certificate() {
        try {
            HttpResponse res = bootpay.certificate("593f8febe13f332431a8ddae");
            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
            System.out.println(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//    public static void goCancel() {
//        Cancel cancel = new Cancel();
//        cancel.receiptId = "593f8febe13f332431a8ddae";
//        cancel.name = "관리자 홍길동";
//        cancel.reason = "택배 지연에 의한 구매자 취소요청";
//
//        try {
//            HttpResponse res = bootpay.receiptCancel(cancel);
//            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//            System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    public static void goSubscribeBilling() {
//        Subscribe subscribeBilling = new SubscribeBilling();
//        subscribeBilling.billing_key = "5b025b33e13f33310ce560fb";
//        subscribeBilling.item_name = "정기결제 테스트 아이템";
//        subscribeBilling.price = 3000;
//        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000);
//
//
//        try {
//            HttpResponse res = api.subscribe_billing(subscribeBilling);
//            String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//            System.out.println(str);
//            System.out.println(new Gson().toJson(subscribeBilling));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

//    public static void goRemoteForm() {
//        RemoteForm form = new RemoteForm();
//        form.pg = "danal";
//        form.fm = Arrays.asList("card", "phone");
//        form.n = "테스트 결제";
//        form.o_key = "unique_value_1234"; // 가맹점의 상품 고유 키
//        form.is_r_n = false; // 구매자가 상품명 입력 허용할지 말지
//        form.is_r_p = false; // 구매자가 가격 입력 허용할지 말지
//        form.is_addr = false; // 주소창 추가 할지 말지
//        form.is_da = false; // 배송비 추가 할지 말지
//        form.is_memo = false;  // 구매자로부터 메모를 받을 지
//        form.tfp = 0d; // 비과세 금액
//        form.ip = 10000d; // 아이템 판매금액
//        form.dp = 0d; // 디스플레이용 가격, 할인전 가격을 의미함, 쿠폰이나 프로모션에 의한 가격 디스카운트 개념 필요 - 페이코 때문에 생긴 개념
//        form.dap = 0d;  // 기본배송비
//        form.dap_jj = 0d; // 제주 배송비
//        form.dap_njj = 0d; // 제주 외 지역 도서산간 추가비용
//
//
//       try {
//        HttpResponse res = api.remote_form(form);
//        String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
//        System.out.println(str);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}

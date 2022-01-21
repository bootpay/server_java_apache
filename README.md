
## PG Analytics - 결제데이터 분석서비스
* 기존 PG 사를 이용 중이신 사업자도 별도의 계약없이 부트페이를 통해 결제 연동과 통계를 무료로 이용하실 수 있습니다.
* 한줄의 소스코드로 인사이트를 얻어 매출을 극대화하세요.



## 결제 검증 및 취소 - 서버사이드용
* 보안상의 이유로 결제검증과 취소는 서버사이드에서 이루어집니다.
* 부트페이 서버와 통신시 Rest용 Application Id, Private Key 값을 보내주셔야 하며, 보내실 서버의 IP는 미리 등록하셔야 합니다.

## gradle로 설치 
```gradle
compile group: 'org.apache.httpcomponents', name: 'httpclient', version: '+'
compile group: 'com.google.code.gson', name: 'gson', version: '+'
```

## 혹은 maven으로 설치 
```xml
<dependency>
    <groupId>org.apache.httpcomponents</groupId>
    <artifactId>httpclient</artifactId>
    <version>LATEST</version>
</dependency>
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>LATEST</version>
</dependency>
```

## 샘플 코드
```java 
public class Test {
    static BootpayApi api;
    public static void main(String[] args) {
        api = new BootpayApi("59bfc738e13f337dbd6ca48a", "FQj3jOvQYp053nxzWxHSuw+cq3zUlSWZV2ec/8fkiyA=");  // application_id, private key 
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
        subscribeBilling.order_id = "" + (System.currentTimeMillis() / 1000); // 고객사에서 관리하는 주문번호로, 고유값으로 생성 후 부트페이에 전달해주셔야합니다 



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

```

### 더 자세한 정보는 [Docs](https://docs.bootpay.co.kr/rest/verify)를 참조해주세요. 

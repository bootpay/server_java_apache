package kr.co.bootpay.javaApache.model.request;

/**
 * Created by ehowlsla on 2018. 5. 29..
 */
public class SubscribeBilling {
    public String billing_key;
    public String item_name;
    public long price;
    public String order_id;
    public String pg;

    public String card_no;
    public String card_pw;
    public String expire_month;
    public String identify_number;
}

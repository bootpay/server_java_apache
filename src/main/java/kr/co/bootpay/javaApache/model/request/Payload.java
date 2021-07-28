package kr.co.bootpay.javaApache.model.request;

import java.util.List;

public class Payload {
    public String pg;
    public String method;
    public List<String> methods;
    public long price;
    public String orderId;
    public String params;
    public int taxFree;
    public String name;
    public User userInfo;
    public List<Item> items;
    public String returnUrl;
    public Extra extra;
}

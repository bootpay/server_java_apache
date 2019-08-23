package kr.co.bootpay.javaApache.model.request;

import java.util.ArrayList;
import java.util.List;

public class RemoteForm {
    public String m_id = "";
    public String pg = "";
    public List<String> fm = new ArrayList<>();
    public Double tfp = 0.0;
    public String n = "";
    public String cn = "";
    public Double ip = 0.0;
    public Double dp = 0.0;
    public Double dap = 0.0;

    public boolean is_r_n = false;
    public boolean is_r_p = false;
    public boolean is_addr = false;
    public boolean is_da = false;
    public boolean is_memo = false;
    public String desc_html = "";
    public Double dap_jj = 0.0;
    public Double dap_njj = 0.0;
    public String o_key = "";
}

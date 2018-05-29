package kr.co.bootpay.javaApache;

import com.google.gson.Gson;
import kr.co.bootpay.javaApache.model.request.Cancel;
import kr.co.bootpay.javaApache.model.request.SubscribeBilling;
import kr.co.bootpay.javaApache.model.request.Token;
import kr.co.bootpay.javaApache.model.response.ResToken;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;
import java.util.List;

/**
 * Created by ehowlsla on 2018. 5. 29..
 */
public class BootpayApi {

    private final String BASE_URL = "https://api.bootpay.co.kr/";
    private final String URL_ACCESS_TOKEN = BASE_URL + "request/token.json";
    private final String URL_VERIFY = BASE_URL + "receipt";
    private final String URL_CANCEL = BASE_URL + "cancel.json";
    private final String URL_SUBSCRIBE_BILLING = BASE_URL + "subscribe/billing.json";

    private String token;
    private String application_id;
    private String private_key;

    public BootpayApi() {}
    public BootpayApi(String rest_application_id, String private_key) {
        this.application_id = rest_application_id;
        this.private_key = private_key;
    }

    public void setToken(String token) {
        this.token = token;
    }

    private HttpGet getGet(String url) throws Exception {
        HttpGet get = new HttpGet(url);
        URI uri = new URIBuilder(get.getURI()).build();
        get.setURI(uri);
        return get;
    }

    private HttpGet getGet(String url, List<NameValuePair> nameValuePairList) throws Exception {
        HttpGet get = new HttpGet(url);
        get.setHeader("Accept", "application/json");
        get.setHeader("Content-Type", "application/json");
        get.setHeader("Accept-Charset", "utf-8");
        URI uri = new URIBuilder(get.getURI()).addParameters(nameValuePairList).build();
        get.setURI(uri);
        return get;
    }

    private HttpPost getPost(String url, StringEntity entity) {
        HttpPost post = new HttpPost(url);
        post.setHeader("Accept", "application/json");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("Accept-Charset", "utf-8");
        post.setEntity(entity);
        return post;
    }

    public void getAccessToken() throws Exception {
        if(this.application_id == null || this.application_id.isEmpty()) throw new Exception("application_id 값이 비어있습니다.");
        if(this.private_key == null || this.private_key.isEmpty()) throw new Exception("private_key 값이 비어있습니다.");

        Token token = new Token();
        token.application_id = this.application_id;
        token.private_key = this.private_key;

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = getPost(URL_ACCESS_TOKEN, new StringEntity(new Gson().toJson(token), "UTF-8"));

        HttpResponse res = client.execute(post);
        String str = IOUtils.toString(res.getEntity().getContent(), "UTF-8");
        ResToken resToken = new Gson().fromJson(str, ResToken.class);

        if(resToken.status == 200)
            this.token = resToken.data.token;
    }

    public HttpResponse verify(String receipt_id) throws Exception {
        if(this.token == null || this.token.isEmpty()) throw new Exception("token 값이 비어있습니다.");

        HttpClient client = HttpClientBuilder.create().build();
        HttpGet get = getGet(URL_VERIFY + "/" + receipt_id + ".json");
        get.setHeader("Authorization", this.token);
        return client.execute(get);
    }

    public HttpResponse cancel(Cancel cancel) throws Exception {
        if(this.token == null || this.token.isEmpty()) throw new Exception("token 값이 비어있습니다.");

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = getPost(URL_CANCEL, new StringEntity(new Gson().toJson(cancel), "UTF-8"));
        post.setHeader("Authorization", this.token);
        return client.execute(post);
    }

    public HttpResponse subscribe_billing(SubscribeBilling subscribeBilling) throws Exception {
        if(this.token == null || this.token.isEmpty()) throw new Exception("token 값이 비어있습니다.");

        HttpClient client = HttpClientBuilder.create().build();
        HttpPost post = getPost(URL_SUBSCRIBE_BILLING, new StringEntity(new Gson().toJson(subscribeBilling), "UTF-8"));
        post.setHeader("Authorization", this.token);
        return client.execute(post);
    }

}

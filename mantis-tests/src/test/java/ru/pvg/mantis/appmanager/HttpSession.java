package ru.pvg.mantis.appmanager;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/*
   Created Владимир  at 18:21  14.06.2019
*/
public class HttpSession {
  private CloseableHttpClient httpClient;
  private ApplicationManager app;

  public HttpSession(ApplicationManager app) {
    this.app = app ;
    // при вызове создается новый клиент - сессия по протоколу http
    httpClient = HttpClients.custom().setRedirectStrategy(new LaxRedirectStrategy()).build();
    }

    public boolean login (String username, String password) throws IOException {

/*
      // Для сайта Addressbook
      // создаем post запрос
      HttpPost post = new HttpPost(app.getProperty("web.baseUrl"));
      List<NameValuePair> params = new ArrayList<>();
//      String log = String.format("user=%s", username)+"&"+String.format("pass=%s", password);
//      params.add(new BasicNameValuePair(log));
      params.add(new BasicNameValuePair("username", username));
      params.add(new BasicNameValuePair("password", password));
      params.add(new BasicNameValuePair("secure_session", "on"));
      params.add(new BasicNameValuePair("return", ""));
      post.setEntity(new UrlEncodedFormEntity(params));
      //отправляем запрос и получаем ответ
      CloseableHttpResponse response = httpClient.execute(post);
      String body = getTextFrom(response);
      //проверяем - действительно ли пользователь зашел в приложение
      return body.contains(String.format("<b>(%s)</b>", username));
*/

      HttpPost post = new HttpPost(app.getProperty("web.baseUrl")+"/login_page.php");
      List<NameValuePair> params = new ArrayList<>();
      params.add(new BasicNameValuePair("username", username));
      //params.add(new BasicNameValuePair("password", password));
      params.add(new BasicNameValuePair("secure_session", "on"));
      params.add(new BasicNameValuePair("return", "index.php"));
      post.setEntity(new UrlEncodedFormEntity(params));
      //отправляем запрос и получаем ответ
      CloseableHttpResponse response = httpClient.execute(post);
      String body = getTextFrom(response);
      Boolean isLog = body.contains(String.format("Введите пароль для '%s'", username));

      params.clear();
      post = new HttpPost(app.getProperty("web.baseUrl")+"/login_password_page.php");
      //List<NameValuePair> params2 = new ArrayList<>();
      //params2.add(new BasicNameValuePair("username", username));
      params.add(new BasicNameValuePair("password", password));
      params.add(new BasicNameValuePair("secure_session", "on"));
      params.add(new BasicNameValuePair("return", "index.php"));
      post.setEntity(new UrlEncodedFormEntity(params));
      //отправляем запрос и получаем ответ
      response = httpClient.execute(post);
      String body2 = getTextFrom(response);
      Boolean isPass = body2.contains(String.format("Введите пароль для '%s'", username));





      //проверяем - действительно ли пользователь зашел в приложение
      return body.contains(String.format("Введите пароль для '%s'", username));


/*
      HttpPost post2 = new HttpPost(app.getProperty("web.baseUrl")+"/login_password_page.php");
      List<NameValuePair> params2 = new ArrayList<>();
      params.add(new BasicNameValuePair("username", username));
      params.add(new BasicNameValuePair("password", password));
      params.add(new BasicNameValuePair("secure_session", "on"));
      params.add(new BasicNameValuePair("return", "index.php"));
      post.setEntity(new UrlEncodedFormEntity(params));
      //отправляем запрос и получаем ответ
      CloseableHttpResponse response2 = httpClient.execute(post2);
      body = getTextFrom(response2);
      //проверяем - действительно ли пользователь зашел в приложение
      return body.contains(String.format("<a href=\"/mantisbt-2.21.1/account_page.php\">%s</a>", username));
*/



    }

  private String getTextFrom(CloseableHttpResponse response) throws IOException {
    try {
      return EntityUtils.toString(response.getEntity());
    } finally {
      response.close();
    }
  }

  public boolean isLoggedInAs(String username) throws IOException {
    HttpGet get = new HttpGet((app.getProperty("web.baseUrl")+"/my_view_page.php"));
    CloseableHttpResponse response = httpClient.execute(get);
    String body = getTextFrom(response);
    return body.contains(String.format("<a href=\"/mantisbt-2.21.1/account_page.php\">%s</a>", username));
  }

}

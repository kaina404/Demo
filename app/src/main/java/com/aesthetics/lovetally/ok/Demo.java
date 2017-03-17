package com.aesthetics.lovetally.ok;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by xujianhui on 2017/3/15.
 */

public class Demo {

     /**
      * Content-Type ：enctype(内容) ->属性规定在发送到服务器之前应该如何对表单数据进行编码。
      * Content-Type 服务端通常是根据请求头（headers）中的 Content-Type 字段来获知请求中的消息主体是用何种方式编码，再对主体进行解析。
      * 最常见的POST请求中 Content-Type，浏览器**原生表单**（FORM）采用"application/x-www-form-urlencoded;charset=utf-8"
      */

     //POST 默认表单
     /**
      * Content-Type application/x-www-form-urlencoded;utf-8
      * 对于提交的数据会进行 key1=value1&key2=value2 同时 key value 会进行 URL 转码
      * 例如：
      * POST http://www.example.com HTTP/1.1
      * Content-Type: application/x-www-form-urlencoded;charset=utf-8

      * title=test&sub%5B%5D=1&sub%5B%5D=2&sub%5B%5D=3  <-Body
      */

     //POST 提交Json

     /**
      * Content-Type application/json;utf-8
      * 表示传输的是序列化后的JSON字符串
      * 例如：
      * POST http://www.example.com HTTP/1.1
      * Content-Type: application/json;charset=utf-8
      *
      * {"title":"test","sub":[1,2,3]} <-Body
      */

     //POST 上传文件

     /**
      * Content-Type:multipart/form-data
      * 早期的 HTTP 本身不支持上传文件，后来新增了multipart/form-data类型，用来支持二进制传输。
      * 例如：
      * POST http://www.example.com HTTP/1.1
      * Content-Type:multipart/form-data; boundary=----WebKitFormBoundaryrGKCBY7qhFd3TrwA
      *
      * ------WebKitFormBoundaryrGKCBY7qhFd3TrwA
      * Content-Disposition: form-data; name="text"
      *
      * title
      * ------WebKitFormBoundaryrGKCBY7qhFd3TrwA
      * Content-Disposition: form-data; name="file"; filename="chrome.png"
      * Content-Type: image/png
      *
      * PNG ... content of chrome.png ...
      * ------WebKitFormBoundaryrGKCBY7qhFd3TrwA--
      *
      *
      * 多部分媒体类型是复合类型。多部分对象包含多个组件类型。下面是一个多部分 / 混合内容实例，每个组件都有自己的 MIME 类型
      *
      *生成的boundary（边界）用来分块不同的内容  每一部分的数据均以这个开始，然后是*内容描述* ，然后是回车，最后
      * 是字段的内容（文本或者二进制）。如果传输的是文件，还要标注文件名称和文件类型信息。消息主题最后以boundary结束。
      *
      */

     //更多Content-Type类型

     /**
      * image 类型
      * png jpeg ...
      */

     /**
      * text 类型
      * xml html...
      */

     /**
      * audio
      */

     /**
      * video
      */

     /**
      * java
      */

     /**
      * message
      */

     /**
      * drawing
      */


     //报文

     /**
      * 请求报文和相应报文只是起始行不一致
      *
      * 请求报文                                响应报文
      * 起始行  GET www.baidu.com HTTP/1.1      HTTP/1.1 200 OK
      * header Accept:text/*                   Content-Type:application/json
      *
      * body(GET没有)                          Hi! msg.....
      *
      */

     public static void postJson(){
          String METHOD = "POST";
          MediaType JSON = MediaType.parse("application/json;charset=utf-8");
          OkHttpClient client = new OkHttpClient();
          RequestBody requestBody = RequestBody.create(JSON, "{\"title\":\"test\",\"sub\":[1,2,3]}");
          Request request = new Request.Builder().method(METHOD,requestBody).url("http://www.baidu.com").build();
          Call call = client.newCall(request);
          call.enqueue(new Callback() {
               @Override
               public void onFailure(Call call, IOException e) {

               }

               @Override
               public void onResponse(Call call, Response response) throws IOException {

               }
          });
     }

     /**
      * Form表单而言
      * key-value形式
      * Content-Type为application/x-www-form-urlencoded;charset=utf-8  这是默认的方式 对于这种Content-Type
      */
     public static void postForm(){
          final MediaType FORM = MediaType.parse("application/x-www-form-urlencoded;charset=utf-8");

     }

     public static void updateFile(){
          new Thread(){
               @Override
               public void run() {
                    super.run();
                    OkHttpClient client = new OkHttpClient();
                    final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");
                    RequestBody requestBody = new MultipartBody.Builder()
                            .setType(MultipartBody.FORM)
                            .addFormDataPart("title", "xu")
                            .addFormDataPart(
                                    "image",
                                    "ok.png",
                                    RequestBody.create(
                                            MEDIA_TYPE_PNG,
                                            new File("/storage/emulated/0/pazq/PALocal/vstock/img/ok.png")))
                            .build();
                    Request request = new Request.Builder()
                            .post(requestBody)
                            .header("Authorization", "Client-ID " + "...")
                            .url("https://api.imgur.com/3/image")
                            .build();
                    Call call = client.newCall(request);
                    call.enqueue(new Callback() {
                         @Override
                         public void onFailure(Call call, IOException e) {
                              Log.d("image", "image onFailure");
                         }

                         @Override
                         public void onResponse(Call call, Response response) throws IOException {
                              Log.d("image", response.body().string());

                         }
                    });
               }
          }.start();
     }

     public static void asyncGet(final Context context) {

          new Thread(){
               @Override
               public void run() {
                    super.run();
                    try {

                         Map<String, String > headMap = new HashMap<String, String>();
                         headMap.put("Content-Type", "application/text");
                         Headers headers = Headers.of(headMap);
                         Request request = new Request.Builder().headers(headers).get().url("http://www.baidu.com").build();
                         Cache cache = new Cache(context.getCacheDir(), 10 * 2014);
                         OkHttpClient okHttpClient = new OkHttpClient.Builder().cache(cache).build();
                         Call call = okHttpClient.newCall(request);
                         call.enqueue(new Callback() {
                              @Override
                              public void onFailure(Call call, IOException e) {

                              }

                              @Override
                              public void onResponse(Call call, Response response) throws IOException {
                                   Log.d("response", "response===="+response.body().string());
                              }
                         });

                    } catch (Exception e) {
                         e.printStackTrace();
                    }
               }
          }.start();


     }

}

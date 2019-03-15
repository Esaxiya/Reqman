package com.one.reqman.request;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * HttpClient最重要的功能是执行HTTP方法
 *
 * 1.需要一个执行请求的对象
 *     {@link HttpClient} 接口仅表示HTTP请求执行的最基本协议。
 *     {@link CloseableHttpClient} 抽象类是{@link HttpClient}的基本实现、其对象可用于执行请求动作
 *     {@link CloseableHttpClient} 也实现了{@link Closeable}、可以关闭的数据的来源或目的地
 *     {@link HttpClients} 是{@link CloseableHttpClient}实例的工厂方法、用于创建实例对象
 * 2.需要一个请求
 *     {@link org.apache.http.HttpMessage} HTTP消息包括从客户端到服务器的请求以及从服务器到客户端的响应。
 *     {@link org.apache.http.HttpRequest} HTTP请求表示从客户端到服务器的请求
 *     {@link org.apache.http.HttpResponse} 在接收并解释请求消息之后，服务器以HTTP响应消息进行响应。
 *     {@link HttpRequestBase}是{@link HttpUriRequest}的基本实现。
 *     {@link HttpUriRequest}是{@link HttpRequest}接口的扩展版本，提供访问请求属性（如请求URI和方法类型）的便捷方法
 *
 *     {@link HttpGet}继承{@link HttpRequest}、HTTP GET方法。
 *          GET方法意味着检索由Request-URI标识的任何信息(in the form of an entity)
 *
 *     {@link HttpGet}、{@link HttpPost}、{@link HttpPut}、等、
 *      作用是检查uri的格式正确性、同时添加请求使用的默认配置信息、《方法、协议、版本号等》
 *
 *
 * 3.执行请求
 *      HttpClient的对象进行请求动作的执行、调用execute()方法、返回HttpResponse对象
 *      httpclient.execute(httpGet);
 *
 * 4.创建HttpResponse变量、接收HttpClient的execute方法返回的对象。
 *
 *
 * 5.处理 HttpResponse 对象的相关信息。
 *      {@link HttpResponse} 在接收并解释请求消息之后，服务器以HTTP响应消息进行响应。
 *      {@link BasicHttpResponse}是{@link HttpResponse}接口的基本实现。
 *
 *      {@link HttpVersion} 表示HTTP版本。
 *      {@link HttpStatus} 常量枚举HTTP状态代码。
 *
 *      System.out.println(response.getProtocolVersion());                  // HTTP/1.1
 *      System.out.println(response.getStatusLine().getStatusCode());       // 200
 *      System.out.println(response.getStatusLine().getReasonPhrase());     // OK
 *      System.out.println(response.getStatusLine().toString());            // HTTP/1.1 200 OK
 *
 * 01.请求头
 *      {@link Header} 表示HTTP标头字段。
 *
 *
 *
 *
 *
 * HTTP 请求
 *      从客户端到服务器的请求消息在该消息的第一行内包括要应用于资源的方法，资源的标识符和正在使用的协议版本。
 *      所有HTTP请求都有一个请求行，包括方法名称，请求URI和HTTP协议版本
 *
 * URI
 *      Request-URI是统一资源标识符，用于标识应用请求的资源。
 *      HTTP请求URI由协议方案，主机名，可选端口，资源路径，可选查询和可选片段组成。
 *      HttpClient提供{@link URIBuilder}实用程序类来简化请求URI的创建和修改
 *      {@link URIBuilder}用来构建{@link URI}实例.
 *
 * HTTP 响应
 *      HTTP响应是服务器在接收并解释请求消息后发送回客户端的消息。
 *      该消息的第一行包括协议版本，后跟数字状态代码及其相关的文本短语。
 *
 *
 * 请求头
 *      HTTP消息可以包含许多描述消息属性的headers，
 *      例如内容长度(content length)，内容类型(content type)等。
 *      HttpClient提供了检索、添加、删除和枚举header的方法
 *
 *      Header h1 = response.getFirstHeader("Set-Cookie");
 *      Header h2 = response.getLastHeader("Set-Cookie");
 *      Header [] hs = response.getHeaders("Set-Cookie");
 *      Header [] hs2 = response.getAllHeaders();
 *
 *      获取给定类型的所有标头的最有效方法是使用{@link HeaderIterator}接口
 *      {@link HeaderIterator}是{@link Header}类型对象的安全迭代器。
 *      >>> HeaderIterator it = response.headerIterator("Set-Cookie");
 *
 *      它还提供了将HTTP消息解析为单个头元素的便捷方法
 *      {@link HeaderElement}是 HTTP{@link Header}的一个元素，值由名称/值对和许多可选的名称/值参数组成。
 *      {@link HeaderElementIterator} 是{@link HeaderElement}类型对象的安全迭代器。
 *      {@link BasicHeaderElementIterator} 是 {@link HeaderElementIterator}的基本实现。
 *      HeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
 *
 *
 * HTTP实体
 *      HTTP消息可以携带与请求或响应相关联的内容实体(content entity)
 *      实体可以在某些请求和某些响应中找到，因为它们是可选的.
 *      使用实体的请求称为封装实体请求.
 *      HTTP规范定义了两个封闭实体的请求方法：POST和 PUT
 *      通常期望响应包含内容实体。
 *      有例外的情况、如应对 HEAD方法204 No Content、304 Not Modified、205 Reset Content 响应
 *
 *      HttpClient区分三种实体：streamed、self-contained、wrapping
 *      流式传输: 内容从流中接收、或在运行中生成。特别是此类别包括从HTTP响应接收的实体。流实体通常不可重复。
 *      自包含:   内容在内存中或通过独立于连接或其他实体的方式获得。自包含实体通常是可重复的。这种类型的实体主要用于封闭HTTP请求的实体。
 *      wrap：   内容从另一个实体获得。
 *
 * 可重复的实体
 *      实体可以是可重复的，这意味着其内容可以被多次读取。
 *      这仅适用于自包含实体（如 ByteArrayEntity或 StringEntity）
 *
 * 使用HTTP实体
 *      {@link HttpEntity} 可以使用HTTP消息发送或接收的实体。
 *      由于实体可以表示二进制和字符内容，因此它支持字符编码(支持后者，即字符内容)。
 *      在执行带有附加内容的请求时或者请求成功并且使用响应主体将结果发送回客户端时，将创建实体.
 *
 *      要从实体读取内容，可以通过{@link HttpEntity}的<code>getContent()<code/>方法检索输入流，该方法返回一个java.io.InputStream，
 *      或者可以向{@link HttpEntity} writeTo(OutputStream)方法提供输出流，一旦所有内容都写入给定流，该输出流将返回.
 *      当收到带有传入消息的实体时，例如Content-Type和 Content-Length标题（如果它们可用）
 *      这些方法 {@link HttpEntity}的getContentType()和getContentLength()方法可用于读取公共元数据，
 *
 *      由于 Content-Type标题可以包含文本mime类型(如text/plain或text/html)的字符编码，
 *      因此该 {@link HttpEntity}的 getContentEncoding() 方法用于读取此信息
 *      如果Header不可用，则返回长度-1，内容类型为NULL。
 *      如果Content-Type 标头可用，Header则返回一个对象。
 *      在为传出消息创建实体时，该元数据必须由实体的创建者提供。
 * 实体
 *      {@link StringEntity} 一个自包含，可重复的实体，从{@link String}获取其内容
 *      {@link FileEntity} 自包含，可重复的实体，从文件中获取其内容。
 *      {@link ContentType} 是内容类型信息由MIME类型和可选字符集组成。
 *      {@link EntityUtils} 用于处理{@link HttpEntity}的静态助手。
 *      为了确保正确释放系统资源，必须关闭与实体关联的内容流或响应本身
 *
 *       确保发布低级资源
 *       为了确保正确释放系统资源，必须关闭与实体关联的内容流或响应本身
 *       关闭内容流和关闭响应之间的区别在于
 *       关闭内容流 尝试通过使用实体内容来保持底层连接处于活动状态，
 *       关闭响应 立即关闭并丢弃连接
 *
 *
 * 消费实体内容
 *      消耗的实体的内容的推荐方法是通过使用{@link HttpEntity}的 getContent() 和 writeTo(OutputStream) 方法
 *      HttpClient还附带了{@link EntityUtils}类，它公开了几种静态方法，以便更容易地从实体中读取内容或信息.
 *      {@link java.io.InputStream} 可以使用此类中的方法检索字符串/字节数组中的整个内容主体，而不是直接读取.
 *      但是EntityUtils强烈建议不要使用，除非响应实体来自可信HTTP服务器并且已知长度有限。
 *
 *      >>> HttpEntity entity = response.getEntity();
 *      >>> InputStream instream = entity.getContent();
 *      >>> long len = entity.getContentLength();
 *      >>> EntityUtils.toString(entity);
 *
 *      在某些情况下，可能需要能够多次读取实体内容。
 *      在这种情况下，实体内容必须以某种方式缓冲，无论是在内存中还是在磁盘上。
 *      实现这一目标的最简单方法是将原始实体与{@link BufferedHttpEntity}类包装在一起。
 *      这将导致原始实体的内容被读入内存缓冲区。在所有其他方式中，实体包装器将具有原始包装器。
 *
 *      >>> HttpEntity entity = response.getEntity();
 *      >>> entity = new BufferedHttpEntity(entity);
 *
 *
 * 制作实体内容
 *      HttpClient提供了几个类、可用于通过HTTP连接有效地流式内容。
 *      这些类的实例可以与封闭请求的实体相关联,例如POST和PUT，以便将实体内容封装到传出的HTTP请求中。
 *      HttpClient为大多数常见数据容器提供了几个类，如字符串，字节数组，输入流，
 *      StringEntity, ByteArrayEntity, InputStreamEntity, and FileEntity.
 *
 *      >>> File file = new File("name.txt");
 *      >>> FileEntity entity = new FileEntity(file, ContentType.create("text/plain","UTF-8"));
 *      >>> HttpPost http_post = new HttpPost("http://localhost/save");
 *      >>> http_post.setEntity(entity);
 *
 *      请注意{@link InputStreamEntity}不可重复，因为它只能从基础数据流中读取一次。
 *      通常，建议实现自HttpEntity包含而不是使用泛型的自定义类InputStreamEntity。 FileEntity可以是一个很好的起点。
 *      {@link InputStreamEntity} 一个流式，不可重复的实体，从{@link InputStream}获取其内容。
 */
public class Chapter1 {

    /**
     * HTTP 请求执行
     * 1.1 Request execution
     */
    public static void execution() throws IOException {
        //测试数据
        String url = "http://www.baidu.com/";
        // 创建执行请求的对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 创建请求、检查请求地址是否格式正确
        HttpGet httpGet = new HttpGet(url);
        // 执行请求、接收响应
        CloseableHttpResponse response = httpClient.execute(httpGet);

    }


    /**
     * HTTP 请求
     * 1.1.1. HTTP request
     * GET、HEAD、POST、PUT、DELETE、TRACE和OPTIONS
     * HttpGet、HttpHead、HttpPost、HttpPut、HttpDelete、HttpTrace、HttpOptions
     *
     */
    public static void req() throws URISyntaxException {

        String uri_1 = "http://www.google.com/search?hl=en&q=httpclient&btnG=Google+Search&aq=f&oq=";
        HttpGet httpget1 = new HttpGet(uri_1);

        /**
         * HttpClient提供URIBuilder实用程序类来简化请求URI的创建和修改
         * {@link URIBuilder}构建{@link URI}实例.
         */
        URI uri = new URIBuilder()
                .setScheme("http")
                .setHost("www.google.com")
                .setPath("/search")
                .setParameter("hl","en")
                .setParameter("q","httpclient")
                .setParameter("btnG","Google Search")
                .setParameter("aq","f")
                .setParameter("oq","")
                .build();

        HttpGet httpGet2 = new HttpGet(uri);
        System.out.println(httpget1);
        System.out.println(httpGet2);
    }

    /**
     * HTTP 响应
     * 1.1.2. HTTP response
     */
    public static void resp(){
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, "OK");
        System.out.println(response.getProtocolVersion());                  // HTTP/1.1
        System.out.println(response.getStatusLine().getStatusCode());       // 200
        System.out.println(response.getStatusLine().getReasonPhrase());     // OK
        System.out.println(response.getStatusLine().toString());            // HTTP/1.1 200 OK
    }


    /**
     * 请求头 headers
     * 1.1.3. Working with message headers
     */
    public static void headers() throws IOException {
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1,HttpStatus.SC_OK,"OK");
        response.addHeader("Set-Cookie","c1=a; path=\"/\"; domain=localhost");
        response.addHeader("Set-Cookie","c2=b; path=\"/\";c3=c; domain=\"localhost\"");
        Header h1 = response.getFirstHeader("Set-Cookie");
        System.out.println(h1); //Set-Cookie: c1=a; path=/; domain=localhost
        Header h2 = response.getLastHeader("Set-Cookie");
        System.out.println(h2); //Set-Cookie: c2=b; path="/", c3=c; domain="localhost"
        Header [] hs = response.getHeaders("Set-Cookie");
        System.out.println(hs.length);
        Header [] hs2 = response.getAllHeaders();


        // 获取给定类型的所有标头的最有效方法是使用 HeaderIterator接口
        HeaderIterator it = response.headerIterator("Set-Cookie");
        while (it.hasNext()){
            System.out.println(it.nextHeader());
        }

        // 它还提供了将HTTP消息解析为单个头元素的便捷方法
        HeaderElementIterator it2 = new BasicHeaderElementIterator(response.headerIterator("Set-Cookie"));
        while (it2.hasNext()){
            HeaderElement elem = it2.nextElement();
            System.out.println(elem.getName()+"="+elem.getValue());
            NameValuePair [] params = elem.getParameters();
            for(int i = 0;i<params.length;i++){
                System.out.println(" "+params[i]);
            }
        }
    }

    /**
     * 使用HTTP实体
     */
    public static void entity() throws IOException {
        StringEntity myEntity = new StringEntity("important message",
                ContentType.create("text/plain","UTF-8"));
        System.out.println(myEntity.getContentType());      // Content-Type: text/plain; charset=utf-8
        System.out.println(myEntity.getContentLength());    // 17
        System.out.println(EntityUtils.toString(myEntity));  // important message
        System.out.println(EntityUtils.toByteArray(myEntity).length);  // 17
    }

    /**
     * 确保发布低级资源
     * 为了确保正确释放系统资源，必须关闭与实体关联的内容流或响应本身
     * 关闭内容流和关闭响应之间的区别在于前者将尝试通过使用实体内容来保持底层连接处于活动状态，而后者立即关闭并丢弃连接
     */
    public static void release() throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("http://www.baidu.com");
        CloseableHttpResponse response = httpClient.execute(httpGet);
        try{

            HttpEntity entity = response.getEntity();
            if(entity != null){
                InputStream inputStream = entity.getContent();
                try{
                    // 对输入流进行各种有用的操作
                    System.out.println("输入流"+inputStream.read());
                }finally {
                    inputStream.close();
                }
            }
        }finally {
            response.close();
        }

    }


    /**
     * 制造实体内容
     */
    public static void stream(){
        File file = new File("file.txt");
        FileEntity entity = new FileEntity(file,
                ContentType.create("text/plain","UTF-8"));
        HttpPost httppost = new HttpPost("http://localhost/save");
        httppost.setEntity(entity);
    }
    public static void main(String[] args) {
        try {
            Chapter1.execution();
            Chapter1.req();
            Chapter1.resp();
            Chapter1.headers();
            Chapter1.entity();
            Chapter1.release();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
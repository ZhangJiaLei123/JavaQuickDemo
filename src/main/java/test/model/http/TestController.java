package test.model.http;

import blxt.qjava.autovalue.inter.*;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;

@Component
@Configuration
@Controller
@RequestMapping("/test")
public class TestController {

    @Value("server.port")
    String port;

    @Run()
    public void Run(){
        System.out.println("hello, TestController, " + port);
    }

    @RequestMapping("/hello")
    public String hello(String name){

        System.out.println("hello, " + name);

        return "hello, " + name;
    }

    @RequestMapping("/register")
    public String Register(HttpExchange httpExchange, String hostname, String ip){

        System.out.println("hello, " + hostname + ", ip:" + ip);

        return "hello, " + hostname;
    }

    @RequestMapping("/hello2")
    public String hello2(@RequestParam(required = false, defaultValue = "$port") String port,
                         @RequestParam(value="uname") String name,
                         @RequestParam(required = false, defaultValue = "1") int age){
        System.out.println("hello2, " + name + ", age:" + age + ",port:" + port);

        return "hello2, " + name + ", age:" + age + ",port:" + port;
    }

    @RequestMapping("/hello3")
    public String hello3(HttpExchange httpExchange, String name) throws IOException {

        System.out.println("hello, " + name);
        /* 结果返回 */
        httpExchange.sendResponseHeaders(200, name.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(name.getBytes());

        return "hello, " + name;
    }

}

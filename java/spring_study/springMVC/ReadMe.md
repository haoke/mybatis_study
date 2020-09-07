* 框架:

* 普通配置文件开发:
    1. 配置前端控制器
        web.xml中 配置 DispatcherServlet
        ```xml
        <servlet>
          <servlet-name>dispatcher</servlet-name>
          <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
          <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:applicationContext.xml</param-value>
          </init-param>
          <load-on-startup>1</load-on-startup>
        </servlet>
        <servlet-mapping>
          <servlet-name>dispatcher</servlet-name>
          <url-pattern>*.do</url-pattern>
        </servlet-mapping>
        ```
    2. 加载springmvc配置文件
    如上, 添加在DispatcherServlet内
    ```xml
      <init-param>
              <param-name>contextConfigLocation</param-name>
              <param-value>classpath:applicationContext.xml</param-value>
      </init-param>
    ```
    3. springmvc文件 结构:

        - handler
        - 处理器控制器
        - 处理器适配器
        - 视图解析器
      
* 注解开发:
  - 常用的注解的使用
  - 参数绑定
  - 自定义参数绑定
* springmvc 与 struts的区别
* 高级:
  - 使用springmvc上传文件
  - 复杂类型的绑定--集合
  - Validation校验器
  - 异常处理器使用
  - Restful支持
  - 拦截器
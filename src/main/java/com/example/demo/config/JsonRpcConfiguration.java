package com.example.demo.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.ConvertedParameterTransformer;
import com.googlecode.jsonrpc4j.ErrorResolver;
import com.googlecode.jsonrpc4j.spring.AutoJsonRpcServiceImplExporter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.List;

@Configuration
public class JsonRpcConfiguration {

  @Bean
  public AutoJsonRpcServiceImplExporter autoJsonRpcServiceImplExporter() {
    AutoJsonRpcServiceImplExporter exp = new AutoJsonRpcServiceImplExporter();
    //in here you can provide custom HTTP status code providers etc. eg:
    //exp.setHttpStatusCodeProvider();
    //exp.setErrorResolver();
//    exp.setErrorResolver(errorResolver());
    exp.setAllowExtraParams(false);
    exp.setAllowLessParams(false);
    return exp;
  }

  @Bean
  public ErrorResolver errorResolver() {
    return new ErrorResolver() {
      @Override
      public JsonError resolveError(Throwable throwable, Method method, List<JsonNode> list) {
        return new JsonError(1000, "ssss", 100);
      }
    };
  }

}

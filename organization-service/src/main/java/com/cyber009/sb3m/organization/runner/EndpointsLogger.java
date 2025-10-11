package com.cyber009.sb3m.organization.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointsLogger implements CommandLineRunner {

  private final RequestMappingHandlerMapping handlerMapping;

  public EndpointsLogger(RequestMappingHandlerMapping handlerMapping) {
    this.handlerMapping = handlerMapping;
  }

  @Override
  public void run(String... args) throws Exception {
    System.out.println("Listing all endpoints at startup:");

    handlerMapping.getHandlerMethods().forEach((requestMappingInfo, handlerMethod) -> {
      if (requestMappingInfo.getPathPatternsCondition() == null
          || requestMappingInfo.getPathPatternsCondition().getPatterns() == null)
        return;
      // Use pathPatternsCondition instead of patternsCondition
      String paths = requestMappingInfo.getPathPatternsCondition().getPatterns().toString();

      String methods = requestMappingInfo.getMethodsCondition().getMethods().toString();

      System.out.println(methods + " " + paths + " -> " +
          handlerMethod.getMethod().getDeclaringClass().getSimpleName() + "#" +
          handlerMethod.getMethod().getName());
    });
  }

}

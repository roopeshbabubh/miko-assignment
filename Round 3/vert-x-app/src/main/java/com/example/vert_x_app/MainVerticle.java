package com.example.vert_x_app;

import com.example.vert_x_app.service.ALangService;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

public class MainVerticle extends AbstractVerticle {

  private final ALangService aLangService = new ALangService();

  @Override
  public void start(Promise<Void> startPromise) throws Exception {
    HttpServer server = vertx.createHttpServer();
    Router router = Router.router(vertx);

    router.route(HttpMethod.POST, "/executeProgram").handler(this::executeProgramStatement);

    server.requestHandler(router).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
  }

  private void executeProgramStatement(RoutingContext routingContext) {
    HttpServerRequest request = routingContext.request();

    request.bodyHandler(body -> {
      try {
        JsonObject jsonBody = body.toJsonObject();
        String result = aLangService.executeProgram(jsonBody.getString("program")).toString();

        System.out.println(result);

        routingContext.response()
          .putHeader("content-type", "application/json")
          .end(Json.encode(result));
      } catch (Exception e) {
        routingContext.fail(500, e);
      }
    });
  }

}

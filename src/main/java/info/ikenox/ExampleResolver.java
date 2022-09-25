package info.ikenox;

import io.smallrye.graphql.cdi.event.ErrorDataFetch;
import io.smallrye.graphql.cdi.event.ErrorInfo;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Source;

@ApplicationScoped
@GraphQLApi
public class ExampleResolver {

  @Query("example")
  public List<Foo> example() {
    // onError method will receive this error if uncommented-out
//    if (true) {
//      throw new RuntimeException("fooError");
//    }

    return List.of(
        new Foo(1),
        new Foo(2),
        new Foo(3)
    );
  }

  public List<Bar> bar(@Source List<Foo> fooList) {
    // onError method won't receive this error
    throw new RuntimeException("barError");
  }

  // exception observer
  public void onError(@Observes @ErrorDataFetch ErrorInfo errorInfo) {
    System.out.println("[error] " + errorInfo.getT().getMessage());
  }

  public record Foo(int fooValue) {

  }

  public record Bar(int barValue) {

  }
}

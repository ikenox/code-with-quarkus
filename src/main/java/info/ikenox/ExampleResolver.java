package info.ikenox;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
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
    return List.of(
        new Foo(1),
        new Foo(2),
        new Foo(3)
    );
  }

  public List<Bar> bar(@Source List<Foo> fooList) {
    return fooList.stream().map(foo -> new Bar(foo.value)).toList();
  }

  public record Foo(int value) {

  }

  public record Bar(int value) {

  }
}

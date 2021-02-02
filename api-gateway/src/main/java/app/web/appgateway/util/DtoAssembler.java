package app.web.appgateway.util;

public interface DtoAssembler<T ,S> {
    T assemble (S entityObject);
}

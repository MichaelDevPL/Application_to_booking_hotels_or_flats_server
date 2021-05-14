package app.web.rentalservice.util;

public interface DtoAssembler<T, S> {
    T assemble (S entityObject);
}

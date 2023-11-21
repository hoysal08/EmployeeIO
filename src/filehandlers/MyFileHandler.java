package filehandlers;

public interface MyFileHandler<T> {

    T read() throws Exception;

    void write(T data) throws Exception;
}

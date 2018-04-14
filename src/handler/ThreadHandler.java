package handler;

public class ThreadHandler<S> extends UncheckedIOExceptionConverterHandler<S> {
    public ThreadHandler(Handler<S> other) {
        super(other);
    }

    @Override
    public void handle(S s) {
        new Thread(() -> super.handle(s)).start();
    }
}


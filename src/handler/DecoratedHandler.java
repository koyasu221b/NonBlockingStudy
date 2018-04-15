package handler;

import java.io.IOException;

public class DecoratedHandler<S> implements Handler<S> {
    private final Handler<S> other;

    protected DecoratedHandler(Handler<S> other) {
        this.other = other;
    }

    @Override
    public void handle(S s) throws IOException {
        other.handle(s);
    }
}

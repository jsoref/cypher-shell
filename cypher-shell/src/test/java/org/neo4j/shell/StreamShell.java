package org.neo4j.shell;

import org.neo4j.driver.v1.exceptions.ClientException;
import org.neo4j.shell.exception.CommandException;
import org.neo4j.shell.exception.ExitException;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class StreamShell extends CypherShell {
    private final ByteArrayOutputStream errStream;
    private final ByteArrayOutputStream outStream;

    public StreamShell() {
        this("");
    }

    public StreamShell(@Nonnull final String input) {
        super("bla", 99, "bob", "pass");
        in = new ByteArrayInputStream(input.getBytes());
        errStream = new ByteArrayOutputStream();
        err = new PrintStream(errStream);
        outStream = new ByteArrayOutputStream();
        out = new PrintStream(outStream);
    }

    @Nonnull
    @Override
    public String prompt() {
        return "";
    }

    @Nullable
    @Override
    public Character promptMask() {
        return null;
    }

    @Override
    public void executeLine(@Nonnull String line) throws ExitException, CommandException {
        if (line.contains("bad")) {
            throw new ClientException("Found a bad line");
        } else {
            printOut("Success");
        }
    }

    public String getOutLog() {
        return outStream.toString();
    }

    public String getErrLog() {
        return errStream.toString();
    }

    public void connect() throws CommandException {
        connect("", 0, "", "");
    }

    @Override
    public void connect(@Nonnull String host, int port,
                        @Nonnull String username, @Nonnull String password) throws CommandException {
        this.session = new TestSession();
    }

    @Override
    public void disconnect() throws CommandException {
        this.session = null;
    }
}

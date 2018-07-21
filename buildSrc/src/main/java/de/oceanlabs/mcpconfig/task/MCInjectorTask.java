package de.oceanlabs.mcpconfig.task;

import com.google.common.collect.ImmutableMap;
import de.oceanlabs.mcpconfig.Helper;
import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;

import java.io.File;

public class MCInjectorTask extends FriendlyJavaExec
{
    // @formatter:off
    private @InputFile File access;
    public File getAccess() { return this.access; }
    public void setAccess(final File access) { this.access = access; }

    private @InputFile File constructors;
    public File getConstructors() { return this.constructors; }
    public void setConstructors(final File constructors) { this.constructors = constructors; }

    private @InputFile File exceptions;
    public File getExceptions() { return this.exceptions; }
    public void setExceptions(final File exceptions) { this.exceptions = exceptions; }

    private @InputFile File input;
    public File getInput() { return this.input; }
    public void setInput(final File input) { this.input = input; }

    private @OutputFile File output;
    public File getOutput() { return this.output; }
    public void setOutput(final File output) { this.output = output; }

    private @Input File log;
    public File getLog() { return this.log; }
    public void setLog(final File log) { this.log = log; }
    // @formatter:on

    @Override
    @SuppressWarnings("ConstantConditions")
    protected void preExec()
    {
        this.setArgs(Helper.fillVariables(
                this.getArgs(),
                ImmutableMap.<String, Object>builder()
                        .put("access", this.getAccess())
                        .put("constructors", this.getConstructors())
                        .put("exceptions", this.getExceptions())
                        .put("input", this.getInput())
                        .put("output", this.getOutput())
                        .put("log", this.getLog())
                        .build()
        ));
    }
}

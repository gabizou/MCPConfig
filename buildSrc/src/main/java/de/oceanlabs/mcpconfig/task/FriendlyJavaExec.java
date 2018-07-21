package de.oceanlabs.mcpconfig.task;

import org.gradle.api.tasks.Input;
import org.gradle.api.tasks.JavaExec;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.jar.Attributes;
import java.util.jar.JarInputStream;

public class FriendlyJavaExec extends JavaExec
{
    private static final Attributes.Name MAIN_CLASS = Attributes.Name.MAIN_CLASS;

    private @Input File jar;
    public File getJar() { return this.jar; }
    public void setJar(final File jar) { this.jar = jar; }

    @Override
    public final void exec()
    {
        this.findMainClass();
        this.pushClasspath();
        this.preExec();
        super.exec();
    }

    private void findMainClass()
    {
        // We don't need to find the main class if we've been given one.
        if (this.getMain() != null)
        {
            return;
        }

        try (final JarInputStream jis = new JarInputStream(Files.newInputStream(this.jar.toPath())))
        {
            this.setMain(jis.getManifest().getMainAttributes().getValue(MAIN_CLASS));
        }
        catch (final IOException e)
        {
            throw new IllegalStateException("An exception was encountered while trying to locate the " + MAIN_CLASS + " attribute from the manifest of '" + this.jar + '\'', e);
        }

        if (this.getMain() == null)
        {
            throw new IllegalStateException("The specified JAR ('" + this.jar + "') does not have the " + MAIN_CLASS + " attribute defined in the manifest.");
        }
    }

    private void pushClasspath()
    {
        this.classpath(this.jar);
    }

    protected void preExec()
    {
    }
}

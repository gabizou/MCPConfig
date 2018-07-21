package de.oceanlabs.mcpconfig.task;

import net.md_5.specialsource.SpecialSource;
import org.gradle.api.DefaultTask;
import org.gradle.api.tasks.InputFile;
import org.gradle.api.tasks.OutputFile;
import org.gradle.api.tasks.TaskAction;

import java.io.File;

public class RemapJarTask extends DefaultTask
{
    private @InputFile File input;
    public File getInput() { return this.input; }
    public void setInput(final File input) { this.input = input; }

    private @InputFile File mappings;
    public File getMappings() { return this.mappings; }
    public void setMappings(final File mappings) { this.mappings = mappings; }

    private @OutputFile File dest;
    public File getDest() { return this.dest; }
    public void setDest(final File dest) { this.dest = dest; }

    @TaskAction
    public void run() throws Exception
    {
        SpecialSource.main(new String[] {
                "--in-jar", this.input.getAbsolutePath(),
                "--out-jar", this.dest.getAbsolutePath(),
                "--srg-in", this.mappings.getAbsolutePath()
        });
    }
}

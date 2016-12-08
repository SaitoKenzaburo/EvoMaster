package org.evomaster.clientJava.instrumentation;


import org.evomaster.clientJava.instrumentation.visitor.CoverageClassVisitor;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Instrumentator {

    private final List<String> prefixes;

    public Instrumentator(String packagePrefixesToCover) {
        Objects.requireNonNull(packagePrefixesToCover);

        prefixes = Arrays.asList(
                packagePrefixesToCover.split(","))
                .stream()
                .map(s -> s.trim())
                .filter(s -> ! s.isEmpty())
                .collect(Collectors.toList());

        if (prefixes.isEmpty()) {
            throw new IllegalArgumentException("You have to specify at least one non-empty prefix, e.g. 'com.yourapplication'");
        }


    }

    /**
     * Get the raw bytes of instrumented class with name {@code className}
     *
     * @param classLoader
     * @param className
     * @param reader
     * @return
     */
    public byte[] transformBytes(ClassLoader classLoader, ClassName className, ClassReader reader) {
        Objects.requireNonNull(classLoader);
        Objects.requireNonNull(className);
        Objects.requireNonNull(reader);

        if (!ClassesToExclude.checkIfCanInstrument(className.getFullNameWithDots())) {
            throw new IllegalArgumentException("Cannot instrument " + className);
        }


        int asmFlags = ClassWriter.COMPUTE_FRAMES;
        ClassWriter writer = new ComputeClassWriter(asmFlags);
        ClassVisitor cv = writer;

        if(canInstrumentForCoverage(className)){

            cv = new CoverageClassVisitor(cv, className);

        } else {
            /*
                For "now" we only instrument classes that will directly
                contribute to coverage, ie the SUT.
                In the future, likely also to instrument other third-party
                library classes for testability transformations
             */
        }

        //avoid reading frames, as we re-compute them
        int readFlags = ClassReader.SKIP_FRAMES;
        reader.accept(cv, readFlags);

        return writer.toByteArray();
    }


    private boolean canInstrumentForCoverage(ClassName className){

        return prefixes.stream()
                .anyMatch(s -> className.getFullNameWithDots().startsWith(s));
    }
}
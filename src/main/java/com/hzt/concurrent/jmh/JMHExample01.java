package com.hzt.concurrent.jmh;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author hzt
 * @date 2021/8/6 18:04
 */

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@State(Scope.Thread)
public class JMHExample01 {

    private final  static String DATA="DUMMY DATA";

    private List<String> arrayList;

    private List<String> linkList;

    @Setup(Level.Iteration)
    public void setUp(){
        this.arrayList=new ArrayList<String>();
        this.linkList=new LinkedList<String>();
    }
    @Benchmark
    public List<String> arrayListAdd(){
        this.arrayList.add(DATA);
        return this.arrayList;
    }
    @Benchmark
    public List<String> linkListAdd(){
        this.linkList.add(DATA);
        return this.linkList;
    }

    public static void main(String[] args) throws RunnerException {
        final Options opts=new OptionsBuilder().include(JMHExample01.class.getSimpleName())
                .forks(1)
                .measurementIterations(10)
                .warmupIterations(10)
                .build();
        new Runner(opts).run();
    }



}

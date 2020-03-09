package Util;

import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.Callable;

public class CustomCallable implements Callable {

    private String sourceCode;

    private long longTimeOut;

    public CustomCallable(String sourceCode) {
        this.sourceCode = sourceCode;
        // this.longTimeOut = TimeOut;
    }

    @Override
    public RunInfo call() throws Exception {
        RunInfo runInfo = new RunInfo();

        Thread t = new Thread(() -> realCall(runInfo));
        t.start();
        try {
            t.join(3000); // 等待3s
            System.out.println("current thread timeout");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t.stop(); // 强制关闭
        System.out.println("current thread shutdown");
        return runInfo;
    }

    public void realCall(RunInfo runInfo) {
        CustomStringJavaCompiler compiler = new CustomStringJavaCompiler(sourceCode);
        if (compiler.compiler()) {
            runInfo.setCompilerSuccess(true);
            try {
                compiler.runMainMethod();
                runInfo.setRunSuccess(true);
                runInfo.setRunTakeTime(compiler.getRunTakeTime());
                System.out.println(compiler.getRunTakeTime());
                runInfo.setRunMessage(compiler.getRunResult()); //获取运行的时候输出内容
            } catch (InvocationTargetException e) {
                //反射调用异常了,是因为超时的线程被强制stop了
                if ("java.lang.ThreadDeath".equalsIgnoreCase(e.getCause().toString())) {
                    runInfo.setRunSuccess(false);
//                    Thread.currentThread().interrupt();
                    System.out.println(e.getCause().toString());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                runInfo.setRunSuccess(false);
                runInfo.setRunMessage(e.getMessage());
            }
        } else {
            //编译失败
            runInfo.setCompilerSuccess(false);
        }
        runInfo.setCompilerTakeTime(compiler.getCompilerTakeTime());
        runInfo.setCompilerMessage(compiler.getCompilerMessage());
        runInfo.setTimeOut(false); //走到这一步代表没有超时
    }

}

package Util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompilerUtil {

    //防止System.out输出内容错乱
    private static ExecutorService pool = Executors.newFixedThreadPool(1);

    //设置超时时间
    //private long longTimeOut;

//    public CompilerUtil(long longTimeOut) {
//        this.longTimeOut = longTimeOut;
//    }

    public static RunInfo getRunInfo(String javaSourceCode) {
        RunInfo runInfo;
        CustomCallable compilerAndRun = new CustomCallable(javaSourceCode);
        Future<RunInfo> future = pool.submit(compilerAndRun);
        //方案1
        try {
            runInfo = future.get();

        } catch (Exception e) {
            e.printStackTrace();
            //代码编译或者运行超时
            runInfo = new RunInfo();
            runInfo.setTimeOut(true);
        }
        return runInfo;
    }

    public static void shutdown() {
        pool.shutdownNow();
    }

}
